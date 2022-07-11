package scene;

import java.util.ArrayList;
import java.util.List;

import elements.*;
import geometries.*;
import primitives.*;

/**
 * A class that produces a list of all the bodies in the scene with a camera,
 * screen, background and ambient Light
 * 
 * @author User
 *
 */
public class Scene {

	String sceneName;
	Color background;
	AmbientLight ambientLight;
	Geometries model3D;
	List<LightSource> lights;
	Camera camera;
	double distance;

	/*********** Constructor *********/
	/**
	 * A constructor that gets the name of the scene and creates an instance of the
	 * geometries
	 * 
	 * @param sceneName
	 */
	public Scene(String sceneName) {
		this.sceneName = sceneName;
		model3D = new Geometries();
		lights = new ArrayList<LightSource>();
	}

	/************ setters ***************/
	/**
	 * Update the background
	 * 
	 * @param background
	 */
	public void setBackground(Color background) {
		this.background = background;
	}

	/**
	 * Update the ambient Light
	 * 
	 * @param ambientLight
	 */
	public void setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
	}

	/**
	 * Update the camera
	 * 
	 * @param camera
	 */
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	/**
	 * Initializing the list 
	 * @param lights
	 */
	public void setLights(List<LightSource> lights) {
		this.lights = lights;
	}

	/**
	 * Update the distance
	 * 
	 * @param distance
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * Updating camera and distance in one operation
	 * 
	 * @param camera
	 * @param distance
	 */
	public void setCameraAndDistance(Camera camera, double distance) {
		this.camera = camera;
		this.distance = distance;
	}

	/********* getters **********/

	/**
	 * getter
	 * 
	 * @return the scene name
	 */
	public String getSceneName() {
		return this.sceneName;
	}

	/**
	 * getter
	 * 
	 * @return the background
	 */
	public Color getBackground() {
		return this.background;
	}

	/**
	 * getter
	 * 
	 * @return the ambient Light
	 */
	public AmbientLight getAmbientLight() {
		return this.ambientLight;
	}

	/**
	 * getter
	 * 
	 * @return the geometries
	 */
	public Geometries getModel3D() {
		return this.model3D;
	}

	/**
	 * getter
	 * 
	 * @return camera
	 */
	public Camera getCamera() {
		return this.camera;
	}

	/**
	 * getter
	 * 
	 * @return distance
	 */
	public double getDistance() {
		return this.distance;
	}
	
	public List<LightSource> getLights() {
		return lights;
	}

	/************** operations *****************/
	/**
	 * A function that adds bodies in the scene to the list
	 * 
	 * @param bodies
	 */
	public void addGeometries(BoundaryBox... bodies) {
		for (BoundaryBox body : bodies)
			this.model3D.add(body);
	}

	/**
	 * A function that adds lighting sources to the list
	 * @param lights
	 */
	public void addLights(LightSource... lights) {
		for (LightSource lightSource : lights) {
			this.lights.add(lightSource);
		}
		
		
	}
}
