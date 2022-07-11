/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Triangle;
import primitives.*;

/**
 * tests for primitives.Triangle functions
 */
public class TriangleTests {

	/**
	 * Test method for {@link geometries.Triangle#getNormal(primitives.Point3D)}.
	 * checking if "get normal" returns the right normal
	 */
	@Test
	public void testGetNormal() {
		Triangle t = new Triangle(new Point3D(1,2,3), new Point3D(3,2,1), new Point3D(4,5,6));
		Vector v = new Vector(t.getNormal(new Point3D(1,2,3)));
		assertEquals(new Vector(6,-12,6).normalize(), v);
	}
	
	/**
	 * Testing all sorts of intersections in triangle
	 */
	@Test
	public void testFindIntersections () {
		Vector v = new Vector(1, 1, -1);
		Triangle t = new Triangle(new Point3D(0,0,0), new Point3D(1,0,0), new Point3D(0, 1, 0));

		
		//Test 1, intersection inside the plane 
		Ray r1 = new Ray(v, new Point3D(-0.8, -0.8, 1));
		assertTrue(t.findIntersections(r1).get(0).point.equals(new Point3D(0.2, 0.2, 0)));
		
		//Test 2, no intersections
		Ray r2 = new Ray(v, new Point3D(3, 4, 1));
		assertTrue(t.findIntersections(r2).isEmpty());
		
		//Test 3, no intersections, but passes between the continuation of the triangle's limps
		Ray r3 = new Ray(v, new Point3D(-2, -2, 1));
		assertTrue(t.findIntersections(r3).isEmpty());
		
		//Test 4, ray starts before the plane and passes through 1 of the limps
		Ray r4 = new Ray(v, new Point3D(-0.5, -0.5, 1));
		assertTrue(t.findIntersections(r4).isEmpty());
		
		//Test 5, ray starts on 1 of the limps
		Ray r5 = new Ray(v, new Point3D(0.5,0.5,0));
		assertTrue(t.findIntersections(r5).isEmpty());
		
		//Test 6, ray starts before the plane and passes through a vertex
		Ray r6 = new Ray(v, new Point3D(-1, -1, 1));
		assertTrue(t.findIntersections(r6).isEmpty());
		
		//Test 7, ray starts on a vertex
		Ray r7 = new Ray(v, new Point3D(0, 0, 0));
		assertTrue(t.findIntersections(r7).isEmpty());
		
		//Test 8, ray starts before the plane and passes through the continuation of a limp
		Ray r8 = new Ray(v, new Point3D(1, -1, 1));
		assertTrue(t.findIntersections(r8).isEmpty());
		
		//Test 9, ray starts on the continuation of a limp
		Ray r9 = new Ray(v, new Point3D(2, 0, 0));
		assertTrue(t.findIntersections(r9).isEmpty());
		
		//Test 10, ray starts on the triangle
		Ray r10 = new Ray(v, new Point3D(0.2, 0.2, 0));
		assertTrue(t.findIntersections(r10).get(0).point.equals(new Point3D(0.2, 0.2, 0)));
	}
}
