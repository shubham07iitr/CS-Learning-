import tester.*;

//================================================================================================
//CREATING FUNCTION OBJECTS

//Defining Unary Function objects
interface IFunction<T, R> {
	R apply(T arg);
}

//Desigining the 2 function objects for negative 
class Neg implements IFunction<Double,Double> {
	Neg () {} //defining the constructor
	public Double apply (Double arg) {return -1*arg;}
}

class Sqr implements IFunction<Double,Double> {
	Sqr () {} //defining the constructor
	public Double apply (Double arg) {return arg*arg;}
}


//Defining Binary Function objects
interface IBiFunction<T, R, U> {
	U apply(T arg1, R arg2); 
}


//Implementing the Plus class 
class Plus implements IBiFunction <Double, Double, Double> {
	Plus() {}
	public Double apply(Double arg1, Double arg2) {return arg1 + arg2;}
}

//Implementing the Minus class
class Minus implements IBiFunction <Double, Double, Double> {
	Minus() {}
	public Double apply(Double arg1, Double arg2) {return arg1 - arg2;}
}

//Implementing the Mul class
class Mul implements IBiFunction <Double, Double, Double> {
	Mul() {}
	public Double apply(Double arg1, Double arg2) {return arg1 * arg2;}
}

//Implementing the Div class
class Div implements IBiFunction <Double, Double, Double> {
	Div() {}
	public Double apply(Double arg1, Double arg2) {if (arg2 != 0.0) {return arg1/arg2;} else {throw new IllegalArgumentException ("Division by zero");}}
}



//================================================================================================
//INTERFACE IARITH
//IARITH is one of (Const, UnaryFormula, BinaryFormula)
//Const is of type (double num)
//UNary Formula will be an abstract class of type (Function<Double, Double> func, String name, IArith Child)
//Binary Formula is an abstract class of type (BiFunction<Double, Double, DOuble> func)
// interp as a new arithemetic language

interface IArith{
	<R>R accept (IArithVisitor<R> visitor); //the visitor function
}

//Class CONST
//Const is of type (double num)
//inter. as a constant in the arithemtic language

class Const implements IArith {
	double num;
	
	//Defining the constructor
	Const (double num) {this.num = num;}
	
	//visitor method
	public <R> R accept (IArithVisitor<R> visitor) {return visitor.visitConst(this);}
}

//CLASS UNARYFORMULA
class UnaryFormula implements IArith{
	IFunction<Double , Double> func;
	String name;
	IArith child;
	
	//Defining the constructor 
	UnaryFormula(IFunction<Double , Double> func, String name , IArith child ){
		this.func = func;
		this.name = name;
		this.child = child;
	}
	
	//visitor method
	public <R> R accept (IArithVisitor<R> visitor) {return visitor.visitUnaryFormula(this);}
}

//CLASS BINARYFORMULA
class BinaryFormula implements IArith {
	IBiFunction <Double , Double, Double> func;
	String name;
	IArith left;
	IArith right;
	
	//Defining the constructor
	BinaryFormula (IBiFunction<Double , Double , Double> func, String name, IArith left, IArith right) {
		this.func = func;
		this.name = name;
		this.left = left;
		this.right=right;
	}
	//visitor method
	public <R> R accept (IArithVisitor<R> visitor) {return visitor.visitBinaryFormula(this);}
}

//================================================================================================
//INtERFACE VISITOR

interface IArithVisitor<R> {
	R visitConst(Const c);
	R visitUnaryFormula(UnaryFormula u);
	R visitBinaryFormula(BinaryFormula b);
	R apply(IArith arg);
}

//EvalVisitor CLASS 
//Evaluates the exp to the double value final
class EvalVisitor implements IArithVisitor<Double> {
	EvalVisitor(){}
	
	public Double visitConst(Const c) {return c.num;} // we will evaluate it the num itself
	public Double visitUnaryFormula(UnaryFormula u) {return u.func.apply(u.child.accept(this));} // u.child.accept(this) would evaluate the child and then we apply the func on it
	public Double visitBinaryFormula(BinaryFormula b) {return b.func.apply(b.left.accept(this), b.right.accept(this));}
	public Double apply(IArith arg) {return arg.accept(this);}
	}


//PrintVisitor CLASS 
//Produces the expanded string  for the expr
class PrintVisitor implements IArithVisitor<String> {
	PrintVisitor() {}
	
