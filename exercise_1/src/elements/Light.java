package elements;

import primitives.Color;

/**
 * abstract class of AmbientLight & DirectionalLight & pointLight & spotLight
 * 
 * @author User
 *
 */
public abstract class Light {

	Color color;
	
	/************constructor*************/
	/** gets the lights' color
	 * @param color the intensity
	 */
	public Light(Color color) {
		this.color = color;
	}

	/**
	 * attenuation of intensity
	 * @return intensity 0f color
	 */
	public Color getIntensity() {
		return color;
	}
}
