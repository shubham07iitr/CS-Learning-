import tester.*;                // The tester library
import javalib.worldimages.*;   // images, like RectangleImage or OverlayImages
import javalib.funworld.*;      // the abstract World class and the big-bang library
import java.awt.Color;          // general colors (as triples of red,green,blue values)
                                // and predefined colors (Color.RED, Color.GRAY, etc.)


//===========================================================================
//INTERFACE ITREE

interface ITree {
	
}


//===========================================================================
//CLASS LEAF
// interp. as a circle on a stem representing leaf

class Leaf implements ITree {
  int size; // represents the radius of the leaf
  Color color; // the color to draw it
  
  Leaf (int size, Color color) {
  	this.size = size;
  	this.color = color;
  }
}



//===========================================================================
//CLASS STEM
//interp. as a stem on a tree

class Stem implements ITree {
  int length;   // How long this stick is
  double theta;   // The angle (in degrees) of this stem, relative to the +x axis
  ITree tree;   // The rest of the tree
  
  Stem (int length, double theta, ITree tree) {
  	this.length = length;
  	this.theta = theta;
  	this.tree = tree;
  }
}


//===========================================================================
//CLASS BRANCH
// interp. as branch of a tree

class Branch implements ITree {
  int leftLength;   // How long the left and right branches are
  int rightLength;// How long the left and right branches are
  double leftTheta;   // The angle (in degrees) of the two branches, relative to the +x axis,
  double rightTheta;   // The angle (in degrees) of the two branches, relative to the +x axis,
  ITree left;   // The remaining parts of the tree
  ITree right;   // The remaining parts of the tree
  
  Branch (int leftLength, int rightLength, double leftTheta, double rightTheta, ITree left, ITree right) {
  	this.leftLength = leftLength;
  	this.rightLength = rightLength;
  	this.leftTheta = leftTheta ;
  	this.rightTheta = rightTheta;
  	this.left = left;
  	this.right = right;
  }
}


//===========================================================================
//CLASS EXAMPLES


class ExamplesA4Tree {
	ExamplesA4Tree () {}
	
	ITree leafb = new Leaf(15, Color.BLUE);
	ITree leafr = new Leaf(10, Color.RED);
	ITree leafg = new Leaf (15, Color.GREEN);
	ITree leafo = new Leaf (15, Color.ORANGE);
	ITree tree1 = new Branch (30,30, 135, 40, leafr, leafb);
	ITree tree2 = new Branch (30,30, 115,65, leafg, leafo);
	ITree stem1 = new Stem (40, 90, tree1);
	ITree stem2 = new Stem (50, 90, tree2);
	
			
}


























