package primitives;

/**
 * A class that calculates the attenuation coefficients of the material of each object
 * @author ארי
 *
 */
public class Material {
	
	public static final Material DEFAULT = new Material(0, 0, 0);
	double kd, ks, kt= 0, kr = 0;
	int nShininess;
	
	/**
	 * constructor
	 * @param kd Defuse attenuation coefficient
	 * @param ks specular attenuation coefficient
	 * @param nShininess Shininess attenuation coefficient
	 */
	public Material(double kd, double ks, int nShininess) {
		this.kd = kd;
		this.ks = ks;
		this.nShininess = nShininess;
	}
	/**
	 * constructor that gets also the reflection level and refraction level
	 * @param kd Defuse attenuation coefficient
	 * @param ks specular attenuation coefficient
	 * @param kt Refraction attenuation coefficient
	 * @param kr reflection attenuation coefficient
	 * @param nShininess 
	 */
	public Material(double kd, double ks, double kt, double kr, int nShininess) {
		this.kd = kd;
		this.ks = ks;
		this.kt = kt;
		this.kr = kr;
		this.nShininess = nShininess;
	}
	/***************getters***********************/
	/**
	 * getter for kd
	 * @return kd
	 */
	public double getKd() {
		return kd;
	}
	/**
	 * getter for ks
	 * @return ks
	 */
	public double getKs() {
		return ks;
	}
	/**
	 * getter for nShininess
	 * @return nShininess
	 */
	public int getnShininess() {
		return nShininess;
	}
	/**
	 * getter for kt
	 * @return kt
	 */
	public double getKt() {
		return kt;
	}
	/**
	 * getter for kr
	 * @return kr
	 */
	public double getKr() {
		return kr;
	}
}
