package ie.gmit.sw.parse;

import java.util.*;

public class ParsableFactory {
	/**
	 * Sorts a HashMap in ascending order by values and 
	 * returns as a LinkedHashMap
	 * @param wordMap Map populated, unsorted map of words
	 * @return sortedMap LinkedHashMap sorted map
	 */
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
	
	/**
	 * Error handling for the data input by the user
	 * @param fileName String name of text file
	 * @param outputFileName String name for output image file
	 * @param inputType int 0 for file, 1 for URL
	 * @return m Map The populated word Map
	 * @throws Exception throws exception if file name is invalid
	 */
	public Map<String, Integer> validateInput(String fileName, String outputFileName, int inputType) throws Exception {
		int minURLLength = 5;
		Map <String, Integer> m = null;
		if(inputType == 0) {
			
			// Set File name to default if not entered
			if(fileName.length() < 1) {
				fileName = "computers.txt";
			}
			FileParser fp = new FileParser();
			try {
				fp.parse(fileName);
				m = fp.getMap();
			} catch (Exception e) {
				System.out.println("Invalid File Name");
			}
		} else {
			// Set URL to default if not entered correctly
			if(fileName.length() < minURLLength) {
				fileName = "http://textfiles.com/computers/1991-12";
			}
			URLParser up = new URLParser();
			try {
				up.parse(fileName);
				m = up.getMap();
			} catch (Exception e) {
				System.out.println("Invalid File Name");
			}
		}
		return m;
	}
	
}
