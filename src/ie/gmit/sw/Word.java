package ie.gmit.sw;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Random;

import ie.gmit.sw.collide.Collidable;
import ie.gmit.sw.collide.CollisionChecker;

public class Word implements Collidable{
	private final CollisionChecker collisionChecker;
    private final String wordString;
    private final Color colour;
    
    private Vector2D position = new Vector2D(0,0);
 
    private int fontWidth, fontHeight;

    private int x = 0;
    private int y = 0;

    private BufferedImage image;
    
    // Called to create every word and draw to a bufferedImage
	public Word(String word, int wordFreq, Color colour, FontMetrics f, CollisionChecker collisionChecker) {
		this.wordString = word;
        this.colour = colour;
        this.collisionChecker = collisionChecker;        
        
		int fontSize = (int)(Math.log(wordFreq)*50);
		Font font = new Font(Font.DIALOG_INPUT, Font.BOLD, fontSize);
		
		int fontWidth = f.stringWidth(word);
		setFontWidth(fontWidth);
		int fontHeight = f.getAscent() - (int)(f.getDescent()*1.3);
		setFontHeight(fontHeight);
		
        this.image = new BufferedImage(fontWidth, fontHeight, BufferedImage.TYPE_4BYTE_ABGR);
        final Graphics graphics = this.image.getGraphics();

		System.out.println("Ascent: " + f.getAscent() + " Descent: " + f.getDescent() + " Height: " + f.getHeight());
				
		Color c;
		c = randColour();
		graphics.setColor(c);
		graphics.setFont(font);
		
		graphics.setColor(Color.RED);
		graphics.drawRect(position.getX(), position.getY(), fontWidth, fontHeight);
		
		graphics.drawString(word, x, y);
	}
	
	public Vector2D getPosition() {
        return position;
    }
	
	private static Color randColour() {
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		
		Color colour = new Color(r, g, b);
		return colour;
	}

	public void draw(Graphics g) {
	    g.drawImage(image, getX(), getY(), null);
	}
	
	public void draw(Graphics graphics, String w) {
		graphics.drawRect(getX(), getY()-fontHeight, fontWidth, fontHeight);
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

	@Override
	public boolean collide(Collidable collidable) {	
        return collisionChecker.collide(this, collidable);
	}
	
	public void setFontHeight(int fontHeight) {
		this.fontHeight = fontHeight;
	}
	
	public void setFontWidth(int fontWidth) {
		this.fontWidth = fontWidth;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		//return image.getWidth();
		return fontWidth;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		//return image.getHeight();
		return fontHeight;
	}
	
	public void setBufferedImage(BufferedImage bufferedImage) {
        this.image= bufferedImage;
    }

	@Override
	public BufferedImage getBufferedImage() {
		// TODO Auto-generated method stub
		return image;
	}
}
