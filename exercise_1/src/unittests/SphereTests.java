package unittests;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
import geometries.Sphere;
import primitives.*;
import primitives.Vector;
import static geometries.BoundaryBox.GeoPoint;

/**
 * tests for primitives.Sphere functions
 */
public class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 * checking if "get normal" returns the right normal
	 */
	@Test
	public void testGetNormal() {
		Sphere s1 = new Sphere(5, new Point3D(0,0,0));
		Vector normal = s1.getNormal(new Point3D(4,3,0));
		assertEquals(normal, new Vector(0.8,0.6,0));
	}
	
	/**
	 * checking all cases of intersections for sphere
	 */
	@Test
	public void testFindIntersections () {
		Sphere s1 = new Sphere(4, Point3D.ZERO);
		Vector v1 = new Vector(1,0,0);
		Point3D p1 = new Point3D(-4,0,0 );
		Point3D p2 = new Point3D(4,0,0);
		Point3D tan = new Point3D(0,0,4);
		
		//Test 1, No intersections 
		Ray r1 = new Ray(v1, new Point3D(-1,0,5));
		assertTrue("test 1",s1.findIntersections(r1).isEmpty());
		
		//Test 2, 2 intersections 
		Ray r2 = new Ray(v1, new Point3D(-5,1,0));
		Point3D p1r2 = new Point3D(Math.sqrt(15)*(-1),1,0);
		Point3D p2r2 = new Point3D(Math.sqrt(15),1,0);
		List<GeoPoint> intersectionsList =s1.findIntersections(r2);
		assertTrue("test 2",intersectionsList.get(0).point.equals(p1r2)  && intersectionsList.get(1).point.equals(p2r2));
		
		//Test 3, 1 intersection, ray starts inside the sphere
		Ray r3 = new Ray(v1, new Point3D(0,1,0));
		assertTrue("test 3",s1.findIntersections(r3).get(0).point.equals(p2r2));
		
		//Test 4, 2 tail intersections 
		Ray r4 = new Ray(v1, new Point3D(5,1,0));
		//IntersectionsList2 is the intersections of the opposite direction of r4
		List<GeoPoint> intersectionsList2 = s1.findIntersections(new Ray(r4.getDirection().scale(-1), r4.getStart()));
		intersectionsList = s1.findIntersections(r4);
		assertTrue("test 4",intersectionsList.isEmpty() && intersectionsList2.get(0).point.equals(p2r2) && intersectionsList2.get(1).point.equals(p1r2));
		
		//From test 5 it will be edge cases
		//Test 5, 2 intersections and passing through the center (the O point)
		Ray r5 = new Ray(v1, new Point3D(-5,0,0));
		intersectionsList = s1.findIntersections(r5);
		assertTrue("test 5",intersectionsList.get(0).point.equals(p1) && intersectionsList.get(1).point.equals(p2));
		
		//Test 6, ray starts on the sphere and has 1 intersection and passing through the center (the O point)
		Ray r6 = new Ray(v1, p1);
		intersectionsList =s1.findIntersections(r6);
		assertTrue("test 6",intersectionsList.get(0).point.equals(p1) && intersectionsList.get(1).point.equals(p2));
		
		//Test 7, ray starts on the sphere with no other intersection and its' tail is passing through the center (the O point)
		Ray r7 = new Ray(v1, p2);
		assertTrue("test 7",s1.findIntersections(r7).get(0).point.equals(p2));
		
		//Test 8, 2 TAIL intersections and passing through the center (the O point)
		Ray r8 = new Ray(v1,new Point3D(5,0,0));
		intersectionsList = s1.findIntersections(r8);
		intersectionsList2 = s1.findIntersections(new Ray(r8.getDirection().scale(-1), r8.getStart()));
		assertTrue("test 8",intersectionsList.isEmpty() && intersectionsList2.get(0).point.equals(p2) && intersectionsList2.get(1).point.equals(p1));
		
		//Test 9, ray starts in the center (the O point) 
		Ray r9 = new Ray(v1, Point3D.ZERO);
		assertTrue("test 9",s1.findIntersections(r9).get(0).point.equals(p2));
		
		//Test 10, ray starts on the sphere and has 1 intersection and NOT passing through the center
		Ray r10 = new Ray(v1, p1r2);
		intersectionsList = s1.findIntersections(r10);
		assertTrue("test 10",intersectionsList.get(0).point.equals(p1r2) && intersectionsList.get(1).point.equals(p2r2));
		
		//Test 11, ray starts on the sphere and has NO intersection and its' tail is NOT passing through the center
		Ray r11 = new Ray(v1,p2r2);
		intersectionsList = s1.findIntersections(r11);
		assertTrue("test 11",s1.findIntersections(r11).get(0).point.equals(p2r2));
		
		//Test 12, ray is tangent to the sphere 
		Ray r12 = new Ray(v1, new Point3D(-2,0,4));
		assertTrue("test 12",s1.findIntersections(r12).get(0).point.equals(tan));
		
		//Test 13, ray is tangent to the sphere and starts on the sphere
		Ray r13 = new Ray(v1, tan);
		assertTrue("test 13",s1.findIntersections(r13).get(0).point.equals(tan));
		
		//Test 14, rays' tail is tangent to the sphere
		Ray r14 = new Ray(v1, new Point3D(2,0,4));
		assertTrue("test 14",s1.findIntersections(r14).isEmpty());
		
		//Test 15, ray is orthogonal to the sphere in the start point 
		Ray r15 = new Ray(v1, new Point3D(0,0,5));
		assertTrue("test 15",s1.findIntersections(r15).isEmpty());
	}
}

