import tester.*;

//============================================================

interface IShape {
	<R> R accept (IVisitorShape<R> visitor);
}
//============================================================
class Circle implements IShape {
	int x,y;
	int radius;
	String color;
	Circle (int x, int y, int r, String color) {
		this.x = x;
		this.y = y;
		this.radius = r;
		this.color =color;
	}
	public <R> R accept(IVisitorShape<R> visitor) {
		return visitor.visitCircle(this);
	}
}

//============================================================
class Rect implements IShape {
	int x,y, w, h;
	String color;
	Rect (int x, int y, int w, int h, String color) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.color =color;
	}
	public <R> R accept(IVisitorShape<R> visitor) {
		return visitor.visitRect(this);
	}
}
//============================================================
interface IFunction <A, R> {
	R apply(A t);
}

//class ShapeArea implements IFunction<IShape, Double> {
//  public Double apply(IShape shape) {
//    if (shape instanceof Circle) {
//      Circle c = (Circle) shape;
//      return Math.PI * c.radius * c.radius;
//    } else {
//      Rect r = (Rect) shape;
//      return r.w * r.h * 1.0;
//    }
//  }
//}

//============================================================
//INTERFACE VISITOR
interface IVisitorShape <R>{
	R visitCircle(Circle circle);
	R visitRect(Rect rect);
}

class VisitorShape implements IVisitorShape<Double> , IFunction<IShape, Double> {
	public Double visitCircle(Circle circle) {
		return Math.PI*circle.radius*circle.radius;
	}
	public Double visitRect(Rect rect) {
		return rect.w * rect.h*1.0;
	}
	public Double apply (IShape shape) {
		return shape.accept(this);
	}
}

class VisitorShapeString implements IVisitorShape<String>, IFunction <IShape, String> {
	public String visitCircle(Circle circle) {
		return circle.color;
	}
	public String visitRect(Rect rect) {
		return rect.color;
	}
	public String apply (IShape shape) {
		return shape.accept(this);
	}
	
	
}

//=================================================================
interface IList<T>{
<R>IList<R> map(IFunction<T,R> function);
}

class MtList<T> implements IList<T> {
 public <R>IList<R> map(IFunction<T,R> function) {
	return new MtList<R>();
}
}

class ConsList<T> implements IList<T> {
T first;
IList<T> rest;

ConsList(T first, IList<T> rest) {
	this.first = first;
	this.rest = rest;
}

public <R> IList<R> map(IFunction<T,R> function) {
	return new ConsList<R> (function.apply(this.first), this.rest.map(function));
}

}

//============================================================

class IShapeExamples {
	IShapeExamples() {}
	
	IList<IShape> shapes = new ConsList <IShape> (new Circle(0,0,10, "red"),
												 new ConsList <IShape> (new Rect(0,0,10,10, "green"),
												 new MtList<IShape>()));
	
	IList<Double> expectedList = new ConsList <Double> (314.15,
			 												 new ConsList <Double> (100.00,
			 												 new MtList<Double>()));
	
	IList<String> expectedList1 = new ConsList <String> ("red",
			 													new ConsList <String> ("green",
			 													new MtList<String>()));
	
	boolean testIShapeMap(Tester t) {
		return t.checkInexact (shapes.map(new VisitorShape	()), expectedList, 0.01);
	}
	
}
























