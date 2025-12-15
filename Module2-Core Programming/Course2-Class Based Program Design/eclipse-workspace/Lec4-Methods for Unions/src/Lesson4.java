import tester.Tester;
public class Lesson4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

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



class Square implements IShape{
CartPt topLeftCorner;
int size;
String color;

Square (CartPt topLeftCorner, int size, String color) {
	this.topLeftCorner = topLeftCorner;
	this.size = size;
	this.color = color;
}
public boolean isBiggerThan(IShape other) {
	return this.area() > other.area();
}

public double distanceToOrigin() {
	return this.topLeftCorner.distanceToOrigin();
}

public double area() {
	return size*size;
}

}

class Circle implements IShape {
	CartPt center;
	int radius;
	String color;
	
	Circle (CartPt center, int radius, String color) {
		this.center = center;
		this.radius = radius;
		this.color = color;
	}
	
	public boolean isBiggerThan(IShape other) {
		return this.area() > other.area();
	}
		
		
	public double distanceToOrigin() {
		return this.center.distanceToOrigin() - this.radius;
	}
	
 public	double area() {
		return Math.PI*radius*radius;
	}
	
}

class Triangle implements IShape{
	CartPt topLeftCorner;
	int base;
	int height;
	String color;
	
	Triangle(CartPt topLeftCorner, int base, int height, String color){
		this.topLeftCorner = topLeftCorner;
		this.base = base;
		this.height = height;
		this.color = color;
	}
	public boolean isBiggerThan(IShape other) {
		return this.area() > other.area();
	}
	
	public double distanceToOrigin() {
		return this.topLeftCorner.distanceToOrigin();
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

















