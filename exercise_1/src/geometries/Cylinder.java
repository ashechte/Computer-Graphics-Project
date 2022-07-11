package geometries;

import primitives.*;
import static primitives.Util.isZero;


/**
 * this class represents a Cylinder in space
 * it inherits from tube for the direction and radius
 * and also has a length that is the height of the Cylinder
 */
public class Cylinder extends Tube {

	private double height;
	
	
	/**********constructor**********/
	
	/**
	 * constructor that gets 3 elements
	 * @param radius the radius of Cylinder
	 * @param centerOfTube the center of Cylinder
	 * @param height the height of Cylinder
	 */
	public Cylinder(double radius, Ray centerOfTube, double h) {
		super(radius, centerOfTube);
		this.height = h;
	}

	/**********getter**********/
	
	/**
	 * getter for the height 
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}
	
	/************Administration************/
	@Override
	public String toString() {
		return super.toString() + "height = " + height;
	}

	@Override
	public Vector getNormal(Point3D point) {
		Point3D p0 = this.direction.getStart();
		Vector v = this.direction.getDirection();
		// projection of the point onto the direction ray t = V*(P-P0)
		double t = v.dotProduct(point.subtract(p0));
		
		if (isZero(t)) {
			return new Vector(v.scale(-1).normalize());
		}
		else if (isZero(t-height)) {
			return new Vector(v.normalize());
		}
		// projection point of P onto the direction ray
		Point3D o = p0.add(v.scale(t));
		// (P - O) is orthogonal to the tube surface
		return point.subtract(o).normalize();
	}
	
}
