package primitives;

/**
 * this class represents a ray in space
 * direction represents the direction of the ray
 * start represents the start point of the ray
 */
public class Ray {
	private Vector direction;
	private Point3D start;
	
	/************constructors************/
	
	/**
	 * constructor that gets vector and a point
	 * @param v vector that represents the direction of the ray
	 * @param start point where the ray starts
	 */
	public Ray(Vector v, Point3D start) {
		this.direction = v.normalize();
		this.start = start;
	}
	
	/**
	 * copy constructor that gets a ray 
	 * @param rayToCopy ray to insert to this class
	 */
	public Ray(Ray rayToCopy) {
		this.direction = rayToCopy.direction;
		this.start = rayToCopy.start;
	}
	/************Getters************/
	
	/**
	 * getter for direction
	 * @return the direction
	 */
	public Vector getDirection() {
		return direction;
	}

	/**
	 * getter for start
	 * @return the start point
	 */
	public Point3D getStart() {
		return start;
	}

	/********** Administration **********/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ray))
			return false;
		Ray other = (Ray) obj;
		return this.direction.equals(other.direction)
			   && this.start.equals(other.start);
	}
	
	@Override
	public String toString() {
		return this.start.toString() + " direction= " + direction.toString();
	}
}
