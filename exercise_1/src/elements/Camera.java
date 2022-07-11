package elements;

import static primitives.Util.isZero;

import java.util.ArrayList;
import java.util.List;
import primitives.*;

/**
 * this class represents the scenes' camera
 * @author ארי
 *
 */
public class Camera {

	private Point3D p0;
	private Vector vUp;
	private Vector vTo;
	private Vector vRight;

	/***************** constructor *************/

	/**
	 * A constructor that gets a point of the camera and 2 vectors that come out of
	 * it
	 * 
	 * @param p0  Camera location
	 * @param vUp Vector oriented up on a minus Y axis
	 * @param vTo Vector in a straight direction on the minus Z axis
	 */
	public Camera(Point3D p0, Vector vUp, Vector vTo) {

		if (isZero(vTo.dotProduct(vUp))) {
			this.p0 = p0;
			this.vUp = vUp.normalize();
			this.vTo = vTo.normalize();
			this.vRight = vTo.crossProduct(vUp).normalize(); // Vector in the right direction on the axis minus X
		}
	}

	/************** getters *************/
	/**
	 * getter for p0
	 */
	public Point3D getP0() {
		return p0;
	}

	/**
	 * getter for vUp
	 */
	public Vector getVup() {
		return vUp;
	}

	/**
	 * getter for vTo
	 */
	public Vector getVto() {
		return vTo;
	}

	/**
	 * getter for vRight
	 */
	public Vector getVright() {
		return vRight;
	}

	/******************* operations *****************/
	/**
	 * this function return the ray from camera to a specific pixel
	 * 
	 * @param nX             how many pixels are on the width
	 * @param nY             how many pixels are on the length
	 * @param i              the pixels' column
	 * @param j              the pixels' row
	 * @param screenDistance distance from camera to the view plane
	 * @param screenWidth    screen width
	 * @param screenHeight   screen height
	 * @return the ray from camera to pixel [i,j]
	 */

	public Ray constructRayThroughPixel(int nX, int nY, int i, int j, double screenDistance, double screenWidth,
			double screenHeight) {
		// if the camera is on the view plane, there are no rays through the pixels
		if (isZero(screenDistance)) {
			return null;
		}
		Point3D pc = p0.add(vTo.scale(screenDistance));// Image center

		// Ratio (pixel width & height)
		double rY = screenHeight / nY;// The height of the pixel
		double rX = screenWidth / nX;// The width of the pixel

		// Pixel[i,j] center, where i represents the columns and j represents the rows
		double yj = rY * (j - nY / 2.0) + rY / 2.0;
		double xi = rX * (i - nX / 2.0) + rX / 2.0;

		Point3D pij = pc;
		if (!isZero(xi)) {// Test that did not exit vector 0 by scalar multiplication of X
			pij = pij.add(vRight.scale(xi));// Find the pixel on the X axis
		}
		if (!isZero(yj)) {// Test that did not exit vector 0 by scalar multiplication of Y
			pij = pij.add(vUp.scale(-yj));// Find the pixel on the Y axis
		}
		Vector vij = pij.subtract(p0).normalize();// direction
		return new Ray(vij, p0);
	}
	
	public List<Ray> constructRaysThroughPixel(int nX, int nY, int i, int j, double screenDistance, double screenWidth,
			double screenHeight){

		// if the camera is on the view plane, there are no rays through the pixels
		if (isZero(screenDistance)) {
			return new ArrayList<Ray>();
		}
		Point3D pc = p0.add(vTo.scale(screenDistance));// Image center

		// Ratio (pixel width & height)
		double rY = screenHeight / nY;// The height of the pixel
		double rX = screenWidth / nX;// The width of the pixel

		// Pixel[i,j] center, where i represents the columns and j represents the rows
		double yj = rY * (j - nY / 2.0) + rY / 2.0;
		double xi = rX * (i - nX / 2.0) + rX / 2.0;
		Point3D pij = pc;
		if (!isZero(xi)) {// Test that did not exit vector 0 by scalar multiplication of X
			pij = pij.add(vRight.scale(xi));// Find the pixel on the X axis
		}
		if (!isZero(yj)) {// Test that did not exit vector 0 by scalar multiplication of Y
			pij = pij.add(vUp.scale(-yj));// Find the pixel on the Y axis
		}
		Vector vij = pij.subtract(p0).normalize();// direction
		Ray centerOfPixel = new Ray(vij,p0);
		List<Ray> pixelRays = new ArrayList<Ray>();
		pixelRays.add(centerOfPixel);
		for (int k = 0; k < 20; k++) {
			double rndX = Math.random() -0.5 * rX;
			double rndY = Math.random() -0.5 * rY;
			Point3D newPij = pij.add(vRight.scale(rndX));
			newPij = newPij.add(vUp.scale(rndY));
			vij = newPij.subtract(p0).normalize();
			pixelRays.add(new Ray(vij,newPij));
		}
		return pixelRays;	
	}
}
