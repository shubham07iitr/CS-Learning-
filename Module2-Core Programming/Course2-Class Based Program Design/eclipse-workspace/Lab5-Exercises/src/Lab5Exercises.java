import tester.*;                // The tester library
import javalib.worldimages.*;   // images, like RectangleImage or OverlayImages
import javalib.funworld.*;      // the abstract World class and the big-bang library
import java.awt.Color;          // general colors (as triples of red,green,blue values)
                                // and predefined colors (Color.RED, Color.GRAY, etc.)


//================================================
//CLASS MYPOSN
//interp. as an extension of Posn class from World library whihc 

class MyPosn extends Posn {

	//standard constructor
	MyPosn (int x, int y) {super (x,y);}
	
	//constructor to convert from a Posn to a MyPosn
	MyPosn(Posn p) {this(p.x, p.y);}
	
	//TEMPLATE
	/*
	 * FIELDS
	 * ...this.x  ...int (inherited)
	 * ...this.y ... int (inherited)
	 * METHODS
	 * ...this.add(MyPosn that) ... MyPosn
	 * ...this.isOffScreen (int xcor, int ycor) ... boolean
	 */
	
	//METHOD ADD
	//SIGNATURE> Self, MyPosn > MyPosn
	//Given another MyPosn will add its x and y values to this one and output a new MyPosn.
	MyPosn add (MyPosn that) {
		return new MyPosn(this.x + that.x , this.y + that.y);
	}
	
	//METHOD isOFFSCREEN
	//SIGNATURE> Self, INt, Int > boolean
	//Given another MyPosn will add its x and y values to this one and output a new MyPosn.
	boolean isOffScreen (int p, int q) {
		return this.x < 0 || this.y < 0 || this.x > p || this.y > q;
	}	
}




//================================================
//CLASS CIRCLE
// interp. as an object of Circle type which has a position and velocity

class Circle {
	 
  MyPosn position; // in pixels
  MyPosn velocity; // in pixels/tick
  int radius; // radius of the circle
  OutlineMode fill; // SOLID or OUTLINE
  Color color; // color of the circle
  
  
  //Defining the constructor
  Circle (MyPosn position, MyPosn velocity, int radius, OutlineMode fill, Color color) {
  	this.position = position;
  	this.velocity = velocity	;
  	this.radius = radius;
  	this.fill = fill;
  	this.color = color;
  }
  
  //TEMPLATE
  /*
   * FIELDS
   * ...this.position ... MyPosn
   * ...this.velocity ... MyPosn
   * METHODS
   * ...this.move() ....Circle
   * ...this.isOffScreen(int p, int q) ... boolean
   * ... this.draw() .... WorldImage
   * ... this.place(WorldScene ws) .... WorldScene
   */
  
	//METHOD MOVE
	//SIGNATURE> Self > Circle
	//Given a circle produces another circle with changed position based on velocity
	Circle move () {return new Circle (this.position.add(this.velocity), this.velocity, this.radius, this.fill, this.color); }

	//METHOD isOFFSCREEN
	//SIGNATURE> Self, Int, Int > boolean
	//given two numbers representing the width and height of a screen, determines if a single circle lies outside of it 
	boolean isOffScreen (int p, int q) {return this.position.isOffScreen(p, q);}
	
	//METHOD draw
	//SIGNATURE> Self  > WorldImage
	//outputs a WorldImage representing the circle. 
	WorldImage draw () {return new CircleImage(this.radius, this.fill, this.color);}
	
	//METHOD place
	//SIGNATURE> Self, WorldScene  > WorldScene
	//outputs a WorldScene with a circle added at the appropriate position 
	WorldScene place (WorldScene ws) {return ws.placeImageXY(this.draw(), this.position.x, this.position.y);}
	
		
}

//================================================
//Interface ILOCIRCLE
//interp. as a list of circles

interface ILoCircle{
	ILoCircle moveAll(); //moves every Circle in the list - produces a new circle with new position after adjusting for velocity
	ILoCircle removeOffScreen(int p, int q); //given width and height of the screen removes all circle which 	is outside the screen
	WorldScene placeAll(WorldScene ws); // places all the circles in the list on a given world scene
	int lenList() ; //returns the lenght of hte list
	ILoCircle addToList(Circle c); // adds a circle to a list of circles
}


//CLASS MTLOCIRCLE
//Interp. as an empty list of circles
class MTLoCircle implements ILoCircle {
	MTLoCircle () {}

	//TEMPLATE
	/*
	 * METHODS
	 * ...this.moveAll() ... ILoCircle
	 * ...this.removeOffScreen(int p, int q)... ILoCircle
	 * ...this.placeAll(WorldScene ws) ... WorldScene
	 * ...this.lenList() ....int
	 * ...this.addToList(Circle c)... ILoCircle
	 */

