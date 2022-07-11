package unittests;


import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;
public class LightningTest {
	
	/**
	 * Test for point light (for shadow)
	 */
//@Test
//public void spotLightTest2(){
//	Scene scene = new Scene("scene 1");
//	scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -100), new Vector(1,0,0), new Vector(0, 0, -1)),100);
//	scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
//	scene.setBackground(Color.BLACK);
//	Geometries geometries = new Geometries();
//	scene.addGeometries(geometries);
//	Sphere sphere = new Sphere(new Material(0.5,0.5,20), new Color(0, 0, 100), 500, new Point3D(0.0, 0.0, -1000));
//	scene.addGeometries(sphere);
//	
//	Triangle triangle = new Triangle(new Material(0.5,0.5,20), 
//									 new Color (0, 0, 100),
//									 new Point3D(-125, -225, -260),
//									 new Point3D(-225, -125, -260),
//									 new Point3D(-225, -225, -270));
//	scene.addGeometries(triangle);
//	
//	scene.addLights(new SpotLight(new Color(255, 255, 100), new Point3D(-200, -200, -150), 
//				   new Vector(2, 2, -3), 0, 0.000001, 0.0000005,1));
//
//	ImageWriter imageWriter = new ImageWriter("Spot test 2.0", 500, 500, 500, 500);
//	
//	Render render = new Render(imageWriter, scene);
//	
//	render.renderImage();
//	render.writeToImage();
//}
	
