import tester.* ;

//================================================================
//INTERFACE IPRED for LISTS
//Will be used for filtering out elements
interface IPred<T> {
	boolean apply (T arg); //will apply to a function of type 
}

class GreaterThanThree implements IPred<Integer> {
	GreaterThanThree() {}
	public boolean apply(Integer num) {return num > 3;} //checks if the number is greater than 3 or not
}

class IsJSONObject implements IPred <IJSON> {
	IsJSONObject() {}
	public boolean apply(IJSON json) {return (json instanceof JSONObject);} 
}

class IsNotJSONBlank implements IPred<IJSON> {
	IsNotJSONBlank() {}
	public boolean apply(IJSON json) {return !(json instanceof JSONBlank);}
}

//================================================================
//INTERFACE IFUNC2 for FOLDR
//Represents functions of signature A1, A2 -> R, for some argument type A1 and A2 and
// result type R
interface IFunc2<A1, A2, R> {
	R apply (A1 a1 , A2 a2);
}

//function object for summing up a list of integers
class sumList implements IFunc2<Integer, Integer, Integer> {
	sumList() {}
	public Integer apply(Integer currentNum, Integer sumTillNow) {
		return currentNum + sumTillNow;
	}
}

//function object which takes in an integer and a JSON pbject and returns the sum of values
class sumListJSON implements IFunc2<IJSON, Integer, Integer> {
	sumListJSON() {}
	public Integer apply (IJSON json, Integer num) { return json.accept(new JSONToNumber()) + num;}
}

//================================================================
//INTERFACE FUNCTION OBJECTS 
// Represents functions of signature A -> R, for some argument type A and
// result type R
interface IFunc<A, R> {
  R apply(A input);
}


//FUNCTION object to extract JSON value from the pair
class getValueFromListOfPairs implements IFunc<Pair<String, IJSON>, IJSON> {
	public IJSON apply (Pair<String, IJSON> jsonPair) {
		return jsonPair.y;
	}
}


//================================================================
//GENERIC PAIRS
class Pair<X, Y> {
  X x;
  Y y;
 
  Pair(X x, Y y) {this.x = x; this.y = y;}
}


//================================================================
// GENERIC LIST
interface IList<T> {
  // map over a list, and produce a new list with a (possibly different)
  // element type
  <U> IList<U> map(IFunc<T, U> f);
  <R> R foldR (IFunc2<T, R, R> f, R initialValue); // defining the method for foldr
  IList<T> find(IPred<T> f); // the predicate function object for filtering the list
  <U> U findSolutionOrElse(IFunc<T, U> convert, IPred<U> pred, U backup); //finds the first element in the list where the result of function applied to that element passes the predicate, and then returns that result
  
  T getFirst() ; //Gets the first element from ConsList, throws exception for MtList
  IList<T> getRest() ; //Gets the rest element from ConsList, throws exception for MtList
  boolean isEmpty() ; //Gives whether a particular list is empty or not
  
  
}
 
// empty generic list
class MtList<T> implements IList<T> {
  public <U> IList<U> map(IFunc<T, U> f) {return new MtList<U>();}
  public  <R> R foldR (IFunc2<T, R, R> f, R initialValue) {return initialValue;} //we reutnr the initial value in case of an MtList
  public IList<T> find(IPred<T> f) {return new MtList<T>();} //returns an empty list of the same type
  public <U> U findSolutionOrElse(IFunc<T, U> convert, IPred<U> pred, U backup) {return backup;} //Returns the backup for an empty list
  public T getFirst() {throw new RuntimeException ("Can't access first element from en empty list");}
  public IList<T> getRest() {throw new RuntimeException ("Can't access rest element from en empty list");}
  public boolean isEmpty() {return true;}
}
 
