import tester.*;
interface IAT {
	int count() ;
	int countHelper();
	int countFemaleAnc();
	int countFemaleAncHelper ();
	boolean wellFormed() ;
	boolean wellFormedHelper (int yob);
	IAT youngerIAT (IAT other);
	IAT youngerIATHelper (IAT other, int otherYob);
	IAT youngestParent();
	IAT youngestGrandParent();
	IAT youngestAncAtGen(int gen);
	
}

class Unknown implements IAT {
	Unknown () {}
	public int count( ) {
		return 0;
	}
	public int countHelper() {
		return 0;
	}
	public int countFemaleAnc ( ) {
		return 0;
	}
	public int countFemaleAncHelper ( ) {
		return 0;
	}
	public boolean wellFormed () {
		return true;
	}
	public boolean wellFormedHelper (int yob) {
		return true;
	}

	public IAT youngerIAT(IAT other) {
		return other;
	}
	public IAT youngerIATHelper(IAT other, int otherYOB) {
		return other;
	}
	public IAT youngestParent() {
		return new Unknown();
	}
	public IAT youngestGrandParent() {
		return new Unknown ();
	}
	public IAT youngestAncAtGen(int gen) {
		if (gen == 0) {
			return this;
		}
		else { return new Unknown();}
	}
}

class Person implements IAT {
	String name;
	int yob;
	boolean isMale;
	IAT mom;
	IAT dad;
	Person (String name, int yob, boolean isMale, IAT mom, IAT dad) {
		this.name = name;
		this.yob = yob; 
		this.isMale = isMale;
		this.mom = mom;
		this.dad = dad;
	}
	public int count ( ) {
		return this.mom.countHelper() + this.dad.countHelper();
	}
	
	public int countHelper ( ) {
		return 1 + this.mom.countHelper() + this.dad.countHelper();
	}
	public int countFemaleAnc ( ) {
		return this.mom.countFemaleAncHelper() + this.dad.countFemaleAncHelper ();
		
	}
	public int countFemaleAncHelper () {
		if (this.isMale == false) {
			return 1 + this.mom.countFemaleAncHelper () + this.dad.countFemaleAncHelper ();
		} else {
			return this.mom.countFemaleAncHelper() + this.dad.countFemaleAncHelper();
		}
	}
	
	public boolean wellFormed() {
		return this.mom.wellFormedHelper(this.yob) &&
				this.dad.wellFormedHelper(this.yob) &&
				this.mom.wellFormed()  && this.dad.wellFormed();				
	}
	
	public boolean wellFormedHelper(int childYob) {
		return this.yob < childYob;	
	}
	
	public IAT youngerIAT (IAT other) {
		return other.youngerIATHelper(this, this.yob);
	}
	
	public IAT youngerIATHelper(IAT other, int otherYob) {
		if (this.yob > otherYob) {return this;}
		else {
			return other;
		}
	}

	public IAT youngestParent() {
		return this.mom.youngerIAT(this.dad);
	}
	public IAT youngestGrandParent() {
		return this.mom.youngestParent().youngerIAT(this.dad.youngestParent());
	}
	public IAT youngestAncAtGen(int gen) {
		if (gen == 0) {
			return this;}
		else if (gen == 1) {
			return this.youngestParent();
		}
		else if (gen == 2) {
			return this.youngestGrandParent();
		} else {
			return this.mom.youngestAncAtGen(gen-1).youngerIAT(this.dad.youngestAncAtGen(gen-1));
		}
	}
	
}

class ExamplesIAT {
  IAT enid = new Person("Enid", 1904, false, new Unknown(), new Unknown());
  IAT edward = new Person("Edward", 1902, true, new Unknown(), new Unknown());
  IAT emma = new Person("Emma", 1906, false, new Unknown(), new Unknown());
  IAT eustace = new Person("Eustace", 1907, true, new Unknown(), new Unknown());

  IAT david = new Person("David", 1925, true, new Unknown(), this.edward);
  IAT daisy = new Person("Daisy", 1927, false, new Unknown(), new Unknown());
  IAT dana = new Person("Dana", 1933, false, new Unknown(), new Unknown());
  IAT darcy = new Person("Darcy", 1930, false, this.emma, this.eustace);
  IAT darren = new Person("Darren", 1935, true, this.enid, new Unknown());
  IAT dixon = new Person("Dixon", 1936, true, new Unknown(), new Unknown());

  IAT clyde = new Person("Clyde", 1955, true, this.daisy, this.david);
  IAT candace = new Person("Candace", 1960, false, this.dana, this.darren);
  IAT cameron = new Person("Cameron", 1959, true, new Unknown(), this.dixon);
  IAT claire = new Person("Claire", 1956, false, this.darcy, new Unknown());

  IAT bill = new Person("Bill", 1980, true, this.candace, this.clyde);
  IAT bree = new Person("Bree", 1981, false, this.claire, this.cameron);

  IAT andrew = new Person("Andrew", 2001, true, this.bree, this.bill);

  boolean testIATCount(Tester t) {
  	return t.checkExpect (andrew.count(), 16);
  }
  
  boolean testIATcountFemaleAnc(Tester t) {
  	return t.checkExpect( andrew.countFemaleAnc(), 8);
  }
  boolean  testIATWellFormed (Tester t) {
  	return t.checkExpect(andrew.wellFormed(),true);
  }
  
  boolean testIATYoungerIAT (Tester t) {
  	return t.checkExpect(bree.youngerIAT(bill), bree) && 
  			t.checkExpect(cameron.youngerIAT(candace),candace);
  }
  boolean testIATYoungestParent(Tester t) {
  	return t.checkExpect(andrew.youngestParent(), bree) && 
  			t.checkExpect(bree.youngestParent(), cameron);
  }
  boolean testIATYoungestGrandParent (Tester t) {
  	return t.checkExpect(andrew.youngestGrandParent(), candace) && 
  			t.checkExpect(bree.youngestGrandParent(), dixon);
  }
  
  boolean testIATYoungestAncAtGen(Tester t ) {
  	return t.checkExpect(andrew.youngestAncAtGen(0), andrew) && 
  			t.checkExpect(andrew.youngestAncAtGen(1), bree) &&
  			t.checkExpect(andrew.youngestAncAtGen(2), candace) &&
  			t.checkExpect(andrew.youngestAncAtGen(3), dixon) &&
  			t.checkExpect(andrew.youngestAncAtGen(4), eustace);
  }
  
}

