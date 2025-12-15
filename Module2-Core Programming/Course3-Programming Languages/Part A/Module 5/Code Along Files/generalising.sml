(* Function returning a function *)
fun double_or_triple f = (*f is in -> bool*)
    if f 7 
    then fn x => 2*x
    else fn x => 3*x ;


val double = double_or_triple (fn x => x-3 = 4);
val nine = (double_or_triple (fn x => x = 42)) 3; (* this will return functino 2x*)
			      
(* Higher order functions over our own datatype bindings *)

datatype exp = Constant of int
	     | Negate of exp
	     | Add of exp*exp
	     | Multiple of exp*exp;

(* given an exp, is every constant in it an even number ? *)
(* given an ex, is every constant in it less than 10 *)

fun true_od_all_constants (f, e) =
    case e of
	Constant i => f
     | Negate e1 => true_of_all_constants (f, e1)
     | Add (e1,e2) => true_of_all_constants (f, e1) andalso true_of_all_constants (f, e2)
     | Multiply (e1,e2) => true_of_all_constants (f, e1) andalso true_of_all_constants (f, e2);

fun_all_even e = true_of_all_constants ((fn x => x mod 2 = 0), e);

										       
			
