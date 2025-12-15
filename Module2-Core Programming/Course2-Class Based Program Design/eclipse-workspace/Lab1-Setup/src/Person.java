import tester.* ;

class Person {
	String name;
	int age;
	String gender;
	
	Person (String name, int age, String gender) {
		this.name = name ;
		this.age = age ;
		this.gender = gender ;
	}
}


class ExamplesPerson {
	ExamplesPerson() {} 
	
	Person tim = new Person("Tim", 23, "Male");
	Person kate = new Person("Kate", 22, "Female");
	Person rebecca = new Person("Rebecca", 23, "Non-Binary");
}