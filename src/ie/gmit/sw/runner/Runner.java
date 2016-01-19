package ie.gmit.sw.runner;

import java.util.Scanner;

import ie.gmit.sw.parse.URLParser;
import ie.gmit.sw.wordcloud.WordCloud;

public class Runner {
	/**
	 * Accepts input from user
	 * Allows the user to create a wordcloud
	 * based on a given text file name or URL
	 * and outputs it to the chosen image file name
	 * @param args String[] arguments
	 * @throws Exception if an error occurs
	 */
	public static void main(String[] args) throws Exception {
		
		Scanner console = new Scanner(System.in);
		String input = "Computers.txt";
		String outputImageName = "WordCloud.png";
		boolean isValid = true;
		
		int inputType = 0;
		int imageWidth = 1000;
		int imageHeight = 1000;

		// Create a new wordcloud using any text file
		// Can output to multiple image formats
		do {
			System.out.println("**************************************");
			System.out.println(" Object Oriented Word Cloud Generator ");
			System.out.println("**************************************");
			System.out.println("\nWhat kind of file do you want to parse?");
			System.out.println("input 0 for text file, input 1 for url");
			
		    while (!console.hasNextInt()) {
		    	console.next();
		    	System.out.println("Invalid selection");
		    }
		    
			inputType = console.nextInt();
			console.nextLine();
			
			// Set text file
			System.out.println("Input your text file name/url link (Leave blank for example link):");
			input = console.nextLine();
			
			if(inputType == 1) {
				isValid = new URLParser().validateURL(input);
			}
			if(!isValid) {
				System.out.println("URL Not valid. Try again");
				continue;
			}
			
			System.out.println("Input the name you want for your word cloud image: (Accepts PNG and JPG)");
			outputImageName = console.nextLine();
			
			if(isValid) {
				new WordCloud(input, outputImageName, inputType, imageWidth, imageHeight);
				isValid = true;
			}
				
		} while(!isValid);
		console.close();
		
	}

}
