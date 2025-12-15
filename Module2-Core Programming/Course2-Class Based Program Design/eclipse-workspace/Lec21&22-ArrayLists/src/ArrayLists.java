import tester.*;

import java.util.ArrayList;

//========================================================================
//FUNCTION OBJECTS

interface IFunc<R,A> {
	R apply(A arg);
}

class CountCharacters implements IFunc<Integer, String> {
	public Integer apply(String value) {
		return value.length();
	}
}


interface IPred<T> {
	boolean apply(T arg);
}


//========================================================================
//CLASS UTILS

class Util {
	Util() {}
	
	<T> void swap(ArrayList<T> arrayList, int index1, int index2) {
		T oldValueAtIndex1 = arrayList.get(index1);
		T oldValueAtIndex2 = arrayList.get(index2);
		
		arrayList.set(index2, oldValueAtIndex1);
		arrayList.set(index1, oldValueAtIndex2);
	}
	
	<T,U> ArrayList<U> map(ArrayList<T> arrayList, IFunc<U,T> func) {
		ArrayList<U> result = new ArrayList <U>();
		return this.mapHelper(arrayList, func, result, 0);
	}
	<T,U> ArrayList<U> mapHelper(ArrayList<T> arrayList, IFunc<U,T> func, ArrayList<U> result, int currentIndex) {
		//Base Case
		if (currentIndex >= arrayList.size()) {
			return result;
		}
		result.add(func.apply(arrayList.get(currentIndex)));
		return this.mapHelper(arrayList, func, result, currentIndex +1);
	}
	
	<T,U> ArrayList<U> map2(ArrayList<T> arrayList, IFunc<U,T> func) {
		ArrayList<U> result = new ArrayList <U>();
		//["1st" , "2nd" , "3rd"]
		for (T elem: arrayList) {result.add(func.apply(elem));}
		return result;}
	
	

	//METHOD FIND 
	//Identifies the first index at which the index value is satisfied
	<T> int find(ArrayList<T> arr, IPred<T> whichOne) {
		return findHelp(arr, whichOne, 0);
	}
	
	
	//METHOD FIND HELP
	//Returns the index of the first item passing the predicate at or after the
	//given index, or -1 if no such such item was found
	<T> int findHelp(ArrayList<T> arr, IPred<T> whichOne, int index) {
		if (index >= arr.size()) {return -1;}
		else if (whichOne.apply(arr.get(index))) {return index;}
		else {return findHelp(arr, whichOne, index + 1);}}
	
	
	
	//METHOD BINARYSEARCH
	//Returns the index of the target string in the given ArrayList, or -1 if the string is not found
	//Assumes that the given ArrayList is sorted aphabetically
	int binarySearch(ArrayList<String> strings, String target) {
		return this.binarySearchHelp(strings, target, 0, strings.size()-1);
	}


	//METHOD BINARYSEACHHELP
	//Returns the index of the target string in the given ArrayList, or -1 if the string is not found
	//Assumes that the given ArrayList is sorted aphabetically
	int binarySearchHelp(ArrayList<String> strings, String target, int lowIdx, int highIdx) {
		int midIdx = (lowIdx + highIdx) / 2;
		if (lowIdx > highIdx) {return -1;}                                                           // not found
		else if (target.compareTo(strings.get(midIdx)) == 0) {return midIdx; }  // found it!
		else if (target.compareTo(strings.get(midIdx)) > 0) {return this.binarySearchHelp(strings, target, midIdx + 1, highIdx);} // too low
		else {return this.binarySearchHelp(strings, target, lowIdx, midIdx - 1);} // too high
	}
	
	
	
	//METHOD SORT
	//EFFECT: Sorts the given list of strings alphabetically
	void sort(ArrayList<String> arr) {
		for (int idx = 0;                                   // (1)
				idx < arr.size();                              // (2)
				idx = idx + 1) {                               // (4)
			int idxOfMinValue = this.findMinIndex(arr, idx);  			// (3)
			this.swap(arr, idx, idxOfMinValue);
		}}

		//METHOD FINDMININDEX
		//Finds the minimum value in a given list
		int findMinIndex(ArrayList<String> arr, int startIdx) {
			int minIdx = startIdx;
			for (int i = startIdx + 1; i < arr.size(); i++) {
				if (arr.get(i).compareTo(arr.get(minIdx)) < 0) {minIdx = i;}}
			return minIdx;}
		
}
	



//========================================================================
//CLASS EXAMPLES
class ArrayListExamples {
	ArrayListExamples() {}
		void testArrayListAddGet(Tester t) {
			ArrayList<String> stringList = new ArrayList <String>();
			t.checkExpect(stringList.size(), 0);
			
			stringList.add("1st item");
			t.checkExpect(stringList.size(), 1);
			
			stringList.add("2nd Item");
			t.checkExpect(stringList.size(), 2);
			
			stringList.remove(1);
			t.checkExpect(stringList.size(), 1);
			
			t.checkExpect(stringList.get(0), "1st item");
			stringList.add(0, "0th item");
			t.checkExpect(stringList.size(), 2);
			
			t.checkExpect(stringList.get(0), "0th item");
			
		}
		
		void testArrayListSwap(Tester t) {
			ArrayList<String> stringList = new ArrayList<String> ();
			Util util = new Util();
			stringList.add("1st");
			stringList.add("2nd");
			
			t.checkExpect(stringList.get(0), "1st");
			t.checkExpect(stringList.get(1), "2nd");
			util.swap(stringList, 0, 1);
			
			t.checkExpect(stringList.get(0), "2nd");
			t.checkExpect(stringList.get(1), "1st");
		}
		
		void testArrayListMap(Tester t) {
			ArrayList<String> stringList = new ArrayList<String> ();
			
			IFunc<Integer, String> funcObj = new CountCharacters();
			
			Util util = new Util();
			
			stringList.add("1st");
			stringList.add("2nd ");
			
			ArrayList<Integer> result = util.map(stringList, funcObj);
			t.checkExpect(result.size(), 2);
			t.checkExpect(result.get(0), 3);
			t.checkExpect(result.get(1), 4);
		}
}











