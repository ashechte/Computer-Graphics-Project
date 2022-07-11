package renderer;

import java.util.List;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import static geometries.BoundaryBox.GeoPoint;
import elements.*;
import primitives.*;
import scene.Scene;

/**
 * That connects all the data of a scene, calculates the next cut points and
 * sends to the imageWriter class to paint the image
 * 
 * @author User
 *
 */
public class Render {

	private static final double EPS = 0.01;
	private static final double MIN_CALC_COLOR_K = 0.001;
	private static final int MAX_CALC_COLOR_LEVEL = 15;
	Scene scene;
	ImageWriter imageWriter;
	int numOfAvailableProcessors;
	boolean thread = false;
	boolean bounding = false;

	/************* constructor ***************/
	/**
	 * A constructor that receives an instance of an imageWriter and a scene and
	 * initializes the fields in the class
	 * 
	 * @param imageWriter
	 * @param scene
	 */
	public Render(ImageWriter imageWriter, Scene scene) {
		this.scene = scene;
		this.imageWriter = imageWriter;
		numOfAvailableProcessors = Runtime.getRuntime().availableProcessors();
	}

	/**
	 * A constructor that receives an instance of an imageWriter and a scene and
	 * initializes the fields in the class
	 * 
	 * @param imageWriter
	 * @param scene
	 */
	public Render(ImageWriter imageWriter, Scene scene, boolean thredPool, boolean boundry) {
		this.scene = scene;
		this.imageWriter = imageWriter;
		numOfAvailableProcessors = Runtime.getRuntime().availableProcessors();
		this.thread = thredPool;
		this.bounding = boundry;
	}

	/**
	 * A function that passes on a view plane and calculates with which color
	 * parameter it should be sent to the writePixel function
	 */
	public void renderImage() throws InterruptedException {
		final ThreadPoolExecutor myPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(numOfAvailableProcessors);
		for (int i = 0; i < imageWriter.getNx(); i++) {
			final int myI = i;
			Runnable worker = () -> {
				for (int j = 0; j < imageWriter.getNy(); j++) {
					final int myJ = j;
					List<GeoPoint> intersectionPoints;
					Ray ray = scene.getCamera().constructRayThroughPixel(imageWriter.getNx(), imageWriter.getNy(), myI,
							myJ, scene.getDistance(), imageWriter.getWidth(), imageWriter.getHeight());
					// A ray that departs from the camera and passes through the current pixel
					// center
					if (bounding) {
						intersectionPoints = scene.getModel3D().findIntersectionsWithBouning(ray);
					} else {
						intersectionPoints = scene.getModel3D().findIntersections(ray);
					}
					// List of all intersections points of the ray in pixel[i,j].
					if (intersectionPoints.size() == 0) {
						imageWriter.writePixel(myI, myJ, scene.getBackground().getColor());
					} else if (!intersectionPoints.isEmpty()) {
						GeoPoint closest = getClosestPoint(intersectionPoints);
						imageWriter.writePixel(myI, myJ, calcColor(closest, ray).getColor());
					}
				}
			};
			if (thread) {
				myPool.execute(worker);
			} else {
				worker.run();
			}
		}
		if (thread) {
			myPool.shutdown();
			while (!myPool.awaitTermination(1, TimeUnit.HOURS));
		}
	}

	/**
	 * A function that returns the closest intersection to the camera
	 * 
	 * @param intersectionsPointsList list of intersections of the ray
	 * @return closest point to camera
	 */
	private GeoPoint getClosestPoint(List<GeoPoint> intersectionsPointsList) {
		// The distance of the closest point of the first body in the list of the
		// current ray
		double distance = intersectionsPointsList.get(0).point.distanceBetweenPoints(this.scene.getCamera().getP0());
		GeoPoint closest = intersectionsPointsList.get(0);// Closest point of the first body in the list of the current
															// ray
		// A loop that checks the smallest cut distance between all the bodies the
		// current ray passes through
		for (GeoPoint geoPoint : intersectionsPointsList) {
			double tmp = geoPoint.point.distanceBetweenPoints(this.scene.getCamera().getP0());
			if (tmp < distance) {
				distance = tmp;
				closest = geoPoint;
			}
		}
		return closest;
	}

