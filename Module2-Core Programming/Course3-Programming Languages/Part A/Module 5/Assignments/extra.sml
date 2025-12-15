(*------------------------------------------*)
(*------------------------------------------*)
(* CONSTANTS *)

val LOI1 = [2,6,3,4,1]; (*random test case *)
	      
(*------------------------------------------*)
(*------------------------------------------*)
(* HELPER FUNCTIONS *)

(*Implementing the check-expect function to test our functions*)
(* Signature -> String, Function with argument, Expected Value -> String*)
(*defining check-expect function which takes in a string (test name), and a value expected from a function and param, and the expected value, and prints pass or fail *)

fun check_expect (name: string, actual: ''a, expected: ''a) =
    if actual = expected then
	print (name ^ " passed\n")
    else
	print (name ^ " failed\n") ;


(* Testing the check_expect *)
check_expect("Check_expect_Test1" , Int.abs ~5, 5); (* testing on integer for pass*)
check_expect("Check_expect_Test2" , Int.abs ~5, 6); (* testing on integer for fail*)
check_expect("Check_expect_Test3" ,String.size "hello", 5); (* testing on string for pass*)
check_expect("Check_expect_Test4" ,String.size "hello", 6); (* testing on string for fail*)
check_expect("Check_expect_Test5" ,hd LOI1, 6); (* testing on list for fail*)
check_expect("Check_expect_Test6" ,hd LOI1, 2); (* testing on list for pass*)


(*------------------------------------------*)
(*------------------------------------------*)
(* DATA DEFINITIONS *)


(*------------------------------------------*)
(*------------------------------------------*)
(* FUNCTIONS*)

(*------------------------------------------*)
(* Problem 1 : COMPOSE_OPT *)
(* Signature> (’b -> ’c option) -> (’a -> ’b option) -> ’a -> ’c option *)
(* Basically takes 3 curried arguments, and produces an option as a result *)
(* Takes 2 functions as arguments, 2nd one operates on the 3rd argument to produce an option, 1st one operates on the valOf of the result of 2nd and 3rd argument, and produces an option*)
(*If either function returns NONE then answer is NONE*)

(* defining the stub is not so important becayse results are polymorphic*)
(*val compose_opt: (’b -> ’c option) -> (’a -> ’b option) -> ’a *)

(*template from curried functinos *)
(*
fun x y z = fn x => fn y => fn z = (...x) (... y) (...z)
*) 


(* writing the actual function below *)
fun compose_opt (f: 'b -> 'c option) (g: 'a -> 'b option) a = 
    case g(a) of
	NONE => NONE
      | SOME b => case f(b) of
		      NONE => NONE
		    | SOME c => SOME c;

						     
(* writing test cases *)
check_expect("compose_opt_test1", compose_opt (fn x => if String.size x >= 6 then SOME 3 else NONE) (fn x => if x >= 3 then SOME "shubham" else NONE) 5,  SOME 3); (* case when one neither function returns NONE *)
check_expect("compose_opt_test1", compose_opt (fn x => if String.size x >= 6 then SOME 3 else NONE) (fn x => if x >= 3 then SOME "shubham" else NONE) 2,  NONE); (* case when one of the function returns NONE *)

(*------------------------------------------*)
(* Problem 2 : DO_UNTIL *)
(* Signature> (’a -> ’a) -> (’a -> bool) -> ’a -> ’a *)
(* Basically takes 3 curried arguments, and produces the non function as the result type *)
(* Takes 2 functions as arguments, we operate on 1st function and 3rd argument continuously until 2nd argument and result of 1st function is false *)


(* defining the stub is not so important becayse results are polymorphic*)
(*val do_until: (’a -> ’a) -> (’a -> bool) -> ’a -> ’a *)

(*template from curried functinos *)
(*
fun x y z = fn x => fn y => fn z = (...x) (... y) (...z)
*) 

(* writing the actual function below *)
fun do_until (f: 'a -> 'a) (g: 'a -> bool) (a: 'a) = 
    case g(f(a)) of
	false => f(a)
      | true => do_until f g (f(a));
						     
(* writing test cases *)
check_expect("do_until_test1", do_until (fn x => x div 2) (fn x => x mod 2 <> 1) 20, 5 ); (* case when we have 20 as the starting point, 1st divisino -> 10-> 5 wih 5 mod 2 = 1 and it stops *)



(*------------------------------------------*)
(* Problem 3 : FACT *)
(* Signature> (’a -> ’a) -> (’a -> bool) -> ’a -> ’a *)
(* Implementing factorial using do_until as above *)

(* defining the stub*)
(*fun fact n = 1; *)

