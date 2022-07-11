/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Cylinder;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * tests for cylinder normal
 */
public class CylinderTests {

	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point3D)}.
	 * checking if "get normal" returns the right normal
	 */
	@Test
	public void testGetNormal() {
		//when the point is ON the cylinder
		Cylinder c = new Cylinder(1,  new Ray(new Vector(0,0,1) ,new Point3D(0,0,1)), 999);
		Vector expect = new Vector(0,0,1);
		assertEquals(expect, c.getNormal(new Point3D(0.5,0,1000)));
		
		//when the point is UNDER the cylinder
		Cylinder c2 = new Cylinder(1,  new Ray(new Vector(0,0,1) ,new Point3D(0,0,1)), 999);
		Vector expect2 = new Vector(0,0,-1);
		assertEquals(expect2, c2.getNormal(new Point3D(0.5,0,1)));
		
		//when the point is on the sides of the cylinder
		Cylinder c3 = new Cylinder(1,  new Ray(new Vector(0,0,1) ,new Point3D(0,0,0)), 100);
		Vector expect3 = new Vector(1,0,0);
		assertEquals(expect3, c3.getNormal(new Point3D(1,0,5)));
	}

}