	/**
	 * A function that calculates light scattering (Diffusive) based on a Pong model
	 * 
	 * @param kd             Attenuation coefficient
	 * @param l              Vector from light source to point
	 * @param n              The normal vector of the point
	 * @param lightIntensity The intensity of light
	 * @return calculation of diffusive
	 */
	Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		double tmp = l.dotProduct(n);
		if (tmp < 0) {
			tmp = -tmp;
		}
		return lightIntensity.scale(kd * tmp);
	}

	/**
	 * A function that calculates the return of light (specular) according to the
	 * Pong model
	 * 
	 * @param ks             Attenuation coefficient
	 * @param l              Vector from light source to point
	 * @param n              The normal vector of the point
	 * @param v              Vector from camera to point
	 * @param nShininess     Shininess attenuation coefficient
	 * @param lightIntensity The intensity of light
	 * @return calculation of specular
	 */
	Color calcSpecular(double ks, Vector l, Vector n, Vector v, double nShininess, Color lightIntensity) {
		Vector r = l.subtract(n.scale(n.dotProduct(l) * 2.0));
		double max = -v.dotProduct(r);
		if (max <= 0)
			max = 0;
		return lightIntensity.scale(Math.pow(max, nShininess) * ks);
	}

	/**
	 * this function checks if a point is shaded or not
	 * 
	 * @param l        light direction to the point
	 * @param n        normal to the point
	 * @param geoPoint the point
	 * @return true if unshaded
	 */
	double transparency(Vector l, Vector n, GeoPoint geoPoint) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Point3D point = addEpsToPoints(n, geoPoint.point, lightDirection);
		Ray lightRay = new Ray(lightDirection, point);
		List<GeoPoint> intersections = this.scene.getModel3D().findIntersections(lightRay);
		double ktr = 1;
		for (GeoPoint gp : intersections) {
			ktr *= gp.geometry.getMaterial().getKt();
		}
		return ktr;
	}

	/**
	 * this function returns the reflected ray of inRay on the point p
	 * 
	 * @param n     normal to the point
	 * @param p     the point
	 * @param inRay the ray that we check its' reflection
	 * @return the reflection ray
	 */
	Ray constructReflectedRay(Vector n, Point3D p, Vector v) {
		Vector r = v.subtract(n.scale(2 * n.dotProduct(v)));
		Point3D epsPoint = p.add(r.scale(EPS));
		return new Ray(r, epsPoint);
	}

	/**
	 * add epsilon to point With a little sliding addition in the normal direction
	 * 
	 * @param normal normal from the point
	 * @param point  point to add epsilon
	 * @param v      ray direction
	 * @return the point added by epsilon
	 */
	private Point3D addEpsToPoints(Vector n, Point3D point, Vector v) {
		Vector epsVector = n.scale(n.dotProduct(v) > 0 ? EPS : -EPS);
		return point.add(epsVector);
	}

	/**
	 * this function contracts the refraction ray
	 * 
	 * @param p     new start for the ray
	 * @param inRay ray to get the direction from
	 * @return contracts of refracted ray
	 */
	Ray constructRefractedRay(Point3D p, Vector v, Vector n) {
		Point3D epsPoint = addEpsToPoints(n, p, v);
		return new Ray(v, epsPoint);
	}

	/**
	 * this function calculates the closest intersection to the reflection and
	 * refraction rays
	 * 
	 * @param ray the reflection and refraction ray
	 * @return closest intersection
	 */
	GeoPoint findClosestIntersection(Ray ray) {
		List<GeoPoint> intersections;
		if (bounding) {
			intersections = this.scene.getModel3D().findIntersectionsWithBouning(ray);
		}
		else {
			 intersections = this.scene.getModel3D().findIntersections(ray);
		}
		if (!intersections.isEmpty()) {
			// The distance of the closest point of the first body in the list of the
			// current ray
			double distance = intersections.get(0).point.distanceBetweenPoints(ray.getStart());
			GeoPoint closest = intersections.get(0);// Closest point of the first body in the list of the current ray
			// A loop that checks the smallest cut distance between all the bodies the
			// current ray passes through
			for (GeoPoint geoPoint : intersections) {
				double tmp = geoPoint.point.distanceBetweenPoints(ray.getStart());
				if (tmp < distance) {
					distance = tmp;
					closest = geoPoint;
				}
			}
			return closest;
		}
		return null;
	}

	/**
	 * This function calculates the color of the point
	 * 
	 * @param point
	 * @return the requested color
	 */
	private Color calcColor(GeoPoint intersection, Ray inRay, int level, double k) {
		if (level == 0 || k < MIN_CALC_COLOR_K)
			return Color.BLACK;
		Color color = intersection.geometry.getEmmission();
		Vector v = inRay.getDirection();
		Vector n = intersection.geometry.getNormal(intersection.point);
		int nShininess = intersection.geometry.getMaterial().getnShininess();
		double kd = intersection.geometry.getMaterial().getKd();
		double ks = intersection.geometry.getMaterial().getKs();
		for (LightSource light : scene.getLights()) {
			Vector l = light.getL(intersection.point);
			if (n.dotProduct(l) * n.dotProduct(v) > 0) {
				double ktr = transparency(l, n, intersection);
				// Send more rays from the spot to the light area to create a soft shadow
				for (int i = 0; i < 99; i++) {
					double randomX = Math.random() * 2 - 1;
					double randomY = Math.random() * 2 - 1;
					double randomZ = Math.random() * 2 - 1;
					double dist = Math.random() * 20 - 10;
					Vector moveFromCenterLight = new Vector(randomX, randomY, randomZ).normalize().scale(dist);
					Point3D tmp = light.getAreaLight().getCenter().add(moveFromCenterLight);
					Ray softRay = new Ray(intersection.point.subtract(tmp), tmp);
					ktr += transparency(softRay.getDirection(), n, intersection);
				}
				if (!Util.isZero(ktr * k)) {
					ktr = ktr / 100;
					Color lightIntensity = light.getIntensity(intersection.point).scale(ktr);
					color = color.add(calcDiffusive(kd, l, n, lightIntensity),
							calcSpecular(ks, l, n, v, nShininess, lightIntensity));
				}
			}
		}
		// reflection recursion
		double kr = intersection.geometry.getMaterial().getKr();
		Ray reflectedRay = constructReflectedRay(n, intersection.point, inRay.getDirection());
		GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
		Color reflectedLight = Color.BLACK;// Default reflectedLight if no found intersection
		if (!(reflectedPoint == null)) {// Check if have intersection
			reflectedLight = reflectedLight.add(calcColor(reflectedPoint, reflectedRay, level - 1, k * kr)).scale(kr);
		}
		// refraction recursion
		double kt = intersection.geometry.getMaterial().getKt();
		Ray refractedRay = constructRefractedRay(intersection.point, inRay.getDirection(), n);
		GeoPoint refractedPoint = findClosestIntersection(refractedRay);
		Color refractedLight = Color.BLACK;
		if (!(refractedPoint == null)) {
			refractedLight = refractedLight.add(calcColor(refractedPoint, refractedRay, level - 1, k * kt)).scale(kt);
		}
		color = color.add(reflectedLight, refractedLight);
		return color;
	}

	private Color calcColor(GeoPoint intersection, Ray inRay) {
		return calcColor(intersection, inRay, MAX_CALC_COLOR_LEVEL, 1.0)
				.add(this.scene.getAmbientLight().getIntensity());
	}

	/**
	 * A function that creates a grid above the background color
	 * 
	 * @param interval
	 */
	public void printGrid(int interval) {
		AmbientLight gridLight = new AmbientLight(new Color(0, 0, 0), 1.0);

		// Paints the vertical lines of the grid
		for (int i = interval; i < this.imageWriter.getNx(); i += interval) {
			for (int j = 0; j < this.imageWriter.getNy(); j++) {
				this.imageWriter.writePixel(i, j, gridLight.getIntensity().getColor());
			}
		}
		// Paints the wide lines of the grid
		for (int i = 0; i < this.imageWriter.getNx(); i++) {
			for (int j = interval; j < this.imageWriter.getNy(); j += interval) {
				this.imageWriter.writePixel(i, j, gridLight.getIntensity().getColor());
			}
		}
	}

	/**
	 * A function that prints the image
	 */
	public void writeToImage() {
		this.imageWriter.writeToImage();
	}

}
