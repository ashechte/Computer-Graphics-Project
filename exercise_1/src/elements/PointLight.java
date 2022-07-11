package elements;

import geometries.Sphere;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * It's a class of lighting without a particular direction, which implements the interface LightSource
 * @author ארי
 *
 */
public class PointLight extends Light implements LightSource {
	Sphere areaLight;
	Point3D position;
	double kc , kl , kq;
	/***************Constructor******************/
	/**
	 * Constructor of PointLight
	 * @param color the intensity
	 * @param pos position of the light
	 * @param kc Attenuation coefficient
	 * @param kl Attenuation coefficient
	 * @param kq Attenuation coefficient
	 */
	public PointLight(Color color, Point3D pos, double kc, double kl, double kq) {
		super(color);
		this.position = pos;
		this.kc = 1;
		this.kl = kl;
		this.kq = kq;
		//sphere for light
		this.areaLight = new Sphere(new Material(0, 0, 0,1,0),new Color(0, 0, 0), 10, pos);
	}

	/***************getters***************/
	
	public Point3D getPosition() {
		return position;
	}


	public double getKc() {
		return kc;
	}


	public double getKl() {
		return kl;
	}


	public double getKq() {
		return kq;
	}
	
	
	@Override
	public Color getIntensity(Point3D p) {//p Point on the shape illuminated by the light object
		return this.getIntensity().reduce(kc + (kl * p.distanceBetweenPoints(this.position) + (kq * p.squereDistanceBetweenPoints(this.position)))); 
	}
	
	@Override
	public Vector getL(Point3D p) {
		
		return p.subtract(position).normalize();
	}

	@Override
	public Vector getD(Point3D p) {
		return getL(p);
	}

	@Override
	public Sphere getAreaLight() {
		return this.areaLight;
	}

}
