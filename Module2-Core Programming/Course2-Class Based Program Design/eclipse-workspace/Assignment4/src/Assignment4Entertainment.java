import tester.*;

interface IEntertainment {
	//compute the total price of this Entertainment
	double totalPrice();
	//computes the minutes of entertainment of this IEntertainment
	int duration();
	//produce a String that shows the name and price of this IEntertainment
	String format();
	//is this IEntertainment the same as that one?
	boolean sameEntertainment(IEntertainment that);
}

//Defining the abstract class
abstract class AEntertainment implements IEntertainment {
	String name;
	double price; //represents price per issue
	int installments;//number of issues per year
	
	AEntertainment (String name, double price, int installments) {
		this.name = name;
		this.price = price;
		this.installments = installments;
	}
	
	public double totalPrice() {return this.price*this.installments;}
	public  int duration() {return this.installments*50;} // to be accepted in TVSeries and Podcast class, overridden in Magazine
	public String format() {return this.name + "," + " " + "$" + Double.toString(this.price) + ".";} // to be accepted in all sub-classes
	public abstract boolean sameEntertainment (IEntertainment that); // to be overridden in every class 
}

//Defining the Magazine class which inherits from AEntertainment
class Magazine extends AEntertainment {	 
	String genre;
	int pages;
 
	Magazine(String name, double price, String genre, int pages, int installments) {
		super(name, price, installments);	//inheriting from the abstract class
		this.genre = genre;
		this.pages = pages;
	
	}
	//computes the price of a yearly subscription to this Magazine
	public double totalPrice() {
		return this.price * this.installments;
	}

	
	//computes the minutes of entertainment of this Magazine, (includes all installments)
	@Override
	public int duration() {
		return this.pages*5*this.installments;
	}

	//is this Magazine the same as that IEntertainment?
	public boolean sameEntertainment(IEntertainment that) {
		if (that instanceof Magazine) {
			Magazine otherMag = (Magazine) that;
			return this.name.equals(otherMag.name)&&
					this.price == (otherMag.price) &&
					this.genre.equals(otherMag.genre) &&
					this.pages == (otherMag.pages) &&
					this.installments == (otherMag.installments) ;			
		} else {return false;}
	}
	
	
	//METHOD FORMAT
	//Inherited frm the parent class
	//produce a String that shows the name and price of this Magazine
//	public String format() {
//		return this.name + "," + " " + "$" + Double.toString(this.price) + ".";
//	}
}


//Defining the TVSeries class which inherits from AEntertainment
class TVSeries extends AEntertainment {
	String corporation; //number of episodes of this series

	TVSeries(String name, double price, int installments, String corporation) {
		super(name, price, installments);	//inheriting from the abstract class
		this.corporation = corporation;
	}

	//METHOD TOTALPRICE
	//computes the price of a yearly subscription to this TVSeries
	//inheriting from parent class
//	public double totalPrice() {
//		return this.price * this.installments;
//	}

	//METHOD DURATION
	//Inherited frm parent class
	//computes the minutes of entertainment of this TVSeries
//	public int duration() {
//		return this.installments*50;
//	}

	//is this TVSeries the same as that IEntertainment?
	public boolean sameEntertainment(IEntertainment that) {
		if (that instanceof TVSeries) {
			TVSeries otherTV = (TVSeries) that;
			return this.name.equals(otherTV.name)&&
					this.price == (otherTV.price) &&
					this.installments == (otherTV.installments) &&
					this.corporation.equals(otherTV.corporation);
		} else {return false;}
	}

	//METHOD FORMAT
	//Inherited frm the parent class
	//produce a String that shows the name and price of this TVSeries
	//	public String format() {
	//		return this.name + "," + " " + "$" + Double.toString(this.price) + ".";
	//	}
}

//Defining the Podcast class which inherits from AEntertainment
class Podcast extends AEntertainment {

	Podcast(String name, double price, int installments) {
		super(name, price, installments);//inheriting frm the parent class
	}
	
	//METHOD TOTALPRICE
	//Inherited from parent class
	//computes the price of a yearly subscription to this Podcast
//	public double totalPrice() {
//		return this.price * this.installments;
//	}

	//METHOD DURATION
	//Inherited frm parent class
	//computes the minutes of entertainment of this Podcast
//	public int duration() {
//		return this.installments*50;
//	}

	//is this Podcast the same as that IEntertainment?
	public boolean sameEntertainment(IEntertainment that) {
		if (that instanceof Podcast) {
			Podcast otherPod = (Podcast) that;
			return this.name.equals(otherPod.name)&&
					this.price == (otherPod.price) &&
					this.installments == (otherPod.installments) ;			
		} else {return false;}
	}

	//produce a String that shows the name and price of this Podcast
	public String format() {
		return this.name + "," + " " + "$" + Double.toString(this.price) + ".";
	}
}

class ExamplesEntertainment {
	IEntertainment rollingStone = new Magazine("Rolling Stone", 2.55, "Music", 60, 12);
	IEntertainment houseOfCards = new TVSeries("House of Cards", 5.25, 13, "Netflix");
	IEntertainment serial = new Podcast("Serial", 0.0, 8);

	IEntertainment merisaheli = new Magazine("Meri Saheli", 5.0, "Family", 90, 12);
	IEntertainment friends = new TVSeries("Friends", 7.0, 24, "HBO");
	IEntertainment rajshamani = new Podcast("Raj Shamani", 0.0, 24);


	//testing total price method
	boolean testTotalPrice(Tester t) {
		return t.checkInexact(this.rollingStone.totalPrice(), 2.55*12, .0001) 
				&& t.checkInexact(this.houseOfCards.totalPrice(), 5.25*13, .0001)
				&& t.checkInexact(this.serial.totalPrice(), 0.0, .0001)
				&& t.checkInexact(this.merisaheli.totalPrice(), 5.0*12, 0.0001)
				&& t.checkInexact(this.friends.totalPrice(), 7.0*24, 0.0001)
				&& t.checkInexact(this.rajshamani.totalPrice(), 0.0*24, 0.0001);
	}

	//testing the duration method
	boolean testDuration (Tester t) {
		return t.checkExpect(this.rollingStone.duration(), 5*60*12)
				&& t.checkExpect(this.houseOfCards.duration(), 13*50)
				&& t.checkExpect(this.serial.duration(), 8*50);
	}

	//testing the format method:
	boolean testFormat (Tester t) {
		return t.checkExpect(this.rollingStone.format(), "Rolling Stone, $2.55.")
				&& t.checkExpect(this.houseOfCards.format(), "House of Cards, $5.25.")
				&& t.checkExpect(this.serial.format(), "Serial, $0.0.");   
	}
	//testing the sameEntertainment method
	boolean testSameEntertainment (Tester t) {
		return t.checkExpect(this.rollingStone.sameEntertainment(rollingStone), true)
				&& t.checkExpect(this.rollingStone.sameEntertainment(merisaheli), false)
				&& t.checkExpect(this.rollingStone.sameEntertainment(houseOfCards), false)
				&& t.checkExpect(this.houseOfCards.sameEntertainment(houseOfCards), true)
				&& t.checkExpect(this.houseOfCards.sameEntertainment(friends), false)
				&& t.checkExpect(this.houseOfCards.sameEntertainment(rajshamani), false)
				&& t.checkExpect(this.serial.sameEntertainment(serial), true)
				&& t.checkExpect(this.serial.sameEntertainment(rajshamani), false)
				&& t.checkExpect(this.serial.sameEntertainment(houseOfCards), false);
	}	
	
}









