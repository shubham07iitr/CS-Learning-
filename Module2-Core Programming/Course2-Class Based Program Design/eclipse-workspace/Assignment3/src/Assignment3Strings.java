// CS 2510, Assignment 3

import tester.*;
//==================================================================
// INTERFACE STRING

// to represent a list of Strings
interface ILoString {
    String combine(); // combine all Strings in this list into one
    ILoString sort() ; //produces a new list of strings with same elemnts just sorted in alphabetical order
    boolean isSorted(); // returns true if the list of string is sorted
    ILoString interleave(ILoString that); // merges 2 los, 1st elemnt from 1st list, 2nd from 2nd, 3rd from 1st and so on, if size is not same, than bigger size elements are leftover elements which will be added to the new list as well
    ILoString merge(ILoString that) ; //tkaes in 2 sorted list of strings and produces a new sorted list of strings, including duplicates
    ILoString reverse() ; //reverses a given list of strnigs
    boolean isDoubledList() ; // checks if the given list has pairs of identival list of elements
    boolean isPalindromeList() ; // checks if reverse of the list is the same as the current list
    
    //Helper Methods
    ILoString insert (String string); //inserts an element at the appropriate place in a given sorted list
    boolean isSame (ILoString los); // checks whether this los is same as the given that los
    String getFirst(); // gets the first element from a ILoSrting
    ILoString getRest (); // gets the rest element from ILoString
    ILoString insertAtEnd(String string); // inserts a string at the end of a list of string
}

//==================================================================
//CLASS MTLoString
// to represent an empty list of Strings
class MtLoString implements ILoString {
    MtLoString(){}
    
    //TEMPLATE
    /*
     * FIELDS
     * METHODS
     * ...this.combine() .... String
     * ...this.sort()  ....ILoString 
     *...this.isSorted() .... boolean
     *...this.interleave(ILoString that) ... ILoString 
     *...this.merge(ILoString that) .... ILoString
     *...this.reverse() ... ILoString
     *...this.isDoubledList()... boolean
     *...this.isPalindromeList ... boolean
     *...this.isSame(ILoString los) ... boolean
     *...this.getFirst() .... String
     *...this.getRest() ... ILoString
     *...this.insertAtEnd(String string) ... IListofString
     */
    
    
    //METHOD COMBINE
    //Signature > Self > String
    // combine all Strings in this list into one
    public String combine() {return "";} 
    
    //METHOD SORT
    //Signature > Self > ILoString
    // combine all Strings in this list into one
    public ILoString sort() {return this;} //this is a STUB
    
    
    //METHOD INSERT
    //Signature > Self, ILoString >  ILoString
    //inserts an element at the appropriate place in a given sorted list
    public ILoString insert(String string) {return new ConsLoString (string, this);}
    
    //METHOD iSSAME
    //Signature > Self, ILoString >  Boolean
    // checks whether this los is same as the given that los
    public boolean isSame(ILoString los) {
    	if (los instanceof MtLoString) {return true;}
    	else {return false;}
    }
    
    
    //METHOD isSORTED
    //Signature > Self > Boolean
    // returns true if the list of string is sorted
    public boolean isSorted() {return true;} //we decide to return true for an empty list of string 

    //METHOD getFIRST
    //Signature > Self > String
    // returns an excepption when trying to acces first elemnt of an empty list
    public String getFirst() {throw  new RuntimeException("Cannot get first of empty list");} //we throw an excwption when trying to access the first element of an empty list
    
    //METHOD getREST
    //Signature > Self > ILoString
    // returns an excepption when trying to acces rest elemnt of an empty list
    public ILoString getRest() {throw  new RuntimeException("Cannot get rest of empty list");} //we throw an excwption when trying to access the first element of an empty listreturn true;} //we decide to return true for an empty list of string
    
    
    //METHOD INTERLEAVE
    //Signature > Self, ILoString > ILoString
    // merges 2 los, 1st elemnt from 1st list, 2nd from 2nd, 3rd from 1st and so on, if size is not same, than bigger size elements are leftover elements which will be added to the new list as well
    public ILoString interleave(ILoString that) {return that;} //we just return the LoS passed as argument

    //METHOD MERGE
    //Signature > Self, ILoString > ILoString
    // tkaes in 2 sorted list of strings and produces a new sorted list of strings, including duplicates
    public ILoString merge(ILoString that) {return that;} //returns the other ILoString which is passed as an argument
    
    //METHOD INSERTATEND
    //Signature > Self, String > ILoString
    //inserts a string at the end of a list of string
    public ILoString insertAtEnd(String string) {return new ConsLoString(string, this);}
    

    //METHOD REVERSE
    //Signature > Self > ILoString
    // reverses a given list of strnigs
    public ILoString reverse() {return this;} //we just return the current empty list when asked for reverse
    
    //METHOD ISDOUBLEDLIST
    //Signature > Self > Boolean
    // checks if the given list has pairs of identival list of elements
    public boolean isDoubledList() {return true;} //we return true if it is an empty list
    
