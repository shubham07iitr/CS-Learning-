import tester.*;



// ========================================================================================================
// PROBLEM-AUTOMOBILE
// Automobile is of type (model - String, price - int, mileage - double (miles per gallon), used - bool
// Interp. as a particular object of automobile
class Automobile {
	String model;
	int price;
	double mileage;
	boolean used;
	 
	Automobile (String model, int price, double mileage, boolean used) {
		this.model = model;
		this.price = price;
		this.mileage = mileage;
		this.used = used;
	}
}
//========================================================================================================
//PROBLEM-AUTOMOBILE
//HOuses is compound data of type (kind-String, rooms - int, address-Address, price - int in dollar
//Interp. as the an object of house with mutliple details

class House {
	String kind;
	int rooms;
	Address address;
	int price;
	
	House (String kind, int rooms, Address address, int price) {
		this.kind = kind ;
		this.rooms = rooms;
		this.address = address ;
		this.price = price;
	}
}
// interp. as the address of a house
class Address {
	int streetno;
	String streetname;
	String city;
	
	Address (int streetno, String streetname, String city) {
		this.streetno = streetno ;
		this.streetname = streetname;
		this.city = city;
	}
}
//========================================================================================================
//PROBLEM-BANK ACCOUNT
//Account is one of 
/*
 * -- Checking Account
 * -- Savings Account
 * -- Certificate of deposit
 */
// interp. as account info belonging to someone
interface IAccount {}

// Checking is of type (id - int, name - string, minbal-int, 	currentbal-int)
// interp as a type of account
class Checking implements IAccount {
	int id;
	String name;
	int minbal;
	int currentbal;
	
	Checking(int id, String name, int minbal, int currentbal) {
		this.id = id ;
		this.name = name;
		this.minbal = minbal;
		this.currentbal = currentbal;}
}

//SAvings is of type (id - int, name - string, interestrate-double, 	currentbal-int)
//interp as a type of account
class Savings implements IAccount {
	int id;
	String name;
	double interestrate;
	int currentbal;

	Savings (int id, String name, double interestrate, int currentbal) {
		this.id = id ;
		this.name = name;
		this.interestrate = interestrate;
		this.currentbal = currentbal;}
}

//CD is of type (id - int, name - string, maturitydate-date, 	currentbal-int)
//interp as a type of account
class CD implements IAccount {
int id;
String name;
double interestrate;
NewDate maturitydate;
int currentbal;

CD (int id, String name,double interestrate, NewDate maturitydate, int currentbal) {
	this.id = id ;
	this.name = name;
	this.interestrate = interestrate;
	this.maturitydate = maturitydate;
	this.currentbal = currentbal;}
}

// NewDate is of type (day-int, month-string, year - int)
// interp. as the date as per gregorian calendar

class NewDate {
	int day;
	String month;
	int year;
	
	NewDate (int day, String month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
}

//========================================================================================================
//PROBLEM-HOUSE LIStingS
//ILoH is one of 
/*
* -- MTLoH
* -- cons House list of houses
*/
//interp. as a list of houses 

interface ILoH{}

//interp. as an empty list of houses
class MTLoH implements ILoH {
	MTLoH () {}
}

// ConsLoH is of type (first - House, rest - ILoH)
// interp as a list of houses and has two fields 
/*
 * -- House consed on to
 * -- List of houses
 */

class ConsLoH implements ILoH {
	House first;
	ILoH rest;
	
	ConsLoH (House first, ILoH rest) {
		this.first = first ;
		this.rest = rest;
	}
}


//========================================================================================================
//PROBLEM-Image Method
//Image is of type (width - int , height - int, source - string) 

//interp. as the specs of an image, width and height are in pixels 

class Image {
	int width; // in pixels
	int height; // in pixels
	String source;
	
	Image(int width, int height, String source) {
		this.width = width;
		this.height = height;
		this.source = source;
	}
	
	//TEMPLATE:
	/*
	 Fields:
	 (....this.width) ...int
	 (...this.height) ...int
	 (...this.source) ...String;
	 
	 Methods:
	 (...this.sizeString() ...boolean
	 (...this.area()       ... int
	 */
	
