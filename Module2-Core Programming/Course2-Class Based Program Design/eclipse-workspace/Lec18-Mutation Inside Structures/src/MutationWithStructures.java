import tester.Tester;


//============================================================
//MAP functions for IList

interface IFunction <A, R> {
R apply(A t);
}


//=================================================================
//ILIST CLASS
interface IList<T>{
<R>IList<R> map(IFunction<T,R> function);
}

class MtList<T> implements IList<T> {
public <R>IList<R> map(IFunction<T,R> function) {
return new MtList<R>();
}
}

class ConsList<T> implements IList<T> {
T first;
IList<T> rest;

ConsList(T first, IList<T> rest) {
this.first = first;
this.rest = rest;
}

public <R> IList<R> map(IFunction<T,R> function) {
return new ConsList<R> (function.apply(this.first), this.rest.map(function));
}

}



//====================================================
//CLASS AUTHOR
//Each author has one book written and each book is written by one author  so a cyclical relation

class Author {
	String first;
	String  last;
	int yob;
//	Book book;
	IList<Book> books;
	
	Author (String first, String  last, int yob ,Book book) {
		this.first = first;
		this.last = last;
		this.yob = yob;
		//this.book = book;
		this.books = new ConsList<Book> (book, new MtList<Book>());
	}
	
	boolean sameAuthor(Author other) {
		return this.first.equals(other.first) &&
				this.last.equals(other.last) &&
				this.yob == other.yob ;
	}
	void updateBook(Book b) {
//		if (this.book !=null) {
//			throw new RuntimeException("Trying to add a second book to an author");}
		 if (!b.author.sameAuthor(this)) {
			throw new RuntimeException ("Book was not written by this author");
		}
		 else {
//			 this.book = b;
			 this.books = new ConsList<Book>(b, this.books);
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
	
	boolean testAuthor(Tester t) {
		
		Author jkRowling = new Author("JK", "Rowling", 1965, null);
		Book hp1 = new Book("HP1", 100, 1, jkRowling);
		jkRowling.book = hp1;
		
		return t.checkExpect(jkRowling.book.author, jkRowling)&&
				t.checkExpect(jkRowling.book, hp1) &&
				t.checkExpect(hp1.author, jkRowling) &&
				t.checkExpect(jkRowling.book.sameBook(hp1), true	);
	}
	
}