	//METHOD MOVEALL
	//SIGNATURE> Self  > ILoCircle
	//moves every Circle in the list - produces a new circle with new position after adjusting for velocity, produces an MtList for an MtList 
	public ILoCircle moveAll () {return this;}

	//METHOD REMOVEOFFSCREEN
	//SIGNATURE> Self  > ILoCircle
	//given width and height of the screen removes all circle which 	is outside the screen 
	public ILoCircle removeOffScreen(int p, int q) {return this;} // returns an empty list for an empty list

	//METHOD PLACEALL
	//SIGNATURE> Self, WorldScene > WorldScene
	//places all the circles in the list on a given world scene 
	public WorldScene placeAll (WorldScene ws) {return ws;} // returns the given worldscene only

	//METHOD lenList
	//SIGNATURE> Self > Int
	//Returns the list of the lenght, 0 for empty list 
	public int lenList () {return 0;} // returns the length of the list
	
	//METHOD addToList
	//SIGNATURE> Self > ILoCircle
	//Returns the new list by adding to the original list 
	public ILoCircle addToList (Circle c) {return new ConsLoCircle(c, this);} // returns the new list with circle added to the front of the list
}




//CLASS ConsLOCIRCLE
//Interp. as a non-empty list of circles

class ConsLoCircle implements ILoCircle {
	Circle first;
	ILoCircle rest;
	
	ConsLoCircle (Circle first, ILoCircle rest) {
		this.first = first; 
		this.rest = rest;
		}

	//TEMPLATE
	/*
	 * METHODS
	 * ...this.moveAll() ... ILoCircle
	 * ...this.removeOffScreen(int p, int q)... ILoCircle
	 * ...this.placeAll(WorldScene ws) ... WorldScene
	 * ...this.lenList() ...int
	 * ...this.addToList(Circle c) ... ILoCircle
	 * METHODS from FIELDS
	 * for FIRST
	 * ...this.first.move() ....Circle
   * ...this.first.isOffScreen(int p, int q) ... boolean
   * ... this.first.draw() .... WorldImage
   * ... this.first.place(WorldScene ws) .... WorldScene
   * for REST
   * ...this.rest.moveAll() ... ILoCircle
	 * ...this.rest.removeOffScreen(int p, int q)... ILoCircle
	 * ...this.rest.placeAll(WorldScene ws) ... WorldScene
	 * ...this.lenList() ..... int
	 * ...this.rest.addToList(Circle c) ... ILoCircle
	 */

	//METHOD MOVEALL
	//SIGNATURE> Self  > ILoCircle
	//moves every Circle in the list - produces a new circle with new position after adjusting for velocity, produces an MtList for an MtList 
	public ILoCircle moveAll () {
		return new ConsLoCircle(this.first.move(), this.rest.moveAll());}

	//METHOD REMOVEOFFSCREEN
	//SIGNATURE> Self  > ILoCircle
	//given width and height of the screen removes all circle which 	is outside the screen 
	public ILoCircle removeOffScreen(int p, int q) {
		if (this.first.isOffScreen(p, q)) {return this.rest.removeOffScreen(p, q);}
		else {return new ConsLoCircle (this.first, this.rest.removeOffScreen(p, q)) ;}
	}

	//METHOD PLACEALL
	//SIGNATURE> Self, WorldScene > WorldScene
	//places all the circles in the list on a given world scene 
	public WorldScene placeAll (WorldScene ws) {
		return this.rest.placeAll(this.first.place(ws));} // returns the given worldscene only
	

	//METHOD lenList
	//SIGNATURE> Self > Int
	//Returns the list of the lenght, called recursively for empty list 
	public int lenList () {return 1 + this.rest.lenList();} // returns the length of the list
	
	//METHOD addToList
	//SIGNATURE> Self > ILoCircle
	//Returns the new list by adding to the original list 
	public ILoCircle addToList (Circle c) {return new ConsLoCircle(c, this);} // returns the new list with circle added to the front of the list
}

//================================================
//CLASS WORLD

class MyWorld extends World {
	ILoCircle loc;
	int height;
	int width;
	
	
	MyWorld(ILoCircle loc, int height, int width) {this.loc = loc; this.height = height; this.width = width;}
	
	//METHOD MAKESCENE
	//This method will draw a scene on a world canvas
	@Override
	public WorldScene makeScene()
	{return this.loc.placeAll(this.getEmptyScene());}
	
	//METHOD WORLDENDS
	//Determines when the gmae is over which is when the count f circles to go offscreen is 0 or less
	@Override
	public WorldEnd worldEnds(){
		if (this.loc.lenList() == 0) {return new WorldEnd(true, this.getEmptyScene());}
		else {return new WorldEnd(false, this.makeScene());}
	}

