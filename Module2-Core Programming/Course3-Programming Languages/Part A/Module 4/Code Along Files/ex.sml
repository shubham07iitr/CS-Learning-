datatype exp = Constant of int
	     | Negate of exp
	     | Add of exp * exp
	     |  Multiply of exp * exp;

fun max_constant e =
    case e of
	Constant i => i
      | Negate e2 =>  max_constant e2
      | Add(e1, e2) =>
	let val m1 = max_constant e1
	    val m2 = max_constant e2
	in if m1 > m2 then m1 else m2 end
					 
      | Multiply(e1, e2) => if  max_constant e1 > max_constant e2 (*compyting recursive call multiple times and will be very inefficient*)
			    then max_constant e1
			    else max_constant e2;

val test_exp = Add (Constant 19, Negate (Constant 4));
val nineteen = max_constant test_exp;
