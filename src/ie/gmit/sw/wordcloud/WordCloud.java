package ie.gmit.sw.wordcloud;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.Random;
import ie.gmit.sw.collide.*;
import ie.gmit.sw.parse.FileParser;
import ie.gmit.sw.parse.URLParser;

import javax.imageio.ImageIO;

public class WordCloud {
	final Random RANDOM = new Random();
	private int imageWidth = 1000, imageHeight = 1000;
    private CollisionChecker collisionChecker = new RectCollisionChecker();
	private final Map<String, Integer> m;
	BufferedImage image; 
	Graphics2D graphics;
	int minURLLength = 5;

	
	private List<Word> words = new ArrayList<Word>();
	
    private final Set<Word> placedWords = new HashSet<>();
    private final Set<Word> skipped = new HashSet<>();
	
	private FileParser fp;
	private URLParser up;
	
	public WordCloud(String fileName, String outputFileName, int inputType) throws Exception {
		super();
		if(inputType == 0) {
			if(fileName.length() < 1) {
				fileName = "computers.txt";
			}
			fp = new FileParser();
			fp.parse(fileName);
			m = fp.getMap();
		} else {
			if(fileName.length() < minURLLength) {
				fileName = "http://textfiles.com/computers/1991-12";
			}
			up = new URLParser();
			up.parse(fileName);
			m = up.getMap();
		}
		generateWordCloud(outputFileName);
	}
	
	private void generateWordCloud(String outputFileName) throws Exception {
		// Create a buffered image to draw words onto
		image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_4BYTE_ABGR);
		graphics = (Graphics2D)image.getGraphics();
		
		drawBackground();
		int totalWordCount = m.size();
		if(m.size() > 150) {
			totalWordCount = 150;
		} 
		int i = 0;
		for(String word : m.keySet()) {
			if(m.get(word) > 1 && i < totalWordCount) {
				drawWord(word, m.get(word));
				i++;
			}
		}
		String extension;
		int j = outputFileName.lastIndexOf('.');
		if (j > 0) {
			extension = outputFileName.substring(j + 1);
        } else {
        	extension = "png";
        	outputFileName +="."+extension;
        }
		ImageIO.write(image, extension, new File(outputFileName));
		graphics.dispose();
	}

	private void drawWord(String wordString, int wordFreq) {
		Word word = new Word(wordString, wordFreq, graphics, collisionChecker);
	    
		words.add(word);
		
		place(word);
	}
		
	private void place(Word word) {
        final int maxRadius = imageWidth;
               
        FontMetrics f = word.getFontMetrics();
        
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
        skipped.add(word);
	}
	
	private boolean isInBounds(Word word) {
		boolean inBounds;
		inBounds = word.getX() >= 0 &&
	                word.getX() + word.getWidth() < imageWidth &&
	                word.getY() - word.getHeight() >= 0 &&
	                word.getY() + word.getHeight() < imageHeight;
	    
        return inBounds;
	}
	
    private void drawBackground() {
        final Graphics graphics = this.image.getGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, imageWidth, imageHeight);
    }
	
	// Return false if word collides with another placed word
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
            placedWords.add(word);
            return true;
        }
        return false;
	}
}
