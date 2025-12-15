import tester.*;

class Address {
	String city;
	String state;
	
	Address(String city, String state) {
		this.city = city;
		this.state = state;
	}
}


class Person {
	String name;
	int age;
	String gender;
	Address address;
	
	Person (String name, int age, String gender, Address address) {
		this.name = name ;
		this.age = age ;
		this.gender = gender ;
		this.address = address;
	}
}


class ExamplesPerson {
	ExamplesPerson() {} 
	
	Person tim = new Person("Tim", 23, "Male", new Address ("Boston", "MA"));
	Person kate = new Person("Kate", 22, "Female", new Address ("Warwick", "RI"));
	Person rebecca = new Person("Rebecca", 23, "Non-Binary", new Address ("Nashua", "NH"));
}