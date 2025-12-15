import tester.*; 

//============================================================================================================
//PROBLEM-EMBROIDERY

//EmpbroideryPiece is of type (String name, Motif motif)
//interp. as an embroidery piece such as a pillow cover 

class EmbroideryPiece {
	String name;
	IMotif motif;
	//defining the constructor
	EmbroideryPiece (String name, IMotif motif) {
		this.name = name ;
		this.motif = motif;
	}
	//TEMPLATE
	/*
	 * FIELDS
	 * ... this.name    ....String
	 * ... this.motif   ....Motif
	 * METHODS
	 * ... this.averageDifficulty ... ouble
	 * ... this.embroideryInfo   ... String
	 * METHODS for FIELDS
	 * ...this.averageDifficultyMotif ....double
	 * ...this.motif.count()   ...double
	 * ...this.motif.sum()      ...double
	 * ...this.motif.embroideryInfoMotif ... String
	 */
	
	//Signature-> Self -> Double
	// Purpose is to get the avg difficulty of all CrossStitch and ChainStich motifs in an embroidery
	
	double averageDifficulty() {
		return this.motif.averageDifficultyMotif();
	}
	
	//Signature -> Self -> String
	// Gets the embroidery info in this format:
	// --"Pillow Cover: bird (cross stitch), tree (chain stitch), rose (cross stitch), poppy (chain stitch), daisy (cross stitch)."
	// --String that has in it all names of cross-stitch and chain-stitch motifs in an EmbroideryPiece, their stitch types in parentheses, and each motif separated by comma and space
	String embroideryInfo () {
		return this.name + ":" + " " + this.motif.embroideryInfoMotif() + ".";
	}
}


//Motif is one of type
// -- CrossStitchMotif is of type (String description, Double Difficulty)
// -- ChainStitchMotif is of type (String description, Double difficulty)
// -- GroupMotif is one of MTMotif,  cons Motif on group of motifs

interface IMotif {
	double averageDifficultyMotif();
	double sum() ;
	double count();
	String embroideryInfoMotif();
}

//CrossStitchMotif is of type (String description, Double Difficulty)
//interp. as a type of motif for an embroidery piece

class CrossStitchMotif implements IMotif {
	String description;
	Double difficulty;
	
	//defining the constructor
	CrossStitchMotif (String description, Double difficulty) {
		this.description = description;
		this.difficulty = difficulty;
	}	
	//TEMPLATE
	/*
	 * FIELDS
	 *... this.description   ... String
	 *... this.difficulty    ... double
	 * METHODS
	 * ... this.averageDifficultyMotif ...double
	 * ... this.sum()   ... double
	 * .... this.count() ... double
	 * ... this.embroideryInfo() ...String
	 */
	
	//Signature-> Self -> Double
	//purpose is to get the avg. difficulty in a cross stitch motif
	public double averageDifficultyMotif () {
		return this.difficulty;
	}
	
	//Gets the sum of all the difficulties in this Motif
	public double sum() {return this.difficulty;}
	//Gets the count of all the difficulties in this motif
	public double count() {return 1.0;}
	
	//Signature> Self -> String
	//Returns the string with name of motif separated by space followed by cross stitch in parentheses
	public String embroideryInfoMotif() {
		return this.description + " (cross stitch)";
	}
}

//ChainStitchMotif is of type (String description, Double Difficulty)
//interp. as a type of motif for an embroidery piece

class ChainStitchMotif implements IMotif {
	String description;
	Double difficulty;

	//defining the constructor
	ChainStitchMotif (String description, Double difficulty) {
		this.description = description;
		this.difficulty = difficulty;
	}	
	//TEMPLATE
	/*
	 * FIELDS
	 *... this.description   ... String
	 *... this.difficulty    ... double
	 * METHODS
	 * ... this.averageDifficultyMotif() ...double
	 * ...this.sum()   ... double
	 * ... this.count() ...double
	 * ....this.embroideryInfo() ... String
	 */
	
