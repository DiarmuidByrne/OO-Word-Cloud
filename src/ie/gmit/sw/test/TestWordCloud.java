package ie.gmit.sw.test;
/*
import static org.junit.Assert.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.junit.*;
import ie.gmit.sw.wordcloud.*;
import ie.gmit.sw.collide.CollisionChecker;
import ie.gmit.sw.collide.RectCollisionChecker;

public class TestWordCloud {
	
	WordCloud wordCloud = null;
	
	@Test
	public void validParams() throws Exception {
		wordCloud = new WordCloud("computers.txt", "WordCloud.png", 0);
	}
	@Test
	public void invalidFile() throws Exception {
		wordCloud = new WordCloud("", "WordCloud.png", 0);
	}
	@Test
	public void invalidURL() throws Exception {
		wordCloud = new WordCloud("", "WordCloud.png", 0);
	}
	
	@Test
	public void nullWordString() throws Exception {
		BufferedImage image = new BufferedImage(1000, 1000, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g;
		g = (Graphics2D)image.getGraphics();
		CollisionChecker c = new RectCollisionChecker();
		Word word = new Word("Test", 1, g, c);
		word.setWordString(null);
		assertNotNull(word.getWordString());
	}
}
*/