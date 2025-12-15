import tester.Tester;
import java.util.*;
import java.util.Iterator;

//ITERATOR CLASS

//interface Iterator<T> {
//	boolean hasNext();
//	T next();
//}

class ArrayListIterator<T> implements Iterator<T>{
	ArrayList<T> arrayList; 
	int currentElement;

	ArrayListIterator(ArrayList<T> arrayList) {
		this.arrayList = arrayList;
		this.currentElement = 0;}

	public boolean hasNext() {return this.currentElement < this.arrayList.size();}

	public T next() {
		T answer = this.arrayList.get(currentElement); //current element starts with 0 and gets incremented
		this.currentElement = this.currentElement + 1;
		return answer;}
	
	public void remove() {throw new UnsupportedOperationException("Remove is not implemented");
	
}}




class Util {
	Util() {}

	void forEach() {
//		ArrayList<Integer> aList = new ArrayList<Integer>();
//		aList.add(1);
//		aList.add(2);
//		aList.add(3);
//		int sum=0;
//
//
//		ArrayListIterator iterator = new ArrayListIterator(aList);
//		while(iterator.hasNext()) {Integer elem = iterator.next();}
//
//		for (Integer i : aList) {sum = sum+i;}
	}

}
	//=================================================================================
	//EXAMPLE CLASSES

	class IteratorExamples {
		IteratorExamples() {}

		void testSelfMadeForEach(Tester t) {
			ArrayList<Integer> aList = new ArrayList<Integer>();
			aList.add(1);
			aList.add(2);
			aList.add(3);
			int sum=0;


			Iterator<Integer> iterator = new ArrayListIterator<Integer>(aList);
			while(iterator.hasNext()) {
				Integer elem = iterator.next();
				sum = sum + elem;}
			t.checkExpect(sum, 6);

		}
	}