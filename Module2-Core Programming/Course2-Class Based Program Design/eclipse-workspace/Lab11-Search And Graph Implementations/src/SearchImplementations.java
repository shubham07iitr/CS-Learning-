import tester.*;
import java.util.*;

//============================================================================
//CLASS HEAPs
//Key attributes include:
//--While removing an element, always remove the highest element or top most element frm the queue
//-- While adding, we first will alwyas fill the previous row/element first, then move on to the next element
//--Highest priority element will be at the top


class Heap<T> {
	ArrayList<T> contents;
	
	Heap (ArrayList<T> contents) {this.contents = contents;}
	
	//TEMPLATE
	/*
	 * FIELDS 
	 * ...this.contents...ArrayList<T>
	 * METHODS
	 * ...this.add(T elem) ....void
	 * ...this.remove() .......T
	 * METHODS from FIELDS
	 * ...this.contents.add(T elem) ... void
	 * ...this.contents.remove(int index) ...int
	 * ...this.contents.size() ....int
	 * ...this.get(int index) ....int
	 */
	
	//METHODS
	
	//METHOD SIZE
	//Returns the no of elements in the heap
	//Signature> Self>Int
	int size() {return this.contents.size();}
	
	//METHOD GETLEFTCHILDINDEX
	//Returns the index left child of an element, throws an error if index out of bounds
	//Signature> Self, int Index> T
	int getLeftChildINDEX(int index) {
		if (index*2+1 >= this.contents.size()) {throw new RuntimeException("No left child for the element exists.");}
		else {return index*2+1;}}
	
	//METHOD GETRIGHTCHILDINDEX
	//Returns the index right child of an element, throws an error if index out of bounds
	//Signature> Self, int Index> T
	int getRightChildIndex(int index) {
		if (index*2+2 >= this.contents.size()) {throw new RuntimeException("No left child for the element exists.");}
		else {return index*2+2;}}
	
	
	//METHOD GETPARENTINDEX
	//REturns the index of the parent of an element, throws an error if index out of bound
	int getParentIndex(int index) {
		if (index == 0) {throw new RuntimeException("No parent for the root node") ;}
		else {return (index-1)/2;}}
	
	//METHOD ADD
	//Adds an element at the appropriate index such that properties of the heap are maintained
	//Updates the heap in place, returns void
	//Signature> Self, T, ICompare<T> > Void
	void add (T elem, ICompare<T> compareFunc) {
		this.contents.add(elem);  //first we add the element at the end of the heap
		int currentIndex = this.size()-1; //we define where is the element currently placed
		Utils util = new Utils();
		while (currentIndex> 0 && compareFunc.compare(this.contents.get(currentIndex), this.contents.get(this.getParentIndex(currentIndex))) >= 0) {
			int tempIndex = this.getParentIndex(currentIndex);
			util.swap(contents, currentIndex, tempIndex);
			currentIndex = tempIndex;}}
	
	//METHOD REMOVE
	//Removes the root element from the heap, while maintaining the balance
	//First we swap the 1st element with last eleemnt, then swap top element with its child until we reach the end of array + we avoid the last element
	//Will return the root element
	//Signature > Self, ICompare<T> > T
	T remove (ICompare<T> compareFunc) {
    Utils util = new Utils();
    T rootElement = this.contents.get(0); // Store root before swapping
    util.swap(contents, 0, this.size()-1);
    this.contents.remove(this.size()-1); // Remove last element
    int currentIndex = 0;
    
    while (currentIndex < this.size()) { // Remove the -1
        int leftIndex = -1, rightIndex = -1, largest = currentIndex;
        
        // Check left child exists
        try { leftIndex = this.getLeftChildINDEX(currentIndex); } catch (RuntimeException e) {}
        if (leftIndex != -1 && compareFunc.compare(this.contents.get(leftIndex), this.contents.get(largest)) > 0) {largest = leftIndex;}
        
        // Check right child exists  
        try { rightIndex = this.getRightChildIndex(currentIndex); } catch (RuntimeException e) {}
        if (rightIndex != -1 && compareFunc.compare(this.contents.get(rightIndex), this.contents.get(largest)) > 0) {largest = rightIndex;}
        
        if (largest == currentIndex) break; // No swap needed
        
        util.swap(contents, currentIndex, largest);
        currentIndex = largest;}
    return rootElement;}
	