	public String visitConst(Const c) {return Double.toString(c.num);} // we return the double as string for a constant
	public String visitUnaryFormula(UnaryFormula u) {return "(" + u.name + " " + u.child.accept(this) + ")";}
	public String visitBinaryFormula(BinaryFormula b) {return "(" + b.name + " " + b.left.accept(this) + " " + b.right.accept(this)+ ")";}
	public String apply(IArith arg) {return arg.accept(this);}
	}


//DoubleVisitor CLASS 
//Produces a new IArith , where each constant value has been doubled
class DoubleVisitor implements IArithVisitor<IArith> {
	DoubleVisitor() {}

	public IArith visitConst(Const c) {return new Const(c.num*2.0);} //mutliplying the constant value by 2
	public IArith visitUnaryFormula(UnaryFormula u) {return new UnaryFormula(u.func, u.name, u.child.accept(this));} // returns a new UnaryFormula
	public IArith visitBinaryFormula(BinaryFormula b) {return new BinaryFormula(b.func, b.name, b.left.accept(this), b.right.accept(this));} // returns a new UnaryFormula
	public IArith apply(IArith arg) {return arg.accept(this);}
}

//NoNegativeResults CLASS 
//Produces true if no negative in the expression
class NoNegativeResults implements IArithVisitor<Boolean> {
	NoNegativeResults() {}

	public Boolean visitConst(Const c) {if (c.num < 0) {return false;} else {return true;}}
	public Boolean visitUnaryFormula(UnaryFormula u) {return u.child.accept(this);}
	public Boolean visitBinaryFormula(BinaryFormula b) {return b.left.accept(this) && b.right.accept(this);}
	public Boolean apply(IArith arg) {return arg.accept(this);}
}





//================================================================================================
//EXAMPLE CLASS

class ExamplesArith {
	ExamplesArith() {} //Defining the constructor
	
	//Defining some sample arith data points
	
	IArith c1 = new Const(10);
	IArith c2 = new Const(-5);
	IArith u1 = new UnaryFormula(new Sqr(), "sqr" , c1); // Will evaluate to 100
	IArith u2 = new UnaryFormula(new Neg(), "neg" , c2); //will evaluate to +5
	
	IArith b1 = new BinaryFormula(new Plus(), "plus", c1, c2) ; // will evaluae to 5
	IArith b2 = new BinaryFormula(new Minus(), "minus", u1, c1); // will evaluate to sqr(10) - 10 = 90
	IArith b3 = new BinaryFormula(new Mul(), "mul" , c1, b2); // will evaluate to 10 * (sqr(10) - 5) = 950 
	IArith b4 = new BinaryFormula (new Div(), "div", u1, u2); //will evaluate to sqr(10) / neg(-5) = 20
	
	//testing the EvalVisitor function
	
	void testEvalVisitor (Tester t) {
		 t.checkExpect(u1.accept(new EvalVisitor()), 100.0);
		 t.checkExpect(u2.accept(new EvalVisitor()), 5.0);
		 t.checkExpect(b1.accept(new EvalVisitor()), 5.0);
		 t.checkExpect(b2.accept(new EvalVisitor()), 90.0);
		 t.checkExpect(b3.accept(new EvalVisitor()), 900.0);
		 t.checkExpect(b4.accept(new EvalVisitor()), 20.0);
	}
	
	//testing the PrintVisitor function
	
	void testPrintVisitor (Tester t) {
		t.checkExpect(c1.accept(new PrintVisitor()), "10.0");
		t.checkExpect(u1.accept(new PrintVisitor()), "(sqr 10.0)");
		t.checkExpect(b4.accept(new PrintVisitor()), "(div (sqr 10.0) (neg -5.0))");
	}
	
	//testing the DoubleVisitor function object
	
	void testDoubleVisitor (Tester t) {
		t.checkExpect(c1.accept(new DoubleVisitor()), new Const(20.0));
		t.checkExpect(u1.accept(new DoubleVisitor()), new UnaryFormula(new Sqr(), "sqr", new Const(20.0)));
	}
	
	//testing the NoNegativeResults function object
	void testNoNegativeResults (Tester t) {
		t.checkExpect(c1.accept(new NoNegativeResults()), true);
		t.checkExpect(c2.accept(new NoNegativeResults()), false);
		t.checkExpect(u1.accept(new NoNegativeResults()), true);
		t.checkExpect(u2.accept(new NoNegativeResults()), false);
		t.checkExpect(b1.accept(new NoNegativeResults()), false);
		t.checkExpect(b2.accept(new NoNegativeResults()), true);
	}
	
}