// non-empty generic list
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;
 
  ConsList(T first, IList<T> rest) {this.first = first;this.rest = rest;}
 
  public <U> IList<U> map(IFunc<T, U> f) {return new ConsList<U>(f.apply(this.first), this.rest.map(f));}
  public  <R> R foldR (IFunc2<T,R, R> f, R initialValue) {return f.apply(this.first, this.rest.foldR(f, initialValue));}
  public IList<T> find(IPred<T> f) {
  	if(f.apply(this.first)) {return new ConsList<T> (this.first, this.rest.find(f));}
  	else {return this.rest.find(f);}
  }
  public <U> U findSolutionOrElse(IFunc<T, U> convert, IPred<U> pred, U backup) {
  	IList<U> result = this.map(convert).find(pred);
  	if (result.isEmpty()) {return backup;}
  	else {return result.getFirst();}
  }
  
  public T getFirst() {return this.first;}
  public IList<T> getRest() {return this.rest;}
  public boolean isEmpty() {return false;}

}

//================================================================
//CLASS EXAMPLES BASE

class Lab6ExamplesBase {
	Lab6ExamplesBase() {}
	
	IList<Integer> listNum = new ConsList<Integer> (3, 
													 new ConsList<Integer> (5, 
													 new ConsList<Integer>(7,
													 new MtList<Integer>())));
	
	IList<Integer> listNumGreaterThanThree = new ConsList<Integer> (5, 
																					 new ConsList<Integer>(7,
																					 new MtList<Integer>()));
	
	boolean testFoldR (Tester t) {
		return t.checkExpect(listNum.foldR(new sumList(), 0), 15);
	}
	
	//Testinf fir IPred<T>
	
	boolean testIPred (Tester t) {
		return t.checkExpect(listNum.find(new GreaterThanThree()), listNumGreaterThanThree);
	}
}

//================================================================
//================================================================
//IMPLEMENTING JSON PROBLEM

//JSON INTERFACE
//a json value
interface IJSON {
	int getNum(); //WIll be used to get number from JSONNumber class and throw runtime exceptions for others
	int getLenStr() ;//WIll be used to get len of string from JSONString class and throw runtime exceptions for others
	Boolean isTrue() ;//WIll be used to identify whether JSONBool object is true or not, throw runtime exceptions for others
	
	<T> T accept(IVisitorJSON<T> visitor); // gives back the equivalent number for each JSON objct 	
}

//================================================================
//Implementing sub-classes
//no value
class JSONBlank implements IJSON {
	public int getNum() {throw new RuntimeException("Accessing number not relevant for JSONBlank");}
	public int getLenStr() {throw new RuntimeException("Accessing length of string not relevant for JSONBlank");}
	public Boolean isTrue() {throw new RuntimeException("Accessing bool not relevant for JSONBlank");}
	public <T> T accept(IVisitorJSON<T> visitor) {return visitor.visitJSONBlank(this);}
}

//a number
class JSONNumber implements IJSON {
	int number;

	JSONNumber(int number) {
		this.number = number;
	}
	public int getNum() {return this.number;}
	public int getLenStr() {throw new RuntimeException("Accessing length of string not relevant for JSONNumber");}
	public Boolean isTrue() {throw new RuntimeException("Accessing bool not relevant for JSONNumber");}
	public <T> T accept(IVisitorJSON<T> visitor) {return visitor.visitJSONNumber(this);}
}

//a boolean
class JSONBool implements IJSON {
	boolean bool;

	JSONBool(boolean bool) {
		this.bool = bool;
	}
	public int getNum() {throw new RuntimeException("Accessing number not relevant for JSONBool");}
	public int getLenStr() {throw new RuntimeException("Accessing length of string not relevant for JSONBool");}
	public Boolean isTrue() {if (this.bool) {return true;} else {return false;}}
	public <T> T accept(IVisitorJSON<T> visitor) {return visitor.visitJSONBool(this);}
}

//a string
class JSONString implements IJSON {
	String str;

	JSONString(String str) {
		this.str = str;
	}
	public int getNum() {throw new RuntimeException("Accessing number not relevant for JSONString");}
	public int getLenStr() {return this.str.length();}
	public Boolean isTrue() {throw new RuntimeException("Accessing bool not relevant for JSONStirng");}
	public <T> T accept(IVisitorJSON<T> visitor) {return visitor.visitJSONString(this);}
}


//JSON List which implements mutual recursion with JSON
//a list of JSON values
class JSONList implements IJSON {
	IList<IJSON> values;

	JSONList(IList<IJSON> values) {this.values = values;}
	