    //METHOD isPALINDROMELIST
    //Signature > Self > Boolean
    // checks if reverse of the list is the same as the current list
    public boolean isPalindromeList() {return true;} //we return true if it is an empty list 
    
}


//==================================================================
//CLASS ConsLoString
// to represent a nonempty list of Strings
class ConsLoString implements ILoString {
    String first;
    ILoString rest;
    
    ConsLoString(String first, ILoString rest){
        this.first = first;
        this.rest = rest;  
    }
    
    /*
     TEMPLATE
     FIELDS:
     ... this.first ...         -- String
     ... this.rest ...          -- ILoString
     
     METHODS
     ... this.combine() ...     -- String
     ...this.sort()  ....ILoString 
     ...this.isSorted() .... boolean
     ...this.interleave(ILoString that) ... ILoString 
     ...this.merge(ILoString that) .... ILoString
     ...this.reverse() ... ILoString
     ...this.isDoubledList()... boolean
     ...this.isPalindromeList ... boolean
     ...this.insert(String string) ... ILoString
     ...this.isSame(ILoString los) ... boolean
     ...this.getFirst() ... String
     ...this.getRest() ... ILoString
     ...this.insertAtEnd(String string) ...ILoString
     
     METHODS FOR FIELDS
     ......FIRST METHODS.........
     ... this.first.concat(String) ...        -- String
     ... this.first.compareTo(String) ...     -- int
     ......REST METHODS.........
     ... this.rest.combine() ...              -- String
     ...this.rest.sort()  ....ILoString 
     ...this.rest.isSorted() .... boolean
     ...this.rest.interleave(ILoString that) ... ILoString 
     ...this.rest.merge(ILoString that) .... ILoString
     ...this.rest.reverse() ... ILoString
     ...this.rest.isDoubledList()... boolean
     ...this.rest.isPalindromeList ... boolean
     ...this.rest.insert(String string) .... ILoString
     ...this.rest.isSame(ILoString los) ... boolean
     ...this.rest.getFirst() ... String
     ...this.rest.getRest() ... ILoString
     ...this.rest.insertAtEnd(String string) ... ILoString
     
     */
    
    // combine all Strings in this list into one
    public String combine(){
        return this.first.concat(this.rest.combine());
    }  
    
    //METHOD INSERT
    //Signature > Self, String >  ILoString
    //inserts an element at the appropriate place in a given sorted list
    public ILoString insert(String string) {
    	if (string.compareTo(this.first) < 0) {return new ConsLoString (string, this);}
    	else { return new ConsLoString (this.first, this.rest.insert(string));}
    }
    

    //METHOD SORT
    //Signature > Self > ILoString
    // combine all Strings in this list into one
    public ILoString sort() {
    	return this.rest.sort().insert(this.first);} // wishing for a method 
    
    //METHOD iSSAME
    //Signature > Self, ILoString >  Boolean
    // checks whether this los is same as the given that los
    public boolean isSame(ILoString los) {
    	if (los instanceof ConsLoString) {
    		if (this.getFirst().equals(los.getFirst()) && this.rest.isSame(los.getRest())) {return true;}
    		else {return false;}
    	} else {return false;}
    	}
    

    //METHOD isSORTED
    //Signature > Self > Boolean
    // returns true if the list of string is sorted
    public boolean isSorted() {return this.sort().isSame(this);} //we just check if srted list is same as the original list 

    //METHOD getFIRST
    //Signature > Self > String
    // returns first element of ConsLoString
    public String getFirst() {return this.first;}
    
    //METHOD getREST
    //Signature > Self > ILoString
    // returns rest element of ConsLoString
    public ILoString getRest() {return this.rest;}
    
    
    //METHOD INTERLEAVE
    //Signature > Self, ILoString > ILoString
    // merges 2 los, 1st elemnt from 1st list, 2nd from 2nd, 3rd from 1st and so on, if size is not same, than bigger size elements are leftover elements which will be added to the new list as well
    public ILoString interleave(ILoString that) {
    	if (that instanceof MtLoString) {return this;}
    	else {
    		return new ConsLoString (this.getFirst(), new ConsLoString(that.getFirst(), this.rest.interleave((ILoString) that.getRest())));}
    	}

    //METHOD MERGE
    //Signature > Self, ILoString > ILoString
    // tkaes in 2 sorted list of strings and produces a new sorted list of strings, including duplicates
    public ILoString merge(ILoString that) {return this.interleave(that).sort();} //this is a STUB
    

    //METHOD INSERTATEND
    //Signature > Self, String > ILoString
    //inserts a string at the end of a list of string
    public ILoString insertAtEnd(String string) {
    	if (this.rest instanceof MtLoString) {return new ConsLoString (this.first, new ConsLoString(string, new MtLoString()));}
    	else {return new ConsLoString(this.first, this.rest.insertAtEnd(string));}
    }
     
    //METHOD REVERSE
    //Signature > Self > ILoString
    // reverses a given list of strnigs
    public ILoString reverse() {return this.rest.reverse().insertAtEnd(this.first);} //this is a STUB
    