	//METHOD REMOVESORT
	//REMOVES each top node until the node is empty and returns a sorted node
	//Signature> Self, ICompareFunc 
	ArrayList<T> removeSort(ICompare<T> compareFunc) {
		ArrayList<T> returnList = new ArrayList<T> ();	 //we create an empty list 
		int addIndex = this.contents.size() - 1;
		int runLength = this.contents.size();
		for (int i = 0; i < runLength; i ++) {returnList.add(null);}
		for (int i = 0; i < runLength ; i++) {
			T elem = this.remove(compareFunc);
			returnList.set(addIndex, elem);
			addIndex--;}
		return returnList;}
	
}



//===========================================================================
//ICOMPARE Interface

interface ICompare<T> {
	int compare(T e1, T e2);}

class CompareInts implements ICompare<Integer> {
	public int compare (Integer e1, Integer e2) {return e1-e2;}
}

class PivotQuickSort<T> {
	ArrayList<T> arr;
	int pivotIndex;
	
	PivotQuickSort(ArrayList<T> arr, int pivotIndex) {this.arr = arr; this.pivotIndex = pivotIndex;}
}


//===========================================================================
//UTILS CLASS

class Utils {
	Utils() {}
	
	
	//METHOD GETMIN
	//Will result in the minimum element in the array
	//Signature> Self, ArrayList<T>, ICompare<T> > T 
	<T> int getMinIndex(List<T> arr, ICompare<T> compareFunc) {
		if (arr.size()==0) {throw new RuntimeException("Can't get minimum for an empty arraylist.");}
		else  {return this.getMinIndexHelper(arr.subList(1, arr.size()) , compareFunc, 0, 0, arr.get(0));}}
	
	//METHOD GETMINHELPER
	<T> int getMinIndexHelper(List<T> arr, ICompare<T> compareFunc, int indexCounter, int smallestValueIndex, T smallestValue ) {
		if (arr.size() == 0) {return  smallestValueIndex;}
		else {if (compareFunc.compare(smallestValue, arr.get(0)) <= 0) {return this.getMinIndexHelper(arr.subList(1, arr.size()), compareFunc, indexCounter+1, smallestValueIndex, smallestValue);}
				 else {return this.getMinIndexHelper(arr.subList(1, arr.size()), compareFunc, indexCounter+1, indexCounter+1, arr.get(0));}}}
	
	
	//METHOD SWAP
	//Will swap two elements for the given index, will throw exception, if any of the index is out of bounds
	//Signature> Self, ArrayList<T>, int, int > Void
	<T> void swap(ArrayList<T> arr, int i1, int i2) {
		if (i1 >= arr.size() || i2 >= arr.size()) {throw new RuntimeException ("Given index is more than the length of the array");}
		else {
			T tempValue1 = arr.get(i1);
			T tempValue2 = arr.get(i2);
			arr.set(i2, tempValue1);
			arr.set(i1, tempValue2);}}
	
	//METHOD MERGE
	//Merges two sorted list in one single list and returns the merged list
	//Signature> Self, ArrayList<T> arr1, ArrayList<T> > ArrayList<T>
	<T> ArrayList<T> mergeLists(ArrayList<T> arr1, ArrayList<T> arr2, ICompare<T> compareFunc) {
		if (arr1.size() == 0) {return arr2;}
		else if (arr2.size()==0) {return arr1;}
		else {
			ArrayList<T> newArr1 = new ArrayList<T> (arr1.subList(1, arr1.size()));
			ArrayList<T> newArr2 = new ArrayList<T> (arr2.subList(1, arr2.size()));
			return this.insert(arr1.get(0), this.insert(arr2.get(0), this.mergeLists(newArr1, newArr2, compareFunc), compareFunc), compareFunc);}} //we wish for an insert function 
	
	//METHOD INSERT
	//Inserts an element T in the appropriate position in a sorted list and returns a new list
	//Signature> Self, T , ArrayList<T> > ArrayList<T>
	<T> ArrayList<T> insert (T elem, ArrayList<T> arr, ICompare<T> compareFunc) {
		int counter = 0;
		while (counter < arr.size() && compareFunc.compare(elem, arr.get(counter)) >= 0) {
			counter = counter +1 ;}
		arr.add(counter, elem);
		return arr;
	}
	
	//METHOD MERGESORT
	//Divides the lists in two halves, and then merges the two recursively sorted lists and returns a new list
	//Signature> Self, ArrayList<T>, ICompare<T> > ArrayList<T>
	<T> ArrayList<T> mergeSort(ArrayList<T> arr1, ICompare<T> compareFunc) {
		if (arr1.size() == 0) {throw new RuntimeException("Can't sort an empty list");}
		if (arr1.size() == 1) {return arr1;}
		else {
			ArrayList<T> subList1 = new ArrayList<T> (arr1.subList(0, arr1.size()/2));
			ArrayList<T> subList2 = new ArrayList<T> (arr1.subList((arr1.size()/2) , arr1.size()));
			return this.mergeLists(this.mergeSort(subList1, compareFunc), this.mergeSort(subList2, compareFunc), compareFunc);}}
	
	
	
