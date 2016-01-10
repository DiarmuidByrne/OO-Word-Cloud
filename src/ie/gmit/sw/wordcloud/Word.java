package ie.gmit.sw.wordcloud;

import java.awt.Color;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import ie.gmit.sw.collide.*;

public class Word implements Collidable{
	private final CollisionChecker collisionChecker;
    private String wordString;
    private final Color colour;
    private final FontMetrics fontMetrics;
    private Vector2D position = new Vector2D(0,0);
    private Graphics2D graphics;
 
    private int fontWidth, fontHeight;

    private BufferedImage image;
    
    // Called to create every word and draw to a bufferedImage
	public Word(String word, int wordFreq, Graphics2D graphics, CollisionChecker collisionChecker) {
		this.wordString = word;
        this.collisionChecker = collisionChecker;
        this.graphics = graphics;
   		float[] colours = new float[3];

		do {
			colours = randColour();
		} while (colours[0] + colours[1] + colours[2] < .5);
		
		Color c = new Color(colours[0], colours[1], colours[2]);
		this.colour = c;
		
		// Change font size based on frequency of the word
		// Limit size to between 18 and 80
		int fontSize = (int)(Math.log(wordFreq))*50;
		if(fontSize > 80) {
			fontSize = 80;
		} else if(fontSize < 18) {
			fontSize = 18;
		}
		Font font = new Font(Font.DIALOG_INPUT, Font.BOLD, fontSize);
		
		this.graphics.setFont(font);
		
		FontMetrics f = fontMetrics = this.graphics.getFontMetrics(font);
		
		int fontWidth = f.stringWidth(word);
		int fontHeight = f.getAscent() - (int)(f.getDescent()*1.3);
		this.fontWidth = fontWidth;
		this.fontHeight = fontHeight;
		
		graphics.setColor(c);
		graphics.setFont(font);

	}
	
	public Vector2D getPosition() {
        return position;
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
	
	public void draw(Graphics graphics, String w) {
		graphics.drawString(w, getX(), getY());
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public int getX() {
		return position.getX();
	}

	public void setX(int x) {
		position.setX(x);
	}

	public int getY() {
		return position.getY();
	}

	public void setY(int y) {
		position.setY(y);
	}

	public CollisionChecker getCollisionChecker() {
		return collisionChecker;
	}

	public String getWordString() {
		return wordString;
	}

	public Color getColour() {
		return colour;
	}
	
	public FontMetrics getFontMetrics() {
		return fontMetrics;
	}

	public boolean collide(Collidable collidable) {	
        return collisionChecker.collide(this, collidable);
	}
	
	public void setFontHeight(int fontHeight) {
		this.fontHeight = fontHeight;
	}
	
	public void setFontWidth(int fontWidth) {
		this.fontWidth = fontWidth;
	}

	public int getWidth() {
		return fontWidth;
	}

	public int getHeight() {
		return fontHeight;
	}
	
	public void setWordString(String wordString) {
		if(wordString != null) {
			this.wordString = wordString;
		}
	}
	
	public void setBufferedImage(BufferedImage bufferedImage) {
        this.image= bufferedImage;
    }
	
	public BufferedImage getBufferedImage() {
		return image;
	}
}