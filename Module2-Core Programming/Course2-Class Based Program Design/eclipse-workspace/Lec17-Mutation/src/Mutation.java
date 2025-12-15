import tester.*;



//====================================================
//CLASS AUTHOR
//Each author has one book written and each book is written by one author  so a cyclical relation

class Author {
	String first;
	String  last;
	int yob;
	Book book;
	
	Author (String first, String  last, int yob ,Book book) {
		this.first = first;
		this.last = last;
		this.yob = yob;
		this.book = book;
	}
	
	boolean sameAuthor(Author other) {
		return this.first.equals(other.first) &&
				this.last.equals(other.last) &&
				this.yob == other.yob &&
				this.book.sameBook(other.book) ;
	}
	
	void updateBook(Book b) {
		if (this.book !=null) {
			throw new RuntimeException("Trying to add a second book to an author");}
		else if (!b.author.sameAuthor(this)) {
			throw new RuntimeException ("Book was not written by this author");
		}
		 else {this.book = b;
	}
}
}

//====================================================
//CLASS BOOK
class Book {
	String title;
	int price;
	int quantity;
	Author author;
	
	Book(String title, int price, int quantity, Author author) {
		this.title = title;
		this.price = price;
		this.quantity = quantity;
		this.author = author;	
		this.author.updateBook(this);
	}
	
	
	boolean sameBook(Book other) {
		return this.title.equals(other.title) &&
				this.price  == other.price &&
				this.quantity  == other.quantity &&
				this.author.sameAuthor(other.author) ;
		
	}
	
}
//====================================================
//CLASS COUNTER

class Counter {
	int val;
	
	Counter() {
		this(0);
	}
	
	Counter (int initialVal) {
		this.val = initialVal;
	}
	int get() {
		int ans = this.val; //0 first time, 1, second time
		this.val ++;  //1 first time, 2 second time
		return ans; //0 first time, 1, second time
	}
}

//====================================================
//CLASS COUNTEREXAMPLES

class ExamplesCounter {
	boolean testCounter (Tester t) {
		Counter c1 = new Counter (); //val = 0
		Counter c2 = new Counter(2); // val = 2
		
		//what should these tests be?
		return t.checkExpect(c1.get(), 0)              // Test 1
        && t.checkExpect(c2.get(), 2)              // Test 2
        								//1           //2
        && t.checkExpect(c1.get() == c1.get(), false)  // Test 3
        								//3					//3
        && t.checkExpect(c2.get() == c1.get(), true)  // Test 4
        							  //4					//4
        && t.checkExpect(c2.get() == c1.get(), true)  // Test 5
        								//5					//6
        && t.checkExpect(c1.get() == c1.get(), false)  // Test 6
        								//5					//7
        && t.checkExpect(c2.get() == c1.get(), false); // Test 7
	}
}

//====================================================
//CLASS BOOKAUTHOREXAMPLES

class BookAuthorExamples {
	BookAuthorExamples() {}
	
	Author jkRowling ;
	Author jackLondon;
	Book hp1 ;
	Book hp2 ;
	Book callOfTheWild;
	
	void initConditions() {
		this.jkRowling = new Author("JK", "Rowling", 1965, null);
		this.hp1 = new Book("HP1", 100, 1, jkRowling);
		this.hp2 = new Book("HP2", 100, 1, jkRowling);
		this.jackLondon = new Author ("Jack", "London", 1875, null);
		this.callOfTheWild= new Book("Call of the Wild", 100, 1, this.jackLondon);
	}
	
	
	boolean testAuthor(Tester t) {
		initConditions();
		jkRowling.updateBook(hp1);
		
		return t.checkExpect(jkRowling.book.author, jkRowling)&&
				t.checkExpect(jkRowling.book, hp1) &&
				t.checkExpect(hp1.author, jkRowling);
	}
	boolean testBook1(Tester t) {
		initConditions();
		jkRowling.updateBook(hp2);
		
		return t.checkExpect(hp2.author, jkRowling) &&
				t.checkExpect(jkRowling.book, hp2) &&
				t.checkExpect(jkRowling.book.author, jkRowling);
		
	}
	
	void test2Authors(Tester t) {
		initConditions();
		t.checkExpect(jkRowling.book, null);
		
		jkRowling.updateBook(hp1);
		t.checkExpect(jkRowling.book, hp1);
//		
//		jkRowling.updateBook(hp2);
//		t.checkExpect(jkRowling.book, hp1);
//		
//		t.checkExpect(hp2.author.book, hp2);
//		t.checkExpect(hp1.author.book, hp1);
		
		t.checkException(new RuntimeException("Trying to add a second book to an author"), 
											this.jkRowling, "updateBook", this.hp2);
		
	}
	
	void testWrongAuthor(Tester t) {
		initConditions();
		this.jkRowling.updateBook(callOfTheWild);
		t.checkException(new RuntimeException("Book was not written by this author"), 
											this.jkRowling, "updateBook", this.callOfTheWild);
	}
}











