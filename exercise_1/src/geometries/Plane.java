package geometries;


import java.util.ArrayList;
import java.util.List;
import static primitives.Util.isZero;
import primitives.*;

/**
 * this class represents a plane in space 
 * the plane contains a point and a vector (normal)
 * to the definition of the plane
 */
public abstract class Plane extends Geometry {
	private Point3D pointOnPlane;
	private Vector normal;
	
	/**********constructors**********/
	
	/**
	 * constructor that gets a point and a vector
	 * @param pointOnPlane a point on the plane 
	 * @param v  the normal of the plane
	 */
	public Plane(Point3D pointOnPlane, Vector v) {
		this.pointOnPlane = pointOnPlane;
		this.normal = v.normalize();
	}
	
	/**
	 * constructor that gets a point and a vector and color
	 * @param pointOnPlane a point on the plane 
	 * @param v  the normal of the plane
	 * @param col the emmission of the body

	 */
	public Plane(Color col, Point3D pointOnPlane, Vector v) {
		this.pointOnPlane = pointOnPlane;
		this.normal = v.normalize();
		this.emmission = col;
	}
	/**
	 * constructor that gets 3 points of the plane 
	 * and calculates the normal of the plane
	 * @param p1 1st point
	 * @param p2 2nd point
	 * @param p3 3rd point
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3) {
		this.normal = (p2.subtract(p1).crossProduct(p3.subtract(p1))).normalize();
		this.pointOnPlane = p1;
	}

	/**********getters**********/
	/**
	 * getter for the point 
	 * @return the point
	 */
	public Point3D getPointOnPlane() {
		return pointOnPlane;
	}

	/**
	 * getter for plumb
	 * @return the plumb
	 */
	public Vector getNormal() {
		return normal;
	}
	
	@Override
	public Color getEmmission() {
		return this.emmission;
	}
	
	/************Administration************/
	@Override
	public String toString() {
		return "Plane = " + this.pointOnPlane + "\n        " + this.normal;
	}

	@Override
	public Vector getNormal(Point3D point) {
		return this.normal;
	}

	

	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		List<GeoPoint> intersection = new ArrayList<GeoPoint>();
		if (ray.getStart().equals(this.pointOnPlane)) {
			intersection.add(0,new GeoPoint(this, this.pointOnPlane));
			return intersection;
		}
		double t = this.normal.dotProduct(this.pointOnPlane.subtract(ray.getStart())) / this.normal.dotProduct(ray.getDirection());
		if (isZero(t)) {
			intersection.add(0, new GeoPoint(this, ray.getStart()) );
		}
		else if (t > 0) {
			Point3D p = ray.getStart().add(ray.getDirection().scale(t));
			intersection.add(0, new GeoPoint(this, p));
		}
		return intersection;
	}
	
	
	/**
	 * return the minimum number between 3 number
	 * 
	 * @param n1
	 * @param n2
	 * @param n3
	 * @return the minimum number
	 */
	protected double getMin(double n1, double n2, double n3) {
		if (n1 > n2) {
			if (n2 > n3)
				return n3;
			else
				return n2;
		} else {
			if (n1 > n3)
				return n3;
			else
				return n1;
		}
	}

	/**
	 * return the maximum number between 3 number
	 * 
	 * @param n1
	 * @param n2
	 * @param n3
	 * @return the maximum number
	 */
	protected double getMax(double n1, double n2, double n3) {
		if (n1 > n2) {
			if (n3 > n1)
				return n3;
			else
				return n1;
		} else {
			if (n3 > n2)
				return n3;
			else
				return n2;
		}
	}
}
