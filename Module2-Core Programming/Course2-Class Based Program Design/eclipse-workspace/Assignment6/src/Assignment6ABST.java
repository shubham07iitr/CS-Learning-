import tester.*; 
import java.util.Comparator; 

//DEFINING ILISTS
interface IList<T> {
	IList<T> add(T arg); //adding an element to the end of the list
	IList<T> addList(IList<T> that); // adding a list at the end of another list
	boolean isEmpty(); //Returns whether the current list is an empty list
	T getFirst(); // returns first element f a list, exception for MtList
	IList<T> getRest() ; // returns remaining elsements of a consList, exception for others
}

//Defining the MtList<T>
class MtList<T> implements IList<T> {

	public IList<T> add(T arg) {return new ConsList <T>(arg, new MtList<T> ());}
	public IList<T> addList(IList <T> argList) {return argList;} 
	public boolean isEmpty() {return true;}
	public T getFirst() {throw new RuntimeException ("Can't access first element from en empty list");}
  public IList<T> getRest() {throw new RuntimeException ("Can't access rest element from en empty list");}
}

//Defining the ConsList<T>

class ConsList<T> implements IList<T> {
	T first;
	IList<T> rest;

	ConsList(T first, IList<T> rest) {this.first = first;this.rest = rest;}
	public boolean isEmpty() {return false;}
	public IList<T> add(T arg) {
    if (this.rest.isEmpty()) {
      return new ConsList<T>(this.first, new ConsList<T>(arg, new MtList<T>()));
  } else {
      return new ConsList<T>(this.first, this.rest.add(arg));  // Keep this.first!
  }
	}
	public IList<T> addList(IList <T> argList) {
		if (argList.isEmpty()) {
      return this;
  } else {
      return this.add(argList.getFirst()).addList(argList.getRest());
  }
	}
	public T getFirst() {return this.first;}
  public IList<T> getRest() {return this.rest;}
}

//====================================================================
//EXTENDING COMPARATOR

class BooksByTitle implements Comparator<Book> {
	
	BooksByTitle(){}
	public int compare (Book b1, Book b2) {
		return b1.title.compareTo(b2.title);
	}
}

class BooksByAuthor implements Comparator<Book> {
	
	BooksByAuthor() {}
	public int compare (Book b1, Book b2) {
		return b1.author.compareTo(b2.author);
	}
}

class BooksByPrice implements Comparator<Book> {
	BooksByPrice() {}
	public int compare (Book b1, Book b2) {
		return b1.price - b2.price;
	}
}

//====================================================================
//ABSTRACT BST
// Is one of Leaf or a Node
// Node is of type (T data, ABST left, ABST Right)

abstract class Assignment6ABST<T> {
	Comparator<T> order;
	
	//Defining the constructor
	Assignment6ABST(Comparator<T> order) {this.order = order;}
	
	abstract Assignment6ABST<T> insert(T arg);
	abstract boolean present (T arg);
	abstract T getLeftMost();
	abstract boolean isLeaf(); //Checks if the current ABST is a leaf or not
	abstract Assignment6ABST<T> getRightTree(); //returns the tree containing all but the leftmost item of this tree.
	abstract boolean sameTree (Assignment6ABST<T> that); //checks whether that tree is same as this tree
	abstract Assignment6ABST<T> getLeft(); //Returns the left node of a node
	abstract Assignment6ABST<T> getRight(); //Returns the right node of a node
	abstract boolean sameData(Assignment6ABST<T> that); //checking if the current ABST has the same data as the given ABST
	abstract boolean presentAll(Assignment6ABST<T> that) ; //whether current BST is completely present in the given BTS
	abstract IList<T> buildList(); //creates a list in the same sorted order as that of the comparator
}


//IMPLEMENTING the Leaf Node
//Leaf node which interprets as an empty or false node
class Leaf<T> extends Assignment6ABST<T> {
	
	//Defining the constructor
	Leaf(Comparator<T> order) {
		super(order);
	}
	
