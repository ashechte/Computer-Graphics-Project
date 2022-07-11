package unittests;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

public class RenderTest {
	/**
	 * Drawing of 1 sphere and 4 triangles
	 * @throws InterruptedException 
	 */
	@Test
	public void basicRendering() throws InterruptedException{
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene.setDistance(250);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 0), 1.0));
		Geometries geometries = new Geometries();
		scene.addGeometries(geometries);
		geometries.add(new Sphere(50, new Point3D(0, 0, 150)));

		geometries.add(new Triangle(new Point3D( 100, 0, 149),
				 					new Point3D(  0, 100, 149),
				 					new Point3D( 100, 100, 149)));

		geometries.add(new Triangle(new Point3D( 100, 0, 149),
				 			 		new Point3D(  0, -100, 149),
				 			 		new Point3D( 100,-100, 149)));

		geometries.add(new Triangle(new Point3D(-100, 0, 149),
				 					new Point3D(  0, 100, 149),
				 					new Point3D(-100, 100, 149)));

		geometries.add(new Triangle(new Point3D(-100, 0, 149),
				 			 		new Point3D(  0,  -100, 149),
				 			 		new Point3D(-100, -100, 149)));

		ImageWriter imageWriter = new ImageWriter("test0", 500, 500, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.printGrid(50);
		render.writeToImage();
	}
	



	@Test
	public void ccc() throws InterruptedException {
		Scene scene2 = new Scene("test 2");
		// add the camera to the scene
		scene2.setCamera(new Camera(new Point3D(200, 150, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene2.setDistance(80);
		// add the background to the scene
		scene2.setBackground(new Color(255, 255, 255));
		Geometries geometries1 = new Geometries();

		// add all the shapes to the scene
		scene2.addGeometries(geometries1);
		//geometries1.add(new Triangle(new Point3D(90, 210, 149), new Point3D(310, 210, 149), new Point3D(200, 30, 149)));

		//geometries1.add(new Triangle(new Point3D(90, 90, 149), new Point3D(310, 90, 149), new Point3D(200, 270, 149)));
		geometries1.add(new Triangle(new Point3D(200, 30, 149), new Point3D(90, 210, 149), new Point3D(100, 210, 149)));
		geometries1.add(new Triangle(new Point3D(200, 30, 149), new Point3D(200, 50, 149), new Point3D(100, 210, 149)));
		
		geometries1.add(new Triangle(new Point3D(200, 30, 149), new Point3D(310, 210, 149), new Point3D(300, 210, 149)));
		geometries1.add(new Triangle(new Point3D(300, 210, 149), new Point3D(200, 50, 149), new Point3D(200, 30, 149)));

		geometries1.add(new Triangle(new Point3D(90, 210, 149), new Point3D(100, 200, 149), new Point3D(310, 210, 149)));
		geometries1.add(new Triangle(new Point3D(300, 200, 149), new Point3D(100, 200, 149), new Point3D(300, 210, 149)));

		geometries1.add(new Triangle(new Point3D(90, 90, 149), new Point3D(100, 90, 149), new Point3D(200, 270, 149)));
		geometries1.add(new Triangle(new Point3D(200, 270, 149), new Point3D(200, 248, 149), new Point3D(100, 90, 149)));

		geometries1.add(new Triangle(new Point3D(200, 253, 149), new Point3D(300, 90, 149), new Point3D(310, 90, 149)));
		geometries1.add(new Triangle(new Point3D(200, 270, 149), new Point3D(200, 253, 149), new Point3D(310, 90, 149)));

		geometries1.add(new Triangle(new Point3D(90, 90, 149), new Point3D(100, 100, 149), new Point3D(300, 100, 149)));
		geometries1.add(new Triangle(new Point3D(90, 90, 149), new Point3D(310, 90, 149), new Point3D(300, 100, 149)));


		geometries1.add(
				new Triangle(new Point3D(-100, -10, 149), new Point3D(-100, -40, 149), new Point3D(500, -40, 149)));

		geometries1
				.add(new Triangle(new Point3D(-100, -10, 149), new Point3D(500, -10, 149), new Point3D(500, -40, 149)));

		geometries1.add(
				new Triangle(new Point3D(-100, 310, 149), new Point3D(-100, 340, 149), new Point3D(500, 340, 149)));

		geometries1
				.add(new Triangle(new Point3D(-100, 310, 149), new Point3D(500, 310, 149), new Point3D(500, 340, 149)));

		// add the dimensions to the scene
		ImageWriter imageWriter1 = new ImageWriter("test880", 500, 500, 500, 500);
		// add the image writer and scene to the render
		Render render1 = new Render(imageWriter1, scene2);

		// add the ambient light to the scene
		AmbientLight b = new AmbientLight(new Color(0, 0, 255), 10);
		scene2.setAmbientLight(b);

		// Builds the image
		render1.renderImage();

		imageWriter1.writeToImage();
		// render.writeToImage();
	}
	
//	/**
//	 * func to test func Render.getClosestPoint
//	 */
//	@Test
//	public  void getClosestPointTest(){
//		List<Point3D> intersectionPoints= new ArrayList<Point3D>();
//		intersectionPoints.add(new Point3D(0, 0, 3));
//		intersectionPoints.add(new Point3D(0, 0, 4));
//		intersectionPoints.add(new Point3D(0, 3, 3));
//		intersectionPoints.add(new Point3D(3, 3, 3));
//		
//		Scene scene = new Scene("Test scene");
//		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)),100);
//		scene.setAmbientLight(new AmbientLight(new Color(0, 200, 100), 3));
//		scene.setBackground(new Color(0, 0, 0));
//		Geometries geometries = new Geometries();
//		scene.addGeometries(geometries);
//		
//		ImageWriter imageWriter = new ImageWriter("test0", 500, 500, 500, 500);
//		Render render = new Render(imageWriter, scene);
//
//		Point3D p=render.getClosestPoint(intersectionPoints);
//		assertEquals(new Point3D(0, 0, 3), p);	
//	}
	
	@Test
	public void basicRendering1() throws InterruptedException{
		Scene scene = new Scene("Test scene2");
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene.setDistance(250);
		scene.setBackground(new Color(0, 0, 0));
		scene.setAmbientLight(new AmbientLight(new Color(15,15,15), 1.0));
		Geometries geometries = new Geometries();
		scene.addGeometries(geometries);
		geometries.add(new Sphere(new Color(100, 100,100 ), 50, new Point3D(0, 0, 150)));

		geometries.add(new Triangle(new Color(0, 255,0 ), new Point3D( 100, 0, 149),
				 							new Point3D(  0, 100, 149),
				 							new Point3D( 100, 100, 149)));

		geometries.add(new Triangle(new Color(255, 0,0 ), new Point3D( 100, 0, 149),
				 			 				new Point3D(  0, -100, 149),
				 			 				new Point3D( 100,-100, 149)));

		geometries.add(new Triangle(new Color(0, 0,255 ), new Point3D(-100, 0, 149),
				 							new Point3D(  0, 100, 149),
				 							new Point3D(-100, 100, 149)));

		geometries.add(new Triangle(new Point3D(-100, 0, 149),
				 			 				new Point3D(  0,  -100, 149),
				 			 				new Point3D(-100, -100, 149)));

		ImageWriter imageWriter = new ImageWriter("test01235", 500, 500, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.printGrid(50);
		render.writeToImage();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}