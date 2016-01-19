package ie.gmit.sw.image;

import java.awt.Color;
import java.util.Random;

public class Colour {
	/**
	 * Creates a random colour.
	 * Continues to call randomise until
	 * the colour is bright enough.
	 */
	
	public Colour() {
		super();
	}

	public Color newColour() {
		float[] colours = new float[3];

		do {
			colours = randColour();
		} while (colours[0] + colours[1] + colours[2] < .5);
		
		Color c = new Color(colours[0], colours[1], colours[2]);
		
		return c;
	}
	
	private float[] randColour() {
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		
		float[] colours = new float[3];
		
		colours[0] = r; colours[1] = g; colours[2] = b;
		return colours;
	}
	
}