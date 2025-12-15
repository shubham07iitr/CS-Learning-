import tester.*;

//==================================================================
//ABSTRACT CLASS ACCOUNT
// Represents a bank account
abstract class Account {

	int accountNum;  // Must be unique
	int balance;     // Must remain above zero (others Accts have more restrictions)
	String name;     // Name on the account
	//Constructor
	public Account(int accountNum, int balance, String name){
		this.accountNum = accountNum;
		this.balance = balance;
		this.name = name;
	}

	abstract int withdraw (int funds); // method to w/d funds from the accunt, returns the new balance
	abstract int deposit (int funds); // method to add funds to an account , returns the new balance


}

//CLASS CHECKING
//Represents a checking account
//Requires a minimum balance
class Checking extends Account{
	int minimum; // The minimum account balance allowed
	//Constructor
	public Checking(int accountNum, int balance, String name, int minimum){
		super(accountNum, balance, name);
		this.minimum = minimum;
	}
	//TEMPLATE
	/*
	 * FIELDS
	 * ...this.accountNum ...int
	 * ...this.balance ....int
	 * ...this.name.... String
	 * ...this.minimum ...int
	 * METHODS
	 * ...this.deposit ....int
	 * ...this.withdraw ...int
	 */
	
	//METHOD DEPOSIT
	//Signature> Self, INt > Int
	//Increases the balance value, and returns the new balance
	public int deposit (int funds) {
		this.balance = this.balance + funds;
		return this.balance;
	}
	
	//METHOD WITHDRAW
	//Signature > self, int > int
	//Reduces the balance by the amount, if balance - amount < minimum, throws an exception
	public int withdraw (int funds) {
		if (funds+ this.minimum	> this.balance) {throw new RuntimeException("Sorry, balance less than minimum required");}
		else {this.balance = this.balance - funds; return this.balance;}
	}


}

//CLASS CREDIT 
//Represents a credit line account
class Credit extends Account{

	int creditLine;  // Maximum amount accessible
	double interest; // The interest rate charged

	public Credit(int accountNum, int balance, String name, int creditLine, double interest){
		super(accountNum, balance, name);
		this.creditLine = creditLine;
		this.interest = interest;
	}
	//TEMPLATE
	/*
	 * FIELDS
	 * ...this.accountNum ...int
	 * ...this.balance ....int
	 * ...this.name.... String
	 * ...this.creditLine ...int
	 * ...this.interest....double
	 * 
	 * METHODS
	 * ...this.deposit ....int
	 * ...this.withdraw ...int
	 */
	
	//METHOD DEPOSIT
	//Signature> Self, INt > Int
	//Reduces the balance owed by the given amount, if funds > balance owed, throw an exception 
	public int deposit (int funds) {
		if (funds > this.balance) {throw new RuntimeException("Can't deposit more than you owe");}
		else {this.balance = this.balance-funds; return this.balance;}
		
	}
	
	//METHOD WITHDRAW
	//Signature > self, int > int
	//Increases the balance owed by the given amount, throws an error if balance + funds > creditLine
	public int withdraw (int funds) {
		if (funds+ this.balance	> this.creditLine) {throw new RuntimeException("Sorry, cannot withdraw more than the limit");}
		else {this.balance = this.balance + funds; return this.balance;}
	}
	
}


//CLASS SAVINGS
//Represents a savings account
//Must maintain a positive balance
class Savings extends Account{

	double interest; // The interest rate

	public Savings(int accountNum, int balance, String name, double interest){
		super(accountNum, balance, name);
		this.interest = interest;
	}
	//TEMPLATE
	/*
	 * FIELDS
	 * ...this.accountNum ...int
	 * ...this.balance ....int
	 * ...this.name.... String
	 * ...this.minimum ...int
	 * ...this.interest...double
	 * METHODS
	 * ...this.deposit ....int
	 * ...this.withdraw ...int
	 */
	
	//METHOD DEPOSIT
	//Signature> Self, INt > Int
	//Increases the balance value, and returns the new balance
	public int deposit (int funds) {
		this.balance = this.balance + funds;
		return this.balance;
	}
	
	//METHOD WITHDRAW
	//Signature > self, int > int
	//Reduces the balance by the amount, if balance < funds, throw an exception
	public int withdraw (int funds) {
		if (this.balance < funds) {throw new RuntimeException("Sorry, balance less than requested funds");}
		else {this.balance = this.balance - funds; return this.balance;}
	}
}


