import tester.*;
//============================================================
//PROBLEM IBOOK

//IBOok is one of -
// -- Book is of type (String title, String author, int dayTaken)
// -- RefBook is of type (String title,int dayTaken)
//-- AudioBook is of type (String title, String author, int dayTaken)
// interp . as type of books availale in a library

interface IBook {
	int daysOverdue ();
	boolean isOverdue();
	int computeFine();
}

//ABook is an abstract class which partially implements IBook
abstract class ABook implements IBook {
	String title;
	int daysTaken;
	
	ABook(String title, int dayTaken) {
		this.title  = title;
		this.daysTaken = dayTaken;
	}
	
	//TEMPLATE
	/*
	 * FIELDS
	 * ...this.title ...String
	 * ...this.daysTaken ...int
	 * METHODS
	 * ... this.daysOverdue ...int
	 * ... this.iSOverdue ...boolean
	 * ...this.computeFine ...int
	 */
	
	//METHOD DAYSOVERDUE
	//Signature -> Self -> Int
	// Returns the no. of days past the due date (14 for B and AB, 2 for RB)
	public int daysOverdue () {
		if (this.daysTaken-14 > 0) {return this.daysTaken - 14;}
		else {return 0;}
	}
	
	//METHOD ISOVERDUE
	//Signature -> Self -> Booleam
	// Returns whether a library book is overdue or not
	public boolean isOverdue () {
		if (this.daysOverdue() > 0) {return true;}
		else {return false;}
	}
	
	//METHOD COMPUTEFINE
	//Signature -> Self -> INT (to be interp.
	// Returns the fine for overdue books, 10 cents per day for book and ref book, 20 for audio books
	public int computeFine () {
		if (this.isOverdue()) {return this.daysOverdue() *10;}
		else {return 0;}
		
	}
	
	
}

//Book is of type (String title, String author, int dayTaken)
// interp. as a type of IBook in a library

class Book extends ABook {
	String author;

	Book(String title, String author, int daysTaken)  {
		super(title, daysTaken);
		this.author = author;
	}
	
	//TEMPLATE
	/*
	 * FIELDS
	 * ...this.title ...String
	 * ...this.author ...String
	 * ...this.daysTaken ...int
	 * METHODS
	 * ... this.daysOverdue ...int (inherited)
	 * ...this.isOverdue .....boolean (inherited)
	 * ...this.computeFine...int (inherited)
	 */

}


//RefBook is of type (String title,int dayTaken)
//interp. as a type of IBook in a library
class RefBook extends ABook {

	RefBook(String title, int daysTaken)  {
		super(title, daysTaken);
	}
	//TEMPLATE
	/*
	 * FIELDS
	 * ...this.title ...String
	 * ...this.author ...String
	 * ...this.daysTaken ...int
	 * METHODS
	 * ... this.daysOverdue ...int
	 * ...this.isOverdue .....boolean (inherited)
	 * ...this.computeFine...int (inherited)
	 */
	
	
	//METHOD DAYSOVERDUE
	//Signature -> Self -> Int
	// Returns the no. of days past the due date (14 for B and AB, 2 for RB)
	@Override
	public int daysOverdue() {
		if (this.daysTaken-2 > 0) {return this.daysTaken - 2;}
		else {return 0;}
	}
}


//AudioBook is of type (String title, String author, int dayTaken)
//interp. as a type of IBook in a library

class AudioBook extends ABook {
	String author;

	AudioBook(String title, String author, int dayTaken)  {
		super(title, dayTaken);
		this.author = author;

		//TEMPLATE
		/*
		 * FIELDS
		 * ...this.title ...String
		 * ...this.author ...String
		 * ...this.daysTaken ...int
		 * METHODS
		 * ... this.daysOverdue ...int (inherited)
		 * ...this.isOverdue .....boolean (inherited)
		 * ...this.computeFine...int (overridden
		 */

	}

	//METHOD COMPUTEFINE
	//Signature -> Self -> INT (to be interp. as cents)
	// Returns the fine for overdue books, 10 cents per day for book and ref book, 20 for audio books
	public int computeFine () {
		if (this.isOverdue()) {return this.daysOverdue() *20;}
		else {return 0;}
	}
}


//============================================================
//PROBLEM MAYBE

//IMaybe is of type:
// -- MTMaybe is of type Null
// -- MaybeValue is of type value Int
// Represents either there is some value or no value

interface IMaybe {}

//MTMaybe is of type null
//Doesn't take any value and returns an empty constructur

class MTMaybe implements IMaybe {
	MTMaybe() {}
}

