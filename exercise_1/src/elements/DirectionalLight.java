package elements;

import geometries.Sphere;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * This class is for direction light 
 * @author ארי
 *
 */
public class DirectionalLight extends Light implements LightSource {
	
	Vector direction;
	Sphere areaLight;
	
	/******************constructor******************/
	/**
	 * @param color the intensity
	 * @param vec ditection of the light
	 */
	public DirectionalLight(Color color, Vector vec) {
		super(color);
		this.direction = vec.normalize();
		Vector tmp = this.direction.scale(-100);//located far from usual scene
		this.areaLight = new Sphere(new Material(0, 0, 0,1,0),new Color(0, 0, 0), 10, tmp.getHead());
	}

	@Override
	public Color getIntensity(Point3D p) {
	
		return getIntensity();
	}

	@Override
	public Vector getL(Point3D pointL) {
		
		return direction;
	}

	@Override
	public Vector getD(Point3D pointD) {
		return direction;
	}

	@Override
	public Sphere getAreaLight() {
		return this.areaLight;
	}
}
