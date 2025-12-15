import tester.*;

//filling is of type (filling1-string, filling2-string)
//represents filling in the class sandwich

class Filling {
	String filling1;
	String filling2;
	
	Filling (String filling1, String filling2) {
		this.filling1 = filling1 ;
		this.filling2 = filling2;
	}
}


//Menu is one of 
/*
 * -- Soup
 * -- Salad
 * -- Sandwiches
 */
//interp. as a menu of items at a deli
interface Menu {
	
}

//Soup is compound data of type (name - String, price - String, vegetarian - boolean)
// interp. as an item on the menu
class Soup implements Menu {
	String name;
	int price;
	boolean vegetarian;
	
	Soup (String name,int price , boolean vegetarian) {
		this.name = name;
		this.price = price ;
		this.vegetarian = vegetarian;
	}
	
}

//SAlad is compound data of type (name - String, price - String, vegetarian - boolean, dressing - String)
//interp. as an item on the menu
class Salad implements Menu {
	String name;
	int price;
	boolean vegetarian;
	String dressing; 
	
	Salad (String name, int price , boolean vegetarian, String dressing) {
		this.name = name;
		this.price = price;
		this.vegetarian = vegetarian;
		this.dressing = dressing;
	}
}

//Sandwich is compound data of type (name - String, price - String, vegetarian - boolean, dressing - String)
//interp. as an item on the menu
class Sandwich implements Menu {
	String name;
	int price;
	String bread;
	Filling filling; 

	Sandwich (String name, int price , String bread, Filling filling) {
		this.name = name;
		this.price = price;
		this.bread = bread;
		this.filling = filling;
	}
}

class ExamplesMenu {
	ExamplesMenu() {}
		
	Menu soup1 = new Soup("Tomato", 10, true);
	Menu soup2 = new Soup("Hot and Sour", 12, false);
	Menu salad1 = new Salad("Ceaser", 15, false , "Olive");
	Menu salad2  = new Salad("Russian", 18, true, "Mayo");
	Menu sandiwch1 = new Sandwich("Chicken", 20, "Focaccia", new Filling ("Peanut Butter", "Jelly"));
	Menu sandiwch2 = new Sandwich("Ham", 20, "Focaccia", new Filling ("Ham", "Cheese"));		
}

















