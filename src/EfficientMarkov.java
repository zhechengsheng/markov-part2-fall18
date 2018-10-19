import java.util.*;


//Inherit class BaseMarkov
public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;
	/**
	 * Efficient constructor with parameter
	 * @param order order of the Markov Model
	 */
	
	public EfficientMarkov(int order){
		super(order);
		myMap = new HashMap<String,ArrayList<String>>();
	}
	/**
	 * Efficient constructor without parameter
	 */
	public EfficientMarkov() {
		this(3);
	}
	/**
	 * Override setTraining using hashmap
	 * @param text source we use to search the keys
	 */
	@Override
	public void setTraining(String text) {
		myMap = new HashMap<String,ArrayList<String>>();//Initialize a new hashmap
		myText = text;
		String[] allchar = text.split("");
		for(int index = 0; index < text.length()-myOrder+1;index++) {
		String key = text.substring(index, index+myOrder); // Get keys
		if (! myMap.containsKey(key)){
	      	myMap.put(key, new ArrayList<String>());
	    	}
		if (index + myOrder >= text.length()){ // Detect the end of text
			myMap.get(key).add(PSEUDO_EOS);
			break;
		}
			String follow = allchar[index+myOrder];// Append new element to the key. 
			myMap.get(key).add(follow);
		}
		
	}
	/**
	 * Override getFollows
	 * @param key key in the hashmap
	 * @return Array list containing all the characters after a key
	 */
	@Override
	public ArrayList<String> getFollows(String key){
		if(! myMap.containsKey(key)) {
			throw new NoSuchElementException(key+" not in map");
		}
		return myMap.get(key);
	}
}