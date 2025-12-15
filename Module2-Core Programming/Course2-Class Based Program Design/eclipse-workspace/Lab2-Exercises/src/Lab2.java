
import tester.*;

// ========================================================================================================
// PROBLEM-TRANSPORTATION
//MoT is one of
/*
 * Bicyle is of type (brand - String)
 * Car is of type (make-string, mpg  or miles per gallon - int)
 */
		
// interp. as mode of transportatino for a person

interface MoT {
	//Signature -> Self , Int -> Boolean
	//Returns whether current transportations fuel efficiency is higher than what is passed
	boolean fuelEfficiencyTargetMoT (int target);
}

//Bicyle is of type (brand - String)
// Interp. as a mode of transportation for a person via a bicylce

class Bicycle implements MoT {
	String brand;
	
	Bicycle (String brand) {
		this.brand = brand;
	}
	
	// TEMPLATE
	// FIELDS
	/*
	 * ... this.brand  ...String
	 * METHODS
	 *  this.fuelEfficiencyTarget (int target) ...boolean
	 */
	
	//Signature -> Self , Int -> Boolean
	//Returns whether current transportations fuel efficiency is higher than what is passed 
	public boolean fuelEfficiencyTargetMoT (int target) {
		return true;
	}
}

// Car is one of type (make-string, mpg  or miles per gallon - int)
// interp. as a mode of transportation for a person via a car

class Car implements MoT {
	String make; //the maker of the car like toyota or honda
	int mpg; // miles per gallon for the car
	
	Car (String make, int mpg) {
		this.make = make;
		this.mpg = mpg;
	}
	// TEMPLATE
	// FIELDS
	/*
	 * ... this.make  ...String
	 * ... this.mpg   ... int
	 * METHODS
	 *  this.fuelEfficiencyTarget (int target) ...boolean
	 */
	
	//Signature -> Self , Int -> Boolean
	//Returns whether persons transportations fuel efficiency is higher than what is passed 
	public boolean fuelEfficiencyTargetMoT (int target) {
		if (this.mpg >= target) {return true;}
		else {return false;}
	}
	
}

// Person is of type (name - String ,mot - MOT)
// interp. as any person with a name and a mode of transportation

class Person {
	String name;
	MoT mot;
	
	Person(String name, MoT mot) {
		this.name = name ;
		this.mot = mot;
	}
	
	//TEMPLATE 
	/*
	 * FIELDS
	 * ...this.name ...String
	 * ...this.mot ....MoT
	 * METHODS
	 * ....
	 * METHODS for FIELDS
	 * ...this.mot.fuelEfficiencyTargetMoT(int target) ....boolean
	 */
	//Signature -> Self , Int -> Boolean
	//Returns whether current persons transportations fuel efficiency is higher than what is passed
	
	boolean fuelEfficiencyTarget (int target) {
		return this.mot.fuelEfficiencyTargetMoT (target);
	}
}

//========================================================================================================
//PROBLEM - PET and PET OWNERS

// PersonPet is of type (name - String, pet - IPet, age - int)
// represents a pet owner
class PersonPet {
	String name;
	IPet pet;
	int age;
	
	PersonPet (String name, IPet pet, int age) {
		this.name = name;
		this.pet = pet; 
		this.age = age;
	}
	
	//TEMPLATE
	/*
	 * FIELDS
	 * ...this.name ...String
	 * ...this.pet  ...Pet
	 * ...this.age  ...Int
	 * METHODS
	 * ...this.isOlder (Person other) ... boolean
	 * ...this.sameNamePet (String name) ...boolean
	 */
	
	//Signature > Self, Person ->Boolean
	// is the person older than the given person?
	
	boolean isOlder (PersonPet other) {
		return this.age > other.age ;
	}
	
	//Signature> Self, String -> Boolean
  //does the name of this person's pet match the given name?
	
	boolean sameNamePet (String name) {
		return this.pet.sameName(name);
	}

}

