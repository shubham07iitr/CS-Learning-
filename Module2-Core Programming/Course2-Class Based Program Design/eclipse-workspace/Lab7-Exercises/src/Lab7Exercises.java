import tester.*;

//==================================================================
//CLASS PERSON
//represents a Person with a user name and a list of buddies
class Person {

 String username;
 ILoBuddy buddies;
 //CONSTRUCTOR
 Person(String username) {this.username = username;this.buddies = new MTLoBuddy();}
 //METHODS
 
 //METHOD HASDIRECTBUDDY
 // returns true if this Person has that as a direct buddy
 boolean hasDirectBuddy(Person that) {return this.buddies.contains(that);}

 //METHOD PARTYCOUNT
 // returns the number of people who will show up at the party given by this person
 int partyCount(){return this.buddies.countExtended();}

 //METHOD COUNTCOMMONBUDDIES
 // returns the number of people that are direct buddies of both this and that person
 int countCommonBuddies(Person that) {return this.buddies.countCommon(that.buddies);} //we make a wishlist for a helper function


  //METHOD HASEXTENDEDBUDDY
 // will the given person be invited to a party organized by this person?
 boolean hasExtendedBuddy(Person that) {return this.buddies.containsExtended(that);}
 
 //METHOD ADDBUDDY
 // will the given person be invited to a party 
 // organized by this person?
 void addBuddy(Person that) {this.buddies =  this.buddies.addToList(that);} //Changes in Place for both the people, 
}
 
//==================================================================
//INTERFACE BUDDIES
//represents a list of person's buddies
interface ILoBuddy{
	ILoBuddy addToList(Person arg); //adds a person at the end of a list
	Person getFirst(); //gets the first element 
	ILoBuddy getRest(); //gets the last element of a list
	boolean isEmpty(); // checks if the given list is empty or not
	boolean contains(Person arg) ; //checks if the given person is present in the list of persons
	int countCommon(ILoBuddy arg) ; // Gives the count of common persons in two list of persons
	boolean containsExtended(Person arg); //Whether the given person is present in any of the list of buddies for any of the person in a given list of persons
	int countExtended() ; //returns the total unique count of extended friends for the given set of persons, including themselves, but excluding duplicates
	int lenList(); //Returns the length of the list
	ILoBuddy removeDuplicates() ; //Removes duplicates from a given list of buddies
	ILoBuddy getExtended(ILoBuddy visitedList); //Gets the extended list of buddies frm the given list of buddies, including duplicates
	ILoBuddy addFullList(ILoBuddy that); //appends the given list to the current list 
}


//CLASS MTLoBUDDY
//Represents an empty list of person's buddies

class MTLoBuddy implements ILoBuddy {
	MTLoBuddy() {}
	//METHODS
	public Person getFirst() {throw new RuntimeException ("Cannot access First of an empty list");}
	public ILoBuddy getRest() {throw new RuntimeException ("Cannot access Rest of an empty list");}
	public ILoBuddy addToList(Person arg) {return new ConsLoBuddy(arg, this);}
	public boolean isEmpty() {return true;} //we always return true here
	public boolean contains (Person arg) {return false;} //we always return false as empty list does not contain anything
	public int countCommon(ILoBuddy arg) {return 0;} //there will be nothing common if one of the list is empty
	public boolean containsExtended(Person arg) {return false;} //empty list will not contain anything
	public int countExtended() {return 0;}
	public int lenList() {return 0;} 
	public ILoBuddy removeDuplicates() {return this;} //we return the same list for remove duplicates
	public ILoBuddy getExtended(ILoBuddy visitedList) {return this;} //we return the same list for extended list of buddies
	public ILoBuddy addFullList(ILoBuddy that) {return that;} //we return the other list for current list as empty list
	
}

//CLASS CONSLOBUDDY
// represents a list of Person's buddies
class ConsLoBuddy implements ILoBuddy {
    Person first;
    ILoBuddy rest;
    //Defining the constructor
    ConsLoBuddy(Person first, ILoBuddy rest) {this.first = first; this.rest = rest;}
    