	//Signature->  Image -> String
	/* --Small - for area < 10K pixels
	 * -- Medium f0r area 10K - 1Mn pixels
	 * -- Large for area > 1Mn pixels
	 */
	// Purpose - Operates on an object of image and returns the size of the image in 3 cats
	String sizeString () {
		if (this.area() <= 10000) { // making a wishlist for area function of an image
			
					return "small";
		}
		else if (this.area() > 10000 && this.area() <= 1000000) {
			return "medium";
		}
		else {
			return "large";
		}
	}
	//Signature - Image - Int
	// Returns the area of an image by multiplying the width and height of the image
	int area() {
		return this.width * this.height ;
	}
	
}

//========================================================================================================
//PROBLEM-WEATHER RECORD
//Weather record is of type (date - Date, today - TempRange for the given date, normal - TempRange expected for a given date, record - Temprange record for the given date 
//interp. as the Temp Range on a particular day

class WeatherRecord {
	Date date;
	TempRange today;
	TempRange normal;
	TempRange record;
	
	WeatherRecord (Date date, TempRange today, TempRange normal, TempRange record) {
	this.date = date;
	this.today = today;
	this.normal = normal;
	this.record = record;
	}
	
	
	//TEMPLATE
	/*
	 * ...FIELDS
	 * ...this.date     ...Date
	 * ...this.today    ...TempRange 
	 * ...this.normal   ...TempRange
	 * ...this.record   ...TempRange
	 
	 * ... METHODS
	 * ...this.withinRange() ....boolean
	 * ...this.recordDay()   ....boolean
	 */
	
	//Signature-> Self WeatherRecord -> Boolean
	//Purpose is to identify whther today's high and low were within normal range (high of today < high of normal and low of today > low of normal)
	
	boolean withinRange() {
		return (this.today.high < this.normal.high && this.today.low > this.normal.low) ;
	}
	
	//Signature -> Self WeatherRecord -> Boolean
	//Purpose is to check whether temp range today broke record either on high side or low side 
	
	boolean recordDay() {
		return (this.today.high > this.record.high || this.today.low < this.record.low) ;
	}
	
}

//Date is of type (day - int, month - int, year - int)
//interp as a normal date in gregorian calendar
class Date {
	int day;
	int month;
	int year;
	
	Date (int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
}

//TempRate is of type (high-int, low-int) in celsius
//interp as temp range on any given day with high and low fields 
class TempRange {
int high;
int low;

TempRange (int high, int low) {
	this.high = high;
	this.low = low;
}
}

//========================================================================================================
//PROBLEM-GROCERY-ITEMS
//Grocery Items is one of 
/*
 * -- Ice Cream of type (brand - Srting, weight - INt (in gms),Price - int (in cents) , flavor - String)
 * -- Coffee  of type (brand - Srting, weight - INt (in gms),Price - int (in cents) , label-String (regular or decaf)
 * -- Juice of type (brand - Srting, weight - INt (in gms),Price - int (in cents), flavor - String, packaging - String
 */
//Interp. as items in a grocery shop

interface IGItems {
	
	double unitPrice() ; //computes unit price of a grocery item
	boolean lowerUnitPrice(double somePrice); //computes whether the given object unitPrice is < the given unitPrice in the argument
	boolean cheaperThan(IGItems item);; //computes if the current item's unitPrice < the unitPrice of 
}

//Ice Cream of type (brand - Srting, weight - INt (in gms),Price - int (in cents) , flavor - String)
// intepr as ice cream in a grocery store

class IceCream implements IGItems {
	String brand;
	int weight; // in gms
	int price; // in cents
	String flavor;
	
	IceCream (String brand, int weight, int price, String flavor) {
		this.brand = brand ;
		this.weight = weight ;
		this.price = price ;
		this.flavor = flavor ;
	}
	
//TEMPLATE
	/*
	 * ...FIELDS
	 * ...this.brand     ...String
	 * ...this.weight    ...int
	 * ...this.price   ...in
	 * ...this.flavor   ...String
	 
	 * ... METHODS
	 * ...this.unitPrice() ....double
	 * ...this.lowerUnittPrice(double somePrice)   ....boolean
	 * ...this.cheaperThan(IGItems item)           ....boolean
	 */
	