	public int getNum() {throw new RuntimeException("Accessing number not relevant for JSONList");}
	public int getLenStr() {throw new RuntimeException("Accessing length of string not relevant for JSONList");}
	public Boolean isTrue() {throw new RuntimeException("Accessing bool not relevant for JSONList");}
	public <T> T accept(IVisitorJSON<T> visitor) {return visitor.visitJSONList	(this);}
}


//JSON PAIR
// A Pair of JSONs
//a list of JSON pairs
class JSONObject implements IJSON {
	IList<Pair<String, IJSON>> pairs;

	JSONObject(IList<Pair<String, IJSON>> pairs) {this.pairs = pairs;}

	public int getNum() {throw new RuntimeException("Accessing number not relevant for JSONObject");}
	public int getLenStr() {throw new RuntimeException("Accessing length of string not relevant for JSONbject");}
	public Boolean isTrue() {throw new RuntimeException("Accessing bool not relevant for JSONObject");}
	public <T> T accept(IVisitorJSON<T> visitor) {return visitor.visitJSONObject(this);}


}


//===================================================
//VISITOR INTERFACE


interface IVisitorJSON<T> {
	T visitJSONBlank(JSONBlank blank);
	T visitJSONNumber(JSONNumber number);
	T visitJSONBool(JSONBool bool);
	T visitJSONString(JSONString string);
	T visitJSONList(JSONList jsonList);
	T visitJSONObject(JSONObject jsonObject);
}

//FUNCTION OBJECT JSONTONUMBER
//Gets the equivalent value of every JSON object
class JSONToNumber implements IVisitorJSON<Integer>, IFunc<IJSON, Integer> { // we also ask it to implement IFUNC<T, R>
	JSONToNumber() {}
	public Integer visitJSONBlank(JSONBlank blank) {return 0;}
	public Integer visitJSONNumber(JSONNumber number) {return number.getNum();}
	public Integer visitJSONBool(JSONBool bool) {if (bool.isTrue()) {return 1;} else {return 0;}}
	public Integer visitJSONString(JSONString string) {return string.getLenStr();}
	public Integer visitJSONList(JSONList jsonList) {return jsonList.values.foldR(new sumListJSON(), 0);} //Mutual recursion - sumListJSON will call accept with argument JSONToNumber which will call visitJSONList and which will call foldr and so on
	public Integer visitJSONObject(JSONObject jsonObject) {return jsonObject.pairs.map(new getValueFromListOfPairs()).foldR(new sumListJSON(), 0);} //we convert list of pairs into list of json , then we call the foldR function object on it with base value as 0
	public Integer apply(IJSON json) {return json.accept(this);}
}

//FUNCTION OBJECT JSONFIND
//constructed with a string and returns the first JSON value it finds in a pair with that string as the keyword. If no such element can be found, return a JSONBlank.
class JSONFind implements IVisitorJSON<IJSON>, IFunc<IJSON, IJSON> {
	String key;
	JSONFind(String key) {this.key = key;}
	
	public IJSON visitJSONBlank(JSONBlank blank) {return blank;}
	public IJSON visitJSONNumber(JSONNumber number) {return new JSONBlank();}
	public IJSON visitJSONBool(JSONBool bool) {return new JSONBlank();}
	public IJSON visitJSONString(JSONString string) {return new JSONBlank();}
	public IJSON visitJSONList(JSONList jsonList)  {
		IList<IJSON> tempJSONObjectList = jsonList.values.find(new IsJSONObject());
		if (tempJSONObjectList.isEmpty()) {return new JSONBlank() ;}
		else {return tempJSONObjectList.findSolutionOrElse(new JSONFind(this.key), new IsNotJSONBlank(), new JSONBlank());}
	}
	public IJSON visitJSONObject(JSONObject jsonObject) {
		if (jsonObject.pairs.getFirst().x.equals(this.key)) {return jsonObject.pairs.getFirst().y;}
		else {
			JSONObject nextJSONObject = new JSONObject(jsonObject.pairs.getRest());
			return nextJSONObject.accept(new JSONFind(this.key));}
		}
	public IJSON apply(IJSON json) {return json.accept(this);}
}



//===================================================
//EXAMPLES JSON