(*template from curried functinos *)
(*
fun x y z = fn x => fn y => fn z = (...x) (... y) (...z)
*) 

(* will have to implement using a helper function and accumulators *)
(* trick is to use 'a to pass in a tuple with an accumulator and a decreasing value *)
fun fact n =
    let
        (* f: one step of factorial *)
        fun f (acc, x) = (acc * x, x - 1)

        (* p: continue while x > 1 *)
        fun p (_, x) = x > 1

        val (result, _) = do_until f p (1, n)
    in
        result
    end;
		

(* writing test cases *)

check_expect("fact_test0", fact 1, 1 ); (* base case when we want to test multiply by 0  *)
check_expect("fact_test1", fact 3, 6 ); (* case when  it is not the base case *)


(*------------------------------------------*)
(* Problem 4 : FIXED_POINT *)
(* Signature> (’’a -> ’’a) -> ’’a -> ’’a *)
(* Apply f on x until the time when f x = x *)
(* Need to use curried property *)

(* defining the stub not important here *)


(*template from curried functinos *)
(*
fun x y z = fn x => fn y => fn z = (...x) (... y) (...z)
*) 


(* trick is to use curried function here where 2nd argument is already given *)
fun fixed_point f x = do_until f (fn p => f(p) = p) x;
		

(* writing test cases , cant think of a particular function that may be relevant here *)


(*------------------------------------------*)
(* Problem 5 : MAP2 *)
(* Signature> ((’a -> ’b) -> ’a * ’a -> ’b * ’b) *)
(* Takes in 2 arguments, 1 function which operates on single element , and a pair of elements, function operates on both pair of elements individually and returns a fresh pair  *)

(* defining the stub*)
(*fun map2 (f: ('a -> 'b)) (p:'a*'a) = (2,4);*)

(*template from curried functinos *)
(*
fun x y z = fn x => fn y => fn z = (...x) (... y) (...z)
*) 


(* writing the actual function *)


fun map2 f (x,y) = (f(x), f(y));


(* writing test cases *)

check_expect("map2_test1", map2 (fn x => 2*x) (2,4), (4,8) ); (* case of testing map2 on a pair of ints *)



(*------------------------------------------*)
(* Problem 6 : APP_ALL *)
(* Signature> (’b -> ’c list) -> (’a -> ’b list) -> ’a -> ’c list *)
(* Function is in curried format and takes in 3 arguments *)
(* -- 2nd argument (function) takes in an element and produces a list of other elements *)
(* -- 1st argument operates on each individual element of the result of 2nd and 3rd argument and for each element produces a list, which is finally concatenated *)

(* not defining the stub here because unnecessary declarations here*)


(*template from curried functinos *)
(*
fun x y z = fn x => fn y => fn z = (...x) (... y) (...z)
*) 


(* writing the actual function *)

fun app_all (f: 'b-> 'c list) (g: 'a-> 'b list) (e: 'a) =
    let fun helper (f: 'b-> 'c list) (loe: 'b list) =
	    case loe of
		[] => []
	      | x::xs' => f(x) @ helper f xs'
    in helper f (g(e)) end;


(* writing test cases *)

check_expect("app_all_test1", app_all (fn x => [x,x]) (fn x => String.explode x) "sh", [#"s", #"s", #"h", #"h"]); (* case of testing app_lal on two functions, one function explodes, the other one doubeles each element in a list *)


(*------------------------------------------*)
(* Problem 8 : PARTITION *)
(* Signature> ('a -> bool) -> 'a list -> ('a list* 'a list) *)
(* Function is in curried format and takes in 2 arguments *)
(* -- 1st argument operates on 2n argument which is a list and produces 2 lists *)
(* -- 1st list is where 1st argument operates to be true, and 2nd list is where 1st argument operates to be false *)
(* -- will be similar to filter *)

(* not defining the stub here because unnecessary declarations here*)


(*template from curried functinos *)
(*
fun x y z = fn x => fn y => fn z = (...x) (... y) (...z)
*) 


(* writing the actual function *)

fun partition (f: 'a -> bool) (loe: 'a list) =
    case loe of
        [] => ([], [])  (* Base case: empty list *)
      | x::xs' =>
            if f x then
                (x :: #1 (partition f xs'), #2 (partition f xs'))
            else
                (#1 (partition f xs'), x :: #2 (partition f xs'));
			   


(* writing test cases *)

check_expect("partition_test1", partition (fn x => x>= 5) [1,3,4,5,6,7], ([5,6,7], [1,3,4])); (* case of testing on a list of ints where bool refers to whether the number is >= 5 or not*)

