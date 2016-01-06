package ie.gmit.sw;


import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class FileParser {
	private Map<String, Integer> wordMap = new HashMap<String, Integer>();
	private StopWordsMap s = new StopWordsMap();
	// Saves top 20 highest occuring word frequencies
//	private Map<String, Integer> cloudMap = new HashMap<String, Integer>();

	public FileParser(String fileName) throws Exception {
		super();
		parse(fileName);
		// TODO Auto-generated constructor stub
	}
	
	private Map<String, Integer> parse(String fileName) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		StringBuffer sb = new StringBuffer();
		
		int j;
		// Continue reading until end of file is reached
		while((j = br.read()) != -1){
			char next = (char)j;
			
			if (next >= 'A' && next <= 'z' || next == '\''){
				sb.append(next);
			}
			
			else {
				String word = sb.toString().toUpperCase();
				sb = new StringBuffer();
				
				// Only add word to map if it isn't in stopwords HashSet
				if (!s.compare(word) && word.length() > 0) {
					int frequency = 0;
					
					if(wordMap.containsKey(word)){
						frequency = wordMap.get(word);
					}
					frequency++;
					wordMap.put(word, frequency);
				}
			}
		}
		for(String word : wordMap.keySet())  {
			double score = getScore(word);
			
			//System.out.println("word: " + word + " freq: " + wordMap.get(word) + " score " + score);
			
			// if (score > 0) {	}
		}
		br.close();
		setWordMap(wordMap);
		return wordMap;
		//System.out.println(cloudMap.size() + " " + cloudMap);
	}	
	
	private double getScore(String word) {
		double score;
		
		double freq = (double)wordMap.get(word);
		int total = wordMap.size();
		
		score = (freq+total)/total;
		score = round(score, 6);
		return score;
	}


	public Map<String, Integer> getWordMap() throws Exception {
		return wordMap;
	}
	
	public void newString(){
		System.out.println("reached");
	}

	public void setWordMap(Map<String, Integer> wordMap) {
		this.wordMap = wordMap;
	}

	private double round(double val, int places)
	{
		String s = String.format("%."+places+"f", val);
		double result = Double.parseDouble(s);
	    return result;
	}


}
