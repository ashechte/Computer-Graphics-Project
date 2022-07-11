package geometries;

import java.util.ArrayList;
import java.util.List;
import primitives.Ray;

/**
 * this is a composite of bodies it implements the function
 * "findIntersection(Ray)"
 */
public class Geometries extends BoundaryBox {
	// list of the bodies
	public List<BoundaryBox> geometries;

	BoundaryBox geoBox;

	/*********** Constructor *****************/
	/**
	 * constructor list of shapes
	 * 
	 * @param geometries list of shapes puts it in _geometries
	 */
	public Geometries(BoundaryBox... geometries) {
		this.geometries = new ArrayList<BoundaryBox>();
		for (BoundaryBox geometry : geometries)
			this.add(geometry);
	}
	
	
	
	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		List<GeoPoint> allIntersections = new ArrayList<GeoPoint>();
			for (BoundaryBox intersection : geometries) { // For each shape, we list the cut of the ray that
															// intersections it
				allIntersections.addAll(intersection.findIntersections(ray));
			}
		// _geometries.forEach(geometry ->
		// allIntersections.addAll(geometry.findIntersections(ray)));
		return allIntersections;
	}

	/**
	 * add bodies to geometries
	 * 
	 * @param bodies
	 */
	public void add(BoundaryBox box) {
		geoBox = box;
		setBox();
		this.geometries.add(box);
	}

	@Override
	protected void setBox() {
		if (this.geoBox.minX < minX)
			minX = this.geoBox.minX;

		if (this.geoBox.maxX > maxX)
			maxX = this.geoBox.maxX;

		if (this.geoBox.minY < minY)
			minY = this.geoBox.minY;

		if (this.geoBox.maxY > maxY)
			maxY = this.geoBox.maxY;

		if (this.geoBox.minZ < minZ)
			minZ = this.geoBox.minZ;

		if (this.geoBox.maxZ > maxZ)
			maxZ = this.geoBox.maxZ;
	}

	@Override
	public List<GeoPoint> findIntersectionsWithBouning(Ray ray) {
		List<GeoPoint> allIntersections = new ArrayList<GeoPoint>();
		if (intersectionBoxCheck(ray)) {
			for (BoundaryBox intersection : geometries) { // For each shape, we list the cut of the ray that
															// intersections it
				allIntersections.addAll(intersection.findIntersectionsWithBouning(ray));
			}
		}
		return allIntersections;
	}
}
