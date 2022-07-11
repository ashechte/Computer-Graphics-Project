package geometries;

import java.util.List;
import static primitives.Util.isZero;
import primitives.*;

/**
 * this class represents a triangle in space by 3 points
 */
public class Triangle extends Plane {

	private Point3D p1;
	private Point3D p2;
	private Point3D p3;

	/********** Constructors ***********/

	/**
	 * constructor that gets points
	 * 
	 * @param p1 vertex 1
	 * @param p2 vertex 2
	 * @param p3 vertex 3
	 */
	public Triangle(Point3D p1, Point3D p2, Point3D p3) {
		super(p1, p2, p3);
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		setBox();
	}

	/**
	 * constructor that gets points and color
	 * 
	 * @param p1  vertex 1
	 * @param p2  vertex 2
	 * @param p3  vertex 3
	 * @param col the color
	 */
	public Triangle(Color col, Point3D p1, Point3D p2, Point3D p3) {
		super(p1, p2, p3);
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.emmission = col;
		setBox();
	}

	/**
	 * constructor that gets points and color and material
	 * 
	 * @param p1       vertex 1
	 * @param p2       vertex 2
	 * @param p3       vertex 3
	 * @param col      the color
	 * @param material the material of the body
	 */
	public Triangle(Material material, Color col, Point3D p1, Point3D p2, Point3D p3) {
		super(p1, p2, p3);
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.emmission = col;
		this.material = material;
		setBox();
	}

	/********** getters **********/

	/**
	 * getter for edge 1
	 * 
	 * @return the point of edge 1
	 */
	public Point3D getPoint1() {
		return p1;
	}

	/**
	 * getter for edge 2
	 * 
	 * @return the point of edge 2
	 */
	public Point3D getPoint2() {
		return p2;
	}

	/**
	 * getter for edge 3
	 * 
	 * @return the point of edge 3
	 */
	public Point3D getPoint3() {
		return p3;
	}

	/************ Administration ************/
	@Override
	public String toString() {
		return "triangle dots are = \n" + this.p1 + "\n" + this.p2 + "\n" + this.p3 + "\n";
	}

	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		List<GeoPoint> intersection = super.findIntersections(ray);
			// P is a point that cuts the plain and we are currently testing whether
			// it is within the triangle area or outside it
			if (intersection.isEmpty()) {
				return EMPTY_LIST;
			}
			Point3D p = intersection.get(0).point;
			Point3D p0 = ray.getStart();
			if (p.equals(p0)) {
				Ray newRay = new Ray(ray.getDirection(), p0.add(ray.getDirection().scale(-0.00001)));
				return this.findIntersections(newRay);
			}
			try {
				// v1, v2 ,v3 they are vectors from P0 to 3 vertices of the triangle
				Vector v1 = p1.subtract(p0);
				Vector v2 = p2.subtract(p0);
				Vector v3 = p3.subtract(p0);
				// 1n,n2,n3 are orthogonal vectors to v1, v2 ,v3
				Vector n1 = v1.crossProduct(v2).normalize();
				Vector n2 = v2.crossProduct(v3).normalize();
				Vector n3 = v3.crossProduct(v1).normalize();
				// num1 ,num2 ,num3 They are points that show us that P cuts in the triangle
				// area if everyone has the same sign (+/-)
				double num1 = p.subtract(p0).dotProduct(n1);
				double num2 = p.subtract(p0).dotProduct(n2);
				double num3 = p.subtract(p0).dotProduct(n3);
				if (isZero(num1) || isZero(num2) || isZero(num3)) {
					return EMPTY_LIST;
				}
				if ((num1 > 0 && num2 > 0 && num3 > 0) || (num1 < 0 && num2 < 0 && num3 < 0)) {
					return intersection;
				}
				// An exception in case there is a vector 0
			} catch (NumberFormatException e) {
				return EMPTY_LIST;
			}
		return EMPTY_LIST;
	}
	
	@Override
	public List<GeoPoint> findIntersectionsWithBouning(Ray ray) {
		List<GeoPoint> intersection = super.findIntersections(ray);
		if (intersectionBoxCheck(ray)) {
			// P is a point that cuts the plain and we are currently testing whether
			// it is within the triangle area or outside it
			if (intersection.isEmpty()) {
				return EMPTY_LIST;
			}
			Point3D p = intersection.get(0).point;
			Point3D p0 = ray.getStart();
			if (p.equals(p0)) {
				Ray newRay = new Ray(ray.getDirection(), p0.add(ray.getDirection().scale(-0.00001)));
				return this.findIntersections(newRay);
			}
			try {
				// v1, v2 ,v3 they are vectors from P0 to 3 vertices of the triangle
				Vector v1 = p1.subtract(p0);
				Vector v2 = p2.subtract(p0);
				Vector v3 = p3.subtract(p0);
				// 1n,n2,n3 are orthogonal vectors to v1, v2 ,v3
				Vector n1 = v1.crossProduct(v2).normalize();
				Vector n2 = v2.crossProduct(v3).normalize();
				Vector n3 = v3.crossProduct(v1).normalize();
				// num1 ,num2 ,num3 They are points that show us that P cuts in the triangle
				// area if everyone has the same sign (+/-)
				double num1 = p.subtract(p0).dotProduct(n1);
				double num2 = p.subtract(p0).dotProduct(n2);
				double num3 = p.subtract(p0).dotProduct(n3);
				if (isZero(num1) || isZero(num2) || isZero(num3)) {
					return EMPTY_LIST;
				}
				if ((num1 > 0 && num2 > 0 && num3 > 0) || (num1 < 0 && num2 < 0 && num3 < 0)) {
					return intersection;
				}
				// An exception in case there is a vector 0
			} catch (NumberFormatException e) {
				return EMPTY_LIST;
			}
		}
		return EMPTY_LIST;
	}



	@Override
	protected void setBox() {

		double p1X = p1.getX().get();
		double p1Y = p1.getY().get();
		double p1Z = p1.getZ().get();

		double p2X = p2.getX().get();
		double p2Y = p2.getY().get();
		double p2Z = p2.getZ().get();

		double p3X = p3.getX().get();
		double p3Y = p3.getY().get();
		double p3Z = p3.getZ().get();

		minX = getMin(p1X, p2X, p3X);
		maxX = getMax(p1X, p2X, p3X);

		minY = getMin(p1Y, p2Y, p3Y);
		maxY = getMax(p1Y, p2Y, p3Y);

		minZ = getMin(p1Z, p2Z, p3Z);
		maxZ = getMax(p1Z, p2Z, p3Z);
	}

}
