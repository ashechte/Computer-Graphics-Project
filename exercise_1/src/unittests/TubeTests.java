/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;


import geometries.Tube;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * tests for primitives.Tube functions
 * testing Tubes normal in 1 case 
 * you can see another cases in cylinder tests
 */
public class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 * testing the normal of Tube if it works in case that the point is 
	 * parallel to the start of the ray 
	 */
	@Test
	public void testGetNormal() {
		Tube c = new Tube(1,  new Ray(new Vector(0,0,1) ,new Point3D(0,0,1)));
		Vector expect = new Vector(1,0,0);
		assertEquals(expect, c.getNormal(new Point3D(1,0,1)));
	}

}
