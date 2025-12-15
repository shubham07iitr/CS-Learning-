import tester.*;

interface IDate {}

class Date implements IDate {
	int year;
	int month;
	int day;

	Date (int year, int month, int day) {
		this.year = (new Utils()).checkRange(year, 1500, 2100, "Invalid year:" + Integer.toString(year));
		this.month = (new Utils()).checkRange(month, 1, 31, "Invalid month:" + Integer.toString(month));
		this.day = (new Utils()).checkRange(day, 1, 31, "Invalid day:" + Integer.toString(day));	
	}
}

class Utils {
	Utils () {}

	int checkRange(int value, int low, int high, String message) {
		if (value >= low && value <=  high) {
			return value;
		} else {
			throw new IllegalArgumentException(message);
		}
	}
	
	
}

class ExamplesDates {
	ExamplesDates () {}

	//Good dates
	IDate d20100228 = new Date (2010, 2, 28); 
	IDate d20091012 = new Date (2009,10,12);
	//bad date
	IDate dn303323 = new Date (-30, 33, 23);
	
	boolean testCheckConstructorException( Tester t) {
		return t.checkConstructorException(new IllegalArgumentException("Invalid year: -20"), "Date", -30, 33, 23 );
	}
	
}