//Pet is one of 
// -- NoPet - is of type Null
// -- Cat is of type (name - String, kind - String, longhaired- boolean)
// -- Dog is of time (name - string, kind - String, male - boolean)
// represetns a pet owned by a person

interface IPet {
	//returns whether the given name as argument matches the name of the pet
	//Signature Self, String -> Boolean
	boolean sameName(String name);
}

//NoPet is of type ()
// Represents that a person does not own any type of pet

class NoPet implements IPet {
	NoPet () {}
	
	//returns whether the given name as argument matches the name of the pet , which in this case we will always chose to return as false ,as it does not have any name
	//Signature Self, String -> Boolean
	public boolean sameName(String name) {
		return false;
	}
}

//Cat is of type (name - String, kind - String, longhaired- boolean)
// represents a pet cat owned by a person
class Cat implements IPet {
	String name;
	String kind;
	boolean longhaired;
	
	Cat (String name, String kind, boolean longhaired) {
		this.name = name;
		this.kind = kind;
		this.longhaired = longhaired;
	}
	
	//TEMPLATE
	/*
	 * FIELDS
	 * ...this.name ...String
	 * ...this.kind ...String
	 * ...this.longhaired ...boolean
	 * METHODS
	 * ...this.sameName(String name) ...boolean
	 * 
	 */
	public boolean sameName  (String name) {
		return this.name.equals(name);
	}
}

//Dog is of type (name - String, kind - String, male- boolean)
//represents a pet dog owned by a person
class Dog implements IPet {
String name;
String kind;
boolean male;

Dog (String name, String kind, boolean male) {
	this.name = name;
	this.kind = kind;
	this.male = male;
}
//TEMPLATE
/*
 * FIELDS
 * ...this.name ...String
 * ...this.kind ...String
 * ...this.longhaired ...boolean
 * METHODS
 * ...this.sameName(String name) ...boolean
 * 
 */
public boolean sameName  (String name) {
	return this.name.equals(name);
}
}


//========================================================================================================
//EXAMPLES

class ExamplesLab2 {
	ExamplesLab2() {}
	
	MoT diamondback = new Bicycle ("Diamondback");
	MoT toyota = new Car ("Toyota", 30);
	MoT lamborghini = new Car ("Lamborghini", 17);
	
	Person bob = new Person ("Bob", diamondback);
	Person ben = new Person ("Ben", toyota);
	Person becca = new Person ("Becca", lamborghini);
	
	//Writing test cases for fuelEfficiencyTarget
	
	boolean testfuelEfficiencyTarget (Tester t) {
		return t.checkExpect(bob.fuelEfficiencyTarget (20), true) && 
				t.checkExpect(ben.fuelEfficiencyTarget(20), true) &&
				t.checkExpect(becca.fuelEfficiencyTarget(20), false);
	}
	// Examples of Pets and Pet Owners
	
		IPet cat1 = new Cat("Phoebe", "furry", false);
		IPet cat2 = new Cat ("pussy", "stinky", true);
		IPet dog1 = new Dog ("Ruffles", "Golden Retriever", false);
		IPet dog2 = new Dog ("Pawsome", "Husky", true);
		IPet nopet = new NoPet(); 
		
		PersonPet p1 = new PersonPet ("Shubham", cat1, 36);
		PersonPet p2 = new PersonPet ("Survi", cat2, 35);
		PersonPet p3 = new PersonPet ("Sharvil", dog1, 4);
		PersonPet p4 = new PersonPet ("Mummy", dog2, 61);
		PersonPet p5 = new PersonPet ("Dady", nopet, 65);
	// Tests for Methods of Pets and Pet Owners
		
		boolean testIPersonPetisOlder (Tester t) {
			return t.checkExpect(p1.isOlder(p2), true) &&
					t.checkExpect (p2.isOlder(p1), false) ;
		}	
		
		boolean testIPersonsameNamePet (Tester t) {
			return t.checkExpect(p1.sameNamePet("Phoebe"), true) && 
					t.checkExpect(p2.sameNamePet("Survi"), false) && 
					t.checkExpect(p5.sameNamePet("shubham"), false) ;
		}
		
	}