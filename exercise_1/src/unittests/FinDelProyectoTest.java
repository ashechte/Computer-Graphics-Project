package unittests;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

public class FinDelProyectoTest {

	Geometries createBearRL(int r, int u, int t) {
		Sphere sphere = new Sphere(new Material(0.5,0.5,0,0,20), new Color(150, 100, 76), 70, new Point3D(0+r, 0+u, 250+t));
		Sphere sphere2 = new Sphere(new Material(0.5,0.5,0,0,20), new Color(0, 0, 0), 35, new Point3D(60+r, -85+u, 250+t));
		Sphere sphere3 = new Sphere(new Material(0.5,0.5,0,0,20), new Color(0, 0, 0), 35, new Point3D(-60+r, -85+u, 250+t));
		Sphere sphere4 = new Sphere(new Material(0.5,0.5,0,0,20), new Color(0, 0, 0), 15, new Point3D(0+r, 0+u, 165+t));
		Sphere sphere5 = new Sphere(new Material(0.5,0.5,0,0,20), new Color(133, 133, 133), 15, new Point3D(15+r, -30+u, 195+t));
		Sphere sphere6 = new Sphere(new Material(0.5,0.5,0,0,20), new Color(133, 133, 133), 15, new Point3D(-15+r, -30+u, 195+t));
		Sphere sphere7 = new Sphere(new Material(0.5,0.5,0,0,20), new Color(0, 0, 0), 5, new Point3D(15+r, -30+u, 180+t));
		Sphere sphere8 = new Sphere(new Material(0.5,0.5,0,0,20), new Color(0, 0, 0), 5, new Point3D(-15+r, -30+u, 180+t));
		//Papillon
		Triangle triangle7 = new Triangle(new Material(0.5,0.5,50), 
		 new Color (0, 150, 0),
		 new Point3D(-10+r, 80+u, 180+t),
		 new Point3D(70+r, 95+u, 180+t),
		 new Point3D(70+r, 65+u, 180+t));
		Triangle triangle8 = new Triangle(new Material(0.5,0.5,50), 
		 new Color (0, 150, 0),
		 new Point3D(10+r, 80+u, 180+t),
		 new Point3D(-70+r, 95+u, 180+t),
		 new Point3D(-70+r, 65+u, 180+t));
		//2 Triangles of Smile
		Triangle triangle1 = new Triangle(new Material(0.5,0.5,50), 
		 new Color (186, 0, 0),
		 new Point3D(30+r, 25+u, 180+t),
		 new Point3D(-20+r, 30+u, 180+t),
		 new Point3D(30+r, 15+u, 180+t));
		Triangle triangle2 = new Triangle(new Material(0.5,0.5,50), 
		 new Color (186, 0, 0),
		 new Point3D(-30+r, 25+u, 180+t),
		 new Point3D(20+r, 30+u, 180+t),
		 new Point3D(-30+r, 15+u, 180+t));
		Geometries g = new Geometries(sphere,sphere2,sphere3,sphere4,sphere5,sphere6, sphere7,sphere8,triangle7,triangle8,triangle1,triangle2);
		return g;
	}
	
	
	
	
	@Test
	public void finalDeLaFinale() throws InterruptedException {
	Scene scene = new Scene("scene 1");
	scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -1000), new Vector(0,-1,0), new Vector(0, 0, 1)),500);
	scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
	scene.setBackground(Color.BLACK);
	Geometries leftUP2x2 = new Geometries(createBearRL(-400,-400,1000),createBearRL(-400,-200,1000),createBearRL(-200,-400,1000),createBearRL(-200,-200,1000));
	Geometries leftDown2x2 = new Geometries(createBearRL(-400,400,1000),createBearRL(-400,200,1000),createBearRL(-200,400,1000),createBearRL(-200,200,1000));
	Geometries rightUp2x2 = new Geometries(createBearRL(400,-400,1000),createBearRL(200,-400,1000),createBearRL(400,-200,1000),createBearRL(200,-200,1000));
	Geometries rightDown2x2 = new Geometries(createBearRL(400,400,1000),createBearRL(200,400,1000),createBearRL(400,200,1000),createBearRL(200,200,1000));
	Geometries midUp2x1 = new Geometries(createBearRL(0,-400, 1000),createBearRL(0,-200, 1000));
	Geometries midDown2x1 = new Geometries(createBearRL(0,400, 1000),createBearRL(0,200,1000));
	Geometries leftMid1x2 = new Geometries(createBearRL(-400,0,1000),createBearRL(-200,0,1000));
	Geometries rightMid1x2 = new Geometries(createBearRL(400,0,1000),createBearRL(200,0,1000));
	Geometries midMid1x1 = new Geometries(createBearRL(0,0,1000));
	Geometries sum1 = new Geometries(leftUP2x2,leftMid1x2);
	Geometries sum2 = new Geometries(leftDown2x2,midDown2x1);
	Geometries sum3 = new Geometries(rightDown2x2,rightMid1x2);
	Geometries sum4 = new Geometries(rightUp2x2,midUp2x1);
	Geometries sumSum1 = new Geometries(sum1,sum2);
	Geometries sumSum2 = new Geometries(sum3, sum4);
	Geometries head =new Geometries(sumSum1,midMid1x1,sumSum2);
	scene.addGeometries(head);
	scene.addLights(new PointLight(new Color(100, 100, 100), new Point3D(0, -1000 ,0),
	  1, 0.000001, 0.0000005));
	ImageWriter imageWriter = new ImageWriter("finale", 500, 500, 1000, 1000);
	Render render = new Render(imageWriter, scene, true, true);
	render.renderImage();
	render.writeToImage();
	
	}

}
