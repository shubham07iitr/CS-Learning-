
public class Unions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}


interface IStation {
	
}
// to represent a subway station 

class TStop implements IStation {
	String name;
	String line;
	double price;
	
	TStop (String name, String line, double price){
		this.name = name;
		this.line = line;
		this.price = price;
	}
}

class CommStation implements IStation {
	String name;
	String line;
	boolean express;
	
	CommStation (String name, String line, boolean express){
		this.name = name;
		this.line = line;
		this.express = express;
		
	}
}

class ExamplesIStation  {
	ExamplesIStation () {}
	
	IStation harvard = new TStop("Harvard", "red", 1.25);
	IStation kenmore = new TStop("Kenmore", "green", 1.25);
	
	IStation backbay = new CommStation ("Back Bay", "Framingham", true);
	IStation wnewton = new CommStation ("West Newton", "Framingham", false);
	
}















