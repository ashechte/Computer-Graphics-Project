/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Point3D;
import primitives.Vector;

/**
 * tests for primitives.Vector functions
 */
public class VectorTests {

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 * test 1 checks if adding works
	 * test 2 checks if adding a vector that returns the ZERO vector throws an Exception
	 */
	@Test
	public void testAdd() {
		//test 1
		Vector vectorTest = new Vector(3.000, 5.6987, 12.548963);
		Vector output = vectorTest.add(new Vector(1,2,3));
		Vector expected = new Vector(4.000, 7.6987, 15.548963);
		assertEquals(expected, output);
		
		//test 2
		try {
			Vector v1 = new Vector(1,1,1);
			v1.add(new Vector(-1, -1, -1));
			fail("Didn't throw zero vector exception!");
		} catch (NumberFormatException e) {
			assertTrue(true);
		}
	}
	
	
	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 * test 1 checks if subtracting works
	 * test 2 checks if subtracting a vector that returns the ZERO vector throws an Exception
	 */
	@Test
	public void testSubtract() {
		//test 1
		Vector v1 = new Vector(1,2,3);
		Vector v2 = v1.subtract(new Vector(1,2,6));
		Vector expected = new Vector(0,0,-3);
		assertEquals(expected, v2);
		
		//test 2
		try {
			Vector v3 = new Vector(1,1,1);
			v3.subtract(v3);
			fail("Didn't throw zero vector exception!");
		}
		catch (NumberFormatException e) {
			assertTrue(true);
		}
	}

	
	
	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 * checking if Scale returns the right vector
	 */
	@Test
	public void testScale() {
		Vector v1 = new Vector(9,8,7);
		assertEquals(new Vector(50.99994,45.33328,39.66662), v1.scale(5.66666));
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 * checking if dot product returns the right value in all conditions
	 * test 1 simple dot product
	 * test 2 product orthogonal vectors
	 * test 3 vectors with an acute angle between them
	 * test 4 vectors with an obtuse angle between them
	 */
	@Test
	public void testDotProduct() {
		Vector v1 = new Vector(5,6,7);
		Vector v2 = new Vector(9,9,9);
		Vector v3 = new Vector(0,-7,6);
		
		//test 1
		assertEquals(v1.dotProduct(v2), 162, 1e-10);
		
		//test 2
		assertEquals(v1.dotProduct(v3), 0, 1e-10);
		
		// test 3 acute angle
		Vector vector1 = new Vector(new Point3D(1, 0, 0));
		Vector vector2 = new Vector(new Point3D(1, 2, 0));
		assertEquals(vector1.dotProduct(vector2), 1.0, 1e-10);
		
		// test 4 obtuse angle
		Vector vector3 = new Vector(new Point3D(-1, 2, 0));
		assertEquals(vector1.dotProduct(vector3), -1.0, 1e-10);
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 * testing if cross product returns the right answer 
	 * test 1 simple cross product
	 * test 2 cross vector with itself
	 * test 3 cross vector with itself multiplied
	 */
	@Test
	public void testCrossProduct() {
		Vector v1 = new Vector(5,6,7);
		Vector v2 = new Vector(9,9,9);
		Vector v3 = new Vector(10,12,14);
		
		//test 1
		assertEquals(v1.crossProduct(v2), new Vector(-9,18,-9));
		
		//test 2
		try {
			v1.crossProduct(v1);
			fail("Didn't throw zero vector exception!");
		} catch (NumberFormatException e) {
			assertTrue(true);
		}
		
		//test 3
		try {
			v1.crossProduct(v3);
			fail("Didn't throw zero vector exception!");
		} catch (NumberFormatException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 * testing if returns the right length
	 */
	@Test
	public void testLength() {
		Vector v1 = new Vector(5,6,7);
		assertEquals(v1.length(), 10.4880884817, 1e-10);
	}

	/**
	 * Test method for {@link primitives.Vector#squareLength()}.
	 * testing if returns the right square length
	 */
	@Test
	public void testSquareLength() {
		Vector v1 = new Vector(5,6,7);
		assertEquals(v1.squareLength(), 110, 1e-10);
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 * testing if returns the right normal 
	 */
	@Test
	public void testNormalize() {
		Vector v1 = new Vector(5,6,7);
		Vector v2 = v1.normalize();
		assertEquals(1, v2.length(), 1e-10);
	}

}
