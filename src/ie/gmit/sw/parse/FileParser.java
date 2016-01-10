package ie.gmit.sw.parse;


import java.io.*;
import java.util.*;

public class FileParser implements Parsable {
	private Map<String, Integer> wordMap = new HashMap<String, Integer>();
	private StopWordsMap s = new StopWordsMap();
	private Map<String, Integer> linkedMap = new LinkedHashMap<String, Integer>();
	// Saves top 20 highest occuring word frequencies

	public FileParser() throws Exception {
		super();
	}
	
	public Map<String, Integer> parse(String fileName) throws Exception {
		
		String extension;
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			extension = fileName.substring(i + 1);
        } else {
        	extension = ".txt";
        	fileName+=extension;
        }
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		StringBuffer sb = new StringBuffer();
		
		int j;
		// Continue reading until end of file is reached
		while((j = br.read()) != -1){
			char next = (char)j;
			
			if (next >= 'A' && next <= 'z'){
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
					if((word).length() > 1 && !word.contains("_")) {
						wordMap.put(word, frequency);
					}
				}
			}
		}
		linkedMap = sortHashMapByValues(wordMap);
		br.close();
		return linkedMap;
	}
	
	// Sort hashmap by frequency in descending order
	public LinkedHashMap<String, Integer> sortHashMapByValues(Map<String, Integer> wordMap) {
	   List<String> mapKeys = new ArrayList<String>(wordMap.keySet());
	   List<Integer> mapValues = new ArrayList<Integer>(wordMap.values());
	   Collections.sort(mapValues, Collections.reverseOrder());
	   Collections.sort(mapKeys, Collections.reverseOrder());

	   LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();

	   Iterator<Integer> valueIt = mapValues.iterator();
	   while (valueIt.hasNext()) {
	       Object val = valueIt.next();
	       Iterator<String> keyIt = mapKeys.iterator();

	       while (keyIt.hasNext()) {
	           Object key = keyIt.next();
	           String comp1 = wordMap.get(key).toString();
	           String comp2 = val.toString();

	           if (comp1.equals(comp2)){
	               wordMap.remove(key);
	               mapKeys.remove(key);
	               sortedMap.put((String)key, (int)val);
	               break;
	           }

	       }

	   }
	   return sortedMap;
	}
	
	public Map<String, Integer> getMap() {
		return linkedMap;
	}
}
