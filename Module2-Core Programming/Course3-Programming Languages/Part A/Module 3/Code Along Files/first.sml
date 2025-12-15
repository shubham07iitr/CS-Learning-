(* this is a comment This is our first program *)


val x = 34; (* int *)
(* static environment: x : int *)
(* dynamic environment: x --> 34*)

val y = 17;
(* static enviroment: x : int, y : int *)
(* dynamic environment: x --> 34, y --> 17*)

val z = (x + y) + (y + 2);
(* static environemnt : x : int, y : int , z : int *)
(* dynamic envirnonment: x --> 34, y --> 17, z --> 70 ,*)

val q = z + 1;
(*static envirnment : x : int, y : int, z : int, q : int *)
(* dynamic environment: x --> 34, y --> 17, z --> 70, w -> 71 *)


val abs_of_z = if z < 0 then 0 - z else z;
(* static env.....: z : (bool) (int) --> int*)
(* dynamic envirpnment : ... , abs_of_z --> 70







