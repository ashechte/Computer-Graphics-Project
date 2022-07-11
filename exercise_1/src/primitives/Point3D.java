package primitives;
/**
 * this class represents a point in
 * space (x,y,z)
 */
public class Point3D extends Point2D {
	
	/**
	 * 
	 */
	public static final Point3D ZERO = new Point3D(0,0,0);
	
	private Coordinate z;

	/************ Constructors ************/
	
	/**
	 * constructor that gets doubles
	 * @param x coord x
	 * @param y coord y
	 * @param z coord z
	 */
	public Point3D(double x, double y, double z) {
		super(x, y);
		this.z = new Coordinate(z);
	}

	/**
	 * copy constructor that gets a point3D
	 * @param pointToCopy point to copy to this class
	 */
	public Point3D(Point3D pointToCopy) {
		super(pointToCopy);
		this.z = pointToCopy.z;
	}

	/**********Getters**********/
	
	/**
	 * getter for z
	 * @return
	 */
	public Coordinate getZ() {
		return z;
	}
	
	/**********Administration**********/
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point3D))
			return false;
		Point3D other = (Point3D) obj;
		return super.equals(other) && z.equals(other.z);
	}

	@Override
	public String toString() {
		return super.toString() + " z=" + this.z.toString();
	}
	
	/************** Operations ***************/
	
	/**
	 * Subtracts a point from this point
	 * and calculate the vector between them
	 * @param other the second point
	 * @return vector from other to me
	 */
	public Vector subtract(Point3D other) {
		return new Vector(this.x._coord - (other.x)._coord,
						  this.y._coord - (other.y)._coord,
						  this.z._coord - (other.z)._coord);
	}
	
	/**
	 * add a vector to a point 
	 * and get the new point
	 * @param vectorToAdd = the vector to add
	 * @return the new point 
	 */
	public Point3D add(Vector vectorToAdd) {
		return new Point3D(this.x._coord + (vectorToAdd.getHead().x)._coord,
						   this.y._coord + (vectorToAdd.getHead().y)._coord,
						   this.z._coord + (vectorToAdd.getHead().z)._coord);
	}
	
	/**
	 * calculate the square distance between points
	 * @param other  the second point
	 * @return  the square of the distance
	 */
	public double squereDistanceBetweenPoints(Point3D other) {
		Vector vectorToCalculateDistance = this.subtract(other);
		return  vectorToCalculateDistance.getHead().x._coord * vectorToCalculateDistance.getHead().x._coord +
				vectorToCalculateDistance.getHead().y._coord * vectorToCalculateDistance.getHead().y._coord +
				vectorToCalculateDistance.getHead().z._coord * vectorToCalculateDistance.getHead().z._coord;
	}
	
	/**
	 * get the actual distance between points
	 * @param other  second point
	 * @return distance
	 */
	public double distanceBetweenPoints(Point3D other) {
		double distance = this.squereDistanceBetweenPoints(other);
		if (Util.isOne(distance)) {
			return 1;
		}
		return Math.sqrt(distance);
	}
}
