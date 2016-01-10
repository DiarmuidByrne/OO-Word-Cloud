package ie.gmit.sw.parse;

import java.util.*;

public interface Parsable {
	public Map<String, Integer> parse(String fileName) throws Exception;
	
	public Map<String, Integer> getMap();
}
