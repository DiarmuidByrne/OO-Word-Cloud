package ie.gmit.sw.test;
/*
import static org.junit.Assert.*;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.junit.*;
import ie.gmit.sw.wordcloud.*;
import ie.gmit.sw.collide.*;

public class TestWordCloud {
	
	WordCloud wordCloud = null;
	
	@Test
	public void validParams() throws Exception {
		wordCloud = new WordCloud("computers.txt", "WordCloud.png", 0, 1000, 1000);
	}
	@Test
	public void invalidInput() throws Exception {
		wordCloud = new WordCloud("", "WordCloud.png", 0, 1000, 1000);
	}
	
	@Test
	public void invalidURL() throws Exception {
		String url = "http://www.gmit.ie";

		wordCloud = new WordCloud(url, "WordCloud.png", 1, 1000, 1000);
	}
	
	@Test
	public void nullWordString() throws Exception {
		BufferedImage image = new BufferedImage(1000, 1000, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g;
		g = (Graphics2D)image.getGraphics();
		OverlapCheckerImpl oc = new OverlapCheckerImpl();
		Word word = new Word("", 1, g, oc);
		assertNotNull(word.getWordString());
	}
	
	@Test
	public void testCollide() {
        Rectangle r1 = new Rectangle(0, (0-20), 60, 20);
        Rectangle r2 = new Rectangle(0, (0-20), 50, 10);
        
        assertTrue(r1.intersects(r2));
	}
}*/