	//Signature-> Self -> Double
	//purpose is to get the avg. difficulty in a chain stitch motif
	public double averageDifficultyMotif () {
		return this.difficulty;
	}
	//Gets the sum of all the difficulties in this Motif
	public double sum() {return this.difficulty;}
	//Gets the count of all the difficulties in this motif
	public double count() {return 1.0;}
	
	//Signature> Self -> String
	//Returns the string with name of motif separated by space followed by chain stitch in parentheses
	public String embroideryInfoMotif() {
		return this.description + " (chain stitch)";
	}
}

//GroupMotif is one of 
// --MTMotif
// --cons Motif on group of motifs
//interp. as aa list of Motifs as described above

interface ILoMotif extends IMotif{
	double averageDifficultyMotif ();
	double count ();
	double sum ();
	String embroideryInfoMotif();
}


//MTMotif represents the end of a list of motifs
class MtLoMotif implements ILoMotif {
	MtLoMotif( ) {}

	//Signature> Self -> Double
	public double averageDifficultyMotif (){
		return 0.0;
	}
	
	//Signature> Self-> Double
	//Purpose - Gets the count of all elements in the ILoMotif
	public double count () {return 0;}
	
	//Signature> Self-> Double
	//Purpose - Gets the sum of all difficulties in the ILoMotif
	public double sum () {return 0;}
	
	//Signature> Self -> String
	//Returns an empty string
	public String embroideryInfoMotif() {
		return "";
	}
	
}

//CoLoMotifs is of type (Motif first_motif, CoLoMotifs, rest_motifs)
class ConsLoMotif implements ILoMotif {
	IMotif first_motif;
	ILoMotif rest_motifs;
	
	ConsLoMotif (IMotif first_motif, ILoMotif rest_motifs) {
		this.first_motif = first_motif;
		this.rest_motifs = rest_motifs;
	}
	
	//TEMPLATE
	/*
	 * FIELDS
	 * ...  this.first_motif ... IMotif
	 * ... this.rest_motif   ....ILoMotif
	 * METHODS
	 * ... this.averageDifficultyMotif() ... double
	 * ... this.count()                  ... double
	 * ... this.sum()                    ... double
	 * ... this.embroideryInfoMotif()    ... String
	 * METHODS for FIELDS
	 * ... this.first_motif.averageDifficultyMotif() ... double
	 * ... this.first_motif.sum()     ...double
	 * ... this.first_motif.count()   ... double
	 * ... this.first_motif.embroideryInfo() ... String
	 * ... this.rest_motif.averageDifficultyMotif () .... double
	 * ... this.rest_motifs.count()                  ....  double
	 * ... this.rest_motifs.sum()                  ....  double
	 * ... this.rest_motifs.embroideryInfoMotif()       .... String
	 */
	
	//Signature> Self-> Double
	//Purpose - Gets the count of all elements in the ILoMotif
	public double count () {
		return 1 + this.rest_motifs.count();
	}
	
//Signature> Self-> Double
	//Purpose - Gets the sum of all difficulties in the ILoMotif
	public double sum () {
		return this.first_motif.sum() + this.rest_motifs.sum();
}
	
	//Signature -> Self -> Double
	//Purpose is to get the average of all Motifs inside a list of motifs
	public double averageDifficultyMotif () {
		 double count = this.count();
	    if (count == 0.0) return 0.0;
	    return this.sum() / count; 
	}
	//Signature-> Self > String
	//Purpose is to get the list of motifs in the format of info + ", " + info
	public String embroideryInfoMotif() {
		 if (this.rest_motifs.count() == 0) {
       return this.first_motif.embroideryInfoMotif();
   } else {
       return this.first_motif.embroideryInfoMotif() + ", " + this.rest_motifs.embroideryInfoMotif();
   }
}
	
}

//============================================================================================================
//PROBLEM-PICTURE

//Picture is one of
//-- Shape is of type (String kind, Int Size)
//-- Combo is of type (	String name, Operation operation)
// interp. as a picture produced either througha  shape or a combination of shapes

interface Picture {
	int getWidth();
	int countShapes();
	int comboDepth();
	Picture mirror();
	String pictureRecipe(int depth);
}


//Shape is of type (String kind , Int Size)
//interp. as a standard shape like circle or square with some size

