package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class StopWordsMap {
	private static HashSet<String> set = new HashSet<String>(); 

	public StopWordsMap() throws Exception {
		super();
		parse("./stopWords.txt");
	}
	
	public boolean compare(String word) {
		// Return true if the file contains a stop word
		if(set.contains(word)) return true;
				
		return false;
	}
	
	// Parses a text file of words to ignore and adds them to a hashset
	private void parse(String filename) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
		StringBuffer sb = new StringBuffer();
		
		int i;
		while((i = br.read()) != -1) {
			char next = (char)i;
			
			if (next != '\n') sb.append(next);
			
			else {
				String sWord = sb.toString().toUpperCase();
				sb = new StringBuffer();
								
				set.add(sWord);
			}
		}
		br.close();
		System.out.println(set);
	}
}
