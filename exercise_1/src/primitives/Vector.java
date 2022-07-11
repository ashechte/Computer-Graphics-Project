package primitives;

/**
 * this class represents a vector in space
 * head represents direction of vector
 */
public class Vector {
	
 	private Point3D head;

	/************ Constructors ************/
 	
 	/**
 	 * constructor that gets a point for the head
 	 * @param head the point to insert
 	 */
	public Vector(Point3D head) {
		if (head.equals(Point3D.ZERO)) 
			throw new NumberFormatException("vector can not direct to (0,0,0)");
		this.head = head;
	}

	/**
	 * copy constructor that gets vector to copy
	 * @param vectorToCopy 
	 */
	public Vector(Vector vectorToCopy) {
		this.head = vectorToCopy.head;
	}

	/**
	 * constructor that gets doubles
	 * @param x coord x
	 * @param y coord y
	 * @param z coord z
	 */
	public Vector (double x, double y, double z) {
		this.head = new Point3D(x, y, z);
		if (this.head.equals(Point3D.ZERO))
			throw new NumberFormatException("vector can not direct to (0,0,0)");
	}
	/**********Getters**********/
	
	/**
	 * getter of the head
	 * @return head of vector
	 */
	public Point3D getHead() {
		return head;
	}

	/**********Administration**********/
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector))
			return false;
		Vector other = (Vector) obj;
		return this.head.equals(other.head);
	}


	@Override
	public String toString() {
		return this.head.toString();
	}
	
	
	/************** Operations ***************/
	
	/**
	 * adds 2 vectors
	 * @param vectorToAdd the second vector
	 * @return  the new vector after adding 
	 */
	public Vector add(Vector vectorToAdd) {
		return new Vector(this.head.getX()._coord + vectorToAdd.head.getX()._coord,
						  this.head.getY()._coord + vectorToAdd.head.getY()._coord,
						  this.head.getZ()._coord + vectorToAdd.head.getZ()._coord);
	}

	/**
	 * Subtracts 2 vectors
	 * @param vectorToSubtract  the second vector 
	 * @return the new vector after subtraction 
	 */
	public Vector subtract(Vector vectorToSubtract) {
		return new Vector (this.head.getX()._coord - vectorToSubtract.head.getX()._coord,
						   this.head.getY()._coord - vectorToSubtract.head.getY()._coord,
						   this.head.getZ()._coord - vectorToSubtract.head.getZ()._coord);
	}
	
	/**
	 * multiply a vector with scalar
	 * @param scale the number to multiply 
	 * @return the new multiplied vector
	 */
	public Vector scale(double scalar) {
		return new Vector(this.head.getX()._coord * scalar,
						  this.head.getY()._coord * scalar,
						  this.head.getZ()._coord * scalar);
	}
	/**
	 * calculates the dot product of 2 vectors
	 * @param other the second vector
	 * @return result of dot product
	 */
	public double dotProduct(Vector other) {
		return (this.head.getX()._coord * (other.head.getX())._coord +
				this.head.getY()._coord * (other.head.getY())._coord +
				this.head.getZ()._coord * (other.head.getZ())._coord);
	}
	/**
	 * calculates the plumb of both, this vector 
	 * and another vector
	 * @param otherVector the second vector
	 * @return the new plumb vector
	 */
	public Vector crossProduct(Vector otherVector) {	
		return new Vector (((this.head.getY()._coord * otherVector.head.getZ()._coord) - (this.head.getZ()._coord * otherVector.head.getY()._coord)),
						   ((this.head.getZ()._coord * otherVector.head.getX()._coord) - (this.head.getX()._coord * otherVector.head.getZ()._coord)),
						   ((this.head.getX()._coord * otherVector.head.getY()._coord) - (this.head.getY()._coord * otherVector.head.getX()._coord)));
	}
	
	/**
	 * calculates the length of the vector
	 * @return the length
	 */
	public double length() {
		return this.head.distanceBetweenPoints(Point3D.ZERO);
	}
	
	/**
	 * calculates the square length of the vector
	 * @return the square length
	 */
	public double squareLength() {
		return this.head.squereDistanceBetweenPoints(Point3D.ZERO);
	}
	
	/**
	 * calculates the normal vector of this vector 
	 * @return the new vector that is normalized
	 */
	public Vector normalize() {
		double length = length();
		return new Vector(this.head.getX()._coord / length,
						  this.head.getY()._coord / length,
		   				  this.head.getZ()._coord / length);
	}
}