	public Assignment6ABST<T> insert(T arg) {
		return new Node<T>(this.order, arg, new Leaf<T>(this.order), new Leaf<T>(this.order)); // inserting at an empty node will create a non empty node with data as the given arg, and the left and right node being empty
	}
	public boolean present(T arg) {return false;} // We always return false because an element cant be present in a leaf
	public T getLeftMost() {throw new RuntimeException("No leftmost item of an empty tree");}
	public boolean isLeaf() {return true; } // returns tre for a leaf
	public Assignment6ABST<T> getRightTree() {throw new RuntimeException("No right of an empty tree");}
	public boolean sameTree(Assignment6ABST<T> that) {
		if (that.isLeaf()) {return true;}
		else {return false;}
	}
	public Assignment6ABST<T> getLeft() {throw new RuntimeException("No left node of an empty tree");}
	public Assignment6ABST<T> getRight() {throw new RuntimeException("No right node of an empty tree");}
	public boolean sameData(Assignment6ABST<T> that) {return this.sameTree(that);} //we return tree if the other is also an emty tree else fasle
	public boolean presentAll(Assignment6ABST<T> that) {return true;} //always return true becayse leaf node will be present in any BST
	public IList<T> buildList () {return new MtList<T>();} // we return an empty list 
}



//CLASS NODE
//Node is of type <T> data, ABST Left, ABST Right
//Interp. as a node on an ABST

class Node<T> extends Assignment6ABST<T> {
	T data;
	Assignment6ABST<T> left;
	Assignment6ABST<T> right;
	
	//Defining the constructor 
	Node (Comparator<T> order,   T data, Assignment6ABST<T> left, Assignment6ABST<T> right) {
		super(order);
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	public Assignment6ABST<T> insert(T arg) {
		if (order.compare(arg, this.data) < 0) {return new Node<T>(this.order, this.data, this.left.insert(arg), this.right);}
		else {return new Node<T>(this.order, this.data, this.left, this.right.insert(arg));}
	}
	
	public boolean present(T arg) {
		if (order.compare(arg, this.data) == 0) {return true;}
		else {return this.left.present(arg) || this.right.present(arg);}
	}
	
	public boolean isLeaf() {return false; } // returns tree for a leaf
	public T getLeftMost() {
		if (this.left.isLeaf()) {return this.data;}
		else {return this.left.getLeftMost()	;}
	}
	public Assignment6ABST<T> getRightTree() {
		if (this.left.isLeaf()) {return this.right;}
		else {return new Node<T> (this.order, this.data, this.left.getRightTree(), this.right) ;} 
	}
	
	public boolean sameTree(Assignment6ABST<T> that) {
		 if (that instanceof Node) {
       Node<T> thatNode = (Node<T>) that;
       return this.data.equals(thatNode.data) && 
              this.left.sameTree(thatNode.left) && 
              this.right.sameTree(thatNode.right);
   } else {
       return false;  // that is a Leaf, this is a Node
   }
	}
	
	public boolean sameData(Assignment6ABST<T> that) {
		return this.presentAll(that) && that.presentAll(this);
	}
	
	public boolean presentAll(Assignment6ABST<T> that) {return that.present(this.data) && this.left.presentAll(that) && this.right.presentAll(that);}
	
	public Assignment6ABST<T> getLeft() {return this.left;}
	public Assignment6ABST<T> getRight() {return this.right;}
	
	public IList<T> buildList() {
		return this.left.buildList().add(this.data).addList(this.right.buildList());
	}
		
	
		
}

//====================================================================
//CLASS BOOK
//Book is of type (String title, String author, int price)
//interp. as a book to be read

class Book {
	String title;
	String author;
	int price;
	
	//Defining the constructor
	Book (String title, String author, int price) {
		this.title = title;
		this.author = author;
		this.price = price;
	}
}

//====================================================================
//CLASS EXAMPLES

class Assignment6Examples{
	Assignment6Examples() {}
	
	Book b1 = new Book("HP1" , "JKR", 15);
	Book b2 = new Book("Dark Matter" , "Blake Crouch", 12);
	Book b3 = new Book("HP2", "JKR", 17);
	Book b4 = new Book("Concepts of Physics1", "HCV", 20);
	Book b5 = new Book("Concepts of Physics2", "HCV2", 24);
	Book b6 = new Book("Physics by Irodov", "Irodov", 26);
	