class Shape implements Picture {
	String kind;
	int size;
	//Defining the constructor
	Shape (String kind, int size) {
		this.kind = kind;
		this.size = size;
		
		//TEMPLATE
		/*
		 * FIELDS
		 * ... this.kind ...string
		 * ... this.size ... int
		 * METHODS
		 * ... this.getWidth() ...int
		 * ... this.countShapes() ...int
		 * ... this.comboDepth() ...int
		 * ...this.mirror()   .... Picture
		 * ...this.pictureRecipe ... String
		 * ... this.nameHelper ... String
		 */
	}
	
	//Signature> Self -> Int
	//Gets the total width of the given shape
	public int getWidth() {return this.size ;}
	
	//Signature> Self -> Int
	//Gets the total no of basic shapes in a shape - which will always be 1
	public int countShapes() {return 1 ;}
	
  //Signature> Self -> Int
	//Gets the total no of operations have been carried out on a basic shape - should be 0 for basic shapes
	public int comboDepth() {return 0 ;}
	
  //Signature> Self -> Picture
	//Gets the mirror image of a basic shape - which will be the basic shape itself
	public Picture mirror() {return this ;}	
	
	//Signature->Self, Int>String
	//Produces pictureRecipe which  will the name of the string itself
	public String pictureRecipe(int depth) {
		return this.kind;
	}

}

//Combo is of type (String name, Operation operation)
//interp as a combination of basic shapes

class Combo implements Picture {
	String name;
	Operation operation;
	
	//Defining the constructor
	Combo (String name, Operation operation) {
		this.name = name;
		this.operation = operation;	
		//TEMPLATE
		/*
		 * FIELDS
		 * ... this.name ...string
		 * ... this.operation ...operation
		 * METHODS
		 * ... this.getWidth()   ...int
		 * ... this.countShapes() ... int 
		 * ... this.comboDepth()  ... int
		 * ... this.mirror()      ... Picture
		 * ... this.pictureRecipe(int depth) ... String
		 * METHODS for FIELDS
		 * ...this.operation.getWidth()  ... int
		 * ... this.operation.countShapes() ... int
		 * ... this.operation.comboDepth() ... int
		 * ... this.operation.mirror()    ... operation
		 * ... this.operation.pictureRecipeOperation(int depth) ... String
		 */
	}
	
	//Signature> Self -> Int
	//Gets the total width of the given shape
	public int getWidth() {
		return this.operation.getWidth();
	}
	
	//Signature> Self -> Int
	//Gets the total no of basic shapes in a picture 
	public int countShapes() {
		return this.operation.countShapes() ;
		}
  //Signature> Self -> Int
	//Gets the total no of operations have been carried out in a combo shape
	public int comboDepth() {
		return this.operation.comboDepth();}	
	
  //Signature> Self -> Picture
	//Gets the mirror image of a combo shape - this will give a new picture where mirror is applied on the operation
	public Picture mirror() {return new Combo(this.name, this.operation.mirror()) ;}
	
	//Signature>  Self, INt-> String
	//At depth 0 - returns name of combo
	//At depth 1 - returns topmost operation and name of subcombo
	//At depth 2 - returns topmost operation , second operation, name of 2nd combo and so on
	
	public String pictureRecipe(int depth) {
		if (depth <= 0) {
			return this.name;
			}else {return this.operation.pictureRecipeOperation(depth - 1);}
	}
	

}

//Operation is defined as one of
//-- Scale is of type (Picture picture)
//--Beside is of type (Picture picture1, Picture picture2)
//--Overlay is of type (Picture picture1, Picture picture2)
//interp. as an operation on combination of pictures

interface Operation {
	int getWidth();
	int countShapes();
	int comboDepth();
	Operation mirror();
	String pictureRecipeOperation(int depth);
}


//-- Scale is of type (Picture picture)
// interp. as an operation to scale a picture

class Scale implements Operation {
	Picture picture;
	//Defining the constructor
	Scale (Picture picture) {
		this.picture = picture;
	}

