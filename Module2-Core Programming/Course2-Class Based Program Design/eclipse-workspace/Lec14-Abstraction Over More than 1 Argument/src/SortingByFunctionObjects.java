import tester.Tester;


interface ILoRunner {
	ILoRunner sortBy(ICompareRunners comp);
	ILoRunner insertBy(ICompareRunners comp, Runner r);
	Runner findMin(ICompareRunners comp);
	Runner findMinHelper(ICompareRunners comp, Runner currentMinRunner);
//	Runner findWinner (ICompareRunners comp);
//	Runner getFirst();
	
//	ILoRunner sortByTime();
//	ILoRunner insertByTime(Runner r);
}
//=================================================================
class MtLoRunner implements ILoRunner {
	MtLoRunner() {}

	public ILoRunner sortBy(ICompareRunners comp) {
		return this;
	}

	public ILoRunner insertBy(ICompareRunners comp, Runner r) {
		return new ConsLoRunner(r,this);
	}
	public Runner findMin(ICompareRunners comp) {
		throw new RuntimeException("Cant find min of an empty list");
	}
	public Runner findMinHelper(ICompareRunners comp, Runner currentMinRunner) {
		return currentMinRunner;
	}
	
	
//	public Runner findWinner(ICompareRunners comp) {
//		throw new RuntimeException("Cant return a winner of an empty list");
//	}
//	public Runner getFirst() {
//		throw new RuntimeException("Cannot return first of an empty list");
//	}
	
//	public ILoRunner sortByTime() {
//		return this;
//	}
//	public ILoRunner insertByTime(Runner r) {
//		return new ConsLoRunner(r, this);
//	}
}

class ConsLoRunner implements ILoRunner {
	Runner first;
	ILoRunner rest;
	
	ConsLoRunner (Runner first, ILoRunner rest) {
		this.first = first;
		this.rest = rest;
	}
	
	public ILoRunner sortBy(ICompareRunners comp) {
		return  this.rest.sortBy(comp).insertBy(comp, this.first);
	}
	
	public ILoRunner insertBy (ICompareRunners comp, Runner r) {
		if (comp.comesBefore(this.first, r)) {
		return new ConsLoRunner(this.first, this.rest.insertBy(comp, r));
		} else {return new ConsLoRunner(r, this);}
	}
	
	public Runner findMin(ICompareRunners comp) {
		return this.findMinHelper(comp, this.first);
	}
	
	public Runner findMinHelper(ICompareRunners comp, Runner currentMinRunner) {
		if (comp.comesBefore(this.first, currentMinRunner)) {
			return this.rest.findMinHelper(comp, this.first);
		} else {
			return this.rest.findMinHelper(comp, currentMinRunner);
		}
	
	}
	
//	public Runner findWinner(ICompareRunners comp) {
//		return this.sortBy(comp).getFirst();
//	}
//	
//	public Runner getFirst() {
//		return this.first;
//	}
	
//	public ILoRunner sortByTime() {
//		return this.rest.sortByTime().insertByTime(this.first);
//	}
//	public ILoRunner insertByTime(Runner r) {
//		if (this.first.finishesBefore(r)) {
//			return new ConsLoRunner(this.first, this.rest.insertByTime(r));
//			} else {return new ConsLoRunner(r, this);
//	}
//}
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
interface ICompareRunners {
	boolean comesBefore(Runner r1 , Runner r2);
}
class CompareByTime implements ICompareRunners{
	public boolean comesBefore(Runner r1, Runner r2) {
		return r1.time < r2.time;
	}

}
class CompareByAge implements ICompareRunners {
	public boolean comesBefore (Runner r1, Runner r2) {
		return r1.age < r2.age;
	}
}

//=================================================================
class ExamplesILoRunnerFunctions {
	ExamplesILoRunnerFunctions() {}
	
  Runner johnny = new Runner("Kelly", 100, 999, true, 30, 360);
  Runner frank  = new Runner("Shorter", 32, 888, true, 245, 130);
  Runner bill = new Runner("Rogers", 36, 777, true, 119, 129);
  Runner joan = new Runner("Benoit", 29, 444, false, 18, 155);
  
  ILoRunner mtlist = new MtLoRunner();
  ILoRunner list1 = new ConsLoRunner(johnny, new ConsLoRunner(joan, mtlist));
  ILoRunner list2 = new ConsLoRunner(frank, new ConsLoRunner(bill, list1));
  
  ILoRunner expectedSortByTime = new ConsLoRunner(this.bill, 
  															 new ConsLoRunner(this.frank,
  															 new ConsLoRunner(this.joan,
  															 new ConsLoRunner(this.johnny,
  															 new MtLoRunner()))));
  
  
  ILoRunner expectedSortbyAge = new ConsLoRunner(this.joan, 
  															new ConsLoRunner(this.frank,
  															new ConsLoRunner(this.bill,
  															new ConsLoRunner(this.johnny,
  															new MtLoRunner()))));
  
  boolean testILoRunnerSortBy(Tester t) {
  	return t.checkExpect(list2.sortBy(new CompareByTime()), expectedSortByTime) &&
  			t.checkExpect(list2.sortBy(new CompareByAge()), expectedSortbyAge);
  }
  
  boolean testGetWinner(Tester t) {
  	return t.checkExpect(list2.findMin(new CompareByTime()), bill)&&
  			t.checkExpect(list2.findMin(new CompareByAge()), joan);
  }
  

}




















