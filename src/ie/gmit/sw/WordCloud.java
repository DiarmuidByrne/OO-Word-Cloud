package ie.gmit.sw;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.Random;
import ie.gmit.sw.collide.*;


import javax.imageio.ImageIO;

public class WordCloud {
	final Random RANDOM = new Random();
	private int imageWidth = 1000, imageHeight = 1000;
    private CollisionChecker collisionChecker = new RectCollisionChecker();
	private final Map<String, Integer> m;
	BufferedImage image; 
	Graphics2D graphics;
	Color c;
	
	private List<Word> words = new ArrayList<Word>();
	
    private final Set<Word> placedWords = new HashSet<>();
    private final Set<Word> skipped = new HashSet<>();

    private double[] thetas = new double[] {0, -Math.PI / 2, Math.PI / 2};
	
	private FileParser fp;
	
	public WordCloud(String fileName, String outputFileName) throws Exception {
		super();
		fp = new FileParser(fileName);
		m = fp.getWordMap();
		generateWordCloud(outputFileName);
	}
	
	private void generateWordCloud(String outputFileName) throws Exception {
		// Create a buffered image to draw words onto
		image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_4BYTE_ABGR);
		graphics = (Graphics2D)image.getGraphics();
		
		drawBackground();
		
		int i=0;
		
		for(String word : m.keySet()) {
			if(m.get(word) > 1 && i < m.size()) {
				drawWord(word, m.get(word));
				//y+=fontHeight;
				i++;
			}
		}
		String extension;
		int j = outputFileName.lastIndexOf('.');
		if (j > 0) {
			extension = outputFileName.substring(j + 1);
        } else {
        	extension = "png";
        }
		ImageIO.write(image, extension, new File(outputFileName));
		
		graphics.dispose();
	}

	private void drawWord(String wordString, int wordFreq) {
		int fontSize = (int)(Math.log(wordFreq)*50);
		
		Font font = new Font(Font.DIALOG_INPUT, Font.BOLD, fontSize);
		
		c = randColour();
		graphics.setColor(c);
		graphics.setFont(font);
		FontMetrics f = graphics.getFontMetrics(font);
		
		Word word = new Word(wordString, wordFreq, c, f, collisionChecker);
		
		words.add(word);
						
		int i=0;
		for(Word nextWord : words) {
			final double theta = thetas[i % thetas.length];
            if(theta != 0) {
                nextWord.setImage(rotate(word.getBufferedImage(), theta));
            }
            i++;
		}
		place(word, f);
	}
	
	private BufferedImage rotate(final BufferedImage bufferedImage, double theta) {

        BufferedImage rotatedImage = new BufferedImage(imageHeight, imageWidth, BufferedImage.TYPE_INT_ARGB);
        AffineTransform xform = new AffineTransform();
        xform.translate(0.5 * imageHeight, 0.5 * imageWidth);
        xform.rotate(theta);
        xform.translate(-0.5 * imageWidth, -0.5 * imageHeight);

        Graphics2D graphics2D = (Graphics2D) rotatedImage.getGraphics();
        graphics2D.drawImage(bufferedImage, xform, null);
        return rotatedImage;
	}
	
	private static Color randColour() {
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		
		Color colour = new Color(r, g, b);
		return colour;
	}
	
	private void place(Word word, FontMetrics f) {
        final int maxRadius = imageWidth;
        
        //final Graphics graphics = image.getGraphics();

        
		int fontWidth = f.stringWidth(word.getWordString());
		int fontHeight = f.getAscent() - (int)(f.getDescent()*1.3);
		
        int startX = RANDOM.nextInt(imageWidth - fontWidth);
        int startY = RANDOM.nextInt(imageHeight - fontHeight);
		
        // Attempt to place in the center and work outwards until an empty space is found
        for(int r = 0; r < maxRadius; r++) {
            for(int x = 0; x <= r; x++) {
                int y1 = (int) Math.sqrt(r * r - x * x);
                int y2 = - y1;

                boolean placed;
                word.setX(startX + x);
                word.setY(startY + y1);

                placed = tryToPlace(word);
                if(!placed) {
                    word.setY(startY + y2);
                    placed = tryToPlace(word);
                }
                if(placed) {
                    word.draw(graphics, word.getWordString());
                    return;
                }

            }
        }
        //System.out.println("skipped: " + word);
        skipped.add(word);

	}
	
	private boolean isInBounds(Collidable collidable) {
		
		return collidable.getX() >= 0 &&
                collidable.getX() + collidable.getWidth() < imageWidth &&
                collidable.getY() >= 0 &&
                collidable.getY() + collidable.getHeight() < imageHeight;
	}
	
    private void drawBackground() {
        final Graphics graphics = this.image.getGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, imageWidth, imageHeight);
    }
	
	// If word collides with another placed word when attempted to place
	// Return false	
	private boolean tryToPlace(Word word) {
		boolean collided = false;
		
        if(!isInBounds(word)) 	return false;
        
        for(Word placeWord : placedWords) {
          // Loops through all currently placed words
          // If collides with existing words
          // it won't be placed
            if(word.collide(placeWord)) {
                collided = true;
                break;
            }
        }
        if(!collided) {
        	System.out.println("Adding to placedWords");
            placedWords.add(word);
            return true;
        }
        return false;
	}
}
