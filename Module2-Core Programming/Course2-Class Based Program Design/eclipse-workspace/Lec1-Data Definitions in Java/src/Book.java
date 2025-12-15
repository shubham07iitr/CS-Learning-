public class Book {
	public static void main (String[] args) {
		ExamplesBooks obj = new ExamplesBooks();
		System.out.println(obj.penguin.year);
		
	}
}

class Author {
	String name;
	int yob;
	
	Author(String name, int yob){
		this.name = name;
		this.yob = yob;
		
	}
}
class NewBook {
	String title;
	Author author;
	Publisher publisher;
	int price;
	
	NewBook(String title, Author author, Publisher publisher, int price){
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		
	}
}

class Publisher {
	String name;
	String country;
	int year;
	
	Publisher (String name, String country, int year) {
		this.name = name;
		this.country = country;
		this.year = year;
	}
}

class ExamplesBooks{
	ExamplesBooks() {}
	
	Author pat = new Author ("Pat Conroy", 1948);
	Publisher penguin = new Publisher("Penguin", "America", 1950 );
	NewBook beaches = new NewBook ("Beaches", this.pat, this.penguin, 20);
	

	
	

}