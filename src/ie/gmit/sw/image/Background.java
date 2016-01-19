package ie.gmit.sw.image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Background {
	/**
	 * Draws a background to display the word cloud against.
	 * The Image height and width is defined in the runner class.
	 */
    public Background() {
		super();
	}

	public void drawBackground(BufferedImage image, int imageWidth, int imageHeight) {
        final Graphics graphics = image.getGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, imageWidth, imageHeight);
    }
}