	//Defining the elements for Constructor interface 
	BooksByTitle bbt = new BooksByTitle();
	BooksByAuthor bba = new BooksByAuthor();
	BooksByPrice bbp = new BooksByPrice();
	
	//Defining the elements for Leaves
	Assignment6ABST<Book> leafNodeBooksByTitle = new Leaf<Book>(bbt);
	Assignment6ABST<Book> leafNodeBooksByAuthor = new Leaf<Book>(bba);
	Assignment6ABST<Book> leafNodeBooksByPrice = new Leaf<Book>(bbp);
	
	Assignment6ABST<Book> bstByTitlePartial = new Node<Book>(bbt, b5, 
																					new Node<Book>(bbt, b4, leafNodeBooksByTitle, leafNodeBooksByTitle),
																					new Node<Book>(bbt, b6, leafNodeBooksByTitle, leafNodeBooksByTitle)); 
	
	IList<Book> listByTitlePartial = new ConsList<Book> (b4, new ConsList<Book> (b5, new ConsList<Book>(b6, new MtList<Book>())));
	
	
	Assignment6ABST<Book> bstByTitlePartialInsert = new Node<Book>(bbt, b5, 
																													new Node<Book>(bbt, b4, leafNodeBooksByTitle, leafNodeBooksByTitle),
																													new Node<Book>(bbt, b6, new Node<Book>(bbt, b3, leafNodeBooksByTitle, leafNodeBooksByTitle), leafNodeBooksByTitle)); 
	
	IList<Book> listByTitlePartialInsert = new ConsList<Book> (b4, new ConsList<Book> (b5, new ConsList<Book>(b3, new ConsList<Book>(b6, new MtList<Book>()))));
	
	
	Assignment6ABST<Book> bstByAuthorPartial = new Node<Book>(bba, b5, 
																								new Node<Book>(bba, b4, leafNodeBooksByAuthor, leafNodeBooksByAuthor),
																								new Node<Book>(bba, b6, leafNodeBooksByAuthor, leafNodeBooksByAuthor));
	
	IList<Book> listByAuthorPartial = new ConsList<Book> (b4, new ConsList<Book> (b5, new ConsList<Book>(b6, new MtList<Book>())));
	
	
	Assignment6ABST<Book> bstByAuthorPartialInsert = new Node<Book>(bba, b5, 
	    																					new Node<Book>(bba, b4, leafNodeBooksByAuthor, leafNodeBooksByAuthor),
	    																					new Node<Book>(bba, b6, leafNodeBooksByAuthor, new Node<Book>(bba, b3, leafNodeBooksByAuthor, leafNodeBooksByAuthor)));
	
	IList<Book> listByAuthorPartialInsert = new ConsList<Book> (b4, new ConsList<Book> (b5, new ConsList<Book>(b6, new ConsList<Book>(b3, new MtList<Book>()))));
	
	 
	Assignment6ABST<Book> bstByPricePartial = new Node<Book>(bbp, b5, 
																								new Node<Book>(bbp, b4, leafNodeBooksByPrice, leafNodeBooksByPrice),
																								new Node<Book>(bbp, b6, leafNodeBooksByPrice, leafNodeBooksByPrice));
	
	IList<Book> listByPricePartial = new ConsList<Book> (b4, new ConsList<Book> (b5, new ConsList<Book>(b6, new MtList<Book>())));
	
	
	Assignment6ABST<Book> bstByPricePartialInsert = new Node<Book>(bbp, b5, 
																						    new Node<Book>(bbp, b4, new Node<Book>(bbp, b3, leafNodeBooksByPrice, leafNodeBooksByPrice), leafNodeBooksByPrice),
																						    new Node<Book>(bbp, b6, leafNodeBooksByPrice, leafNodeBooksByPrice));
	
	IList<Book> listByPricePartialInsert = new ConsList<Book> (b3, new ConsList<Book> (b4, new ConsList<Book>(b5, new ConsList<Book>(b6, new MtList<Book>()))));
	
	
	
	

