package unittests;



import org.junit.Test;

import elements.AmbientLight;
import primitives.Color;
import renderer.ImageWriter;

public class ImageWriterTest {
/**
 * 	 Building a background with a grid on it, the background is 500 by 500 pixels,
	 and a grid of 10 by 10 squares
 */

	@Test
	public void imageWriterTest() {
		ImageWriter image = new ImageWriter("avner", 10, 10, 500, 500);
		AmbientLight ambientBackground = new AmbientLight(new Color(255, 255, 0), 1.0);
		AmbientLight ambientGrid = new AmbientLight(new Color(5, 5, 5), 1.0);
		// background
		for (int i = 0; i < 500; i++) {
			for (int j = 0; j < 500; j++) {
				image.writePixel(i, j, ambientBackground.getIntensity().getColor());
			}
		}
		// Paints the vertical lines of the grid
		for (int i = 0; i < 500; i += 50) {
			for (int j = 0; j < 500; j++) {
				image.writePixel(i, j, ambientGrid.getIntensity().getColor());
			}
		}
		// Paints the wide lines of the grid
		for (int i = 0; i < 500; i++) {
			for (int j = 0; j < 500; j += 50) {
				image.writePixel(i, j, ambientGrid.getIntensity().getColor());
			}
		}
		// Paints the last wide line of the grid
		for (int i = 0; i < 500; i++) {
			image.writePixel(i, 499, ambientGrid.getIntensity().getColor());
		}
		// Paints the last vertical line of the grid
		for (int i = 0; i < 500; i++) {
			image.writePixel(499, i, ambientGrid.getIntensity().getColor());
		}

		image.writeToImage();
	}

}
