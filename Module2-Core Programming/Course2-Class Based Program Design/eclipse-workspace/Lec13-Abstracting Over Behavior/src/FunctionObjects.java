	import tester.*;
	
	
	interface ILoRunner {
	//	ILoRunner findAllMaleRunners();
	//	ILoRunner findAllFemaleRunners();
	//	ILoRunner findAllUnderPos50();
		ILoRunner find(IRunnerPredicate predicate);
	}
	//=================================================================
	class MtLoRunner implements ILoRunner {
		MtLoRunner() {}
	//	public ILoRunner findAllMaleRunners() {
	//		return new MtLoRunner();
	//	}
	//	public ILoRunner findAllFemaleRunners() {
	//		return new MtLoRunner();
	//	}
	//	public ILoRunner findAllUnderPos50() {
	//		return new MtLoRunner() ;
	//	}
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
		
	//	
	//	public ILoRunner findAllMaleRunners() {
	//		if (this.first.isMale()) {
	//			return new ConsLoRunner(this.first, this.rest.findAllMaleRunners());
	//		} else {return this.rest.findAllMaleRunners();}
	//	}
	//	public ILoRunner findAllFemaleRunners () {
	//		if (this.first.isFemale()) {
	//			return new ConsLoRunner(this.first, this.rest.findAllFemaleRunners());
	//		} else {return this.rest.findAllFemaleRunners();}
	//	}
	//	public ILoRunner findAllUnderPos50 () {
	//		if (this.first.isPosUnder50()) {
	//			return new ConsLoRunner(this.first, this.rest.findAllUnderPos50());
	//		} else {return this.rest.findAllUnderPos50();}
	//	}
	//	
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
	}
	
	//=================================================================
	
	interface IRunnerPredicate{
		boolean apply(Runner r);
	}
	
	class IsMale implements IRunnerPredicate {
		IsMale() {}
		public boolean apply (Runner r) {return r.isMale;}
	}
	
	class IsFemale implements IRunnerPredicate {
		IsFemale() {}
		public boolean apply (Runner r) {return ! r.isMale;}
	}
	
	class IPosUnder50 implements IRunnerPredicate {
		IPosUnder50() {}
		
		public boolean apply (Runner r) {
			return r.pos <= 50;
		} 
	}
	
	class IsUnder40Runner implements IRunnerPredicate {
		IsUnder40Runner() {}
		public boolean apply(Runner r) {
			return r.age <= 40;
		}
	}
	
	class AndPredicate implements IRunnerPredicate {
		IRunnerPredicate left;
		IRunnerPredicate right;
		AndPredicate(IRunnerPredicate left, IRunnerPredicate right) {
			this.left = left;
			this.right = right;
		}
		public boolean apply (Runner r) {
			return this.left.apply(r) && this.right.apply(r);
		}
	}
	
	
	
	//=================================================================
	class ExamplesILoRunner {
		ExamplesILoRunner() {}
		
	  Runner johnny = new Runner("Kelly", 100, 999, true, 30, 360);
	  Runner frank  = new Runner("Shorter", 32, 888, true, 245, 130);
	  Runner bill = new Runner("Rogers", 36, 777, true, 119, 129);
	  Runner joan = new Runner("Benoit", 29, 444, false, 18, 155);
	  
	  ILoRunner mtlist = new MtLoRunner();
	  ILoRunner list1 = new ConsLoRunner(johnny, new ConsLoRunner(joan, mtlist));
	  ILoRunner list2 = new ConsLoRunner(frank, new ConsLoRunner(bill, list1));
	  
	  ILoRunner expectedFemaleRuners = new ConsLoRunner(this.joan, new MtLoRunner());
	  ILoRunner expectedMaleRunners = new ConsLoRunner(this.frank, new ConsLoRunner(this.bill, new ConsLoRunner(bill, mtlist)));
	  
	  
	//  boolean testILoRunnerFind(Tester t) {
	//  	return t.checkExpect(list2.findAllMaleRunners(), expectedMaleRunners) &&
	//  			t.checkExpect(list2.findAllFemaleRunners(), expectedFemaleRuners) ;
	//
	//  }
	  boolean testILoRunnerFind(Tester t) {
	  	return t.checkExpect(list2.find(new IsMale()), expectedMaleRunners) &&
	  			t.checkExpect(list2.find(new IsFemale()), expectedFemaleRuners) ;
	  			
	  }
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
