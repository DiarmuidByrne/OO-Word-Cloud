package ie.gmit.sw.parse;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class StopWordsMap {
	private static HashSet<String> set = new HashSet<String>(); 
	
	/**
	 * Constructor
	 * Calls parse() method to populate stopWords set
	 * @throws Exception throw Exception if FileName not found
	 */
	public StopWordsMap() throws Exception {
		super();
		parse("./stopWords.txt");
	}
	
	/**
	 * Compares URL or File parser word with stopwords HashSet
	 * if Set contains the word, return true
	 * @param word String the word
	 * @return boolean
	 */
	public boolean compare(String word) {
		// Return true if the file contains a stop word
		if(set.contains(word)) return true;
				
		return false;
	}
	/**
	 * Creates a Hashset from the
	 * stopwords text file
	 * @param fileName String the file name
	 * @throws Exception throw exception if error occurs
	 */
	private void parse(String fileName) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		StringBuffer sb = new StringBuffer();
		
		int i;
		while((i = br.read()) != -1) {
			char next = (char)i;
			
			if (next != '\n') sb.append(next);
			
			else {
				String sWord = sb.toString().toUpperCase().trim();
				sb = new StringBuffer();
						
				set.add(sWord);
			}
		}
		br.close();
	}
}
