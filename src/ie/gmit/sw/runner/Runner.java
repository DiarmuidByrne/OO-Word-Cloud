package ie.gmit.sw.runner;

import java.util.Scanner;

import ie.gmit.sw.wordcloud.WordCloud;

public class Runner {
	public static void main(String[] args) throws Exception {
		Scanner console = new Scanner(System.in);
		String inputFileName = "Computers.txt";
		String outputImageName = "WordCloud.png";
		int inputType = 0;

		// Create a new wordcloud using any text file
		// Can output to multiple image formats
		
		System.out.println("What kind of file do you want to parse?");
		System.out.println("input 0 for text file, input 1 for url");
		inputType = console.nextInt();
		console.nextLine();
		
		// Set text file
		System.out.println("Input your text file name/url link (Leave blank for example link):");
		inputFileName = console.nextLine();
		
		System.out.println("Input the name you want for your word cloud image: (Accepts PNG and JPG)");
		outputImageName = console.nextLine();
		
		//  Use default output name if none is entered
		if(outputImageName.length() < 1) {
			outputImageName = "WordCloud.png";
		}
		try {
			WordCloud w = new WordCloud(inputFileName, outputImageName, inputType);
		} catch(Exception e) {
			e.printStackTrace();
		}
		console.close();
		
		System.out.println("Finished. Word cloud generated!");
	}

}