	//METHOD onTICK
	//Determines when the gmae is over which is when the count f circles to go offscreen is 0 or less
	@Override
	public MyWorld onTick(){
		return new MyWorld(this.loc.moveAll().removeOffScreen(height, width), this.height, this.width);
	}
	
	//METHOD onMOUSECLICKED
	//add a circle to the game where the player clicked, velocity only in y direction and not in x
	@Override
	public MyWorld onMouseClicked(Posn pos){
		return new MyWorld(this.loc.addToList(new Circle(new MyPosn (pos), new MyPosn(0, -20), 20, OutlineMode.OUTLINE, Color.RED)), this.height, this.width);
	}
}

//================================================
//CLASS WORLDEXAMPLE

class ExamplesMyWorld {
	ILoCircle listStart = new ConsLoCircle(new Circle(new MyPosn (250,250), new MyPosn(0, -20), 20, OutlineMode.OUTLINE, Color.RED), new MTLoCircle());
 
	boolean testBigBang(Tester t) {
		MyWorld w = new MyWorld(listStart, 500, 500);
		return w.bigBang(w.height, w.width, 1.0/10.0);
		
	}
	
}


//================================================
//CLASS EXAMPLES

class Lab5Examples {
	Lab5Examples() {}
	
	WorldImage test1 = new RectangleImage(30, 20, OutlineMode.SOLID, Color.RED);
	
	boolean testImages(Tester t) {
	  return t.checkExpect(new RectangleImage(30, 20, OutlineMode.SOLID, Color.GRAY),
	                       new RectangleImage(30, 20, OutlineMode.SOLID, Color.GRAY));	 
	}
	
	//MyPosn Class methods and tests
	
	MyPosn p1 = new MyPosn(20,30);
	MyPosn p2 = new MyPosn (100, 200);
	
	boolean testAddMyPosn (Tester t) {
		return t.checkExpect(p1.add(p1), new MyPosn(120,230)) &&
				t.checkExpect(p2.add(p1), new MyPosn(120, 230));
	}
	
	boolean testIsOffScreenMyPosn(Tester t) {
		return t.checkExpect(p1.isOffScreen(50,60), false) &&
				t.checkExpect(p2.isOffScreen(50,60), true);
	}
	
	//Circle class objects and tests
	
	Circle c1 = new Circle (new MyPosn(30,30), new MyPosn(2,2), 5, OutlineMode.OUTLINE, Color.RED );
	Circle c2 = new Circle (new MyPosn(32,32), new MyPosn(2,2), 5, OutlineMode.OUTLINE, Color.RED );
	
	boolean testMoveCircle(Tester t) {return t.checkExpect(c1.move(), c2);}
	
	boolean testIsOffScreenCircle (Tester t) {
		return t.checkExpect(c1.isOffScreen(20, 40), true ) &&
				t.checkExpect(c1.isOffScreen(50, 50), false );
	}
	
	WorldImage circle1 = new CircleImage (5,OutlineMode.OUTLINE, Color.RED);
	WorldScene ws1 = new WorldScene (50,50);
	WorldScene ws2 = ws1.placeImageXY(circle1, 30, 30);
	
	boolean testDrawCircle (Tester t) {
		return t.checkExpect(c1.draw(), circle1);}
	
	
	boolean testPlaceCircle (Tester t) {
		return t.checkExpect(c1.place(ws1), ws2);
	}

	//ILoCircle objects and method tests
	
	Circle c3 = new Circle (new MyPosn(34,34), new MyPosn(4,4), 15, OutlineMode.SOLID, Color.BLUE );
	WorldImage circle3 = new CircleImage (15,OutlineMode.SOLID, Color.BLUE);
	Circle c4 = new Circle (new MyPosn(38,38), new MyPosn(4,4), 15, OutlineMode.SOLID, Color.BLUE );
	
	ILoCircle locmt = new MTLoCircle();
	ILoCircle loc = new ConsLoCircle(c1, 
									 new ConsLoCircle(c3, locmt));
	
	ILoCircle locMove = new ConsLoCircle(c2, new ConsLoCircle(c3, locmt));
	ILoCircle locRemove = new ConsLoCircle(c1, locmt);
	
	boolean testMoveAll (Tester t) {
		return t.checkExpect(loc.moveAll(), locMove);
	}
	
	boolean testRemoveOffScreen (Tester t) {
		return t.checkExpect(loc.removeOffScreen(32, 32), locRemove);
	}
	
	boolean testPlaceAll (Tester t) {
		return t.checkExpect(loc.placeAll(ws1), ws2.placeImageXY(circle3, 34, 34));
	}
}












