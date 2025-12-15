import tester.*;


class Book {
	String title;
	String author;
	int price;
	
	Book (String title, String author, int price) {
		this.title = title;
		this.author = author;
		this.price = price;
	}
	
	/* TEMPLATE:
	 * Fields:
	 * ... this.title... -- String
	 * ... this.author ... -- Author
	 * ... this.price ... -- int
	 */
	
	//Compute sale price of this book given discount rate as %
	int salePrice(int discount) {
		return this.price - (this.price*discount)/100;
	}
}

class ExamplesBook {
	ExamplesBook() {}
	
	Book htdp = new Book ("HtDP", "FFFK", 60);
	Book beaches = new Book ("Beaches", "PC", 20);
	
	boolean testSalePrice(Tester t) {
		return t.checkExpect(this.beaches.salePrice(20), 16);
	}
}


















