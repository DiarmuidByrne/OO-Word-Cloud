package ie.gmit.sw;

import java.awt.*;
import java.awt.image.*;
import java.awt.Color;
import javax.imageio.*;
import java.io.*;
import java.util.Random;

public class ReallySimpleWordCloud 	{
	public static void main(String args[]) throws Exception{
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 62);
		Color c = new Color(0);
		BufferedImage image = new BufferedImage(600, 300, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.red);
		graphics.setFont(font);
		graphics.drawString("Object-Oriented", 0, 100);
		
		font = new Font(Font.SANS_SERIF, Font.ITALIC, 42);
		graphics.setFont(font);
		
		c = randColour();
		graphics.setColor(c);
		graphics.drawString("Software Development", 10, 150);
		
		font = new Font(Font.MONOSPACED, Font.PLAIN, 22);
		graphics.setFont(font);
		c = randColour();
		graphics.setColor(c);
		graphics.drawString("2012 Assignment", 40, 180);
		
		graphics.dispose();
		ImageIO.write(image, "png", new File("image.png"));
	}
	
	private static Color randColour() {
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		
		Color colour = new Color(r, g, b);
		return colour;
	}
}