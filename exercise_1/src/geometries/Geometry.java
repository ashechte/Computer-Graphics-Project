package geometries;


import primitives.*;

/**
 * this abstract is for all calculation of normals like plane, sphere and tube.
 */
public abstract class Geometry extends BoundaryBox {

	Color emmission;
	Material material;
	
	/**
	 * constructor that gets 2 elements
	 * @param emmission of the body
	 * @param material of the body
	 */
	public Geometry(Color emmission, Material material) {
		this.emmission = emmission;
		this.material = material;
	}

	
	public Geometry() {
		this.emmission = Color.BLACK;
		this.material = Material.DEFAULT;
	}


	/**
	 * get the normal for this point
	 * 
	 * @param point on the surface of the geometry body
	 * @return the normal after normalizing
	 */
	public abstract Vector getNormal(Point3D point);

	/**
	 * @return the emmission of the body
	 */
	public abstract Color getEmmission();

	/**
	 * @return the material of the body
	 */
	public Material getMaterial() {
		return material;
	}

}