    //METHODS
    //AddToList Method
    public ILoBuddy addToList(Person arg) {
    	if (this.getRest().isEmpty()) {return new ConsLoBuddy(this.first, new ConsLoBuddy (arg, this.getRest())); }
    	else {return new ConsLoBuddy(this.first, this.rest.addToList(arg));}
    }
    public Person getFirst () {return this.first;}
    public ILoBuddy getRest () {return this.rest;}
    public boolean isEmpty() {return false;}
    public boolean contains	(Person arg) {return this.first.equals(arg) || this.rest.contains(arg);}
    public int countCommon(ILoBuddy that) {if (that.isEmpty()) {return 0;}
    	else {if (that.contains(this.first)) {return 1 + this.rest.countCommon(that);} else {return this.rest.countCommon(that);}}}
    public boolean containsExtended (Person arg) {return this.first.buddies.contains(arg) || this.first.buddies.getRest().containsExtended(arg)|| this.rest.containsExtended(arg);}
    
    public ILoBuddy getExtended(ILoBuddy visited) {
      if (visited.contains(this.first)) {
          return this.rest.getExtended(visited); // skip already visited
      } else {
          return new ConsLoBuddy(this.first,
              this.first.buddies.getExtended(visited.addToList(this.first))
                                .addFullList(this.rest.getExtended(visited.addToList(this.first))));
      }
  }
    
    public ILoBuddy removeDuplicates() {if (this.rest.contains(this.first)) {return this.rest.removeDuplicates();} else {return new ConsLoBuddy(this.first, this.rest.removeDuplicates());}} 
    public ILoBuddy addFullList(ILoBuddy that) {	
    	if (that.isEmpty()) {return this;} 
    	else {return this.addToList(that.getFirst()).addFullList(that.getRest());}}
    public int lenList() {return 1+ this.rest.lenList();}
    
    public int countExtended() {return this.getExtended(new MTLoBuddy()).removeDuplicates().lenList();} // we first get the non unique extended list> remove duplicates > then get the length of the list
}

//==================================================================
class ExamplesBuddies {
	ExamplesBuddies() {}


	Person ann; 
	Person bob; 
	Person cole;
	Person dan;
	Person ed ;
	Person fay ;
	Person gabi ;
	Person hank ;
	Person jan ;
	Person kim ;
	Person len ;

	//Defining the INITCONDITIONS
	void initConditions() {
		ann = new Person ("Ann");
		bob = new Person("Bob");
		cole = new Person("Cole");
		dan = new Person("Dan");
		ed = new Person("Ed");
		fay = new Person("Fay");
		gabi = new Person ("Gabi");
		hank = new Person ("Hank");
		jan = new Person ("Jan");
		kim = new Person ("Kim");
		len = new Person ("Len");
		ann.addBuddy(bob); ann.addBuddy(cole); //Adding buddies for Ann
		bob.addBuddy(ann); bob.addBuddy(ed); bob.addBuddy(hank); //Adding buddies for bob
		cole.addBuddy(dan); //Adding buddy for cole
		ed.addBuddy(fay); //adding buddy for ed 
		fay.addBuddy(ed); fay.addBuddy(gabi);
		gabi.addBuddy(ed); gabi.addBuddy(fay);
		jan.addBuddy(kim); jan.addBuddy(len);
		kim.addBuddy(jan); kim.addBuddy(len);
		len.addBuddy(jan); len.addBuddy(kim);
	}

	//Testing the INIT CONDITIONS
	void testIniConditions (Tester t) {
		this.initConditions();
		t.checkExpect(len.buddies, new ConsLoBuddy(jan, new ConsLoBuddy(kim, new MTLoBuddy())));
		t.checkExpect(fay.buddies, new ConsLoBuddy(ed, new ConsLoBuddy(gabi, new MTLoBuddy())));
		t.checkExpect(hank.buddies, new MTLoBuddy());
	}

	//testing for DirectBUDDY METHOD
	void testDirectBuddy (Tester t) {
		this.initConditions() ;
		t.checkExpect(len.hasDirectBuddy(jan), true);
		t.checkExpect(len.hasDirectBuddy(ann), false);
		t.checkExpect(ann.hasDirectBuddy(jan), false);
	}

	//Testing for COUNTCOMMONBUDDIES
	void testCountCommonBuddies (Tester t) {
		this.initConditions();
		t.checkExpect(ann.countCommonBuddies(bob), 0);
		t.checkExpect(fay.countCommonBuddies(gabi), 1);
		}
	
	//Testing for HASEXTENDEDBUDDY
	void testHasExtendedBuddy (Tester t) {
		this.initConditions();
		t.checkExpect(ann.hasExtendedBuddy(ed), true);
		t.checkExpect(ann.hasExtendedBuddy(fay), true);
		
	}
	
	
	//Testinf for PARTYCOUNT
	void testPartyCount (Tester t) {
		this.initConditions();
		t.checkExpect(ann.partyCount(), 8);
	}








}