	//TEMPLATE
	/*
	 * FIELDS
	 * ... this.picture ...Picture
	 * METHODS
	 * ... this.getWidth() ...int
	 * ... this.countShapes() ... int
	 * .... this.comboDepth() ... int
	 * ... this.mirror()      ... operation
	 * ... this.pictureRecipeOperation ... String
	 * METHODS from FIELDS
	 * ...this.picture.getWidth() ... int
	 * ... this.picture.countShapes()     ... int
	 * ... this.picture.comboDepth()   ... int
	 * ... this.picture.mirror()       ...picture
	 * ... this.picture.pictureRecipe() ... String
	 */

	//Signature> Self -> Int
	//Gets the total width for the scale operation on a picture
	public int getWidth() {
		return 2* this.picture.getWidth();
	}

	//Signature> Self -> Int
	//Gets the total basic shapes in a picture after scaling up the operation
	public int countShapes() {
		return this.picture.countShapes();
	}
	
  //Signature> Self -> Int
	//Gets the total no of operations have been carried out after using a scale operation on a picture
	public int comboDepth() {
		return 1 + this.picture.comboDepth();}	
	
	//Signature> Self -> Operation
	//Keeps the operation unchanged for any operation which is not beside
	public Operation mirror() {
		return new Scale (this.picture.mirror());
	}
	
	// Signature > Self, Int -> String
	//  Returns "Scale(this.picturepictureRecipe)
	
	public String pictureRecipeOperation (int depth) {
		return "Scale("+ this.picture.pictureRecipe(depth)+")";
	}
}


//-- Beside is of type (Picture p1, Picture p2)
//interp. as an operation to put 2 pictures side by side

class Beside implements Operation {
	Picture p1;
	Picture p2;
	//Defining the constructor
	Beside (Picture p1, Picture p2) {
		this.p1 = p1;
		this.p2  = p2;
	}
	//TEMPLATE
	/*
	 * FIELDS
	 * ... this.p1 ...Picture
	 * ... this.p2 ...Picture
	 * METHODS
	 * ... this.getWidth() ...int
	 * ... this.countShapes() ...int
	 * ... this.comboDepth()  ...int
	 * ... this.mirror()      ... Operation
	 *... this.pictureRecipeOperation...String
	 * METHODS from FIELDS
	 * ...this.p1.getWidth() ... int
	 * ... this.p1.countShapes() ... int
	 * ... this.p1.comboDepth()  ... int
	 * ... this.p1.mirror()      ... Picture
	 * ... this.pi.pictureRecipe() ... String
	 * ...this.p2.getWidth() ... int
	 * ... this.p2.countShapes() ... int
	 * ... this.p2.comboDepth()  ... int
	 * ... this.p2.mirror()      ... Picture
	 * ... this.p2.pictureRecipe() ... String
	 */

	//Signature> Self -> Int
	//Gets the total width for the beside operation on two pictures
	public int getWidth() {
		return this.p1.getWidth() + this.p2.getWidth();
	}
	//Signature> Self -> Int
	//Gets the total basic shapes in a picture obtained after using the beside operation
	public int countShapes() {
		return this.p1.countShapes() + this.p2.countShapes();
	}
	
  //Signature> Self -> Int
	//Gets the total no of operations have been carried out after using a beside operation on two pictures
	public int comboDepth() {
		return 1 + this.p1.comboDepth() + this.p2.comboDepth();}

	//Signature> Self -> Operation
	//Returns a new beside operation where p1 and p2 are swapped
	public Operation mirror() {
		return new Beside (this.p2.mirror(), this.p1.mirror());
	}
	
	
	// Signature > Self, Int -> String
	// Returns Beside(picture.pictureRecipe(depth) , picture.pictureRecipe(depth))
	
	public String pictureRecipeOperation (int depth) {
		return "Beside("+ this.p1.pictureRecipe(depth)+"," + this.p2.pictureRecipe(depth) + ")";
	}
	
}


//-- Overlay is of type (Picture p1, Picture p2)
//interp. as an operation to put 2 pictures on top of each other

