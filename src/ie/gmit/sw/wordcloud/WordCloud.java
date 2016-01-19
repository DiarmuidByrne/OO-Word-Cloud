package ie.gmit.sw.wordcloud;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.Random;

import ie.gmit.sw.collide.*;
import ie.gmit.sw.parse.*;
import ie.gmit.sw.image.*;

import javax.imageio.ImageIO;

public class WordCloud {
	final Random RANDOM = new Random();
	private int imageWidth, imageHeight;
    private OverlapCheckerImpl collisionChecker = new OverlapCheckerImpl();
	private Map<String, Integer> m;
	private BufferedImage image; 
	private Graphics2D graphics;
		
    private final Set<Word> placedWords = new HashSet<>();
		
	public WordCloud(String fileName, String outputFileName, int inputType, int imageWidth, int imageHeight) throws Exception {
		super();
		this.imageHeight = imageHeight;
		this.imageWidth = imageWidth;
		ParsableFactory pf = new ParsableFactory();
		m = pf.validateInput(fileName, outputFileName, inputType);
		generateWordCloud(outputFileName);
	}
	
	/**
	 * Generates a word cloud and outputs to an 
	 * image file (png or jpg).
	 * @param outputFileName String name of image file 
	 * @throws Exception
	 */
	private void generateWordCloud(String outputFileName) throws Exception {
		// Create a buffered image to draw words onto
		image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_4BYTE_ABGR);
		graphics = (Graphics2D)image.getGraphics();
		
		new Background().drawBackground(image, imageWidth, imageHeight);
		
		/*
		 * Output up to 150 words depending
		 * on overall font sizes
		 */
		int totalWordCount;
		if(m.size() > 150) {
			totalWordCount = 150;
		} else totalWordCount = m.size();
		
		int i = 0;
		for(String word : m.keySet()) {
			if(m.get(word) > 1 && i < totalWordCount) {
				newWord(word, m.get(word));
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
		
		System.out.println("Finished. Word cloud " + outputFileName + " generated!\n");
		
		graphics.dispose();
	}
	
	/**
	 * Creates new Word object with given parameters
	 * @param wordString
	 * @param wordFreq
	 */
	private void newWord(String wordString, int wordFreq) {
		Word word = new Word(wordString, wordFreq, graphics, collisionChecker);
		
		place(word);
	}
	/**
	 * Used from Kumo WordCloud.place()
	 * method (see references in Readme)
	 * @param word Word
	 */
	private void place(Word word) {               
        FontMetrics f = word.getFontMetrics();
        
		int fontWidth = f.stringWidth(word.getWordString());
		int fontHeight = f.getAscent() - (int)(f.getDescent()*1.3);
		
        int startX = RANDOM.nextInt(imageWidth - fontWidth);
        int startY = RANDOM.nextInt(imageHeight - fontHeight);
        
        final int maxRadius = imageWidth;
        for(int r = 0; r < maxRadius; r++) {
            for(int x = 0; x <= r; x++) {
                int y1 = (int) Math.sqrt(r * r - x * x);
                int y2 = - y1;

                boolean placed;
                word.setXPosition(startX + x);
                word.setYPosition(startY + y1);
                
                // Check for collision with another word before placing
                placed = addWord(word);
                if(!placed) {
                    word.setYPosition(startY + y2);
                    placed = addWord(word);
                }
                if(placed) {
                	System.out.println("Drawing " + word.getWordString());
                    word.draw(graphics, word.getWordString());
                    return;
                }
            }
        }
	}
	
	/**
	 * Ensures that the word is rendered
	 * within the bounds of the image
	 * @param word Word
	 * @return inBounds boolean
	 */
	private boolean checkBoundaries(Word word) {
		boolean inBounds;
		
		inBounds = word.getXPosition() >= 0 &&
	                word.getXPosition() + word.getWidth() < imageWidth &&
	                word.getYPosition() - word.getHeight() >= 0 &&
	                word.getYPosition() + word.getHeight() < imageHeight;
	    
        return inBounds;
	}
	
	/** Loops through all currently placed words
	 * If it collides with existing words, or if
	 * the word is out of bounds,
	 * it won't be placed
	 * @param word Word object
	 * @return boolean
	 */
	private boolean addWord(Word word) {
		boolean collided = false;
		
        if(!checkBoundaries(word)) 	return false;
        
        for(Word placeWord : placedWords) {
        	// Return false if word collides with another placed word
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
