import tester.*;

interface IBookPredicate {
	boolean apply(Book b) ;
}

interface IRunnerPredicate {
	boolean apply (Runner r) ;
}

//****************************************//
//FILTER or FIND

interface IPred<T> {
	boolean apply (T t);
}

class BookByAuthor implements IPred<Book> {
	public boolean apply(Book b) {
		return b.author.equals("JKR");
	}
}

class IsPosUnder50Runner implements IPred<Runner> {
	public boolean apply(Runner r) {
		return r.pos <= 50;
	}
}

class IsStringLongerThan10 implements IPred<String> {
	public boolean apply (String s) {
		return s.length() <= 10;
	}
}

//=================================================================
//MAP
interface IFunction <A, R> {
	R apply (A a);
}

class RunnerToStringName implements IFunction<Runner, String> {
	public String apply (Runner r) {
		return r.name;
	}
}


class RunnerToAge implements IFunction<Runner, Integer> {
	public Integer apply(Runner r) {
		return r.age ;
	}
}

class BookToAuthor implements IFunction<Book, String> {
	public String apply(Book b) {
		return b.author;
	}
}

//=================================================================
//FOLDR 
interface IFunction2 <A1,A2, R> {
R apply (A1 a1, A2 a2);
}

//sum = sum+ age of runner
class TotalAgeofRunners implements IFunction2<Runner, Integer, Integer>
{
	public Integer apply(Runner runner, Integer sum) {
		return runner.age + sum;
	}
}


//=================================================================
class Runner {
	String name;
	int age;
	int bib;
	boolean isMale;
	int pos;
	int time;

	Runner(String name, int age, int bib, boolean isMale, int pos, int time) {
		this.name = name;
		this.age = age;
		this.bib = bib;
		this.isMale = isMale;
		this.pos = pos;
		this.time = time;
	}
	boolean finishesBefore(Runner r) {
		return this.time < r.time;
	}
}

//=================================================================

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

//=================================================================

interface ILoBook {
	ILoBook find(IBookPredicate predicate);
}
class MtLoBook implements ILoBook {
	MtLoBook() {}
	public ILoBook find (IBookPredicate predicate) {
		return new MtLoBook();
	}
}
class ConsLoBook implements ILoBook {
	Book first;
	ILoBook rest;

	ConsLoBook (Book first, ILoBook rest) {
		this.first = first;
		this.rest = rest;
	}

	public ILoBook find(IBookPredicate predicate) {
		if (predicate.apply(this.first)) {
			return new ConsLoBook(this.first, this.rest.find(predicate));
		} else {return this.rest.find(predicate);}
	}
}

//=================================================================

interface ILoRunner {
	ILoRunner find(IRunnerPredicate predicate);
}
//=================================================================
class MtLoRunner implements ILoRunner {
	MtLoRunner() {}
	public ILoRunner find(IRunnerPredicate predicate) {
		return new MtLoRunner() ;
	}
}

class ConsLoRunner implements ILoRunner {
	Runner first;
	ILoRunner rest;

	ConsLoRunner (Runner first, ILoRunner rest) {
		this.first = first;
		this.rest = rest;
	}

	public ILoRunner find(IRunnerPredicate predicate) {
		if(predicate.apply(this.first)) {
			return new ConsLoRunner (this.first, this.rest.find(predicate));
		} else {return this.rest.find(predicate);}
	}
}

//=================================================================
interface IList<T>{
	IList<T> find(IPred<T> predicate);
	<R>IList<R> map(IFunction<T,R> function);
	<R> R     foldr(IFunction2<T, R, R> function, R initialValue);
}

class MtList<T> implements IList<T> {
	public IList<T> find(IPred<T> predicate) {
		return new MtList<T> ();
	}
	 public <R>IList<R> map(IFunction<T,R> function) {
		return new MtList<R>();
	}
	 public <R> R  foldr(IFunction2<T, R, R> function, R initialValue) {
		 return initialValue;
	 }
}

class ConsList<T> implements IList<T> {
	T first;
	IList<T> rest;
	
	ConsList(T first, IList<T> rest) {
		this.first = first;
		this.rest = rest;
	}
	
	public IList<T> find(IPred<T> predicate) {
		if (predicate.apply(this.first)) {
			return new ConsList<T> (this.first, this.rest.find(predicate));
		} else {return this.rest.find(predicate);
	}
}
	public <R> IList<R> map(IFunction<T,R> function) {
		return new ConsList<R> (function.apply(this.first), this.rest.map(function));
	}
	
	public <R> R foldr (IFunction2<T, R, R> function, R initialValue) {
		return function.apply(this.first, this.rest.foldr(function, initialValue));
		
	}
}


//=================================================================
class IPredExamples {
	IPredExamples() {}

	IPred<Book> byAuthor = new BookByAuthor() ;
	IPred<Runner> posUnder50 = new IsPosUnder50Runner();
}

class IListExamples {
	IListExamples() {}
	
	Runner frank  = new Runner("Shorter", 32, 888, true, 245, 130);
	Runner joan = new Runner("Benoit", 29, 444, false, 18, 155);
	
	
	IList<Runner> runnerList = new ConsList<Runner> (this.frank,
														 new ConsList<Runner> (this.joan, 
														 new MtList<Runner>()));
	
	IList<String> booklist = new ConsList<String> ("First",
													 new ConsList<String> ("Second",
													 new MtList<String> ()));
	
	IList<Integer> integerlist = new ConsList<Integer> (1,
			 											  new ConsList<Integer> (2,
			 											  new MtList<Integer> ()));
	
	IList<Runner> expectedRunnerList = new ConsList<Runner> (joan, new MtList<Runner>());
	
	IPred<Runner> posUnder50Runner = new IsPosUnder50Runner();
		
	boolean testIListExamples(Tester t) {
		return t.checkExpect(runnerList.find(posUnder50Runner), new ConsList<Runner> (joan, new MtList<Runner>()));
		
	}
	boolean testIListMap(Tester t) {
		return t.checkExpect(this.expectedRunnerList.map(new RunnerToStringName()), 
													new ConsList<String> ("Benoit", new MtList<String>()));
	}
	boolean testIListFoldR(Tester t) {
		return t.checkExpect(runnerList.foldr(new TotalAgeofRunners(), 0), 61);
	}
	
}