	//METHOD SELECTIONSORT
	//Compares 1st element with the lowest element in the rest of the list, and swaps it and then recurses, returns the sorted list
	//Total iterations required > n-1 , where n is size of list
	//Signature> ArrayList<T> arr, ICompare<T> > Arr
	<T> ArrayList<T> selectionSort(ArrayList<T> arr, ICompare<T> compareFunc) {
		for (int i = 0 ; i < arr.size() -1; i++) {
			int minIndex = this.getMinIndex(arr.subList(i, arr.size()), compareFunc) + i;
			this.swap(arr, minIndex, i);}
		return arr;}


	//METHOD PIVOTARRANGE
	//Takes in an element, and an array, loops through the array to arrange elements less than the pivot on left side, and elements greater than the pivot on the right side
	//Signature> T, ArrayList<T> > ArrayList<T>
	//Puts the pivot element in the correct place
	//Example> 5, list(2,1,8,4,9,3) > list(3,4,1,2,5,8,9)
	
	<T> PivotQuickSort<T> pivotArrange (T pivot, ArrayList<T> arr, ICompare<T> compareFunc) {
		ArrayList<T> tempList = new ArrayList<T>();
		tempList.add(pivot);
		int pivotIndex = 0;
		for (int i =0; i < arr.size(); i++) {
			if (compareFunc.compare(arr.get(i), pivot) >= 0) {tempList.add(arr.get(i));}
			else {tempList.add(0, arr.get(i)); pivotIndex = pivotIndex + 1;}}
		return new PivotQuickSort<T>(tempList, pivotIndex);}
	
	//METHOD QUICKSORT
	//Takes in a pivot element and rearranges the elements arund it recursively
	//Signature> Self, ArrayList<T>, ICompare<T> > ArrayList<T>
	
	<T> ArrayList<T> quickSort(ArrayList<T> arr, ICompare<T> compareFunc) {
		if (arr.size() == 0 || arr.size() == 1) {return arr;} //for an empty list or a single element list we get 
		else {
			ArrayList<T> subList = new ArrayList<T> (arr.subList(1, arr.size()));
			int pivotIndex = this.pivotArrange(arr.get(0), subList, compareFunc).pivotIndex;
			ArrayList<T> pivotList = this.pivotArrange(arr.get(0), subList, compareFunc).arr;
			ArrayList<T> subList1 = this.quickSort(new ArrayList<T> (pivotList.subList(0, pivotIndex)), compareFunc);
			ArrayList<T> subList2 = this.quickSort(new ArrayList<T> (pivotList.subList(pivotIndex+1, pivotList.size())), compareFunc);
			subList1.add(arr.get(0));
			subList1.addAll(subList2);
			return subList1;}}
	


}

//===========================================================================
//INSERTSEARCH ALGO

interface IListNew<T> {
	IListNew<T> sort(ICompare<T> compareFunc); //sorts the given list as per the comparator
	IListNew<T> insert(T element, ICompare<T> compareFunc); // adds a given element in a sorted list of elements
}

class MtListNew<T> implements IListNew<T> {
	//Defining the constructor
	MtListNew() {}
	
	public IListNew<T> sort(ICompare<T> compareFunc) {return this;} //sorting an empty list returns an empty list
	public IListNew<T> insert(T element, ICompare<T> compareFunc) {return new ConsListNew<T>(element, this);}
}

class ConsListNew<T> implements IListNew<T> {
	T first;
	IListNew<T> rest;
	
	//Defining the constructor
	ConsListNew(T first, IListNew<T> rest) {this.first = first; this.rest = rest;}
	
	//METHODS
	public IListNew<T> sort(ICompare<T> compareFunc) {
		return this.rest.sort(compareFunc).insert(this.first,compareFunc);} //inserts the given first element in a sorted list oof rest of elements
	
	public IListNew<T> insert(T element, ICompare<T> compareFunc) {
		if (compareFunc.compare(element, this.first) <= 0) {return new ConsListNew<T> (element, this);}
		else {return new ConsListNew<T>(this.first, this.rest.insert(element, compareFunc));}
	}

}



//===========================================================================
//TESTING SORTING ALGOS
//CLASS EXAMPLES

class ExamplesSort{
	ExamplesSort() {}
	
