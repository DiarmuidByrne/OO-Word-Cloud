package ie.gmit.sw;

public class Runner {
	public static void main(String[] args) throws Exception {
		// Create a new wordcloud using any text file
		// Can output to multiple image formats
		WordCloud w = new WordCloud("Bunkers.txt", "Wordcloud.png");
	}

}