//MaybeValue is of type value Int
//interp. as one type of Maybe X
class MaybeValue implements IMaybe {
	int value;
	
	MaybeValue(int value) {this.value = value;}
}

//============================================================
//PROBLEM ILoInt

//ILoInt is of type:
//-- MTLoInt is an empty list
//-- ConsLoInt is of type (value - int, ILoInt) where value is a positive integers
//Represents a list of integets

interface ILoInt {
	int highestFNo(); // returns the number that appears in the longest consecutive sublist;
	int highestFNoHelper(int currentNo, int consecutiveNo, int countConsecutiveCurrent, int countConsectutiveList); //Helper function for highestFNo
}

//MTLoInt is an empty list of type int
//Doesn't take any value and returns an empty constructur

class MTLoInt implements ILoInt {
	MTLoInt () {}
	
	//TEMPLATE
	/*
	 * FIELDS
	 * METHODS
	 * ...this.highestFNo ...int
	 * ...this.higestFNoHelper(int currentNo, int consecutiveNo, int countConsecutiveCurrent, int countConsectutiveList) ....int
	 */
	
	//METHOD HighestFNo
	//Signature> Self > Int
	// returns the number that appears in the longest consecutive sublist;
	public int highestFNo () {return -1;} // returns -1 to represent it was an empty list
	
	public int highestFNoHelper(int currentNo, int consecutiveNo, int countConsecutiveCurrent, int countConsectutiveList) {return consecutiveNo;}
	
}

//ConsLoInt is of type (value - int, ILoInt)
// interp. as a pair of ILoInt

class ConsLoInt implements ILoInt {
	int first;
	ILoInt rest;

	ConsLoInt(int first, ILoInt rest) {this.first = first; this.rest = rest;}

	//TEMPLATE
	/*
	 * FIELDS
	 * METHODS
	 * ...this.highestFNo ...int
	 */

	//METHOD HighestFNo
	//Signature> Self > Int
	// returns the number that appears in the longest consecutive sublist;
	
	public int highestFNo() {
		return this.rest.highestFNoHelper(this.first, this.first, 1, 1);
	}
	
	//Below are the details of the args passed
	//-- currentNo - it is the current running consecutive No
	//--consecutiveNo - it is the longest running consecutive number till now in the list
	//-- countConsecutiveCurrent - Consecutive count of the current consecutive number
	//-- countConsecutiveList - Consecutive count of the the longest running consecutive number till now in the list
	
	
	public int highestFNoHelper(int currentNo, int consecutiveNo, int countConsecutiveCurrent, int countConsectutiveList) {
		if (this.first== currentNo && countConsecutiveCurrent >= countConsectutiveList)  {
			return this.rest.highestFNoHelper(this.first, this.first,countConsecutiveCurrent+1, countConsecutiveCurrent+1); 
		} else if (this.first== currentNo && countConsecutiveCurrent < countConsectutiveList) {
			return this.rest.highestFNoHelper(this.first, consecutiveNo,countConsecutiveCurrent+1, countConsectutiveList);
		} else {return this.rest.highestFNoHelper (this.first, consecutiveNo, 1, countConsectutiveList) ; }
	}



}

//============================================================
//PROBLEM TASKS

// task is of type (id int, ILoTasks)
// interp. as an id f task and the list of tasks which must be completed before the given task

class Task {
	int id;
	ILoTask lot;
	
	Task(int id, ILoTask lot) {this.id = id; this.lot = lot;}
	
	//TEMPLATE
	/*
	 * FIELDS
	 * ...this.id ...int
	 * ...this.lot ...ILoTask
	 * METHODS
	 * ...this.completeTaskPosssible(ILoTask lot) ... boolean
	 * METHODS from FIELDS
	 * ...this.lot.completeTasks() ......ILoTask
	 * ...this.lot.isEmpty() ................boolean
	 * ...this.lot.containsSingle(int id) ........boolean
	 * ...this.lot.cotainsList(ILoTask lor) ..... boolean
	 * ...this.lot.getFirst() ...... Task
	 * ...this.lot.getRest() .... ILoTask
	 */
	
	public boolean completeTaskPossible (ILoTask lotinput) {
    if (this.lot.isEmpty()) return true;  // No prerequisites needed
    return lotinput.containsList(this.lot);
}
	
}

//ILoTask is one of:
//--MTLoTask - an empty list of tasks
//--ConsLoTask - a pair of task, list of tasks 