	//Signature-> Self IceCream -> Double
	//computes unit price of a grocery item (weight divided by price)
	
	public double unitPrice() {
		return (this.weight / this.price) ;
	}
	
	//Signature -> Self IceCream, Double -> Boolean
	//computes whether the given object unitPrice is < the given unitPrice in the argument
	public boolean lowerUnitPrice (double someunitprice) {
		return (this.unitPrice() < someunitprice); 
	}
	
	//Signature -> Self IceCream, Other IGItems -> Boolean
	//Computes if current object's unitPrice is lower than the IGItem object passed
	public boolean cheaperThan (IGItems item) {
		return this.unitPrice() < item.unitPrice(); 
	}
	
}

//Coffee  of type (brand - Srting, weight - INt (in gms),Price - int (in cents) , label-String (regular or decaf)
//intepr as Coffee in a grocery store

class Coffee implements IGItems {
String brand;
int weight; // in gms
int price; // in cents
String type; //"regular" or "decaf"

Coffee (String brand, int weight, int price, String type) {
	this.brand = brand ;
	this.weight = weight ;
	this.price = price ;
	this.type = type;
}
//TEMPLATE
/*
 * ...FIELDS
 * ...this.brand     ...String
 * ...this.weight    ...int
 * ...this.price   ...in
 * ...this.flavor   ...String
 
 * ... METHODS
 * ...this.unitPrice() ....double
 * ...this.lowerUnittPrice(double somePrice)   ....boolean
 * ...this.cheaperThan(IGItems item)           ....boolean
 */

//Signature-> Self IceCream -> Double
//computes unit price of a grocery item (weight divided by price)

public double unitPrice() {
	return (this.weight / this.price) ;
}

//Signature -> Self IceCream, Double -> Boolean
//computes whether the given object unitPrice is < the given unitPrice in the argument
public boolean lowerUnitPrice (double someunitprice) {
	return (this.unitPrice() < someunitprice); 
}

//Signature -> Self IceCream, Other IGItems -> Boolean
//Computes if current object's unitPrice is lower than the IGItem object passed
public boolean cheaperThan (IGItems item) {
	return this.unitPrice() < item.unitPrice(); 
}



}

//Juice of type (brand - Srting, weight - INt (in gms),Price - int (in cents), flavor - String, packaging - String
//intepr as Juice in a grocery store

class Juice implements IGItems {
	String brand;
	int weight; // in gms
	int price; // in cents
	String flavor;
	String packaging; //"frozen", "fresh" , "bottled", or "canned"

	Juice (String brand, int weight, int price, String flavor, String packaging) {
		this.brand = brand ;
		this.weight = weight ;
		this.price = price ;
		this.flavor = flavor;
		this.packaging = packaging;
	}
	
//TEMPLATE
	/*
	 * ...FIELDS
	 * ...this.brand     ...String
	 * ...this.weight    ...int
	 * ...this.price   ...in
	 * ...this.flavor   ...String
	 
	 * ... METHODS
	 * ...this.unitPrice() ....double
	 * ...this.lowerUnittPrice(double somePrice)   ....boolean
	 * ...this.cheaperThan(IGItems item)           ....boolean
	 */
	
	//Signature-> Self IceCream -> Double
	//computes unit price of a grocery item (weight divided by price)
	
	public double unitPrice() {
		return (this.weight / this.price) ;
	}
	
	//Signature -> Self IceCream, Double -> Boolean
	//computes whether the given object unitPrice is < the given unitPrice in the argument
	public boolean lowerUnitPrice (double someunitprice) {
		return (this.unitPrice() < someunitprice); 
	}
	
