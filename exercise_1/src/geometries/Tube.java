package geometries;

import primitives.*;
import static primitives.Util.isZero;

import java.util.List;

/**
 * this class represents a tube in space
 * it inherits from RadialGeometry for the radius of the tube
 * it is defined by a ray and radius
 */
public class Tube extends RadialGeometry {

	protected Ray direction;

	/**********constructor**********/
	/**
	 * constructor that gets radius and a ray
	 * @param radius radius of tube
	 * @param r the ray that is the center of the tube
	 */
	public Tube(double radius, Ray r) {
		super(radius);
		this.direction = new Ray(r);
	}

	/**********getter**********/
	
	/**
	 * getter for the ray
	 * @return the ray of the tube
	 */
	public Ray getCenterOfTube() {
		return direction;
	}

	/************Administration************/
	@Override
	public String toString() {
		return super.toString() + "diraction = " + direction;
	}

	@Override
	public Vector getNormal(Point3D point) {
		Point3D p0 = direction.getStart();
		Vector v = direction.getDirection();
		// projection of the point onto the direction ray t = V*(P-P0)
		double t =v.dotProduct(point.subtract(p0));
		if (isZero(t))
			return point.subtract(this.direction.getStart()).normalize();
		// projection point of P onto the direction ray
		Point3D o = p0.add(v.scale(t));
		// (P - O) is orthogonal to the tube surface
		return point.subtract(o).normalize();
	}


	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getEmmission() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setBox() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<GeoPoint> findIntersectionsWithBouning(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
