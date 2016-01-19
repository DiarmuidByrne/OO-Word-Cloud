package ie.gmit.sw.parse;


import java.nio.file.*;
import java.util.*;

public class FileParser implements Parsable {
	private Map<String, Integer> wordMap = new HashMap<String, Integer>();
	private StopWordsMap s;
	private Map<String, Integer> linkedMap = new LinkedHashMap<String, Integer>();
	
	/**
	 * Constructor
	 * Parses a file and saves words to LinkedHashMap 
	 * @throws Exception if stopwords map doesn't build successfully
	 */
	public FileParser() throws Exception {
		super();
		s = new StopWordsMap();
	}
	
	/**
	 * Parses a filename entered when the user runs the program.
	 * If no extension is given, a default extension of .txt is used
	 */
	public Map<String, Integer> parse(String fileName) throws Exception {
		fileName = validateExtension(fileName);
		
		for (String line : Files.readAllLines(Paths.get(fileName))) {
		    for (String word : line.split("\\W|\\s+|\\s")) {
		    	word = word.toUpperCase().trim();
		    	
		    	if(!s.compare(word)) {
		    		int frequency = 0;
					
					if(wordMap.containsKey(word)){
						frequency = wordMap.get(word);
					}
					frequency++;
					if((word).length() > 1 && !word.contains("_")) {
						wordMap.put(word, frequency);
					}
		    	}
		    }
		}

		linkedMap = new ParsableFactory().sortHashMapByValues(wordMap);
		return linkedMap;
	}
	
	public Map<String, Integer> getMap() {
		return linkedMap;
	}
	
	private String validateExtension(String fileName) {
		String extension;
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			extension = fileName.substring(i + 1);
        } else {
        	extension = ".txt";
        	fileName+=extension;
        }
		return fileName;
	}
}
