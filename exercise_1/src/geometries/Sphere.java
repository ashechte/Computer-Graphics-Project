package geometries;

import java.util.*;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.isZero;

/**
 * this class represents a sphere in space defined by radius and a point
 */
public class Sphere extends RadialGeometry {

	private Point3D center;

	/********** constructor **********/

	/**
	 * constructor that gets 2 elements
	 * 
	 * @param radius the radius of the sphere
	 * @param center the center of the sphere
	 */
	public Sphere(double radius, Point3D center) {
		super(radius);
		this.center = center;
		setBox();
	}

	/**
	 * constructor that gets 3 elements
	 * 
	 * @param radius the radius of the sphere
	 * @param center the center of the sphere
	 * @param col    the color
	 */
	public Sphere(Color col, double radius, Point3D center) {
		super(radius);
		this.center = center;
		this.emmission = col;
		setBox();
	}

	/**
	 * constructor that gets 4 elements
	 * 
	 * @param radius   the radius of the sphere
	 * @param center   the center of the sphere
	 * @param col      the color
	 * @param material the material of the body
	 */
	public Sphere(Material material, Color col, double radius, Point3D center) {
		super(radius);
		this.center = center;
		this.emmission = col;
		this.material = material;
		setBox();
	}

	/********** getter **********/

	/**
	 * getter for the center
	 * 
	 * @return the center
	 */
	public Point3D getCenter() {
		return this.center;
	}

	@Override
	public Color getEmmission() {
		return this.emmission;
	}

	/************ Administration ************/
	@Override
	public String toString() {
		return super.toString() + "center = " + this.center;
	}

	@Override
	public Vector getNormal(Point3D point) {
		return point.subtract(this.center).normalize();
	}

	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		List<GeoPoint> intersections = new ArrayList<GeoPoint>();
		// p0: Represents the starting point of the ray
		Point3D p0 = ray.getStart();
		// v: Represents the direction of the ray
		Vector v = ray.getDirection();
		// r: Represents the radius of the sphere
		double r = this.radius;
		// o: Represents the center of the sphere
		Point3D o = this.center;

		// A case in which p0 begins at O
		if (o.equals(p0)) {
			Point3D p = o.add(v.scale(r));
			intersections.add(0, new GeoPoint(this, p));
		} else {

			// u: Represents the distance between p0 and o
			Vector u = o.subtract(p0);
			// tm: Represents the distance between p0 and the middle of the ray within the
			// sphere
			double tm = v.dotProduct(u);
			// d: Represents the distance between o and the middle of the ray within the
			// sphere
			double d = Math.sqrt(u.squareLength() - (tm * tm));

			if (d > r) // That is, the ray is outside of sphere and therefore will return an empty list
				return intersections;

			// th: Represents the distance between p0 and the middle of the ray within the
			// sphere
			double th = Math.sqrt((r * r) - (d * d));
			// t1: Represents the distance between p0 and the p1(Intersections point first)
			double t1 = tm - th;
			// t2: Represents the distance between p0 and the p2(Intersections point second)
			double t2 = tm + th;

			if (t1 > 0) {// That is, the p0 begins before the sphere
				Point3D p1 = p0.add(v.scale(t1));
				intersections.add(0, new GeoPoint(this, p1));
			} else if (isZero(t1) || isZero(t2)) {// That is, the p0 begins at p1 or at p2
				intersections.add(0, new GeoPoint(this, p0));
			}
			if (t2 > 0 && !isZero(t2 - t1)) {// Checks in case the ray tangent to sphere
				Point3D p2 = p0.add(v.scale(t2));
				if (intersections.isEmpty())// That is, the p0 begins within the sphere
					intersections.add(0, new GeoPoint(this, p2));
				else
					intersections.add(1, new GeoPoint(this, p2));
			}
		}
		return intersections;
	}

	@Override
	protected void setBox() {
		minX = center.getX().get() - radius;
		maxX = center.getX().get() + radius;

		minY = center.getY().get() - radius;
		maxY = center.getY().get() + radius;

		minZ = center.getZ().get() - radius;
		maxZ = center.getZ().get() + radius;
	}

	@Override
	public List<GeoPoint> findIntersectionsWithBouning(Ray ray) {
		List<GeoPoint> intersections = new ArrayList<GeoPoint>();
		if (intersectionBoxCheck(ray)) {
			// p0: Represents the starting point of the ray
			Point3D p0 = ray.getStart();
			// v: Represents the direction of the ray
			Vector v = ray.getDirection();
			// r: Represents the radius of the sphere
			double r = this.radius;
			// o: Represents the center of the sphere
			Point3D o = this.center;

			// A case in which p0 begins at O
			if (o.equals(p0)) {
				Point3D p = o.add(v.scale(r));
				intersections.add(0, new GeoPoint(this, p));
			} else {

				// u: Represents the distance between p0 and o
				Vector u = o.subtract(p0);
				// tm: Represents the distance between p0 and the middle of the ray within the
				// sphere
				double tm = v.dotProduct(u);
				// d: Represents the distance between o and the middle of the ray within the
				// sphere
				double d = Math.sqrt(u.squareLength() - (tm * tm));

				if (d > r) // That is, the ray is outside of sphere and therefore will return an empty list
					return intersections;

				// th: Represents the distance between p0 and the middle of the ray within the
				// sphere
				double th = Math.sqrt((r * r) - (d * d));
				// t1: Represents the distance between p0 and the p1(Intersections point first)
				double t1 = tm - th;
				// t2: Represents the distance between p0 and the p2(Intersections point second)
				double t2 = tm + th;

				if (t1 > 0) {// That is, the p0 begins before the sphere
					Point3D p1 = p0.add(v.scale(t1));
					intersections.add(0, new GeoPoint(this, p1));
				} else if (isZero(t1) || isZero(t2)) {// That is, the p0 begins at p1 or at p2
					intersections.add(0, new GeoPoint(this, p0));
				}
				if (t2 > 0 && !isZero(t2 - t1)) {// Checks in case the ray tangent to sphere
					Point3D p2 = p0.add(v.scale(t2));
					if (intersections.isEmpty())// That is, the p0 begins within the sphere
						intersections.add(0, new GeoPoint(this, p2));
					else
						intersections.add(1, new GeoPoint(this, p2));
				}
			}
		}
		return intersections;
	}
}
