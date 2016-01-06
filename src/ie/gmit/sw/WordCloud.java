package ie.gmit.sw;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.Random;

import javax.imageio.ImageIO;

public class WordCloud {
	BufferedImage image; 
	Graphics graphics;
	Color c;
	
	FileParser fp;
	
	public WordCloud(String fileName, String outputFileName) throws Exception {
		super();
		fp = new FileParser(fileName);
		generateWordCloud(outputFileName);
	}
	
	private void generateWordCloud(String outputFileName) throws Exception {
		Map<String, Integer> m = new HashMap<String, Integer>();
		m = fp.getWordMap();
		// Create a buffered image to draw words onto
		image = new BufferedImage(1080, 1080, BufferedImage.TYPE_4BYTE_ABGR);
		graphics = (Graphics)image.getGraphics();
		
		int x=0, y=100, i=0;
		for(String word : m.keySet()) {
			if(m.get(word) > 1 && i < m.size()) {
				int fontHeight = drawWord(word, m.get(word), x, y);
				System.out.println(word + " drawn. y: " + y);
				y+=fontHeight;
				i++;
			}
		}
		System.out.println(i);
		graphics.dispose();
		String extension;
		int j = outputFileName.lastIndexOf('.');
		if (j > 0) {
			extension = outputFileName.substring(j + 1);
        } else {
        	extension = "png";
        }
		ImageIO.write(image, extension, new File(outputFileName));
	}
	
	private int drawWord(String word, int wordFreq, int x, int y) {
		int fontSize = (int)(Math.log(wordFreq)*50);
		
		Font font = new Font(Font.DIALOG_INPUT, Font.BOLD, fontSize);
		
		c = randColour();
		graphics.setColor(c);
		graphics.setFont(font);
		FontMetrics f = graphics.getFontMetrics(font);

		int fontWidth = f.stringWidth(word);
		int fontHeight = f.getAscent() - (int)(f.getDescent()*1.3);
		
		System.out.println(f.getAscent() + "  newHeight: " + fontSize);
		
		// Calculate the width and height of the text to prevent overlap
		int widthXHeight[] = new int[2];
		widthXHeight[0] = fontWidth;
		widthXHeight[1] = fontHeight;
		Graphics2D g2D = (Graphics2D) graphics;
		
		graphics.drawString(word, x, y+(fontHeight));
		//graphics.setColor(Color.WHITE);
		//graphics.drawRect(x, y, fontWidth, fontHeight);
		return fontHeight;
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
