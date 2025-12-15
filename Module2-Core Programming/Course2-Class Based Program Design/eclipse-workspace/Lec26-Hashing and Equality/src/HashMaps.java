import java.util.*;
import tester.*;
//=====================================================================
//DEFINING DICTIONARY CLASS

class DictionaryEntry{
	String word;
	String definition;
	
	DictionaryEntry (String word, String definition) {
		this.word = word; 
		this.definition = definition;
	}
}

interface IDictionary <T, U> {
	boolean contains (T word);
	void put (T word, U meaning);
	U getMeaning (T word);
}

class StringDictionary implements IDictionary < String, String> {
	ArrayList<DictionaryEntry> entries;
	
	StringDictionary() {entries = new ArrayList<DictionaryEntry>();}
	
	public boolean contains(String word) {
		for (DictionaryEntry entry: entries) {
			if (entry.word.equals(word)) {return true;}}
		return false;}
	
	public void put (String word, String meaning) {
		entries.add(new DictionaryEntry(word, meaning));
	}
	
	public String getMeaning (String word) {
		for (DictionaryEntry entry: entries) {
			if (entry.word.equals(word)) {return entry.definition;}
		} throw new IllegalArgumentException("Word not in dictionary");
	}
	
}

//=====================================================================
//DEFINING GENERIC DICTIONARY


class GenericDictionaryEntry<T,U>{
	T key;
	U value;
	
	GenericDictionaryEntry (T key, U value) {
		this.key = key; 
		this.value = value;
	}
}

class GenericDictionary<T,U> {
	ArrayList<GenericDictionaryEntry<T,U>> entries;
	
	GenericDictionary() {entries = new ArrayList<GenericDictionaryEntry<T,U>>();}
	
	public boolean contains(T key) {
		for (GenericDictionaryEntry<T,U> entry: entries) {
			if (entry.key.equals(key)) {return true;}}
		return false;}
	
	public void put (T key, U value) {
		int indexToPutAt = hashCode(key);
		entries.add(new GenericDictionaryEntry<T,U>(key, value));
	}
	
	public U getValue (T  key) {
		int indexToGetFrom = hasCode(key);
		return entries.get(indexToGetFrom).value;
		
		
		for (GenericDictionaryEntry<T,U> entry: entries) {
			if (entry.key.equals(key)) {return entry.value;}
		} throw new IllegalArgumentException("Word not in dictionary");
	}
	
}











//=====================================================================
//EXAMPLE CLASS



class Examples {
	Examples() {}
	
	void testExamples(Tester t) {
		ArrayList<String> dictionary = new ArrayList<String> ();
		String word = "Java";
		String meaning = "A modern programming language.";
		
		dictionary.add(word);
		dictionary.add(meaning);
		
		t.checkExpect(dictionary.get(0), "Java");
		
	}
}


//=====================================================================
//CLASS HASHMAP EXAMPLES 

class HashmapExamples {
	IDictionary<String, String> dictionary;
	HashmapExamples(){};
	
	void initData() {
		dictionary = new StringDictionary();
		dictionary.put("darkness", "The absence of light");
		dictionary.put("Java", "A modern programming language");
		dictionary.put("Laptop", "A mobile computer");
	}
	
	void testHashMapExample1 (Tester t) {
		initData();
		
		t.checkExpect(dictionary.contains("darkness"), true);
		t.checkExpect(dictionary.contains("Java"), true);
		t.checkExpect(dictionary.contains("Laptop"), true);
		t.checkExpect(dictionary.contains("other"), false);
		
		t.checkExpect(dictionary.getMeaning("darkness"), "The absence of light");
		t.checkExpect(dictionary.getMeaning("Java"), "A modern programming language");
		t.checkExpect(dictionary.getMeaning("Laptop"), "A mobile computer");
		t.checkException(new IllegalArgumentException("Word not in dictionary."),dictionary, "getMeaning", "other");
	}
}


















