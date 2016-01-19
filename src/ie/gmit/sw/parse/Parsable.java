package ie.gmit.sw.parse;

import java.util.*;

public interface Parsable {
	/**
	 * @param fileName String name of file
	 * @return Map populated map
	 * @throws Exception if error occurs
	 */
	public Map<String, Integer> parse(String fileName) throws Exception;

	public Map<String, Integer> getMap();
}
