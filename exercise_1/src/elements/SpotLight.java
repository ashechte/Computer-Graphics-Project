package elements;

import static primitives.Util.isZero;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * It is a class of lighting with a particular direction, which extends the class PointIight
 * @author ארי
 *
 */
public class SpotLight extends PointLight {
	//for shrinking the light spread
	int pow;
	Vector direction;

	/**
	 *  Constructor that gets color, position vector direction and attenuation coefficients
	 * @param color the intensity
	 * @param pos position of the light
	 * @param direction of the light
	 * @param kc Attenuation coefficient 
	 * @param kl Attenuation coefficient
	 * @param kq Attenuation coefficient
	 * @param pow power for shrinking the light spread
	 */
	public SpotLight(Color color, Point3D pos, Vector direction, double kc, double kl, double kq, int pow) {
		super(color, pos, kc, kl, kq);
		this.direction = direction.normalize();
		this.pow = pow;
	}

	/**
	 * In addition to calculating pointLight attenuation factors, the function here
	 * does not color the places at an angle above 90 degrees The spotLight
	 * direction vector
	 */
	@Override
	public Color getIntensity(Point3D p) {
		Vector l = p.subtract(this.position).normalize();// vector from PL to P
		double cos = this.direction.dotProduct(l);
		if (cos > 0 && !isZero(cos)) {
			return super.getIntensity(p).scale(Math.pow(cos, this.pow));
		}
		return Color.BLACK;
	}
	/*************getters***************/
	public int getPow() {
		return pow;
	}

	public Vector getDirection() {
		return direction;
	}

	@Override
	public Vector getL(Point3D p) {
		return super.getL(p);
	}

	@Override
	public Vector getD(Point3D p) {
		return direction;
	}

}
