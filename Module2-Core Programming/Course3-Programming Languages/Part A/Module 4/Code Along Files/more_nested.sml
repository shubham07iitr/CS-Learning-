fun nondecreasing1 xs = (* int list -> bool *)
    case xs of
	[] => true
     |  x:xs'  => case xs' of
		      [] => true
		    | y::ys' => x <= y andalso nondecreasing1 xs' ;

fun nondecreasing2 xs = (* int list -> bool *)
    case xs of
	[] => true
      | x::[] => true
      | head :: (neck:: rest) => head <= neck andalso nondecreasing2(neck::rest);

datatype sgn = P | N | Z;


fun mutlsign (x1, x2) = (* int * int -> sgn *)
    let fun sign x = if x = 0 then Z else if x > 0 then P else N
    in
	case (sign x1, sign x2) of
	    (Z, _) => Z
         |   (_, z) => Z
         |   (P, P) => P
	 |   (N, N) => N						   
	 |   (P, P) => P						   
	 |   _ => N						   






					
