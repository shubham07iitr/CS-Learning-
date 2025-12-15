fun compose (f, g) = fn x => f(g x);

(* ( 'b -> 'c) * ('a -> 'b) -> ('a -> 'c) *)


fun sqrt_of_abs i = Math.sqrt (Real.fromInt (abs i));

fun sqrt_of_abs i = (Math.sqrt o Real.fromInt o abs) i;

val sqrt_of_abs = Math.sqrt o Real.fromInt o abs ;

fun backup1(f, g) = fn x => case f x of
				NONE => g x
			     | SOME y =>  y

