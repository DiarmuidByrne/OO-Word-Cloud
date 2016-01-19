package ie.gmit.sw.parse;

import org.apache.commons.validator.routines.UrlValidator;
import java.util.*;
import org.jsoup.*;
import org.jsoup.nodes.Document;

public class URLParser implements Parsable {
	private HashMap<String, Integer> urlMap = new HashMap<String, Integer>();
	private StopWordsMap s;
	private Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
	
	/**
	 * Constructor
	 * Parses a URL using JSoup
	 * @throws Exception if StopWords aren't initialized
	 */
	public URLParser() throws Exception {
		super();
		s = new StopWordsMap();
	}
	
	/**
	 * Uses JSoup to parse a URL and strip the 
	 * html elements leaving just the plain text.
	 * Each word is then compared to the stopwords 
	 * HashSet and adds the word to a HashMap
	 * @param urlString String the URL
	 * @return sortedMap Map the sorted LinkedHashMap
	 */
	public Map<String, Integer> parse(String urlString) throws Exception  {
		boolean isValid = validateURL(urlString);
		
		if(isValid) {
			Document doc = Jsoup.connect(urlString).get();
			
			String plainText = doc.body().text();
			
			// Add each word to array, ignoring whitespace and non-word characters
			String[] words = plainText.split("\\W+|\\s|\\s+");
			
			// Continue reading until end of file is reached
			for(String word : words) {
				word = word.toUpperCase().trim();
				// Only add word to map if it isn't in stopwords HashSet
				if (!s.compare(word) && word.length() > 0) {
					int frequency = 0;
					if(urlMap.containsKey(word)){
						frequency = urlMap.get(word);
					}
					frequency++;
					if((word).length() > 1 && !word.contains("_")) {
						urlMap.put(word, frequency);
					}
				}
			}
			sortedMap = new ParsableFactory().sortHashMapByValues(urlMap);
		}
		
		return sortedMap;	
	}
	
	/**
	 * Validates a given url before it is parsed.
	 * @param url String the URL String
	 * @return boolean
	 */
	public boolean validateURL(String url) {
		String[] schemes = {"http","https"}; // DEFAULT schemes = "http", "https", "ftp"
		UrlValidator urlValidator = new UrlValidator(schemes);
		if (urlValidator.isValid(url)) {
		   return true;
		}
		
		return false;
	}

	public Map<String, Integer> getMap() {
		return sortedMap;
	}
	
}