class Overlay implements Operation {
	Picture p1;
	Picture p2;
	//Defining the constructor
	Overlay (Picture p1, Picture p2) {
		this.p1 = p1;
		this.p2  = p2;
	}
	//TEMPLATE
	/*
	 * FIELDS
	 * ... this.p1 ...Picture
	 * ... this.p2 ...Picture
	 * METHODS
	 * ... this.getWidth() ...int
	 * ... this.countShapes() ...int
	 * ... this.comboDepth()  ...int
	 * ... this.mirror()      ... Operation
	 *... this.pictureRecipeOperation...String
	 * METHODS from FIELDS
	 * ...this.p1.getWidth() ... int
	 * ... this.p1.countShapes() ... int
	 * ... this.p1.comboDepth()  ... int
	 * ... this.p1.mirror()      ... Picture
	 * ... this.pi.pictureRecipe() ... String
	 * ...this.p2.getWidth() ... int
	 * ... this.p2.countShapes() ... int
	 * ... this.p2.comboDepth()  ... int
	 * ... this.p2.mirror()      ... Picture
	 * ... this.p2.pictureRecipe() ... String
	 */

	//Signature> Self -> Int
	//Gets the total width for the overlay operation on two pictures
	public int getWidth() {
		if  (this.p1.getWidth() >= this.p2.getWidth()) {
			return p1.getWidth();
		} else {return p2.getWidth();}
	}
	//Signature> Self -> Int
	//Gets the total basic shapes in a picture obtained after using the beside operation
	public int countShapes() {
		return this.p1.countShapes() + this.p2.countShapes();
	}
	
  //Signature> Self -> Int
	//Gets the total no of operations have been carried out after using an overlay operation on two pictures
	public int comboDepth() {
		return 1 + this.p1.comboDepth() + this.p2.comboDepth();}	
	
	//Signature> Self -> Operation
	//Returns a new beside operation where p1 and p2 are swapped
	public Operation mirror() {
		return new Overlay (this.p1.mirror(), this.p2.mirror());
	}
	
	
	// Signature > Self, Int -> String
	// Returns Overlay(picture.pictureRecipe(depth) , picture.pictureRecipe(depth))
	
	public String pictureRecipeOperation (int depth) {
		return "Overlay("+ this.p1.pictureRecipe(depth)+"," + this.p2.pictureRecipe(depth) + ")";
	}
	
}


//============================================================================================================
//EXAMPLES

class ExamplesAssignment2 {
	ExamplesAssignment2 () {}
	//Data and tests for Motifs
	IMotif daisy = new CrossStitchMotif ("daisy" , 3.2);
	IMotif poppy = new ChainStitchMotif ("poppy" , 4.75);
	IMotif rose = new CrossStitchMotif ("rose" , 5.0);
	ILoMotif flowers = new ConsLoMotif (rose, new ConsLoMotif (poppy, new ConsLoMotif (daisy, new MtLoMotif())));
	
	IMotif bird = new CrossStitchMotif ("bird", 4.5);
	IMotif tree = new ChainStitchMotif("tree", 3.0);
	ILoMotif nature = new ConsLoMotif (bird, new ConsLoMotif (tree, flowers));
	EmbroideryPiece pillowCover = new EmbroideryPiece ("Pillow Cover", nature);
	
	//Tests for Motifs
	boolean testaverageDifficultyMotif (Tester t) {
		return t.checkInexact(daisy.averageDifficultyMotif(), 3.2, 0.01) &&
				t.checkInexact(poppy.averageDifficultyMotif(), 4.75, 0.01) &&
				t.checkInexact(rose.averageDifficultyMotif(), 5.0, 0.01) &&
				t.checkInexact(flowers.averageDifficultyMotif(), 4.32, 0.1) && 
				t.checkInexact(pillowCover.averageDifficulty(), 4.09, 0.1);
	}
		
	//Tests for EmbroideryInfo
	boolean testEmbroideryInfo(Tester t) {
		return t.checkExpect(pillowCover.embroideryInfo(), "Pillow Cover: bird (cross stitch), tree (chain stitch), rose (cross stitch), poppy (chain stitch), daisy (cross stitch).");
	}
	
	
	//Data and tests for Picture
	