	Assignment6ABST<Book> bstByTitleFull = new Node<Book>(bbt, b1,  // Root: HP1
			new Node<Book>(bbt, b2,  // Left subtree root: Dark Matter
					new Node<Book>(bbt, b4,  // Left-left: Concepts of Physics1
							leafNodeBooksByTitle,
							new Node<Book>(bbt, b5, leafNodeBooksByTitle, leafNodeBooksByTitle)),  // Concepts of Physics2
					leafNodeBooksByTitle),  // Right of Dark Matter
			new Node<Book>(bbt, b3,  // Right subtree root: HP2
					leafNodeBooksByTitle,
					new Node<Book>(bbt, b6, leafNodeBooksByTitle, leafNodeBooksByTitle)));  // Physics by Irodov
	
	
	
	Assignment6ABST<Book> bstByAuthor = new Node<Book>(bba, b5,  // Root: HCV2
	    new Node<Book>(bba, b2,  // Left: Blake Crouch
	    		leafNodeBooksByAuthor,
	        new Node<Book>(bba, b4, leafNodeBooksByAuthor, leafNodeBooksByAuthor)),  // HCV
	    new Node<Book>(bba, b1,  // Right: JKR (b1)
	    		leafNodeBooksByAuthor,
	        new Node<Book>(bba, b3,  // JKR (b3)
	        		leafNodeBooksByAuthor,
	            new Node<Book>(bba, b6, leafNodeBooksByAuthor, leafNodeBooksByAuthor))));  // Irodov
	
	
	Assignment6ABST<Book> bstByPrice = new Node<Book>(bbp, b3,  // Root: price 17
	    new Node<Book>(bbp, b1,  // Left: price 15
	        new Node<Book>(bbp, b2, leafNodeBooksByPrice, leafNodeBooksByPrice),  // price 12
	        leafNodeBooksByPrice),
	    new Node<Book>(bbp, b4,  // Right: price 20
	    		leafNodeBooksByPrice,
	        new Node<Book>(bbp, b5,  // price 24
	        		leafNodeBooksByPrice,
	            new Node<Book>(bbp, b6, leafNodeBooksByPrice, leafNodeBooksByPrice))));  // price 26
	
	
	//Testing for insert method 
	boolean testInsert(Tester t) {
		return t.checkExpect(bstByTitlePartial.insert(b3), bstByTitlePartialInsert) &&
					 t.checkExpect(bstByAuthorPartial.insert(b3), bstByAuthorPartialInsert) &&
					 t.checkExpect(bstByPricePartial.insert(b3), bstByPricePartialInsert);
	}
	
	//Testing for present method
	
	boolean testPresent(Tester t) {
		return t.checkExpect(bstByAuthorPartialInsert.present(b1), true) &&
				t.checkExpect(bstByTitlePartialInsert.present(b1), false);
	}
	
	//Testing for getLeftMost method
	boolean testGetLeftMost (Tester t) {
		return t.checkExpect(bstByAuthorPartial.getLeftMost(), b4) &&
				t.checkExpect(bstByPricePartialInsert.getLeftMost(), b3);}
	
	
	//Testinf for sameTree
	boolean testSameTree (Tester t) {
	return t.checkExpect(bstByAuthorPartial.sameTree(bstByTitlePartial), true) &&
			t.checkExpect(bstByPricePartialInsert.sameTree(bstByTitlePartial	), false);}
	
	//Testing for getRight method
	boolean testGetRight (Tester t) {
		return t.checkExpect(bstByPricePartialInsert.getRightTree(), bstByPricePartial);
	}
	
	//Testing for sameData method
	boolean testSameData (Tester t) {
		return t.checkExpect(bstByPricePartialInsert.sameData(bstByPricePartialInsert), true) &&
				t.checkExpect(bstByPricePartialInsert.sameData(bstByAuthorPartial), false);
				
	}
	
	//Testing for buildList method
	boolean testBuildList (Tester t) {
		return t.checkExpect(bstByPricePartialInsert.buildList(), listByPricePartialInsert) &&
				t.checkExpect(bstByAuthorPartialInsert.buildList(), listByAuthorPartialInsert);
	}
}



















