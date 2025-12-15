fun sorted3_tupled (x,y,z) = z >= y andalso y >= x ;

val t1 =  (7,9,11);

(* new way : currying *)

val sorted3 = fn x => fn y => fn z => z >= y andalso y >= x;

val t2 = ((sorted3 7) 9) 11;

val t3=  sorted3 3 7 9;

fun sorted3_nicer x y z = z >= y andalso y >=x;

val sum = fold (fn (x,y) => x+y ) 0
val sum_list = sum [1,2,4,5];
