
public class IAT {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

interface iAT {}
	
class Unknown implements iAT {
	Unknown (){}
}

class Person implements iAT {
	String name;
	iAT mom;
	iAT dad;
	
	Person (String name, iAT mom, iAT dad) {
		this.name = name;
		this.mom = mom;
		this.dad = dad;
	}
}

class ExamplesAncestors {
	ExamplesAncestors() {};
	iAT unknown = new Unknown();
	iAT mary = new Person ("Mary", this.unknown, this.unknown); 
	iAT robert = new Person ("Robert", this.unknown, this.unknown); 
	iAT john = new Person ("John", this.unknown, this.unknown);
	iAT jane = new Person ("Jane", this.mary, this.robert);
	iAT dan = new Person("Dan", this.jane, this.john);
}








