package primitives;
/**
 * this class represents 
 * a point on the plane (x,y)
 */
public class Point2D {
	protected Coordinate x;
	protected Coordinate y;

	/************ constructors ************/
	/**
	 * constructor that gets doubles
	 * @param x coord x
	 * @param y coord y
	 */
	public Point2D(double x, double y) {
		this.x = new Coordinate(x);
		this.y = new Coordinate(y);
	}

	/**
	 * copy constructor that gets a point2D
	 * @param pointToCopy point to copy to this class
	 */
	public Point2D(Point2D pointToCopy) {
		this.x = pointToCopy.x;
		this.y = pointToCopy.y;
	}
	
	/**********Getters**********/
	/**
	 * getter for x
	 * @return coord x
	 */
	public Coordinate getX() {
		return x;
	}

	/**
	 * getter for y
	 * @return coord y
	 */
	public Coordinate getY() {
		return y;
	}

	/**********administration**********/
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point2D))
			return false;
		Point2D other = (Point2D) obj;
		return x.equals(other.x) && y.equals(other.y);
	}
	
	@Override
	public String toString() {
		return " x= " + this.x.toString() + " y= "+ this.y.toString(); 
	}
}
