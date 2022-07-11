package geometries;

import java.util.ArrayList;
import java.util.List;
import primitives.Point3D;
import primitives.Ray;

public abstract class BoundaryBox {

	double minX, minY, minZ, maxX, maxY, maxZ;

	/**
	 * A class that returns the point and shape of that point
	 *
	 */
	public static class GeoPoint {

		public Geometry geometry;
		public Point3D point;

		/**********************c-tor**********************/
		/** constructor that gets geometry and point
		 * @param geo the body
		 * @param p the point
		 */
		public GeoPoint(Geometry geo, Point3D p) {
			this.geometry = geo;
			this.point = p;
		}

		/****************** getters ****************/
		/**
		 * getter for geometry
		 */
		public Geometry getGeometry() {
			return geometry;
		}

		/**
		 * getter for the point
		 */
		public Point3D getPoint() {
			return point;
		}
	}

	/**
	 * Check if the box is intersected
	 * 
	 * @param the ray that may intersect
	 * @return true if intersect false if not
	 */
	public boolean intersectionBoxCheck(Ray ray) {

		Point3D rayPoint = ray.getStart();
		Point3D rayDirection = ray.getDirection().getHead();

		double rayPointX = rayPoint.getX().get();
		double rayPointY = rayPoint.getY().get();
		double rayPointZ = rayPoint.getZ().get();

		double rayDirX = rayDirection.getX().get();
		double rayDirY = rayDirection.getY().get();
		double rayDirZ = rayDirection.getZ().get();

		double tMin = (minX - rayPointX) / rayDirX;
		double tMax = (maxX - rayPointX) / rayDirX;

		if (tMin > tMax) {
			double temp = tMin;
			tMin = tMax;
			tMax = temp;
		}

		double tyMin = (minY - rayPointY) / rayDirY;
		double tyMax = (maxY - rayPointY) / rayDirY;

		if (tyMin > tyMax) {
			double temp = tyMin;
			tyMin = tyMax;
			tyMax = temp;
		}

		if (tMin > tyMax || tMax < tyMin)
			return false;

		if (tyMin > tMin)
			tMin = tyMin;

		if (tyMax < tMax)
			tMax = tyMax;

		double tzMin = (minZ - rayPointZ) / rayDirZ;
		double tzMax = (maxZ - rayPointZ) / rayDirZ;

		if (tzMin > tzMax) {
			double temp = tzMin;
			tzMin = tzMax;
			tzMax = temp;
		}

		if (tMin > tzMax || tMax < tzMin)
			return false;

		return true;
	}

	// This is an empty list for triangle that sometimes need to return 0
	// intersections
	public static final List<GeoPoint> EMPTY_LIST = new ArrayList<GeoPoint>();

	/**
	 * This function calculates the intersection points of a ray on the body
	 * 
	 * @param ray the ray that might intersect
	 * @return a list with all those points
	 */
	public abstract List<GeoPoint> findIntersections(Ray ray);

	/**
	 * This function calculates the intersection points of a ray on the body
	 * but first checks if intersect the boundary box
	 * @param ray the ray that might intersect
	 * @return a list with all those points
	 */
	public abstract List<GeoPoint> findIntersectionsWithBouning(Ray ray);

	/**
	 * Set the min and max X, Y, Z for each Geometry
	 */
	protected abstract void setBox();

}
