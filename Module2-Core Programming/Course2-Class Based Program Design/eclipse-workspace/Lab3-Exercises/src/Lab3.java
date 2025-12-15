import tester.*;
//===========================================================================
//PROBLEM GAMEPIECE

//GamePiece is one of-
//-- Base-Tile which is of type Number-int
//-- Merge-Tile of type piece1 GP, piece2 GP
// Interp. as an individual game tile for game 2048

interface IGamePiece {
	int getValue();
	IGamePiece merge(IGamePiece piece);
	boolean isValid();
}

//baseTile is of type value-int
//interp. as a type of tile in a gamepiece for 2038

class BaseTile implements IGamePiece {
	int value;
	
	BaseTile (int value) {this.value = value;}
	//TEMPLATE
	/*
	 * FIELDS
	 * .... this.value ...int
	 * METHODS
	 * ...this.getValue() ....int
	 * ...this.merge(IGamePiece piece) .... IGamePiece
	 * ...this.isValid() ...boolean
	 * METHODS for Args
	 * ...piece.getValue() ....int
	 * ...piece.merge(IGamePiece piece) ... MergeTile
	 */
	
	//METHOD GETVALUE
	//Signature> Self, -> Int
	//Returns the value of a base tile 
	
	public int getValue () {
		return this.value;
	}
//METHOD MERGE
	//Signature> Self, IGamePiece-> IGamePiece
	//Returns a new gamepiece mergepiece which results in after merging og two gamepieces
	
	public IGamePiece merge(IGamePiece piece) {
		return new MergeTile(this, piece);
	}
	
	//METHOD ISVALID
	//Signature> Self-> Boolean
	//Checks if tile was created as per rules of 2048
	public boolean isValid() {
		return true; //we will return true always for base tiles
	}

	
}

//MergeTile is of type v
//interp. as a type of tile in a gamepiece for 2038

class MergeTile implements IGamePiece {
	IGamePiece piece1;
	IGamePiece piece2;
	
	MergeTile(IGamePiece piece1, IGamePiece piece2) {
		this.piece1 = piece1;
		this.piece2 = piece2;
	}
//TEMPLATE
	/*
	 * FIELDS
	 * .... this.piece1 ...GamePiece
	 * .... this.piece2 ...GamePiece
	 * METHODS
	 * ...this.getValue() ....int
	 * ...this.merge(IGamePiece piece)....IGamePiece
	 * ...this.isValid() ....boolean
	 * METHODS for FIELDs
	 * ...this.piece1.getValue() ...int
	 * ...this.piece2.getValue() ...int
	 * ...this.piece1.isValid() ... boolean
	 * ...this.piece2.isValid() ... boolean
	 * ...this.piece1.merge(IGamePiece piece) ... IGamePiece
	 * ...this.piece2.merge(IGamePiece piece) ... IGamePiece
	 * METHODS for Args
	 * ...piece.getValue() ....int
	 * ...piece.merge(IGamePiece piece) ... IGamePiece
	 */
	
	
	
	//METHOD GETVALUE
	//Signature> Self, -> Int
	//Returns the value of all the gamepieces in a base tile
	
	public int getValue () {
		return this.piece1.getValue() + this.piece2.getValue();
	}
//METHOD MERGE
	//Signature> Self, IGamePiece-> IGamePiece
	//Returns a new gamepiece mergepiece which results in after merging og two gamepieces
	
	public IGamePiece merge(IGamePiece piece) {
		return new MergeTile(this, piece);
	}
	
	
	//METHOD ISVALID
	//Signature> Self-> Boolean
	//Checks if tile was created as per rules of 2048 - merge tile can only be created if and only if values of two tiles are same
	public boolean isValid() {
		if (this.piece1.getValue() == this.piece2.getValue()) {return true;}
		else {return false;}
	}
}

//===========================================================================
//EXAMPLES GAMEPIECE

class testGameExamples {
	testGameExamples() {}
	
	IGamePiece b1 = new BaseTile (2);
	IGamePiece b2 = new BaseTile (4);
	
	
	IGamePiece m1 = new MergeTile (b1, b2);
	IGamePiece m2 = new MergeTile (m1, b1);
	IGamePiece m3 = new MergeTile (b1,b1);
	IGamePiece m4 = new MergeTile (m1,m1);
			
	boolean testGetValue (Tester t) {
		return t.checkExpect(b1.getValue(),2)&&
				t.checkExpect(m1.getValue(), 6) &&
				t.checkExpect(m2.getValue(), 8);
		
	}
	boolean testMerge(Tester t) {
		return t.checkExpect(b1.merge(b2), m1 ) &&
				 t.checkExpect(m1.merge(b1), m2 );
	}
	
	boolean testIsValid(Tester t) {
		return t.checkExpect(b1.isValid(), true) &&
				t.checkExpect(m1.isValid(), false) &&
				t.checkExpect(m3.isValid(), true) &&
				t.checkExpect(m4.isValid(), true) ;

		
		
	}
	
}
