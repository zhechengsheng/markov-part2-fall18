import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientWordMarkov extends BaseWordMarkov {
	private Map<WordGram,ArrayList<String>> myMap;
	public EfficientWordMarkov(int order){
		super(order);
		myMap = new HashMap<WordGram,ArrayList<String>>();
	}
	
	public EfficientWordMarkov() {
		this(2);
	}
@Override
public void setTraining(String text) {
	myMap = new HashMap<WordGram,ArrayList<String>>();
	myWords = text.split("\\s+");
	for(int index = 0; index < myWords.length-myOrder+1;index++) {
	WordGram key = new WordGram(myWords, index ,myOrder);
	if (! myMap.containsKey(key)){
      	myMap.put(key,  new ArrayList<String>());
    	}
	if (index + key.length() >= text.length()){
		myMap.get(key).add(PSEUDO_EOS);
		break;
	}
		String follow = myWords[index+key.length()];
		myMap.get(key).add(follow);
	}
	
}
@Override
public ArrayList<String> getFollows(WordGram key){
	if(! myMap.containsKey(key)) {
		throw new NoSuchElementException(key+" not in map");
	}
	return myMap.get(key);
}
}
