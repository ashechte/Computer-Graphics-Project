package elements;

import geometries.Sphere;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
/**
 * this class is for light source
 */
public interface LightSource {
	
	/**
	 * get intensity in point p
	 * @return color
	 */
	Color getIntensity(Point3D p);
	
	/**
	 * get the vector from the light to the body (pointL)
	 * @return vector
	 */
	Vector getL(Point3D pointL);
	
	/**
	 * get the direction of the light 
	 * @return vector
	 */
	Vector getD(Point3D pointD); 
	
	/**
	 * this function returns the area light for all lightSources 
	 * @return a sphere that represent the area
	 */
	Sphere getAreaLight();
}