    //METHOD ISDOUBLEDLIST
    //Signature > Self > Boolean
    // checks if the given list has pairs of identival list of elements
    public boolean isDoubledList() {
    return this.first.equals(this.rest.getFirst()) && this.rest.getRest().isDoubledList();}
    
    //METHOD isPALINDROMELIST
    //Signature > Self > Boolean
    // checks if reverse of the list is the same as the current list
    public boolean isPalindromeList() {
    	return this.reverse().isSame(this);} //this is a STUB
}




//==================================================================
//CLASS ConsLoString
// to represent examples for lists of strings
class ExamplesStrings{
    
    ILoString mary = new ConsLoString("Mary ",
                     new ConsLoString("had ",
                     new ConsLoString("a ",
                     new ConsLoString("little ",
                     new ConsLoString("lamb.", new MtLoString())))));
    
    ILoString family = new ConsLoString ("Daddy",
    									 new ConsLoString ("Mummy",
    									 new ConsLoString ("Shubham", 
    									 new ConsLoString ("Krishna", 
    									 new ConsLoString ("Survi", new MtLoString())))));
    
    ILoString familyreverse = new ConsLoString ("Survi",
														 	new ConsLoString ("Krishna",
														 	new ConsLoString ("Shubham", 
														  new ConsLoString ("Mummy", 
														  new ConsLoString ("Daddy", new MtLoString()))))); 
    
    ILoString otherFamily = new ConsLoString("Shivansh", 
    												new ConsLoString("Aditi", new MtLoString()));
    
    ILoString fofinter = new ConsLoString ("Daddy",
    										 new ConsLoString ("Shivansh",
    										 new ConsLoString ("Mummy",
    										 new ConsLoString ("Aditi",
    										 new ConsLoString ("Shubham", 
												 new ConsLoString ("Krishna", 
												 new ConsLoString ("Survi", new MtLoString())))))));
    
    
    
    ILoString otherFamilySorted = new ConsLoString ("Aditi", 
    															new ConsLoString ("Shivansh", new MtLoString()));
    
    ILoString familySorted = new ConsLoString ("Daddy",
				 										 new ConsLoString ("Krishna",
														 new ConsLoString ("Mummy", 
														 new ConsLoString ("Shubham", 
														 new ConsLoString ("Survi", new MtLoString())))));  
    
    
    ILoString fsofsmerge = new ConsLoString ("Aditi",
    											 new ConsLoString ("Daddy",
													 new ConsLoString ("Krishna",
													 new ConsLoString ("Mummy", 
													 new ConsLoString ("Shivansh",
													 new ConsLoString ("Shubham", 
													 new ConsLoString ("Survi", new MtLoString())))))));  
    
    ILoString familyPalindrome = new ConsLoString ("Aditi",
														 new ConsLoString ("Shivansh",
														 new ConsLoString ("Mummy", 
														 new ConsLoString ("Shivansh", 
														 new ConsLoString ("Aditi", new MtLoString())))));  
    
    ILoString familyPaired = new ConsLoString ("Aditi",
														 new ConsLoString ("Aditi",
														 new ConsLoString ("Shivansh",
														 new ConsLoString ("Shivansh", 
														 new ConsLoString ("Mummy", 
														 new ConsLoString ("Mummy", new MtLoString()))))));  
    
    
    // test the method combine for the lists of Strings
    boolean testCombine(Tester t){
        return 
            t.checkExpect(this.mary.combine(), "Mary had a little lamb.");
    }
    
    //testing for Sorted method
    boolean testSort (Tester t) {
    	return t.checkExpect(family.sort(), familySorted) &&
    	t.checkExpect(otherFamily.sort(), otherFamilySorted);
    }
    //testing for isSorted method
    boolean testIsSorted(Tester t) {
    	return t.checkExpect(family.isSorted(), false) &&
    			t.checkExpect(familySorted.isSorted(), true);
    }
  //testing for interleave method
    boolean testInterleave(Tester t) {
    	return t.checkExpect(family.interleave(otherFamily), fofinter);
    }
    //testing for merge method
    boolean testMerge(Tester t) {
    	return t.checkExpect(family.merge(otherFamily), fsofsmerge);
    }
    
    
//    //testing for insertAtEnd method
//    boolean testInsertAtEnd(Tester t) {
//    	return t.checkExpect(otherFamily.insertAtEnd("Shubham"), new MtLoString());
//    }
//  
    //testing for reverse method
    boolean testReverse(Tester t) {
    	return t.checkExpect(family.reverse(), familyreverse);
    }
    
    //testing for isDoubledList method
    boolean isDoubledList(Tester t) {
    	return t.checkExpect(family.isDoubledList(), false) &&
    			t.checkExpect(familyPaired.isDoubledList(), true);
    }
//
    //testing for IsPalindromeList method
    boolean testIsPalindromList(Tester t) {
    	return t.checkExpect(family.isPalindromeList(), false) &&
    			t.checkExpect(familyPalindrome.isPalindromeList(), true);
    }
  
   }

