interface ILoTask {
	ILoTask completeTasks();
	ILoTask completeTasksHelper(ILoTask fullist); //helper function to implement completeTasks
	boolean isEmpty(); //just to check for empty list
	boolean containsSingle(int id) ; //CHecks if a given task id is present in a given list of tasks
	boolean containsList(ILoTask lor);  // checks if all the ids from the argument list are present in the object list
	int getFirst() ; //represents the ID from the first element in an ILoTask
	ILoTask getRest() ; //represents the rest element in an ILoTask
	
}

//MTLoTask is an empty list of tasks
class MTLoTask implements ILoTask {
	MTLoTask () {}
	
	//TEMPLATE
	/*
	 * FIELDS
	 * METHODS
	 * ...this.completeTasks() ...ILoTask
	 * ...this.completeTasksHelper() .... ILoTask
	 * ...this.isempty?............boolean
	 * ...this.containsSingle(int id) ....boolean
	 * ...this.containsList(ILoTask lor) ... boolean
	 * ...this.getFirst() ...int
	 * ...this.getRest() ... ILoTask
	 */
	
	//METHOD GETFIRST
	//Signature > Self > ID
	// represents the ID from the first element in an ILoTask
	public int getFirst () {return -1;} // assuing no id can be negative
	
	//METHOD GETREST
	//Signature > Self > ID
	// represents the ID from the first element in an ILoTask
	public ILoTask getRest () {return this;} // returns an empty list
	
	
	
	//METHOD CONTAINSLIST
	//Signature > Self, ILoTask > Boolean
	//checks if all the ids from the argument list are present in the object list
	public boolean containsList(ILoTask lor) {return lor.isEmpty();}
	
	
	//METHOD CONTAINSSINGLE
	//Signature > Self, Int > Boolean
	//Checks whether the given int is present in a list of tasks
	public boolean containsSingle(int id) {return false;}
	
	
	//METHOD ISEMPTY
	//Signature > Self > Boolean
	//Represents whether the list is empty or not
	public boolean isEmpty() {return true;}
	
	//METHOD COMPLETETASK
	//Signature> Self, ILoTask
	//Produces a list of tasks which can be completed by the given list of tasks
	public ILoTask completeTasks() {
		return this; // we return an empty list if an empty list is passed
	}
	
	public ILoTask completeTasksHelper(ILoTask fullist) {return this;}
	
}

//ConsLoTask is a pair of task, list of tasks (mutual reference to task, self reference to ILoTask)

class ConsLoTask implements ILoTask {
	Task first;
	ILoTask rest;
	
	ConsLoTask(Task first, ILoTask rest) {
		this.first =  first; 
		this.rest = rest;}
	
//TEMPLATE
	/*
	 * FIELDS
	 * ... this.first ...task
	 * ...this.rest ....ILoTask
	 * METHODS
	 * ...this.completeTasks() ...ILoTask
	 * ...this.isEmpty() .....boolean
	 * ...this.containsSingle(int id) ....boolean
	 * ...this.containsList(IloTask lor) ... boolean
	 * ... this.getFirst() ....int
	 * ...this.getRest() ....ILoTask
	 * METHODS from FIELDS
	 * ...this.task.completeTaskPossible() ... boolean
	 */
	
	
	//METHOD GETFIRST
	//Signature > Self > ID
	// represents the ID from the first element in an ILoTask
	public int getFirst () {return this.first.id;} // returns the id of the first element
	
	//METHOD GETFIRST
	//Signature > Self > ID
	// represents the ID from the first element in an ILoTask
	public ILoTask getRest () {return this.rest;} // returns the rest of the ConsList
	
	
	//METHOD CONTAINSLIST
	//Signature > Self, ILoTask > Boolean
	//checks if all the ids from the argument list are present in the object list
	public boolean containsList(ILoTask lor) {
		if (lor.isEmpty()) {return true;}
		else {return this.containsSingle(lor.getFirst()) && this.containsList(lor.getRest());}
	}
	
	//METHOD CONTAINSSINGLE
	//Signature > Self, Int > Boolean
	//Checks whether the given int is present in a list of tasks
	public boolean containsSingle(int id) {
		if (this.first.id == id) {return true;}
		else {return this.rest.containsSingle(id);}
	}
	
	//METHOD ISEMPTY
	//Signature > Self > Boolean
	//Represents whether the list is empty or not
	public boolean isEmpty() {return false;}
	
	
	//METHOD COMPLETETASK
	//Signature> Self, ILoTask
	//Produces a list of tasks which can be completed by the given list of tasks

	public ILoTask completeTasks() {
		return this.completeTasksHelper(this); // Pass the full list
	}