	//Signature -> Self IceCream, Other IGItems -> Boolean
	//Computes if current object's unitPrice is lower than the IGItem object passed
	public boolean cheaperThan (IGItems item) {
		return this.unitPrice() < item.unitPrice(); 
	}	
}


//========================================================================================================
//EXAMPLES
class ExamplesPractice {
	ExamplesPractice () {}
	//Examples for Autombiles
	Automobile a1 = new Automobile ("city", 150000, 15.0, false);
	Automobile a2 = new Automobile ("verna", 200000, 14.0, true);
	
	//Examples for houses
	House h1 = new House ("Ranch", 7, new Address (23, "Maple Street", "Brookline"), 375000 );
	House h2 = new House ("Colonial", 9, new Address (5, "Joy Road", "Newton"), 450000 );
	House h3 = new House ("Cape", 6, new Address (83, "Winslow Road", "Waltham"), 235000 );
	
	//Examples for BankAccount
	IAccount account1 = new Checking (1729, "Earl Gray", 500, 1250);
	IAccount account2 = new CD (4104, "Ima Flatt", 0.04, new NewDate (1, "June" , 2005), 10123);
	IAccount account3 = new Savings (2992, "Annie Proulx	", 0.04, 10123);
	
	// Examples for List of Houses
	ILoH mt = new MTLoH();
	ILoH list1 = new ConsLoH (h1, mt);
	ILoH list2 = new ConsLoH (h2 , list1);
	ILoH list3 = new ConsLoH (h3 , list2);
	
	
	//Examples Tests for the Image problem
	Image i1 = new Image (400, 300, "TV");
	Image i2 = new Image (30, 40, "Phone");
	Image i3 = new Image (4000, 3000, "Movie");
	
	//Test methods for class Image
	
	boolean testImage(Tester t) {
		return t.checkExpect(this.i1.sizeString(), "medium") &&
				t.checkExpect(this.i2.sizeString(), "small") &&
				t.checkExpect(this.i3.sizeString(), "large") ;
	}
	
	//Examples for Weather Record and allied classes
	Date d1 = new Date (5, 12, 2024);
	Date d2  = new Date (10,5,2025);
	
	TempRange t1 = new TempRange (18, 9);
	TempRange t2 = new TempRange (39,32);
	TempRange t1n  = new TempRange (16,7);
	TempRange t1r = new TempRange (20,5);
	TempRange t2n = new TempRange (40, 30);
	TempRange t2r = new TempRange (45, 15);
	
	
	WeatherRecord w1 = new WeatherRecord (d1, t1, t1n, t1r);
	WeatherRecord w2 = new WeatherRecord (d2, t2, t2n, t2r);
	
	//Testing the method for withinRange
	
	boolean testwithinRange(Tester t) {
		return t.checkExpect(this.w1.withinRange(), false) &&
				t.checkExpect(this.w2.withinRange(), true);
		}
	
	//Testing the methof for recordDay
	
	boolean testrecordDay(Tester t) {
		return t.checkExpect(this.w2.recordDay(), false) &&
				t.checkExpect(this.w2.recordDay(), false);
	}
	
	
	// Examples for IGItems and allied classes and methods
	
	IGItems ic1 = new IceCream("Havmor", 200, 100, "Taj Mahal");
	IGItems co1 = new Coffee("Nescafe", 500, 125, "Regular");
	IGItems j1 = new Juice ("Tropicana", 400 , 400, "Orange", "Frozen");
	
	// Testing for methods of IGItems;
	
	boolean testunitPrice (Tester t) {
		return t.checkInexact(ic1.unitPrice(),2.0 , 0.01) &&
				t.checkInexact(co1.unitPrice(), 4.0, 0.01) &&
				t.checkInexact(j1.unitPrice(), 1.0, 0.01);
	}
	
	boolean testlowerUnitPrice(Tester t) {
		return t.checkExpect(ic1.lowerUnitPrice(3.0), true) && 
				t.checkExpect(co1.lowerUnitPrice(2.0), false) ;
	}
	boolean testcheaerThan (Tester t) {
		return t.checkExpect(ic1.cheaperThan(co1) , true) && 
				t.checkExpect (co1.cheaperThan(j1), false) ;
	}
}






