//==================================================================
//LIST OF ACCOUNTS INTERFACE
//INTERFACE ILOA
interface ILoA{
	ILoA addToList(Account arg); //adds an account at the end of a list
	Account getFirst(); //gets the first element 
	ILoA getRest(); //gets the last element of a list
	boolean isEmpty(); // checks if the given list is empty or not
	boolean contains(Account arg) ; //checks if the given account is present in the list of accunts
	int lenList(); //Returns the length of the list
	ILoA removeDuplicates() ; //Removes duplicates from a given list of accounts
	ILoA addFullList(ILoA that); //appends the given list to the current list
	ILoA addFunds(int funds, int accountNum); //deposits funds in a given account by identifying that account from a given list of accounts based on account num, or throws exception if necessary
	ILoA withdrawFunds (int funds, int accountNum); //withdraws funds in a given account by identifying that account from a given list of accounts based on account num, or throws exception if necessary
	ILoA removeAccount(int accountNum); //returns a new list of account, after removing the identified account from the list of accounts
}

//CLASS MtLoA
class MtLoA implements ILoA {
	MtLoA() {}
	//METHODS
	public Account getFirst() {throw new RuntimeException ("Cannot access First of an empty list");}
	public ILoA getRest() {throw new RuntimeException ("Cannot access Rest of an empty list");}
	public ILoA addToList(Account arg) {return new ConsLoA(arg, this);}
	public boolean isEmpty() {return true;} //we always return true here
	public boolean contains (Account arg) {return false;} //we always return false as empty list does not contain anything
	public int lenList() {return 0;} 
	public ILoA removeDuplicates() {return this;} //we return the same list for remove duplicates
	public ILoA addFullList(ILoA that) {return that;} //we return the other list for current list as empty list
	public ILoA addFunds(int funds, int accountNum) {throw new RuntimeException("Sorry no such bank account exists");} // we  throw an exception
	public ILoA withdrawFunds(int funds, int accountNum) {throw new RuntimeException("Sorry no such bank account exists");} // we  throw an exception
	public ILoA removeAccount(int accountNum) {throw new RuntimeException("Sorry no such bank account exists");} // we  throw an exception
}

 //Represents a non-empty List of Accounts...
class ConsLoA implements ILoA{

  Account first;
  ILoA rest;

  public ConsLoA(Account first, ILoA rest){
      this.first = first;
      this.rest = rest;
  }
  
  /* Template
   *  Fields:
   *    ... this.first ...         --- Account
   *    ... this.rest ...          --- ILoA
   *
   *  Methods:
   *
   *  Methods for Fields:
   *
   */
  
  //METHODS
  
  public ILoA addToList(Account arg) {
  	if (this.getRest().isEmpty()) {return new ConsLoA(this.first, new ConsLoA (arg, this.getRest())); }
  	else {return new ConsLoA(this.first, this.rest.addToList(arg));}}
  public Account getFirst () {return this.first;}
  public ILoA getRest () {return this.rest;}
  public boolean isEmpty() {return false;}
  public boolean contains	(Account arg) {return this.first.equals(arg) || this.rest.contains(arg);}
  public ILoA removeDuplicates() {if (this.rest.contains(this.first)) {return this.rest.removeDuplicates();} else {return new ConsLoA(this.first, this.rest.removeDuplicates());}} 
  public ILoA addFullList(ILoA that) {	
  	if (that.isEmpty()) {return this;} 
  	else {return this.addToList(that.getFirst()).addFullList(that.getRest());}}
  public int lenList() {return 1+ this.rest.lenList();}
  
  public ILoA addFunds(int funds, int accountNum) {
  	if (this.first.accountNum == accountNum) {this.first.deposit(funds); return new ConsLoA (this.first, this.rest);}
  	else {return this.rest.addFunds(funds, accountNum);}}
  
  public ILoA withdrawFunds(int funds, int accountNum) {
  	if (this.first.accountNum == accountNum) {this.first.withdraw(funds); return new ConsLoA (this.first, this.rest);}
  	else {return this.rest.withdrawFunds(funds, accountNum);}}
  
  public ILoA removeAccount(int accountNum) {
  	if (this.first.accountNum == accountNum) {return new ConsLoA (this.rest.getFirst(), this.rest.getRest());}
  	else {return new ConsLoA(this.first, this.rest.removeAccount(accountNum));}}
}
 
//==================================================================
//CLASS BANK
//Represents a Bank with list of accounts
class Bank {
	String name;
	ILoA accounts;
	//Constructor
	Bank(String name){
		this.name = name;
		// Each bank starts with no accounts
		this.accounts = new MtLoA();
	}
	
	//TEMPLATE
	/*
	 * FIELDS
	 * ...this.name ....String
	 * ...this.acounts...ILoA
	 * METHODS
	 *....this.add(Account acc) ... ILoA
	 */
	