class Lab6ExamplesJSON {
	Lab6ExamplesJSON() {}
	
	IJSON j1 = new JSONBlank();
	IJSON j2 = new JSONNumber(10);
	IJSON j3 = new JSONBool(true);
	IJSON j4 = new JSONBool(false);
	IJSON j5 = new JSONString("shubham");
	
	boolean testJSONToNumber (Tester t) {
		return t.checkExpect(j1.accept(new JSONToNumber()), 0)
				&& t.checkExpect(j2.accept(new JSONToNumber()), 10)
				&& t.checkExpect(j3.accept(new JSONToNumber()), 1)
				&& t.checkExpect(j4.accept(new JSONToNumber()), 0)
				&& t.checkExpect(j5.accept(new JSONToNumber()), 7);			
	}
	
	//Defining IJSON List
	IList<IJSON> listJSON = new ConsList<IJSON> (j1,
													new ConsList<IJSON> (j2,
													new ConsList<IJSON> (j3,
													new ConsList<IJSON> (j4,
													new ConsList<IJSON> (j5, new MtList<IJSON>())))));
	
	//Defining mapList for JSON
	IList<Integer> listJSONMap = new ConsList<Integer> (0, 
			 												 new ConsList<Integer> (10, 
															 new ConsList<Integer>(1,
															 new ConsList<Integer>(0,
															 new ConsList<Integer>(7, new MtList<Integer>())))));
	
	//Tesing map on IJSON List
	boolean testJSONToNumberList (Tester t) {
		return 	t.checkExpect(listJSON.map(new JSONToNumber()), listJSONMap);
	}
	
	//Testing findSolutionOrElse function on listJSON
	boolean testFindSolutionOrElse (Tester t) {
		return t.checkExpect(listJSON.findSolutionOrElse(new JSONToNumber(), new GreaterThanThree(), 100), 10);
	}
	
	//JSON List example and definitions
	IJSON j6 = new JSONList(listJSON);
	
	//Testing the sum values function for listJSON
	boolean testJSONToNumberListonJSONList (Tester t) {
		return t.checkExpect(j6.accept(new JSONToNumber()), 18);
	}
	
	//Testing for JSON pairs and Map function object to get list of Values
	
	Pair<String , IJSON> jsonPair1 = new Pair <String, IJSON>("key1", j1);
	Pair<String , IJSON> jsonPair2 = new Pair <String, IJSON>("key2", j2);
	Pair<String , IJSON> jsonPair3 = new Pair <String, IJSON>("key3", j3);
	Pair<String , IJSON> jsonPair4 = new Pair <String, IJSON>("key4", j4);
	Pair<String , IJSON> jsonPair5 = new Pair <String, IJSON>("key5", j5);
	
	IList<Pair<String, IJSON>> listJSONPairs = new ConsList<Pair<String, IJSON>> (jsonPair1, 
																						 new ConsList<Pair<String, IJSON>> (jsonPair2,
																						 new ConsList<Pair<String, IJSON>> (jsonPair3,		 
																						 new ConsList<Pair<String, IJSON>> (jsonPair4,
																						 new ConsList<Pair<String, IJSON>> (jsonPair5,
																						 new MtList<Pair<String, IJSON>>())))));
	
	IJSON jsonObject = new JSONObject (listJSONPairs);
	
	//testing for the function object getValueFromListOfPairs
	boolean testGetValueFromListOfPairs (Tester t) {
		return t.checkExpect(listJSONPairs.map(new getValueFromListOfPairs()), listJSON);
	}
	
	// testing for the method JSONToNumber for JSONObject
	boolean testJSONToNumberJSONObject (Tester t) {
		return t.checkExpect(jsonObject.accept(new JSONToNumber()) , 18);
	}
	
	//testing for the JSONFind method
	
	IJSON j7 = new JSONList (new ConsList<IJSON> (jsonObject, listJSON));
	
	boolean testJSONFind (Tester t) {
		return t.checkExpect(j7.accept(new JSONFind("key3")), j3)
				&& t.checkExpect(j1.accept(new JSONFind("key1")), new JSONBlank()) 
				&& t.checkExpect(jsonObject.accept(new JSONFind("key4")), j4);
	}
			
}














