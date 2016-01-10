package ie.gmit.sw.parse;

import java.io.*;
import java.net.*;
import java.util.*;

public class URLParser implements Parsable{
	private HashMap<String, Integer> urlMap = new HashMap<String, Integer>();
	private StopWordsMap s = new StopWordsMap();
	private Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
	
	public URLParser() throws Exception
	{
		super();
	}
	
	public Map<String, Integer> parse(String urlString) throws Exception 
	{
		URL url = new URL(urlString);

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        
        StringBuffer sb = new StringBuffer();
		
		int j;
		boolean ignore = false;
		// Continue reading until end of file is reached
		while((j = br.read()) != -1){
			char next = (char)j;
			
			if (next >= 'A' && next <= 'z' && !ignore){
				sb.append(next);
			} 
			else if(next == '<')  ignore = true;
			
			else if(next == '>') 	ignore = false;
			
			else {
				if(!ignore) {
					String word = sb.toString().toUpperCase();
					sb = new StringBuffer();
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
			}
		}
		FileParser fp = new FileParser();
		setSortedMap(fp.sortHashMapByValues(urlMap));
		br.close();
		
		return sortedMap;		
	}

	public Map<String, Integer> getMap()
	{
		return sortedMap;
	}
	
	public void setSortedMap(Map<String, Integer> sortedMap) {
		this.sortedMap = sortedMap;
	}
	}