	public ILoTask completeTasksHelper(ILoTask fullList) {
		if (this.first.completeTaskPossible(fullList)) {
			return new ConsLoTask(this.first, this.rest.completeTasksHelper(fullList));
		}
		else {
			return this.rest.completeTasksHelper(fullList);
		}
	}
	
}





//============================================================
//EXAMPLES CLASS

class Lab4Examples {
	Lab4Examples () {}
	
	//Examples and methds for IBook
	
	IBook b1 = new Book ("HP1", "JKR", 5);
	IBook b2 = new Book ("Quantum", "Manjit Kumar", 20);
	IBook b3 = new Book ("HP2", "JKR", 0);
	
	IBook  rb1 = new RefBook ("Physics1" , 5);
	IBook  rb2 = new RefBook ("Physics2" , 1);
	
	IBook ab1 = new AudioBook("HP3", "JKR", 10);
	IBook ab2 = new AudioBook("LOTR", "Tolkien", 20);
	
	boolean testDaysOverDue (Tester t) {
		return t.checkExpect(b1.daysOverdue(), 0) &&
				t.checkExpect(b2.daysOverdue(), 6) &&
				t.checkExpect(rb2.daysOverdue(), 0) &&
				t.checkExpect(rb1.daysOverdue(), 3) &&
				t.checkExpect(ab1.daysOverdue(), 0) &&
				t.checkExpect(ab2.daysOverdue(), 6);
	}
	
	boolean testIsOverdue(Tester t) {
		return t.checkExpect(b1.isOverdue(), false) &&
				 t.checkExpect(b2.isOverdue(), true) &&
				 t.checkExpect(rb2.isOverdue(), false) &&
				 t.checkExpect(rb1.isOverdue(), true) &&
				 t.checkExpect(ab1.isOverdue(), false) &&
				 t.checkExpect(ab2.isOverdue(), true) ;
						
	}
	
	boolean testComputeFine(Tester t) {
		return t.checkExpect(b1.daysOverdue(), 0) &&
		t.checkExpect(b2.computeFine(), 60) &&
		t.checkExpect(rb2.computeFine(), 0) &&
		t.checkExpect(rb1.computeFine(), 30) &&
		t.checkExpect(ab1.computeFine(), 0) &&
		t.checkExpect(ab2.computeFine(), 120);
	}
	
//Examples for Maybe X
	
	IMaybe maybe1 = new MTMaybe(); //represents an empty/null Maybe
	IMaybe maybe2 = new MaybeValue(10); //represents an empty Maybe
	
	
	//Examples and tests for ILoInt
	
	ILoInt loi1 = new MTLoInt () ;
	ILoInt loi2 = new ConsLoInt (1, loi1);
	ILoInt loi3 = new ConsLoInt (1, loi2);
	ILoInt loi4 = new ConsLoInt (5, loi3);
	ILoInt loi5 = new ConsLoInt (5, loi4);
	ILoInt loi6 = new ConsLoInt (5, loi5);
	ILoInt loi7 = new ConsLoInt (4, loi6);
	ILoInt loi8 = new ConsLoInt (3, loi7);
	ILoInt loi9 = new ConsLoInt (4, loi8);
	ILoInt loi10 = new ConsLoInt (4, loi9);
	ILoInt loi11= new ConsLoInt (4, loi10);
	
	boolean testHighestFNo(Tester t) {
		return t.checkExpect(loi11.highestFNo(), 5);
	}
	
			
	
	//Examples and tests for Tasks Problem
	
	ILoTask mt = new MTLoTask();
	Task t1 = new Task (100, mt); // base task which does not require any prereuisite
	Task t2 = new Task (101, mt); //base task which does not require any prerequisite
	ILoTask lot1 = new ConsLoTask (t1, mt); //List of tasks with only a single element
	ILoTask lot2 = new ConsLoTask (t2, mt); //List of tasks with only a single element
	Task t3 = new Task(102, lot1); //Task 102 with prerequisites for 100
	Task t4 = new Task(103, lot2); //Task 103 with prerequisite for 101
	ILoTask lot3 = new ConsLoTask(t4, new ConsLoTask(t3, mt)); // LIst of tasks for t4 and t3
	Task t5 = new Task(104, lot3); // Task 104 which has pre of 102 which has pr of 100, and pr of 103 which has pr of 101
	ILoTask lot4 = new ConsLoTask (t5, lot3); //
	
	boolean testCompleteTasks(Tester t) {
	
		return //t.checkExpect(lot3.completeTasks(), mt) &&
				t.checkExpect(lot4.completeTasks(), new ConsLoTask(t5, mt));
	
}
}

