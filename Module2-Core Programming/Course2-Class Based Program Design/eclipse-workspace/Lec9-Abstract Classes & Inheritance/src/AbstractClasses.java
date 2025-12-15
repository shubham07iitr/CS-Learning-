import tester.Tester;

interface IShape {
	double area();
	double distanceToOrigin();
	boolean isBiggerThan(IShape other);
}


class CartPt{
	int x;
	int y;
	
	CartPt(int x, int y){
		this.x = x;
		this.y = y;
	}
	double distanceToOrigin() {
		return Math.sqrt(this.x * this.x + this.y*this.y);
	}
}

abstract class AShape implements IShape{
	CartPt location;
	String color;
	
	AShape (CartPt location , String color) {
		this.location = location;
		this.color = color;
	}
	public boolean isBiggerThan(IShape other) {
		return this.area() > other.area();
	}
	public double distanceToOrigin() {
		return this.location.distanceToOrigin();
	}
	public abstract double area();
}

class Square extends AShape{
int size;

Square (CartPt topLeftCorner, int size, String color) {
	super(topLeftCorner, color);
	this.size = size;
}
public double area() {
	return size*size;
}
}

class Circle extends AShape {
	int radius;
	
	Circle (CartPt center, int radius, String color) {
		super(center, color);
		this.radius = radius;	
	}
	@Override 
	public double distanceToOrigin() {
		return this.location.distanceToOrigin() - this.radius;
	}
 public	double area() {
		return Math.PI*radius*radius;
	}
}






class Triangle extends AShape{
	int base;
	int height;
	
	Triangle(CartPt topLeftCorner, int base, int height, String color){
		super(topLeftCorner, color);	
		this.base = base;
		this.height = height;
	}
	public double area() {
		return 0.5 * base * height;
	}
}

class ShapeUtil{
	ShapeUtil() {}
	static double doubleTheArea(IShape shape) {
		return shape.area() * 2;
	}
}



class ExamplesIShape {
	ExamplesIShape() {}
	
	CartPt p1 = new CartPt(0,0);
	
	IShape s1 = new Square(p1,30,"red");
	IShape c1 = new Circle(p1,10 , "red");
	IShape t1 = new Triangle(p1,10,5, "red");
	
	boolean testShapeUtil(Tester t ) {
		return t.checkInexact(ShapeUtil.doubleTheArea(s1),1800.0, 0.01);
	}
	
	boolean testIShapeArea(Tester t) {
		return t.checkInexact(s1.area(), 900, 0.01) &&
				t.checkInexact(c1.area(), 314, 0.1) &&
				t.checkInexact(t1.area(), 25, 0.01);
		
		
	}
	
}

















