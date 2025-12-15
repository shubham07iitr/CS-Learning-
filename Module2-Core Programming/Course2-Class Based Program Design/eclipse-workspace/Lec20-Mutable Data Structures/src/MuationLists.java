import tester.Tester;

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

interface IMutableLoPerson {
	void add (String name, int phoneNum);
	void remove(String name);
}

class MutableLoPerson implements IMutableLoPerson {
	ILoPerson sentinel;
	MutableLoPerson () {sentinel = new Sentinel (new MtLoPerson()); }
	
	public void add(String name, int phoneNum) {
		this.sentinel = this.sentinel.insert(name, phoneNum);}
	public void remove(String name) {
		this.sentinel.removePerson(name);
	}
	
}


interface ILoPerson {
	boolean contains(String name);
	int findPhoneNum(String name);
	void changePhone(String name, int newNum);
	void removePerson(String name);
	void removePersonHelper(String name, AListPerson prev);
	ILoPerson insert(String name, int number);
}

abstract class AListPerson implements ILoPerson {
	ILoPerson rest;
	
	AListPerson(ILoPerson rest) {this.rest = rest;}
	public abstract boolean contains(String name);
	public abstract int findPhoneNum(String name);
	public abstract void changePhone(String name, int newNum);
	public abstract void removePerson(String name);
	public abstract void removePersonHelper(String name, AListPerson prev);
}

class Sentinel extends AListPerson {
		
	Sentinel (ILoPerson rest) {
		super(rest);
	}
	
	public boolean contains(String name) {return this.rest.contains(name);};
	public int findPhoneNum(String name) {return this.rest.findPhoneNum(name);}
	public void changePhone(String name, int newNum) { this.rest.changePhone(name, newNum);}
	public void removePerson(String name) {this.rest.removePersonHelper(name, this);}
	public void removePersonHelper(String name, AListPerson prev) {return ;}
	public ILoPerson insert(String name, int number) {return new Sentinel(this.rest.insert(name, number));}
}

class MtLoPerson implements ILoPerson {
	MtLoPerson() {}
	public boolean contains (String name) {return false;}
	public int findPhoneNum(String name) {return -1;}
	public void changePhone(String name, int newNum) {return ;}
	public void removePerson(String name) {return;}
	public void removePersonHelper(String name, AListPerson prev) {return ;}
	public ILoPerson insert(String name, int number) {
		return new ConsLoPerson(new Person(name, number), this);
	}
}

class ConsLoPerson extends AListPerson {
	Person first;
	ConsLoPerson (Person first, ILoPerson rest) {
		super(rest);
		this.first = first;
		
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
	public void removePersonHelper(String name, AListPerson prev) {
		if (this.first.sameName(name)) {
			prev.rest = this.rest;
			
		} else {this.removePerson(name);}
	}
	
	public void removePerson(String name) {
		this.rest.removePersonHelper(name , this);
	}
	public ILoPerson insert (String name, int number) {
		return new ConsLoPerson(this.first, this.rest.insert(name, number));
	}
}


//============================================================
//CLASS EXAMPLES REMOVE PERSON

class RemovePersonExamples {
	RemovePersonExamples() {}

	Person anne;
	Person clyde;
	Person henry;
	ILoPerson list1;
	ILoPerson list2;

	void initData() {
		this.anne = new Person("Anne", 1234);
		this.clyde = new Person("Clyde", 2345);
		this.henry = new Person("Henry", 3456);

		this.list1 = new Sentinel (new ConsLoPerson(this.anne, 
															 new ConsLoPerson(this.clyde,
															 new ConsLoPerson(this.henry,  
															 new MtLoPerson()))));

		this.list2 = new Sentinel(new ConsLoPerson(this.anne, 
														  new ConsLoPerson(this.clyde,
														  new ConsLoPerson(this.henry,  
														  new MtLoPerson()))));
	}

		void testRemoveFirstPerson(Tester t) {
			this.initData();

			//Check initial conditions
			t.checkExpect(list1.contains("Anne"), true);
			t.checkExpect(list2.contains("Anne"), true);
			//Modify list1
			list1.removePerson("Anne");
			//Check that list1 has been modified....
			t.checkExpect(list1.contains("Anne"), false);
			//... but that list2 has not
			t.checkExpect(list2.contains("Anne"), true);
		}
		
		//Tests removing the middle person
		void testRemoveMiddlePerson(Tester t) {
			this.initData();

			//Check initial conditions
			t.checkExpect(list1.contains("Clyde"), true);
			t.checkExpect(list2.contains("Clyde"), true);
			//Modify list1
			list1.removePerson("Clyde");
			//Check that list1 has been modified....
			t.checkExpect(list1.contains("Clyde"), false);
			//... but that list2 has not
			t.checkExpect(list2.contains("Clyde"), true);
		}
		
		//Tests removing the last person
		void testRemoveLastPerson(Tester t) {
			this.initData();

			//Check initial conditions
			t.checkExpect(list1.contains("Henry"), true);
			t.checkExpect(list2.contains("Henry"), true);
			//Modify list1
			list1.removePerson("Henry");
			//Check that list1 has been modified....
			t.checkExpect(list1.contains("Henry"), false);
			//... but that list2 has not
			t.checkExpect(list2.contains("Henry"), true);
		}
		
		void testInsert (Tester t) {
			this.initData () ;
			ILoPerson expected = new ConsLoPerson(this.anne,
													 new ConsLoPerson (this.clyde, 
													 new ConsLoPerson(this.henry, new MtLoPerson())));
		
		ILoPerson listInsert = new MtLoPerson() ;
		listInsert = listInsert.insert("Anne", 1234);
		listInsert = listInsert.insert("Clyde", 1234);
		listInsert = listInsert.insert("Henry", 1234);
		t.checkExpect(listInsert, expected);
		}
		
		void testIMutablePerson (Tester t) {
			IMutableLoPerson newList = new MutableLoPerson();
			newList.add("Anne", 1234);
			newList.add("Clyde", 1234);
			newList.add("Henry", 3456);
			
			newList.remove("Clyde");
			
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


















