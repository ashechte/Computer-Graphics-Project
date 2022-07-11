package elements;

import primitives.Color;

/**
 * A class that adds a ambient light
 * 
 * @author User 
 *
 */
public class AmbientLight extends Light {
	//double ka;
	
	/*************** constructor *****************/
	/**
	 * A constructor that gets the ambient light and its power
	 * 
	 * @param color
	 * @param ka    The power of ambient light
	 */
	public AmbientLight(Color color, double ka) {
		super(color.scale(ka));
		//this.ka = ka;
	}
}
