package ie.gmit.sw.wordcloud;

import java.awt.Color;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import ie.gmit.sw.collide.*;
import ie.gmit.sw.image.*;

public class Word implements CollisionDetector{
    private String wordString;
    private final FontMetrics fontMetrics;
    private Position position = new Position(0,0);
    private Graphics2D graphics;
    private OverlapChecker overlapChecker;
 
    private int fontWidth, fontHeight;
    /**
     * Word object Constructor.
     * Initializes a new Word object
     * @param word String the word.
     * @param wordFreq int Frequency the word occurs
     * @param graphics Graphics2D
     * @param collisionChecker CollisionChecker Collision Checker interface
     */
    // Called to create every word and draw to a bufferedImage
	public Word(String word, int wordFreq, Graphics2D graphics, OverlapCheckerImpl collisionChecker) {
		
		if(!word.isEmpty()) {
			this.wordString = word;
		} else {
			this.wordString = "INVALID";
		}
		
        this.graphics = graphics;
        this.overlapChecker = collisionChecker;
        Colour colour = new Colour();
        Color c = colour.newColour();
				
		// Change font size based on frequency of the word
		// Limit size to between 18 and 80 to 
        // prevent outliers from ruining the image
		int fontSize = (int)(Math.log(wordFreq))*40;
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
	
	/**
	 * Collision Detection.
	 * Delegates collide method to OverlapCheckerImpl
	 * @param collidable Collidable interface
	 * @return collide boolean
	 */
	public boolean collide(CollisionDetector collidable) {	
        return overlapChecker.collide(this, collidable);
	}
	/**
	 * Draws the word onto the BufferedImage
	 * @param graphics Graphics2D
	 * @param w String the word
	 */
	public void draw(Graphics graphics, String w) {
		graphics.drawString(w, getXPosition(), getYPosition());
	}
	
	/**
	 * Encapsulation
	 * Getters and Setters.
	 */
	public int getXPosition() {
		return position.getX();
	}

	public void setXPosition(int x) {
		position.setX(x);
	}

	public int getYPosition() {
		return position.getY();
	}

	public void setYPosition(int y) {
		position.setY(y);
	}

	public String getWordString() {
		return wordString;
	}

	public FontMetrics getFontMetrics() {
		return fontMetrics;
	}
	
	public int getWidth() {
		return fontWidth;
	}

	public int getHeight() {
		return fontHeight;
	}
}