	Picture circle = new Shape ("circle", 20);
	Picture square = new Shape ("square", 30);
	Picture cbig = new Shape ("circle", 50);
	Picture sbig = new Shape ("circle", 50);
	Operation scaleCircle = new Scale (circle);
	Operation overlaySquareCircle = new Overlay(circle, square);
	Picture bigCircle = new Combo("bigCircle" ,scaleCircle);
	Picture squareInCircle = new Combo ("square on circle", overlaySquareCircle);
	Operation besideSquareInCircle = new Beside (squareInCircle, squareInCircle);
	Picture besideCircleSquare = new Combo ("doubled square on circle", besideSquareInCircle);
	//Mirror Method
	Operation circleBesideSquare = new Beside (circle, square);
	Operation circleBesideSquareMirror = new Beside (square, circle);
	Picture comboCircleBesideSquare = new Combo ("comboCircleBesideSquare", circleBesideSquare);
	Picture comboCircleBesideSquareMirror = new Combo ("comboCircleBesideSquareMirror", circleBesideSquareMirror);
	Operation overlayCircleCBS = new Overlay(cbig, comboCircleBesideSquare);
	Operation overlayCircleCBSMirror = new Overlay(cbig, comboCircleBesideSquare.mirror());
	Picture comboOverlayCircleCBS = new Combo ("comboOverlayCircleCBS", overlayCircleCBS);
	Picture comboOverlayCircleCBSMirror = new Combo("comboOverlayCircleCBSMirror", overlayCircleCBSMirror);
	Operation overlaySquareCBS = new Overlay(sbig, comboCircleBesideSquare);
	Operation overlaySquareCBSMirror = new Overlay(sbig, comboCircleBesideSquare.mirror());
	Picture comboOverlaySquareCBS = new Combo ("comboOverlaySquareCBS", overlaySquareCBS);
	Picture comboOverlaySquareCBSMirror = new Combo ("comboOverlaySquareCBSMirror", overlaySquareCBSMirror);
	Operation besideComboCircleSquare = new Beside (comboOverlayCircleCBS, comboOverlaySquareCBS);
	Operation besideComboCircleMirror = new Beside (comboOverlaySquareCBSMirror, comboOverlayCircleCBSMirror);
	Picture comboBesideComboCircleSquare = new Combo("comboBesideComboCircleSquare", besideComboCircleSquare);
	Picture comboBesideComboCircleSquareMirror = new Combo("comboBesideComboCircleSquareMirror", besideComboCircleMirror);
	

	//Writing tests for Picture
	//testing for getWidth()
	boolean testgetWidth (Tester t) {
		return t.checkExpect(circle.getWidth(), 20) &&
				t.checkExpect(square.getWidth(), 30) &&
				t.checkExpect(bigCircle.getWidth(), 40) &&
				t.checkExpect(squareInCircle.getWidth(), 30) &&
				t.checkExpect(besideCircleSquare.getWidth(), 60);
	}
	//testing for countShapes()
	boolean testcountShapes (Tester t) {
		return t.checkExpect(circle.countShapes(), 1) &&
				t.checkExpect(square.countShapes(), 1) &&
				t.checkExpect(bigCircle.countShapes(), 1) &&
				t.checkExpect(squareInCircle.countShapes(), 2) &&
				t.checkExpect(besideCircleSquare.countShapes(), 4) ;
	}
	//testing for comboDepth()
	boolean testcomboDepth(Tester t) {
		return t.checkExpect(circle.comboDepth(), 0) &&
				t.checkExpect(square.comboDepth(), 0) &&
				t.checkExpect(bigCircle.comboDepth(), 1) &&
				t.checkExpect(squareInCircle.comboDepth(), 1) &&
				t.checkExpect(besideCircleSquare.comboDepth(), 3) ;
	}
	//testing for mirror()
//	boolean testMirror (Tester t) {
//		return t.checkExpect(comboBesideComboCircleSquare.mirror(), comboBesideComboCircleSquareMirror);
//	}
	
	//testing for pictureRecipe
	boolean testPictureRecipe (Tester t) {
		return t.checkExpect(besideCircleSquare.pictureRecipe(0), "doubled square on circle")&&
		 t.checkExpect(besideCircleSquare.pictureRecipe(1), "Beside(square on circle,square on circle)");
	}
	
}























