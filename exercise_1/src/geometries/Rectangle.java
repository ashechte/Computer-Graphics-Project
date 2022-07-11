package geometries;

import java.util.ArrayList;
import java.util.List;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

/**
 * class represents rectangle
 */
public class Rectangle extends Plane {

	Point3D p1;
	Point3D p2;
	Point3D p3;
	Point3D p4;

	/****** Constructors *********/

	/**
	 * constructor
	 * 
	 * @param a        point p1
	 * @param b        point p2
	 * @param c        point p3
	 * @param color    emission color of the plane
	 * @param material material of the plane
	 */
	public Rectangle(Point3D a, Point3D b, Point3D c, Color color, Material material) {
		super(a, b, c);
		p1 = a;
		p2 = b;
		p3 = c;
		this.emmission = color;
		this.material = material;
		Vector ab = p2.subtract(p1);
		Vector ac = p3.subtract(p1);
		setBox();
		if (!Util.isZero(ab.dotProduct(ac))) {
			throw new IllegalArgumentException("ab is not orthogonal to ac");
		}
	}

	/**
	 * constructor
	 * 
	 * @param a        point p1
	 * @param b        point p2
	 * @param c        point p3
	 * @param color    emission color of the plane
	 * @param material material of the plane
	 */
	public Rectangle(Point3D a, Point3D b, Point3D c, Point3D d, Color color, Material material) {
		super(a, b, c);
		p1 = a;
		p2 = b;
		p3 = c;
		p4 = d;
		this.emmission = color;
		this.material = material;
		Vector ab = p2.subtract(p1);
		Vector ac = p3.subtract(p1);
		setBox();
		if (!Util.isZero(ab.dotProduct(ac))) {
			throw new IllegalArgumentException("ab is not orthogonal to ac");
		}
	}

	/****** Operations *********/

	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		// get plane intersections
		List<GeoPoint> planeIntersection = super.findIntersections(ray);

		if (planeIntersection.isEmpty())
			return planeIntersection;

		List<GeoPoint> intersectionList = planeIntersection;
		if (intersectionList.get(0).equals(p1) || intersectionList.get(0).equals(p2)
				|| intersectionList.get(0).equals(p3))
			return EMPTY_LIST;
		// the point is inside the rectangle if (0 < PA * AB < AB * AB) AND (0 < PA * AC
		// < AC * AC)
		Vector AB = p2.subtract(p1);
		Vector AC = p3.subtract(p1);

		Vector PA = intersectionList.get(0).point.subtract(p1);
		double u = PA.dotProduct(AB);
		double v = PA.dotProduct(AC);
		if (!(u >= 0.0 && u <= AB.dotProduct(AB) && v >= 0.0 && v <= AC.dotProduct(AC)))
			planeIntersection.clear();

		return planeIntersection;
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

	@Override
	public List<GeoPoint> findIntersectionsWithBouning(Ray ray) {
		// get plane intersections
		List<GeoPoint> planeIntersection = EMPTY_LIST;
		if (intersectionBoxCheck(ray)) {
			planeIntersection = super.findIntersections(ray);
			if (planeIntersection.isEmpty())
				return planeIntersection;

			List<GeoPoint> intersectionList = planeIntersection;
			if (intersectionList.get(0).equals(p1) ||intersectionList.get(0).equals(p2)||intersectionList.get(0).equals(p3))
				return EMPTY_LIST;
			// the point is inside the rectangle if (0 < PA * AB < AB * AB) AND (0 < PA * AC
			// < AC * AC)
			Vector AB = p2.subtract(p1);
			Vector AC = p3.subtract(p1);

			Vector PA = intersectionList.get(0).point.subtract(p1);
			double u = PA.dotProduct(AB);
			double v = PA.dotProduct(AC);
			if (!(u >= 0.0 && u <= AB.dotProduct(AB) && v >= 0.0 && v <= AC.dotProduct(AC)))
				planeIntersection.clear();			
		}
		return planeIntersection;
	}

//	/**
//	 * return the minimum number between 4 number
//	 * 
//	 * @param n1
//	 * @param n2
//	 * @param n3
//	 * @param n4
//	 * @return the minimum number
//	 */
//	protected double getMin(double n1, double n2, double n3, double n4) {
//		double min = super.getMin(n1, n2, n3);
//		if (n4 < min)
//			return n4;
//		return min;
//	}
//
//	/**
//	 * return the maximum number between 4 number
//	 * 
//	 * @param n1
//	 * @param n2
//	 * @param n3
//	 * @param n4
//	 * @return the maximum number
//	 */
//	protected double getMax(double n1, double n2, double n3, double n4) {
//		double max = super.getMax(n1, n2, n3);
//		if (n4 > max)
//			return n4;
//		return max;
//	}
}