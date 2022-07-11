/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import org.junit.Test;
import geometries.Plane;
import geometries.Plane2;
import primitives.*;

/**
 * tests for primitives.Plane functions
 */
public class PlaneTests {

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 * checking if "get normal" returns the right normal
	 */
	@Test
	public void testGetNormalPoint3D() {
		Plane2 p = new Plane2(new Point3D(1,2,3), new Point3D(3,2,1), new Point3D(4,5,6));
		Vector v = new Vector(p.getNormal(new Point3D(1,2,3)));
		assertEquals(new Vector(6,-12,6).normalize(), v);
	}
	
	/**
	 * checking all sorts of intersections
	 */
	@Test
	public void testFindIntersections() {
		Point3D point1 = new Point3D(1,1,1);
		Point3D q0 =new Point3D(1,1,0);
		Vector vector1 =new Vector(3, 3, 0);
		Vector vector2 = new Vector(2,2,-1);
		Vector orthogonal = new Vector(0,0,1);
		Plane2 p = new Plane2(q0, orthogonal);

		
		//Test 1, Ray intersects the plane
		Ray r1 = new Ray(vector2, point1);
		assertTrue("test 1",p.findIntersections(r1).get(0).point.equals(new Point3D(3, 3, 0)));
		
		//Test 2,  Ray does not intersect the plane (but not parallel to the plane)
		Ray r2 = new Ray(new Vector(2,2,1), point1);
		assertTrue(p.findIntersections(r2).isEmpty());
		
		//Test 3, Ray is parallel to the plane the ray is included in the plane
		Ray r3 = new Ray(vector1, new Point3D(0, 0, 0));
		assertTrue(p.findIntersections(r3).isEmpty());
		
		//Test 4, Ray is parallel to the plane the ray is not included in the plane
		Ray r4 = new Ray(vector1, point1);
		assertTrue(p.findIntersections(r4).isEmpty());
		
		//Test 5, Ray is orthogonal to the plane and P0 is before the plane
		Ray r5 = new Ray(orthogonal, new Point3D(1,1,-2));
		assertTrue(p.findIntersections(r5).get(0).point.equals(q0));
		
		//Test 6, Ray is orthogonal to the plane and P0 is on the plane
		Point3D p0 =new Point3D(1, 2, 0);
		Ray r6 = new Ray(orthogonal, p0);
		assertTrue(p.findIntersections(r6).get(0).point.equals(p0));
		
		//Test 7, Ray is orthogonal to the plane and P0 is after the plane
		Ray r7 = new Ray(orthogonal, point1);
		assertTrue(p.findIntersections(r7).isEmpty());
		
		//Test 8, Ray begins at the plane (p0 is in the plane, but not the ray)
		Ray r8 = new Ray(vector2, p0);
		assertTrue(p.findIntersections(r8).get(0).point.equals(p0));
	}
}
