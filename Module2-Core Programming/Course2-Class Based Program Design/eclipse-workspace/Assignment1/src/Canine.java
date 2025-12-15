import tester.*;

//========================================================================================================
//PROBLEM-DOG CLASS


// Dog is of type (name-string, breed-string, yob-int, state- String, hypoallergenic - boolean
// interp. as an object of class Dog with certain specs

class Dog {
	String name;  //name of dog
	String breed; //breed of dog 
	int yob; // Year of birth represented in 4 numbers like 2025 
	String state; //represents state of residence , two letter abbreviation
	boolean hypoallergenic; //represents whether dog is allergic or not
	
	
	Dog (String name, String breed, int yob, String state, boolean hypoallergenic) {
		this.name = name;
		this.breed = breed;
		this.yob = yob;
		this.state = state;
		this.hypoallergenic = hypoallergenic;
	}
}

//========================================================================================================
//PROBLEM-ICE-CREAM CLASS


//ICE-CREAM is one of
//--EmptyServing (equivalent to empty list for list of elements)
//--Scooped  

interface IIceCream {}


// EmptyServing is of type (cone - boolean)
// interp as whether the icecream is served in cone or a cup

class EmptyServing implements IIceCream {
	boolean cone;
	
	EmptyServing(boolean cone) {
		this.cone = cone;
	}
}

//Scooped is ot type (more-IceCream, flavor - string)
// interp. as a scoop of flavpron top of icecream
// here flavor represents first and icecream reperesents second

class Scooped implements IIceCream {
	String flavor ;
	IIceCream more;
	
	Scooped (String flavor, IIceCream more) {
		this.flavor = flavor;
		this.more = more;
	}
}

//========================================================================================================
//PROBLEM- HOUSING & TRAVEL
//Housing is one of 
//--Hut is of type (capacity-int, population - int)
//-- Inn is of type (name - string, capacity-int, population - int , stalls -int)
//-- Castle is of type (name - string, family-name - string, population - int, carraige-house - int)
// Interp. as type of housing in a medieval setting

interface Housing {}

//Hut is of type (capacity-int, population - int)
// interp. as one of the types of housing in medieval world
class Hut implements Housing {
	int capacity; // interp. as capacity of hte Hut
	int population; // the current population in the Hut, should be < capacity
	
	Hut (int capacity, int population) {
		this.capacity = capacity;
		this.population = population ;
	}
}

//Inn is of type (name-String, capacity-int, population - int, stalls-int)
//interp. as one of the types of housing in medieval world

class Inn implements Housing {
	String name; //name of the inn
	int capacity; // interp. as capacity of hte inn
	int population; // the current population in the inn, should be < capacity
	int stalls; //no. of stalls to be occcupied by horses

	Inn (String name, int capacity, int population, int stalls) {
		this.name = name;	
		this.capacity = capacity;
		this.population = population ;
		this.stalls = stalls	;
	}
}

//Castle is of type (name-String, family name - String, population - int, carriage houses-int)
//interp. as one of the types of housing in medieval world

class Castle implements Housing {
String name; //name of the Castle
String family_name; //family name of the castle
int population; // the current population in the castle
int carriage_houses; //no. of carriage houses to be occcupied by carriages

Castle (String name, String family_name, int population, int carriage_houses) {
	this.name = name;	
	this.family_name = family_name;
	this.population = population ;
	this.carriage_houses = carriage_houses;
}
}

//Transportation is one of - 
// -- Horse is of type (from - Housing, to - Housing, name - String, color - String)
// -- Carriages is of type (from - Housing, to - Housing, tonnage - int)
// interp. as the type of transportation in medieval society

interface Transportation{}

//Horse is of type (from - Housing, to - Housing, name - String, color - String)
// interp. as the a transoporting vehicle in medieval society 

class Horse implements Transportation {
	Housing from ; //indicating from where the horse is giong 
	Housing to ; // indicating to where the horse is going , can only go to inn if stall > 0
	String name; //name of the horse
	String color; // color of the horse
	
	Horse (Housing from, Housing to, String name, String color) {
		this.from = from;
		this.to = to;
		this.name = name;
		this.color = color;
	}
}

//Carriage is of type (from - Housing, to - Housing, tonnage - int)
//interp. as the a transoporting vehicle in medieval society 

class Carriage implements Transportation {
Housing from ; //indicating from where the carriage is giong 
Housing to ; // indicating to where the carriage is going , can only go to inn if stall > 0
int tonnage; // how much weight the carriage can carry

Carriage (Housing from, Housing to, int tonnage) {
	this.from = from;
	this.to = to;
	this.tonnage = tonnage;
}
}


//========================================================================================================
//EXAMPLE CLASS

class ExamplesAssignment {
	ExamplesAssignment () {}
	
	
	//Examples for Dog
	Dog hufflepuff = new Dog ("Hufflepuff", "Wheaten Terrier", 2012, "TX", true);
	Dog pearl = new Dog ("Pearl", "Labrador Retriever", 2016, "MA", false);
	
	
//Examples for IceCream
	
	IIceCream cone = new EmptyServing (true);
	IIceCream cup = new EmptyServing (false);
	IIceCream ScoopCone1 = new Scooped ("strawberry", cone);
	IIceCream ScoopCone2 = new Scooped ("vanilla", ScoopCone1);
	IIceCream order1 = new Scooped ("chocolate", ScoopCone2);
	IIceCream ScoopCup1 = new Scooped ("caramel swirl", cup);
	IIceCream ScoopCup2 = new Scooped ("black rasberry", ScoopCup1);
	IIceCream ScoopCup3 = new Scooped ("coffee", ScoopCup2);
	IIceCream order2 = new Scooped ("mint chip" , ScoopCup3);
	
// Exampels from Housing 
	Housing hovel = new Hut (5, 1);
	Housing winterfell = new Castle ("Winterfell", "Stark", 500, 6);
	Housing crossroads = new Inn ("Inn at the crossroads", 40, 20 , 12);
	Housing hut2 = new Hut (10, 2);
	Housing hogwarts = new Castle ("Hogwarts", "Hogwarts", 5000, 100);
	Housing godrics_hollow = new Inn ("Godrics Hollow", 100, 30, 20);
	
//Examples from Transportation
	Transportation horse1 = new Horse (hovel, winterfell, "Chetak", "Black");
	Transportation horse2 = new Horse (hovel, crossroads, "Voldemort", "White");
	Transportation carriage1 = new Carriage (winterfell, crossroads, 1000);
	Transportation carriage2 = new Carriage (crossroads, winterfell, 500);
	
}






















