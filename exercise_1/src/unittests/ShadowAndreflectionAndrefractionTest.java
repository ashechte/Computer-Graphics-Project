package unittests;


import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

public class ShadowAndreflectionAndrefractionTest {

	@Test
	public void shadowTest1() throws InterruptedException {
		Scene scene = new Scene("scene 1");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -1000), new Vector(0,-1,0), new Vector(0, 0, 1)),1000);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.addGeometries(geometries);
		Sphere sphere = new Sphere(new Material(0.5,0.5,20), new Color(0, 0, 255), 150, new Point3D(0.0, 0.0, 400));
		scene.addGeometries(sphere);
		
		Triangle triangle = new Triangle(new Material(0.5,0.5,20), 
										 new Color (0, 0, 255),
										 new Point3D(70, 125, 50),
										 new Point3D(145, 50, 50),
										 new Point3D(145, 125, 50));
		scene.addGeometries(triangle);
		
		scene.addLights(new SpotLight(new Color(255, 150, 150), new Point3D(250, 200, -350), 
					   new Vector(-2, -2, 3), 0, 0.000001, 0.0000005,1));
	
		ImageWriter imageWriter = new ImageWriter("shadow test 1", 500, 500, 2000, 2000);
		
		Render render = new Render(imageWriter, scene);
		
		render.renderImage();
		render.writeToImage();
	}
	
	
	@Test
	public void shadowTest2() throws InterruptedException {
		Scene scene = new Scene("scene 1");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -1000), new Vector(0,-1,0), new Vector(0, 0, 1)),1000);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.addGeometries(geometries);
		Sphere sphere = new Sphere(new Material(0.5,0.5,20), new Color(0, 0, 255), 125, new Point3D(0.0, 0.0, 400));
		scene.addGeometries(sphere);
		
		Triangle triangle = new Triangle(new Material(0.5,0.5,20), 
										 new Color (0, 0, 255),
										 new Point3D(50, 100, 50),
										 new Point3D(125, 25, 50),
										 new Point3D(125, 100, 90));
		scene.addGeometries(triangle);
		
		scene.addLights(new SpotLight(new Color(255, 255, 0), new Point3D(250, 200, -300), 
					   new Vector(-2, -2, 3), 0, 0.000001, 0.0000005,1));
	
		ImageWriter imageWriter = new ImageWriter("shadow test 2", 500, 500, 2000, 2000);
		
		Render render = new Render(imageWriter, scene);
		
		render.renderImage();
		render.writeToImage();
	}
	
	/**
	 * moving the light
	 * @throws InterruptedException 
	 */
	@Test
	public void shadowTest3() throws InterruptedException {
		Scene scene = new Scene("scene 1");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -1000), new Vector(0,-1,0), new Vector(0, 0, 1)),1000);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.addGeometries(geometries);
		Sphere sphere = new Sphere(new Material(0.5,0.5,20), new Color(0, 0, 255), 150, new Point3D(0.0, 0.0, 400));
		scene.addGeometries(sphere);
		
		Triangle triangle = new Triangle(new Material(0.5,0.5,100), 
										 new Color (0, 0, 100),
										 new Point3D(0, 0, 100),
										 new Point3D(70, -100, 100),
										 new Point3D(-70, -120, 100));
		scene.addGeometries(triangle);
		
		scene.addLights(new SpotLight(new Color(255, 255, 0), new Point3D(0, -140, -90), 
					   new Vector(-2, 2, 3), 0, 0.000001, 0.0000005,1));
	
		ImageWriter imageWriter = new ImageWriter("shadow test 3", 500, 500, 2000, 2000);
		
		Render render = new Render(imageWriter, scene);
		
		render.renderImage();
		render.writeToImage();
	}
	
	
	/**
	 * moving the light
	 * @throws InterruptedException 
	 */
	@Test
	public void shadowTest4() throws InterruptedException {
		Scene scene = new Scene("scene 1");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -1000), new Vector(0,-1,0), new Vector(0, 0, 1)),1000);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.addGeometries(geometries);
		Sphere sphere = new Sphere(new Material(0.5,0.5,20), new Color(0, 0, 255), 150, new Point3D(0.0, 0.0, 400));
		scene.addGeometries(sphere);
		
		Triangle triangle = new Triangle(new Material(0.5,0.5,100), 
										 new Color (0, 0, 100),
										 new Point3D(70, 100, 100),
										 new Point3D(145, 25, 100),
										 new Point3D(145, 100, 109));
		scene.addGeometries(triangle);
		
		scene.addLights(new SpotLight(new Color(255, 255, 0), new Point3D(150, 110, 10), 
					   new Vector(-2, -2, 3), 0, 0.000001, 0.0000005,1));
	
		ImageWriter imageWriter = new ImageWriter("shadow test 4", 500, 500, 2000, 2000);
		
		Render render = new Render(imageWriter, scene);
		
		render.renderImage();
		render.writeToImage();
	}
	
	
	@Test
	public void shadowTest5() throws InterruptedException  {
		Scene scene = new Scene("scene 1");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -1000), new Vector(0,-1,0), new Vector(0, 0, 1)),1000);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Sphere sphere = new Sphere(new Material(0.0,0.4,0.6,0,100), new Color(0, 0, 100), 70, new Point3D(-50, 100, 300));
		Sphere sphere2 = new Sphere(new Material(0.5,0.5,0,0,100), new Color(100, 0, 200), 35, new Point3D(-50, 100, 300));
		Triangle triangle = new Triangle(new Material(0,0,0.2,1,100), 
										 new Color (0, 0, 0),
										 new Point3D(-150, -300, 1000),
										 new Point3D(-150, 200, 1000),
										 new Point3D(350, 200, 1000));
		
		Triangle triangle2 = new Triangle(new Material(0,0,0.2,1,100), 
										  new Color (0, 0, 0),
										  new Point3D(-150, -300, 1000),
										  new Point3D(350, 200, 1000),
										  new Point3D(200, -50, -1000));
		
		
		scene.addGeometries(triangle,triangle2,sphere,sphere2);
		scene.addLights(new PointLight(new Color(200, 100, 100), new Point3D(-1000, 1000 ,650),
				   1, 0.000001, 0.0000005));
		ImageWriter imageWriter = new ImageWriter("shadow test 5", 500, 500, 1000, 1000);
		
		Render render = new Render(imageWriter, scene);
		
		render.renderImage();
		render.writeToImage();
	}
	
	
	
	@Test
	public void shadowTest6() throws InterruptedException {
		Scene scene = new Scene("scene 1");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -1000), new Vector(0,-1,0), new Vector(0, 0, 1)),600);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Sphere sphere = new Sphere(new Material(0.5,0.5,0,0,20), new Color(150, 100, 76), 70, new Point3D(0, 0, 250));
		Sphere sphere2 = new Sphere(new Material(0.5,0.5,0,0,20), new Color(0, 0, 0), 35, new Point3D(60, -85, 250));
		Sphere sphere3 = new Sphere(new Material(0.5,0.5,0,0,20), new Color(0, 0, 0), 35, new Point3D(-60, -85, 250));
		Sphere sphere4 = new Sphere(new Material(0.5,0.5,0,0,20), new Color(0, 0, 0), 15, new Point3D(0, 0, 165));
		Sphere sphere5 = new Sphere(new Material(0.5,0.5,0,0,20), new Color(133, 133, 133), 15, new Point3D(15, -30, 195));
		Sphere sphere6 = new Sphere(new Material(0.5,0.5,0,0,20), new Color(133, 133, 133), 15, new Point3D(-15, -30, 195));
		Sphere sphere7 = new Sphere(new Material(0.5,0.5,0,0,20), new Color(0, 0, 0), 5, new Point3D(15, -30, 180));
		Sphere sphere8 = new Sphere(new Material(0.5,0.5,0,0,20), new Color(0, 0, 0), 5, new Point3D(-15, -30, 180));	
		Triangle triangle1 = new Triangle(new Material(0.5,0.5,50), 
										  new Color (186, 0, 0),
										  new Point3D(30, 25, 180),
										  new Point3D(-20, 30, 180),
										  new Point3D(30, 15, 180));
		Triangle triangle3 = new Triangle(new Material(0.5,0.5,50), 
										  new Color (186, 0, 0),
										  new Point3D(-30, 25, 180),
										  new Point3D(20, 30, 180),
										  new Point3D(-30, 15, 180));
		
		Rectangle rectangle = new Rectangle(new Point3D(100,-100,-1000), 
											new Point3D(100, 100, -1000), 
											new Point3D(100,-100,300), 
											new Color(0, 0, 0), 
											new Material(0, 0, 0,1,0));
		
		Rectangle rectangle2 = new Rectangle(new Point3D(-100,-100,-1000), 
											 new Point3D(-100, 100, -1000), 
											 new Point3D(-100,-100,300), 
											 new Color(0, 0, 0), 
											 new Material(0, 0, 0,1,0));
	//	-----------------------------------------------------------------------------------------------
		Triangle triangle8 = new Triangle(new Material(0.5,0.5,50), 
									 	  new Color (0, 150, 0),
									 	  new Point3D(-10, 80, 180),
									 	  new Point3D(70, 95, 180),
									 	  new Point3D(70, 65, 180));
		Triangle triangle9 = new Triangle(new Material(0.5,0.5,50), 
										  new Color (0, 150, 0),
										  new Point3D(10, 80, 180),
										  new Point3D(-70, 95, 180),
										  new Point3D(-70, 65, 180));
			
	//   ---------------------------------------------------------------------------------------------	
		scene.addGeometries(sphere,sphere2,sphere3,sphere4,triangle1, triangle3,sphere5,sphere6,rectangle,rectangle2,triangle8,triangle9, sphere7,sphere8);
		//scene.addLights(new PointLight(new Color(50, 50, 50), new Point3D(0, 0 ,0),
		//		   1, 0.000001, 0.0000005));
		scene.addLights(new PointLight(new Color(100, 100, 100), new Point3D(0, -1000 ,0),
				   1, 0.000001, 0.0000005));
		ImageWriter imageWriter = new ImageWriter("shadow test 6", 500, 500, 1000, 1000);
		
		Render render = new Render(imageWriter, scene);
		
		render.renderImage();
		render.writeToImage();
	}
	
	@Test
	public void shadowTest7() throws InterruptedException {
		Scene scene = new Scene("scene 1");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -1000), new Vector(0,-1,0), new Vector(0, 0, 1)),1000);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.addGeometries(geometries);
		Sphere sphere = new Sphere(new Material(0.5,0.5,20), new Color(0, 0, 255), 150, new Point3D(0.0, 0.0, 450));
		scene.addGeometries(sphere);
		
		Triangle triangle = new Triangle(new Material(0.5,0.5,20), 
										 new Color (0, 0, 255),
										 new Point3D(90, 150, 15),
										 new Point3D(165, 75, 15),
										 new Point3D(165, 150, 15));
		scene.addGeometries(triangle);
		
		scene.addLights(new SpotLight(new Color(255, 150, 150), new Point3D(250, 200, -300), 
					   new Vector(-2, -2, 3), 0, 0.000001, 0.0000005,1));
	
		ImageWriter imageWriter = new ImageWriter("shadow test 10", 500, 500, 1000, 1000);
		
		Render render = new Render(imageWriter, scene);
		
		render.renderImage();
		render.writeToImage();
	}
	
	
	@Test
	public void shadowTest8() throws InterruptedException{
		
		Scene scene = new Scene("scene 5");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, 0), new Vector(1,0,0), new Vector(0, 0, -1)),100);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.addGeometries(geometries);
		Sphere sphere = new Sphere(new Material(0.6,0.6,50), new Color(0, 0, 100), 500, new Point3D(0.0, 0.0, -1000));;
		
		Triangle triangle = new Triangle(new Material(0.3,0.7,0,0,170),
										 new Color(0,100,0), 
										 new Point3D(  3500,  3500, -2000),
				 						 new Point3D( -3500, -3500, -1000),
				 						 new Point3D(  3500, -3500, -2000));

		Triangle triangle2 = new Triangle(new Material(0.3,0.7,0,0,170),
										  new Color(0,100,0),
										  new Point3D(  3500,  3500, -2000),
				  						  new Point3D( -3500,  3500, -1000),
				  						  new Point3D( -3500, -3500, -1000));
		scene.addGeometries(sphere, triangle, triangle2);
		//scene.addLights(new SpotLight(new Color(255, 150, 150), new Point3D(-350, -500, -150), 
			//   new Vector(2, 2, -3), 0, 0.000001, 0.0000005,1));
		scene.addLights(new DirectionalLight(new Color(255, 255,100), new Vector(2,-2,-1)));

		ImageWriter imageWriter = new ImageWriter("Light With Sphera", 500, 500, 500, 500);
		Render render = new Render(imageWriter, scene);
		
		render.renderImage();
		render.writeToImage();
	}
	
	
	/**
	 * sphere && spot && point light
	 * @throws InterruptedException 
	 */
	@Test
	public void shadowTest9() throws InterruptedException {

		Scene scene = new Scene("scene with sphere && triangles");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, 0), new Vector(1, 0, 0), new Vector(0, 0, -1)), 100);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.addGeometries(geometries);

		Triangle triangle = new Triangle(new Material(0.5, 0.5, 20), new Color(15, 15, 15),
				new Point3D(3500, 3500, -2000), new Point3D(-3500, -3500, -1000), new Point3D(3500, -3500, -2000));
		Triangle triangle2 = new Triangle(new Material(0.5, 0.5, 20), new Color(15, 15, 15),
				new Point3D(3500, 3500, -2000), new Point3D(-3500, 3500, -1000), new Point3D(-3500, -3500, -1000));
		Sphere sphere = new Sphere(new Material(0.5, 0.5, 20), new Color(0, 0, 100), 550,
				new Point3D(200, -200, -1000));
		scene.addGeometries(triangle, triangle2, sphere);

		//scene.addLights(new PointLight(new Color(255, 100, 100), new Point3D(-200, -200, -500),0,  0.000001, 0.000001));
	//	scene.addLights(new SpotLight(new Color(0, 200, 200), new Point3D(400, -200, -300),new Vector(-200, 0, -700),0,  0.0001, 0.000000001,1));
		scene.addLights(new DirectionalLight(new Color(255,255,255),new Vector(-2, -5, -1)));

		ImageWriter imageWriter = new ImageWriter("משולשים וכדור", 500, 500, 1000, 1000);

		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
	}
}
