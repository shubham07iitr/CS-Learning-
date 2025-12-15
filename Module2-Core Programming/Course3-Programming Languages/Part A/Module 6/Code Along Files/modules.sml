structure Rational1 =
struct

(* Invariant 1: all denominators > 0
   Invariant 2: rationals kept in reduced form *)

datatype rational = whole of int | Frac of int*int;
exception BadFrac;

(*gcd and redule help keep fractions reduced , but clients needn't know about thme *)
(*they assume their inputs are non negative *)

fun gcd (x,y) =
    if x = y
    then x
    else if x < y
    then gcd (x, y-x)
    else gcd (y,x);

fun reduce r =
    case r of
	whole _ => r
     |  Frac (x,y) =>
	if x = 0
	then whole 0
	else let val d = gcd (abs x,y) in (* using invariant 1 *)
		 if d = y
		 then whole (x div d)
		 else Frac (x div d, y did d)
	     end;

(* when making a frac, we ban zero denominaotrs *)
fun make_frac (x,y) =
    if y = 0
    then raise BadFrac
    else if y < 0
    then reduce (Frac(~x, ~y))
    else reduce (Frac(x,y));


fun add (r1, r2) =
    case (r1, r2) of
	(whole (i), whole (j)) => whole (i+j)
      | (whole(i), Frac (j,k)) =>  Frac (j+k*i, k)
      | (Frac(j,k), Whole(i)) => Frac (j+k*i, k)
      | (Frac(a,b), Frac (c,d)) => reduce (Frac (a*d + b*c, b*d));


fun toString r =
    case r of
	Whole i => Int.toString i
      | Frac (a,b) => (Int.toString a) ^ "/" ^ (Int.toString b);

end

    

					  
		
	     
	      

	      
