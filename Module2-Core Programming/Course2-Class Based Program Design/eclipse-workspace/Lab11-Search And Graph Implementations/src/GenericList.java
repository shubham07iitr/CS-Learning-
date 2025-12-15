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

//================================================================
//INTERFACE FUNCTION OBJECTS 
// Represents functions of signature A -> R, for some argument type A and
// result type R
interface IFunc<A, R> {
  R apply(A input);
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
  
  IList<T> add(T arg); //adding an element to the end of the list
	IList<T> addList(IList<T> that); // adding a list at the end of another list
  boolean contains (T arg); //checks whether a given element is present in the list or not
 
  
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
  public IList<T> add(T arg) {return new ConsList <T>(arg, new MtList<T> ());}
	public IList<T> addList(IList <T> argList) {return argList;}
	public boolean contains (T arg) {return false;}
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
	public IList<T> add(T arg) {
    if (this.rest.isEmpty()) {return new ConsList<T>(this.first, new ConsList<T>(arg, new MtList<T>()));} 
    else {return new ConsList<T>(this.first, this.rest.add(arg)); } // Keep this.first!
	}
	public IList<T> addList(IList <T> argList) {
		if (argList.isEmpty()) {return this;} 
		else {return this.add(argList.getFirst()).addList(argList.getRest());}
	}
	public boolean contains (T arg) {
		if (this.first.equals(arg)) {return true;} 
		else {return this.rest.contains(arg);}
	}
}