	//METHOD ADD
	//Signature> Self, Account > Void
	//Adds another account to the current list of acounts for a bank - mutates in place
	public void add (Account acc) {
		this.accounts = accounts.addToList(acc);
	}
	//METHOD DEPOSIT
	//Signature> Self, Int, Int > Void
	//Deposits some funds to the given bank Acccount Num
	public void deposit (int funds, int accountNum) {
		this.accounts = this.accounts.addFunds(funds, accountNum); //we wish for a helper function 
	}

	//METHOD WITHDRAW
	//Signature> Self, Int, Int > Void
	//Withdraws some funds in a given bank account based on the num provided
	public void withdraw (int funds, int accountNum) {
		this.accounts = this.accounts.withdrawFunds(funds, accountNum); //we wish for a helper function 
	}
	
	//METHOD REMOVEACCOUNT
	//Signature> Self, Int > Void
	//Withdraws some funds in a given bank account based on the num provided
	public void removeAccount (int accountNum) {
		this.accounts = this.accounts.removeAccount(accountNum); //we wish for a helper function which removes the given account from the list of account 
	}
	
	
	
}


//==================================================================
//CLASS EXAMPLES
//Bank Account Examples and Tests
class Examples {

  Examples(){ this.reset(); }
 
 Account check1;
 Account savings1;
 Account credit1;
 ILoA loa;
 Bank bank;
 
 // Initializes accounts to use for testing with effects.
 // We place inside reset() so we can "reuse" the accounts
  void reset(){
     
     // Initialize the account examples
     check1 = new Checking(1, 100, "First Checking Account", 20);
     savings1 = new Savings(4, 200, "First Savings Account", 2.5);
     credit1 = new Credit(5, 300, "First Credit Account", 500, 2.5);
     loa = new ConsLoA(check1, new ConsLoA(savings1, new ConsLoA(credit1, new MtLoA())));
     bank = new Bank("HDFC");
 }
 
 // Tests the exceptions we expect to be thrown when
 //   performing an "illegal" action.
  void testExceptions(Tester t){
     reset();
     t.checkException(new RuntimeException("Sorry, balance less than minimum required"), this.check1, "withdraw", 1000);
     t.checkException(new RuntimeException("Sorry, cannot withdraw more than the limit"), this.credit1, "withdraw", 1000);
     t.checkException(new RuntimeException("Sorry, balance less than requested funds"), this.savings1, "withdraw", 1000);
     t.checkException(new RuntimeException("Can't deposit more than you owe"), this.credit1, "deposit", 1000);
 }
 
 // Test the Withdraw method(s)
  void testWithdraw(Tester t){
     reset();
     t.checkExpect(check1.withdraw(25), 75);
     t.checkExpect(savings1.withdraw(100), 100);
     t.checkExpect(credit1.withdraw(100), 400);
 }
  
  //Test the Deposit method(s)
  void testDeposit(Tester t){
    reset();
    t.checkExpect(check1.deposit(100), 200);
    t.checkExpect(savings1.deposit(100), 300);
    t.checkExpect(credit1.deposit(100), 200);}
  
  //METHODS for BANK
  //METHOD test for ADD
  void testAdd(Tester t) {
  reset();
  bank.add(check1);
  bank.add(savings1);
  bank.add(credit1);
  t.checkExpect(bank.accounts, loa);}
  
  //Test method for deposit in BANK
  void testDepositBank(Tester t) {
  	reset();
  	bank.add(check1);
    bank.add(savings1);
    bank.add(credit1);
  	bank.deposit(100, 1);
  	t.checkExpect(bank.accounts.getFirst().balance, 200);
  	t.checkException(new RuntimeException("Can't deposit more than you owe"), this.bank, "deposit", 1000, 5);
  	t.checkException(new RuntimeException("Sorry no such bank account exists"), this.bank, "deposit", 1000, 20);	
  }
  
  //Test method for Withdrawal in BANK
  void testWithdrawBank(Tester t) {
  	reset();
  	bank.add(check1);
    bank.add(savings1);
    bank.add(credit1);
  	bank.withdraw(70, 1);
  	t.checkExpect(bank.accounts.getFirst().balance, 30);
  	t.checkException(new RuntimeException("Sorry, cannot withdraw more than the limit"), this.bank, "withdraw", 1000, 5);
  	t.checkException(new RuntimeException("Sorry no such bank account exists"), this.bank, "withdraw", 1000, 20);	
  }
  
  //Test method for removeAccount in BANK
  void testRemoveAccount(Tester t) {
  	reset();
  	bank.add(check1);
    bank.add(savings1);
    bank.add(credit1);
  	bank.removeAccount(4);
  	t.checkExpect(bank.accounts, new ConsLoA(check1, new ConsLoA(credit1, new MtLoA())));
  	t.checkException(new RuntimeException("Sorry no such bank account exists"), this.bank, "removeAccount", 20);	
  }
  
  
  
  }


















