
val x = 1;

fun f y =
    let
	val x = y + 1
    in
	fn z => x + y + z (* function f returns an anonymous function which returns 2y+1+z *)
    end;

val x = 3; (*irreleant for evaluation of f y*)

val g = f 4; (* returns a function that adds 9 to its argument *)

val y = 5; (* irrelvant *)

val z = g 6; (* get 15*)



	  
	    