	/**
	 * Test for spot light
	 * @throws InterruptedException 
	 */
	@Test
	public void spotLightTest() throws InterruptedException{
		
		Scene scene = new Scene("scene 2");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, 0), new Vector(1,0,0), new Vector(0, 0, -1)),100);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.addGeometries(geometries);
		Sphere sphere = new Sphere(new Material(0.5,0.5,20), new Color(0, 0, 100), 800, new Point3D(0.0, 0.0, -1000));
		scene.addGeometries(sphere);
		scene.addLights(new SpotLight(new Color(255, 255, 200), new Point3D(-180, -150, -150), 
					   new Vector(0, 0, -3), 0, 0.000001, 0.0000005,1));
		
		ImageWriter imageWriter = new ImageWriter("Spot test", 500, 500, 500, 500);
		
		Render render = new Render(imageWriter, scene);
		
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Another test for point light 
	 * @throws InterruptedException 
	 */
	@Test
	public void pointLightTest() throws InterruptedException{
		
		Scene scene = new Scene("scene 3");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -100), new Vector(1,0,0), new Vector(0, 0, -1)),100);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.addGeometries(geometries);
		Sphere sphere = new Sphere (new Material(0.5,0.5,20), new Color(0, 0, 100), 800, new Point3D(0.0, 0.0, -1000));
		scene.addGeometries(sphere);
		scene.addLights(new PointLight(new Color(255,100,100), new Point3D(100, -200, -100), 
					   0, 0.000001, 0.0000005));
	
		ImageWriter imageWriter = new ImageWriter("Point test", 500, 500, 500, 500);
		
		Render render = new Render(imageWriter, scene);
		
		render.renderImage();
		render.writeToImage();
	}
	
	/**
	 * Another test for spot light
	 * @throws InterruptedException 
	 */
	@Test
	public void spotLightTest3() throws InterruptedException{
		
		Scene scene = new Scene("scene 4");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, 0), new Vector(1,0,0), new Vector(0, 0, -1)),100);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.addGeometries(geometries);
		Triangle triangle = new Triangle(new Material(0.5,0.5,20),
										 new Color(0,0,0), 
										 new Point3D(  3500,  3500, -2000),
				 						 new Point3D( -3500, -3500, -1000),
				 						 new Point3D(  3500, -3500, -2000));

		Triangle triangle2 = new Triangle(new Material(0.5,0.5,20), 
										  new Color(0,0,0), 
										  new Point3D(  3500,  3500, -2000),
				  						  new Point3D( -3500,  3500, -1000),
				  						  new Point3D( -3500, -3500, -1000));
		
		scene.addGeometries(triangle);
		scene.addGeometries(triangle2);
		
		scene.addLights(new SpotLight(new Color(255, 0, 0), new Point3D(200, -200, -100), 
					   new Vector(-2, 0, -30), 0, 0.000001, 0.0000005, 1));
	
		
		ImageWriter imageWriter = new ImageWriter("Spot test 2", 500, 500, 500, 500);
		
		Render render = new Render(imageWriter, scene);
		
		render.renderImage();
		render.writeToImage();
	}
	/**
	 * Test for point light sphere on 2 triangles 
	 * @throws InterruptedException 
	 */
	@Test
	public void pointAndSpotLightTest() throws InterruptedException{
		
		Scene scene = new Scene("scene 5");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, 0), new Vector(1,0,0), new Vector(0, 0, -1)),100);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.addGeometries(geometries);
		Sphere sphere = new Sphere(new Material(0.5,0.5,0,0,170), new Color(0, 0, 100), 500, new Point3D(0.0, 0.0, -1000));;
		
		Triangle triangle = new Triangle(new Material(0.5,0.5,50),
										 new Color(0,0,0), 
										 new Point3D(  3500,  3500, -2000),
				 						 new Point3D( -3500, -3500, -1000),
				 						 new Point3D(  3500, -3500, -2000));

		Triangle triangle2 = new Triangle(new Material(0.5,0.5,50),
										  new Color(0,0,0),
										  new Point3D(  3500,  3500, -2000),
				  						  new Point3D( -3500,  3500, -1000),
				  						  new Point3D( -3500, -3500, -1000));
		scene.addGeometries(sphere, triangle, triangle2);

		
		//scene.addLights(new PointLight(new Color(255, 255, 255), new Point3D(200, 200, -500), 
			//		   1, 0.000001, 0.0000005));
		scene.addLights(new SpotLight(new Color(255, 255, 100), new Point3D(-350, -350, -150), 
				   new Vector(2, 2, -3), 0, 0.000001, 0.0000005,1));
		//scene.addLights(new DirectionalLight(new Color(100, 255,100), new Vector(2,-2,-1)));
		ImageWriter imageWriter = new ImageWriter("Point and spot", 500, 500, 1000, 1000);
		Render render = new Render(imageWriter, scene);
		
		render.renderImage();
		render.writeToImage();
	}
	/**
	 * This test is for Japan 
	 * @throws InterruptedException 
	 */
	@Test
	public void japanTest() throws InterruptedException{
		
		Scene scene = new Scene("japaneese scene");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, 0), new Vector(0,-1,0), new Vector(0, 0, 1)),
				100);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Triangle triangle = new Triangle(new Material(0.5, 0.5, 20), new Color(255,255, 255),
				new Point3D(-350,-200, 150), new Point3D(350, -200, 150), new Point3D(350, 200, 150));
		Triangle triangle2 = new Triangle(new Material(0.5, 0.5, 20), new Color(255, 255, 255),
				new Point3D(-350,-200, 150), new Point3D(-350,200,150), new Point3D(350, 200, 150));
		Sphere sphere = new Sphere(new Material(0.5, 0.5, 20),new Color(255, 0, 0),85,new Point3D(0,0,150));
		scene.addGeometries(triangle,triangle2,sphere);
		
		//scene.addLights(new PointLight(new Color(0, 100, 100), new Point3D(-200, -200, -100), 
			//	 1, 0.00001, 0.000005));
		scene.addLights(new SpotLight(new Color(255,255,255), new Point3D(0,100,0), new Vector(0,-100,100),
				 1, 0.00001, 0.000005,1));
	
		ImageWriter imageWriter = new ImageWriter("bela ciao", 500, 500, 500, 500);
		
		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
	}
	
	/**
	 * This test is for PACMAN 
	 * @throws InterruptedException 
	 */
	@Test
	public void pacmanTest() throws InterruptedException {
		Scene scene = new Scene("pacman scene");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -1000), new Vector(0,-1,0), new Vector(0, 0, 1)), 1000);
		scene.setAmbientLight(new AmbientLight(Color.BLACK, 3));
		scene.setBackground(Color.BLACK);
		
		Sphere food = new Sphere(new Material(0.5, 0.5, 20),new Color(51, 204, 255), 5, new Point3D(105, 0, 49));
		Sphere food2 = new Sphere(new Material(0.5, 0.5, 20),new Color(51, 204, 255), 5, new Point3D(155, 0, 49));
		Sphere food3 = new Sphere(new Material(0.5, 0.5, 20),new Color(51, 204, 255), 5, new Point3D(205, 0, 49));
		Sphere sphere = new Sphere(new Material(0.5, 0.5, 20),new Color(255, 255, 0),80,new Point3D(0,0,150));
		Triangle t = new Triangle(new Material(0,0,0), scene.getBackground(), new Point3D(0,0,49), new Point3D(75,60,49), new Point3D(75, -60, 49));
		scene.addGeometries(sphere, t, food, food2,food3);
		
		scene.addLights(new SpotLight(new Color(0,0,255), new Point3D(0,-100, 0), new Vector(0,100,100),
				 1, 0.00001, 0.000005,1));
	
		ImageWriter imageWriter = new ImageWriter("pac", 500, 500, 500, 500);
		
		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
	}	
	@Test
	public void spheraWithSphera() throws InterruptedException{
		
		Scene scene = new Scene("scene 5");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, 0), new Vector(1,0,0), new Vector(0, 0, -1)),100);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.addGeometries(geometries);
		Sphere sphere = new Sphere(new Material(0.5,0.5,0,0,170), new Color(0, 0, 100), 500, new Point3D(0.0, 0.0, -1000));
		Sphere sphere2 = new Sphere(new Material(0.5,0.5,0,0,170), new Color(255, 0, 0), 200, new Point3D(0.0, 0.0, -1000));
		Triangle triangle = new Triangle(new Material(0.5,0.5,50),
										 new Color(0,0,0), 
										 new Point3D(  3500,  3500, -2000),
				 						 new Point3D( -3500, -3500, -1000),
				 						 new Point3D(  3500, -3500, -2000));

		Triangle triangle2 = new Triangle(new Material(0.5,0.5,50),
										  new Color(0,0,0),
										  new Point3D(  3500,  3500, -2000),
				  						  new Point3D( -3500,  3500, -1000),
				  						  new Point3D( -3500, -3500, -1000));
		scene.addGeometries(sphere,sphere2, triangle, triangle2);

		
		scene.addLights(new PointLight(new Color(255, 100, 100), new Point3D(200, 200, -500), 
					   1, 0.000001, 0.0000005));
		scene.addLights(new SpotLight(new Color(255, 100, 100), new Point3D(-350, -350, -150), 
				   new Vector(2, 2, -3), 0, 0.000001, 0.0000005,1));
		scene.addLights(new DirectionalLight(new Color(100, 100,100), new Vector(2,-2,-1)));
		ImageWriter imageWriter = new ImageWriter("Sphera with Sphera", 500, 500, 500, 500);
		Render render = new Render(imageWriter, scene);
		
		render.renderImage();
		render.writeToImage();
	}
	@Test
	public void spotLightWithSpheraTest() throws InterruptedException{
		
		Scene scene = new Scene("scene 5");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, 0), new Vector(1,0,0), new Vector(0, 0, -1)),100);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.addGeometries(geometries);
		Sphere sphere = new Sphere(new Material(0.6,0.6,50), new Color(0, 0, 0), 500, new Point3D(0.0, 0.0, -1000));;
		
		Triangle triangle = new Triangle(new Material(0.6,0.6,50),
										 new Color(128,128,128), 
										 new Point3D(  3500,  3500, -2000),
				 						 new Point3D( -3500, -3500, -1000),
				 						 new Point3D(  3500, -3500, -2000));

		Triangle triangle2 = new Triangle(new Material(0.3,0.3,50),
										  new Color(128,128,128),
										  new Point3D(  3500,  3500, -2000),
				  						  new Point3D( -3500,  3500, -1000),
				  						  new Point3D( -3500, -3500, -1000));
		scene.addGeometries(sphere, triangle, triangle2);
		scene.addLights(new PointLight(new Color(255, 255, 255), new Point3D(200, 100, -500), 
				   1, 0.000001, 0.0000005));
		ImageWriter imageWriter = new ImageWriter("Light With Sphera", 500, 500, 500, 500);
		Render render = new Render(imageWriter, scene);
		
		render.renderImage();
		render.writeToImage();
	}
}


