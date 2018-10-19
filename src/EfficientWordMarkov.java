import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

// Inherit class BaseWordMarkov

public class EfficientWordMarkov extends BaseWordMarkov {
	private Map<WordGram,ArrayList<String>> myMap;
	/**
	 * Efficient constructor with parameter
	 * @param order order of the Markov Model
	 */
	public EfficientWordMarkov(int order){
		super(order);
		myMap = new HashMap<WordGram,ArrayList<String>>();
	}
	/**
	 * Efficient constructor without parameter
	 */
	public EfficientWordMarkov() {
		this(2);
	}
	/**
	 * Override setTraining using hashmap
	 * @param text source we use to search the keys
	 */
@Override
public void setTraining(String text) {
	myMap = new HashMap<WordGram,ArrayList<String>>(); //Initialize a new hashmap
	myWords = text.split("\\s+");
	for(int index = 0; index < myWords.length-myOrder+1;index++) {
	WordGram key = new WordGram(myWords, index ,myOrder);// Get keys
	if (! myMap.containsKey(key)){
      	myMap.put(key,  new ArrayList<String>());
    	}
	if (index + key.length() >= myWords.length){ // Detect the end of text
		myMap.get(key).add(PSEUDO_EOS);
		break;
	}
		String follow = myWords[index+key.length()];
		myMap.get(key).add(follow); // Append new element to the key. 
	}
	
}
/**
 * Override getFollows
 * @param key key in the hashmap
 * @return Array list containing all the words after a key
 */
@Override
public ArrayList<String> getFollows(WordGram key){
	if(! myMap.containsKey(key)) {
		throw new NoSuchElementException(key+" not in map");
	}
	return myMap.get(key);
}
}
