import tester.*;
import java.util.*;

//=================================================================================
//CLASS STRING CREATOR

class StringCreator {
	String string;
	Stack<String> lastStrings;
	
	//Defining the constructor
	StringCreator() {this.string = ""; this.lastStrings = new Stack<String>();}
	
	//Convenience constructor
	StringCreator(String string) {this.string = string;}
	
	//TEMPLATE
	/*
	 * FIELDS
	 * ...this.string ....String
	 * METHODS
	 * ...this.add(Character c) ...void
	 * ...this.remove()....void
	 * ...this.getString()....String
	 * 
	 */
	
	//METHODS
	
	//METHOD ADD
	//Signature> Self, Character > String
	//Adds the given character to the end of a string
	void add(Character c) {
		this.lastStrings.push(this.string);
		this.string = this.string + c;}
	
	//METHOD REMOVE
	//Signature> Self > String
	//Removes a character from the end of a string
	void remove() {
		this.lastStrings.push(this.string);
		String tempString = "";
		for (int i=0; i< this.string.length()-1; i++) {
			tempString = tempString + this.string.charAt(i);}
		this.string = tempString;}
	
	//METHOD GETSTRING
	//Signature> Self > String
	//Returns the string for the class
	String getString() {
		return this.string;}
	
	//METHOD UNDO
	//Signature > Self > Void
	//undoes the last operation done on the string
	void undo() {
		if (this.lastStrings.isEmpty()) {return;}
		else {this.string = this.lastStrings.pop();}}
	}




//=================================================================================
//CLASS UTILS

class Utils {
	<T> ArrayList<T> reverse(ArrayList<T> source) {
		Stack<T> tempStack = new Stack<T>(new Deque<T>());
		ArrayList<T> returnList = new ArrayList<T>();
		for (int i = 0; i< source.size(); i++) {tempStack.push(source.get(i));} //we first add to the stack on top
		for (int i = 0; i <source.size(); i++) {returnList.add(tempStack.pop());} // adn then pop from the stack and add back to he returnList
		return returnList;}
}



//=================================================================================
//CLASS STACK
//Can only push or pop from the top

class Stack<T> {
  Deque<T> contents;
  
  //Simple constructor
  Stack(Deque<T> contents) {this.contents = contents;}
  
  //Convenience Constructor
  Stack() {this.contents = new Deque<T>();}
  
  //TEMPLATE
  /*
   * FIELDS
   * ...this.contents ...Deque
   * METHODS
   * ...this.push(T item)...void
   * ...this.isEmpty() ...boolean
   * ...this.pop() .....T
   * METHODS FROM DEQUE
   * ...this.contents.size() ....int
	 * ...this.contents.addAtHead(ANode<T> arg) ....void
	 * ...this.contents.addAtHead(ANode<T> arg) ....void
	 * ...this.contents.addAtTail(ANode<T> ard) ...void
	 * ...this.contents.removeeFromHead()....ANode<T>
	 * ...this.contents.removeeFromTail()....ANode<T>
	 * ...this.contents.find(IPredicate<T> arg) ...ANode<T>
	 * ...this.contents.removeNode(ANode<T> arg)...void
   */
  
  //DEFINING METHODS
  
  //METHOD PUSH
  // adds an item to the top of the stack
  //Signature> Self  , T > Void
  void push(T item) {
  	ANode<T> newNode = new Node<T>(item);
  	this.contents.addAtHead(newNode) ;}
  
  //METHOD ISEMPTY
  //Size of the deque is 0
  boolean isEmpty() {return this.contents.size() ==0;}
  
  //METHOD POP
  // removes an item to the top of the stack
  //Signature> Self  > Void
  T pop() {return this.contents.removeFromHead().getData();}
  
}





//=================================================================================
//CLASS EXAMPLES STACK

class ExamplesStack {
	ExamplesStack() {}
	
	ANode<String> nodeSentinel; 
	ANode<String>  node1 ;
	ANode<String>  node2 ;
	ANode<String>  node3 ;
	ANode<String>  node4 ;
	Deque<String> emptyD ;
	Deque<String> orderedD ;
	Stack<String> stackEmpty;
	Stack<String> stackNew;
	
	
	

	void initConditions() {
		nodeSentinel = new Sentinel<String>();
		node1 = new Node<String>("abc", nodeSentinel, nodeSentinel);
		node2 = new Node<String>("bcd", node1, nodeSentinel);
		node3 = new Node<String>("cde", node2, nodeSentinel);
		node4 = new Node<String>("def", node3, nodeSentinel);
		emptyD = new Deque<String>();  //empty deque
		orderedD = new Deque<String>(nodeSentinel);
		stackEmpty = new Stack<String>(emptyD);
		stackNew = new Stack<String>(orderedD);}
	
	void testPush(Tester t) {
		this.initConditions();
		this.stackNew.push("geh");
		t.checkExpect(this.stackNew.contents.header.next.getData(), "geh");}
	
	void testIsEmpty(Tester t) {
		this.initConditions();
		t.checkExpect(this.stackNew.isEmpty(), false);
		t.checkExpect(this.stackEmpty.isEmpty(), true);}
	
	void testPop(Tester t) {
		this.initConditions();
		t.checkExpect(this.stackNew.pop(), "abc");}
	
	void testReverse(Tester t) {
		ArrayList<Integer> testList = new ArrayList<Integer>(Arrays.asList(1,2,3));
		ArrayList<Integer> reverseList = new ArrayList<Integer>(Arrays.asList(3,2,1));
		Utils util = new Utils();
		t.checkExpect(util.reverse(testList), reverseList);}
	
	void testRun(Tester t) {
    StringCreator creator = new StringCreator();
    t.checkExpect(creator.getString(),"");
    creator.add('c');
    creator.add('d');
    t.checkExpect(creator.getString(),"cd");
    creator.add('e');
    t.checkExpect(creator.getString(),"cde");
    creator.remove();
    creator.remove();
    t.checkExpect(creator.getString(),"c");
    creator.undo(); //undoes the removal of 'd'
    t.checkExpect(creator.getString(),"cd");
    creator.undo(); //undoes the removal of 'e'
    creator.undo(); //undoes the addition of 'e'
    t.checkExpect(creator.getString(),"cd");
    creator.add('a');
    t.checkExpect(creator.getString(),"cda");
    creator.undo(); //undoes the addition of 'a'
    creator.undo(); //undoes the addition of 'd'
    creator.undo(); //undoes the addition of 'c'
    t.checkExpect(creator.getString(),"");
    creator.undo(); //no effect, there is nothing to undo
}
	
	
}












