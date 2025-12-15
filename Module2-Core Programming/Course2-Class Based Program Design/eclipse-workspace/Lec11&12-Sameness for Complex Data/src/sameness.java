import tester.* ;

interface IShape {
	boolean sameShape(IShape otherShape);
	
	boolean sameTriangle(Triangle otherTriangle);
	boolean sameCircle(Circle otherCircle);
	boolean sameRect(Rect otherRect);
	boolean sameSquare(Square otherSquare);
	
	boolean isCircle ();
	boolean isRect ();
	boolean isSquare ();
	boolean isTriangle();
}

abstract class AShape implements IShape{
	public boolean isCircle() {return false;}
	public boolean isRect() {return false;}
	public boolean isSquare() {return false;}
	public boolean isTriangle() {return false;}
	
	public boolean sameCircle(Circle otherCircle) {return false;}
	public boolean sameRect(Rect otherRect) {return false;}
	public boolean sameSquare(Square otherSquare) {return false;}
	public boolean sameTriangle(Triangle otherTriangle) {return false;}
}


class Circle extends AShape {
	int x,y;
	int radius;
	
	Circle (int x, int y , int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius ; 
	}
	
	public boolean sameShape (IShape otherShape) {
		return otherShape	.sameCircle(this);
//		if (otherShape.isCircle()) {
//			return this.sameCircle((Circle) otherShape);
//		}
//		else {
//			return false;
//		}
	}
	
	public boolean sameCircle (Circle otherCircle) {
		return this.x == otherCircle.x && 
				this.y == otherCircle.y &&
				this.radius == otherCircle.radius;
	}
//	public boolean sameRect(Rect other) {
//		return false;
//	}
//	public boolean sameSquare(Square other) {
//		return false;
//	}
	public boolean isCircle () {return true;}
//	public boolean isRect () {return false;}
//	public boolean isSquare () {return false;}
}


class Rect extends AShape {
	int x,y;
	int w,h;
	Rect (int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	public boolean sameShape (IShape otherShape) {
		return otherShape.sameRect(this);}
//		if (otherShape.isRect()) {
//			return this.sameRect((Rect) otherShape);
//		}
//		else {
//			return false;
//		}
//	}
	
	public boolean sameRect(Rect otherRect) {
		return this.x == otherRect.x &&
				this.y == otherRect.y &&
				this.w == otherRect.w &&
				this.h == otherRect.h ;
	}
//	public boolean sameCircle(Circle otherCircle) {
//		return false;
//	}
//	
//	
//	public boolean sameSquare(Square other) {
//		return false;}
//	
//	public boolean isCircle () {return false;}
	public boolean isRect () {return true;}
//	public boolean isSquare () {return false;}
}


class Square extends Rect {
	Square (int x, int y, int size	) {
		super(x,y,size, size);
	}
	public boolean sameSquare(Square other) {
		return this.x == other.x &&
				this.y == other.y  &&
				this.w == other.w;
	}
	public boolean sameShape(IShape other) {
		return other.sameSquare(this);}
//		if (other.isSquare()) {
//			return this.sameSquare((Square)other) ;
//		} else { return false;}
//	}
	
	public boolean sameRect(Rect other) {return false;}
//	public boolean isCircle () {return false;}
	public boolean isRect () {return false;}
	public boolean isSquare () {return true;}
}


class Triangle extends AShape{
	int x;
	int y;
	int base;
	int height;
	Triangle(int x, int y, int base, int height) {
		this.x = x;
		this.y = y;
		this.base = base;
		this.height = height;
	}
	
	public boolean sameShape(IShape other) {
		return other.sameTriangle(this);
	}
	
	public boolean isTriangle ( ) {
		return true;
	}
	public boolean sameTriangle(Triangle otherTriangle) {
		return this.x == otherTriangle.x &&
				this.y == otherTriangle.y &&
				this.base == otherTriangle.base &&
				this.height == otherTriangle.height ;
	}
}


class ExamplesIShape {
	ExamplesIShape() {}
	
	IShape c1 = new Circle(3,4,5);
	IShape c2 = new Circle (4,5,6);
	IShape c3 = new Circle (3,4,5);
	
	IShape r1 = new Rect (3,4,5,5);
	IShape r2 = new Rect(4,5,6,7);
	IShape r3 = new Rect (3,4,5,5);
	
	IShape s1 = new Square (3,4,5);
	IShape t1 = new Triangle (3,4,5,5);
	IShape t2 = new Triangle (3,4,5,5);
	
	
	boolean testIShapeSame (Tester t) {
		return t.checkExpect(c1.sameShape(c2), false) &&
				t.checkExpect(c2.sameShape(c1), false) &&
				t.checkExpect(c1.sameShape(c3), true) &&
				t.checkExpect(c3.sameShape(c1), true) &&
				
				t.checkExpect(r1.sameShape(r2), false) &&
				t.checkExpect(r2.sameShape(r1), false) &&
				t.checkExpect(r1.sameShape(r3), true) &&
				t.checkExpect(r3.sameShape(r1), true) &&
				
				t.checkExpect(c1.sameShape(r1), false) &&
				t.checkExpect(r1.sameShape(c1), false) &&
				
				t.checkExpect(r1.sameShape(s1), false) &&
				t.checkExpect(s1.sameShape(r1), false) &&
				
				t.checkExpect(t1.sameShape(s1), false) &&
				t.checkExpect(t1.sameShape(r1), false) &&
				t.checkExpect(t1.sameShape(c1), false) &&
				
				t.checkExpect(c1.sameShape(t1), false) &&
				t.checkExpect(r1.sameShape(t1), false) &&
				t.checkExpect(s1.sameShape(t1), false) &&
				
				t.checkExpect(t2.sameShape(t1), true) &&
				t.checkExpect(t1.sameShape(t2), true) ;
		
		
	}
			
}













