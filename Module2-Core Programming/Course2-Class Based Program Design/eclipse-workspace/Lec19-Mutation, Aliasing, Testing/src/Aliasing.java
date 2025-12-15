import tester.*;

//============================================================
//CLASS PERSON

class Person {
  String name;
  int phone;
  Person(String name, int phone) {
    this.name = name;
    this.phone = phone;
  }
  // Returns true when the given person has the same name and phone number as this person
  boolean samePerson(Person that) {
    return this.name.equals(that.name) && this.phone == that.phone;
  }
  boolean sameName(String name) {
  	return this.name.equals(name);
  }
  void changePhone(int newNum) {
  	this.phone = newNum;
  }
  
}



//============================================================
//CLASS ILoPerson
interface ILoPerson {
	boolean contains(String name);
	int findPhoneNum(String name);
	void changePhone(String name, int newNum);
}

class MtLoPerson implements ILoPerson {
	MtLoPerson() {}
	public boolean contains (String name) {return false;}
	public int findPhoneNum(String name) {return -1;}
	public void changePhone(String name, int newNum) {return ;}
}

class ConsLoPerson implements ILoPerson {
	Person first;
	ILoPerson rest;
	ConsLoPerson (Person first, ILoPerson rest) {
		this.first = first;
		this.rest = rest;
	}
	public boolean contains (String name) {
		return this.first.sameName(name) || this.rest.contains(name);
	}
	public int findPhoneNum(String name) {
		if (this.first.sameName(name)) {
			return this.first.phone;
		} else {return this.rest.findPhoneNum(name);}
	}
	public void changePhone (String name, int newNum) {
		 if (this.first.sameName(name)) {
       this.first.changePhone(newNum);
   } else {
       this.rest.changePhone(name, newNum);
   }
}
	}


//============================================================
//CLASS EXAMPLES


class ExamplePhoneLists {
  Person anne = new Person("Anne", 1234);
  Person bob = new Person("Bob", 3456);
  Person clyde = new Person("Clyde", 6789);
  Person john = new Person("John", 8901);
  
  ILoPerson friends, work;
  void initData() {
  	this.friends = new ConsLoPerson(this.anne, 
  								 new ConsLoPerson(this.bob,
        					 new ConsLoPerson(this.clyde, 
        					 new ConsLoPerson(this.john, 
        					 new MtLoPerson()))));
  	
  	this.friends = new ConsLoPerson(this.anne, 
				 new ConsLoPerson(this.bob,
				 new ConsLoPerson(this.clyde,  
				 new MtLoPerson())));
  }
  	
  	void testContainsName(Tester t) {
  		this.initData();
  		t.checkExpect(this.friends.contains("Anne"), true);
  		t.checkExpect(this.work.contains("Zelda"), false);
  	}
  	
 void testFindPhoneNum(Tester t) {
	 this.initData();
	 t.checkExpect(this.friends.findPhoneNum("Bob"), 3456);
	 t.checkExpect(this.work.findPhoneNum("Zelda"), -1);
	 t.checkExpect(this.friends.findPhoneNum("Anne"), this.work.findPhoneNum("Anne"));
 }
 void testChangePhoneNum(Tester t) {
	 this.initData();
	 
	 t.checkExpect(this.friends.findPhoneNum("Bob"), 3456);
	 t.checkExpect(this.friends.findPhoneNum("Bob"), this.work.findPhoneNum("Bob"));
	 t.checkExpect(this.bob.phone, 3456);
	 
	 this.friends.changePhone("Bob", 9021);
	 t.checkExpect(this.friends.findPhoneNum("Bob"), 9021);
	 t.checkExpect(this.friends.findPhoneNum("Bob"), this.work.findPhoneNum("Bob"));
	 t.checkExpect(this.bob.phone, 9021);
 }
 
 void testChangePhoneonPerson(Tester t) {
	 this.initData();
	 
	 t.checkExpect(this.bob.phone, 3456);
	 this.bob.changePhone(2222);
	 t.checkExpect(this.bob.phone, 2222);
	 
	 
 }
 
 void testAliasing(Tester t) {
	 // Create Person objects that are the same
	 Person johndoe1 = new Person("John Doe", 12345);
	 Person johndoe2 = new Person("John Doe", 12345);
	 //Alias johndoe1 to johndoe3
	 Person johndoe3 = johndoe1;
	 
	 //Check that all 3 john does are same accoriging to same Person
	 t.checkExpect(johndoe1.samePerson(johndoe2), true);
	 t.checkExpect(johndoe1.samePerson(johndoe3), true);
	 
	 //Modify johndoe1
	 johndoe1.name = "Johnny Deere";
	 t.checkExpect(johndoe1.samePerson(johndoe2), true);
	 t.checkExpect(johndoe1.samePerson(johndoe3), true);
	 t.checkExpect(johndoe1==johndoe3, true);
 }
}


















