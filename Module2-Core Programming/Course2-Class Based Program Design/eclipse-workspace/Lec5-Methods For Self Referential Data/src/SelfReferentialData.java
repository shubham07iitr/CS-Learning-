import tester.Tester;

public class SelfReferentialData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Book {
	String title;
	String author;
	int price;
	
	Book(String title, String author, int price) {
		this.title = title;
		this.author = author;
		this.price =price;
	}
}

interface ILoBook {
	int count();
	int totalPrice();
	ILoBook cheaperThan(int price);
	
}

class MtLoBook implements ILoBook{
	MtLoBook() {}
	public int count() {
		return 0;
	}
	public int totalPrice() {
		return 0;
	}
	public ILoBook cheaperThan(int price) {
		return this; //this is already an empty list of books
	}
}

class ConsLoBook implements ILoBook {
	Book first;
	ILoBook rest;
	
	ConsLoBook(Book first, ILoBook rest) {
		this.first = first;
		this.rest = rest;		
	}
	public int count() {
		return 1 + rest.count();
	}
	
	public int totalPrice( ) {
		return first.price + this.rest.totalPrice();
	}
	public ILoBook cheaperThan(int price) {
		if (this.first.price < price) {
			return new ConsLoBook(this.first, this.rest.cheaperThan(price));
			} else {
				return this.rest.cheaperThan(price);
			}
		
	}
}


class ILoBooksExample {
	ILoBooksExample() {}
	
	Book hp1 = new Book("HP1", "JKR", 20);
	Book hp2 = new Book("HP2", "JKR", 30);
	Book hp3 = new Book("HP3", "JKR", 40);
	
	ILoBook hpList3 = new ConsLoBook(hp1, new ConsLoBook(hp2, new ConsLoBook(hp3, new MtLoBook())));
	ILoBook hpList1 = new ConsLoBook(hp1, new MtLoBook());
	ILoBook emptyList = new MtLoBook();
	ILoBook hpList4 = new ConsLoBook(hp1, this.hpList3);
	
	
	boolean testILoBookCount(Tester t) {
		return t.checkExpect (hpList3.count(), 3) &&
				t.checkExpect(emptyList.count(), 0) &&
				t.checkExpect(hpList1.count(), 0) &&
				t.checkExpect(hpList4.count(), 4);
	}
	
	boolean testIloBookTotalPrice (Tester t) {
		return t.checkExpect(hpList3.totalPrice(), 90) &&
				t.checkExpect(emptyList.totalPrice(), 0) &&
				t.checkExpect(hpList1.totalPrice(), 20) &&
				t.checkExpect(hpList4.totalPrice(), 110); 
	}
	boolean testILoBookCheaperThan(Tester t) {
	return t.checkExpect(emptyList.cheaperThan(10), new MtLoBook()) &&
			t.checkExpect(hpList3.cheaperThan(40), new ConsLoBook(hp1, new ConsLoBook (hp2, new MtLoBook()))) &&
			t.checkExpect(hpList3.cheaperThan(50), hpList3);
	}
	
}













