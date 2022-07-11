package geometries;

import static primitives.Util.isZero;


/**
 * this class is for elements in space 
 * That need a radius
 */
public abstract class RadialGeometry extends Geometry {
	
	protected double radius;
	/*********Constructor*********/
	/**
	 * constructor that gets a double 
	 * and checking that the radius is not negative
	 * @param radius the radius of the elements
	 */
	public RadialGeometry(double radius) {
		if (radius < 0 || isZero(radius)) {
			throw new NumberFormatException("negative radius");
		}
		this.radius = radius;
	}
	
	
	/******getter******/
	
	/**
	 * getter for radius
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}
	
	/************Administration************/
	@Override
	public String toString() {
		return "radius = " + radius;
	}
}