	//InsertSort TESTNG
	IListNew<Integer> insertListUnsorted = new ConsListNew<Integer> (3, new ConsListNew<Integer> (2, new ConsListNew<Integer>(1, new MtListNew<Integer>())));
	IListNew<Integer> insertListSorted = new ConsListNew<Integer> (1, new ConsListNew<Integer> (2, new ConsListNew<Integer>(3, new MtListNew<Integer>())));
	void testInsertSort(Tester t) {
		t.checkExpect(insertListUnsorted.sort(new CompareInts()), insertListSorted);}

	//TEST SELECTION SORT
	void testSelectionSort (Tester t) {
		ArrayList<Integer> selectionSortUnsorted = new ArrayList<Integer> (Arrays.asList(3,5,2,6,10));
		ArrayList<Integer> selectionSortSorted = new ArrayList<Integer> (Arrays.asList(2,3,5,6,10));
		Utils util = new Utils();
		t.checkExpect(util.selectionSort(selectionSortUnsorted, new CompareInts()), selectionSortSorted);}

	//TEST SWAP AND GETMININDEX
	void testUtilsSwapAndGetMinIndex(Tester t) {
		ArrayList<Integer> selectionSortUnsorted = new ArrayList<Integer> (Arrays.asList(3,5,2,6,10));
		Utils util = new Utils();
		util.swap(selectionSortUnsorted, 1, 3);
		t.checkExpect(selectionSortUnsorted.get(1), 6);
		t.checkExpect(selectionSortUnsorted.get(3), 5);
		t.checkExpect(util.getMinIndex(selectionSortUnsorted, new CompareInts()), 2);
	}
	
	//Test for Merging two lists
	void testMergeLists(Tester t) {
		ArrayList<Integer> arr1 = new ArrayList<Integer> (Arrays.asList(2,3,5,8));
		ArrayList<Integer> arr2 = new ArrayList<Integer> (Arrays.asList(4,7,8,10));
		ArrayList<Integer> arr1Insert = new ArrayList<Integer> (Arrays.asList(2,3,4,5,8));
		ArrayList<Integer> arr3 = new ArrayList<Integer> (Arrays.asList(2,3,4,4,5,7,8,8,10));
		Utils util = new Utils();
		t.checkExpect(util.insert(4, arr1, new CompareInts()), arr1Insert);
		t.checkExpect(util.mergeLists(arr1, arr2, new CompareInts()), arr3);}
	
	//TEST MERGESORT
	void testMergeSort(Tester t) {
		ArrayList<Integer> mergeSortUnsorted = new ArrayList<Integer> (Arrays.asList(3,5,2,6,10));
		ArrayList<Integer> mergeSortSorted = new ArrayList<Integer> (Arrays.asList(2,3,5,6,10));
		Utils util = new Utils();
		t.checkExpect(util.mergeSort(mergeSortUnsorted, new CompareInts()), mergeSortSorted);}
	
	//TEST PIVOTARRANGE
	void testPivotArrange(Tester t) {
		ArrayList<Integer> pivotList = new ArrayList<Integer> (Arrays.asList(2,1,8,4,9,3));
		ArrayList<Integer> pivotArrangeList = new ArrayList<Integer> (Arrays.asList(3,4,1,2,5,8,9));
		Utils util = new Utils();
		t.checkExpect(util.pivotArrange(5, pivotList, new CompareInts()).arr, pivotArrangeList);}
	
	//TEST QUICKSORT
	void testQuickSort(Tester t) {
		ArrayList<Integer> quickSortUnsorted = new ArrayList<Integer> (Arrays.asList(4,5,2,6,10));
		ArrayList<Integer> quickSortSorted = new ArrayList<Integer> (Arrays.asList(2,4,5,6,10));
		Utils util = new Utils();
		t.checkExpect(util.quickSort(quickSortUnsorted, new CompareInts()), quickSortSorted);}
	
	//TESTING FOR HEAP
	void testHeap(Tester t) {
		Heap<Integer> testHeap = new Heap<Integer>(new ArrayList<Integer>());
		testHeap.add(80, new CompareInts());
		testHeap.add(100, new CompareInts());
		testHeap.add(200, new CompareInts());
		testHeap.add(30, new CompareInts());
		ArrayList<Integer> sortedList = new ArrayList<Integer> (Arrays.asList(30, 80, 100));
		t.checkExpect(testHeap.contents.get(0), 200);
		t.checkExpect(testHeap.contents.get(1), 80);
		t.checkExpect(testHeap.contents.get(3), 30);
		int removedElem = testHeap.remove(new CompareInts());
		t.checkExpect(testHeap.size(), 3);
		t.checkExpect(testHeap.contents.get(0), 100);
		t.checkExpect(testHeap.contents.get(1), 80);
		t.checkExpect(testHeap.contents.get(2), 30);
		t.checkExpect(testHeap.removeSort(new CompareInts()), sortedList);}


}



















