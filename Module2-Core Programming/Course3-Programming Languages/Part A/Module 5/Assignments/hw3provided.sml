(*------------------------------------------*)
(*------------------------------------------*)
(* CONSTANTS *)

exception NoAnswer

val LOI1 = [2,6,3,4,1]; (*random test case *)
val LOS0 = [] ; (* empty LOS useful for P1 *)
val LOS1 = ["Shubham" , "survi" , "Sharvil" , "mummy", "Shivansh"];(*string list to test out rsesults for problem 1*)
val LOS2 = ["shubham", "survi"]; (* case when no elements in list are capitalised , useful for P1*)
val LOS3 = ["shubham" , "survi" , "Sharvil" , "mummy"];(*string list useful for testing P2 *)
val S0 = ""; (* sample empty string useful for testing P6*)
val S1 = "shubham"; (* sample string useful for testing P6*)
val S2 = "shubham gupta"; (* sample string useful for testing P6*)
val LOS4  = ["shubham" , "survi"]; (* list of strings which are useful for testing P8*)
	      
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

datatype pattern = Wildcard
		 | Variable of string
		 | UnitP
		 | ConstP of int
		 | TupleP of pattern list
		 | ConstructorP of string * pattern;

(* pattern is one of *)
(* -- Wildcard which can take any value *)
(* -- Variable of string - which can take only string value *)
(* -- Unitp which signifies a type of pattern *)
(* -- ConstP which reflects an int value *)
(* -- TupleP which is actually a recursive call to each pattern in the list of patterns through self reference *)
(* -- ConstructorP of string*pattern which is again a recursive call to pattern*)
(* interp. as a tpe of pattern whic we use to pattern match data in SML*)


val PW = Wildcard;
val PV = Variable "P0";
val PV1 = Variable "P1"
val PU = UnitP;
val PC = ConstP 10;
val PCT = ConstructorP ("Random" , Wildcard);
val PT0 = TupleP [PW, PV];
val PT = TupleP [PW, PV, PU, PC, PT0, PCT];
val PT1 = TupleP [PW, PV, PU,PV, PC, PT0, PCT , PV1];
val PT2 = TupleP [PW, PV, PU, PC,  PCT , PV1];
val LOP = [PU, PC]; (*Useful for problem 12*)
		 
(* template for pattern *)

(*
fun fn-for-p p
   case p of
   Wildcard => (.... Wildcard)
   Variable s => (....s)
   UnitP => (....UnitP)
   ConstP i => (.... i)
   TupleP [] => (...)
   TupleP p:p' => (...p) (fn-for-p p')
   ConstructorP (s , p) => (...s) (fn-for-p p)
*)
		  
datatype valu = Const of int
	      | Unit
	      | Tuple of valu list
	      | Constructor of string * valu;

(* datatype is one of *)
(* -- Const of type int representing constant *)
(* -- Unit is any specific unit of any particular datatype like pass or fail *)
(* -- TupleP of valu List is a list of values *)
(* -- Constructor is a pair of constructor name and value *)
(* interp. as any actual value defined in any new constructor type or defined datatype *)

val VC = Const 10;
val VU = Unit;
val VT = Tuple [VC, VU];
val VCT = Constructor ("Random", VC);
	       

(* template for valu *)
(*
fun fn-for-v v
   case v of
     Const x => (...x)
     Unit => (...)
     Tuple [] => (...)
     Tuple x::xs' => (fn-for-x) (fn-for-lov xs') (* we will have to implement mutual recursion here *)
     Constructor (x, p) => (...s) (fn-for-p p)
*)


fun g f1 f2 p =
    let 
	val r = g f1 f2 
    in
	case p of
	    Wildcard          => f1 ()
	  | Variable x        => f2 x
	  | TupleP ps         => List.foldl (fn (p,i) => (r p) + i) 0 ps
	  | ConstructorP(_,p) => r p
	  | _                 => 0
    end

(**** for the challenge problem only ****)

datatype typ = Anything
	     | UnitT
	     | IntT
	     | TupleT of typ list
	     | Datatype of string

(*------------------------------------------*)
(*------------------------------------------*)
(* FUNCTIONS*)
(* Problem 1 : ONLY_CAPITALS *)
(* Signature , List of String  -> String List *)
(* Takes in a list of strings, and fitlers out only those strings which start with an uppercase letter *)

(* defining the stub *)
(*fun only_capitals (los: string list ) = los; *)

(*template from filter *)
(*
fun fn-for-filter = List.filter (fn : ('a -> bool), x)
*)


fun only_capitals (los: string list) =
    List.filter (fn x => Char.isUpper(String.sub (x, 0))) los;


(* writing test cases *)
check_expect("only_capitals_test0", only_capitals LOS0,[]); (* base case of an empty list returning an empty list *)
check_expect("only_capitals_test1", only_capitals LOS1,["Shubham", "Sharvil", "Shivansh"] ); (* case of a list having both cpaital and non capital 1st letter *)
check_expect("only_capitals_test2", only_capitals LOS2, []); (* case where in LOS all strings start with non capital letters *)

(*------------------------------------------*)
(* Problem 2 : LONGEST_STRING1 *)
(* Signature , List of String  -> String  *)
(* Takes in a list of strings, and returns longest string in the list  *)
(* if there are strings with same length, return the one CLOSER to START of the list *)

(* defining the stub *)
(*fun longest_string1 (los: string list) = "";*)

(*template from foldl *)
(*
fun fn-for-foldl = List.Filter (fn: ('a 'a => 'a), 'a ,  'a los)
*) 



fun longest_string1 (los: string list) =
    List.foldl (fn (x,y) => if String.size x > String.size y then x else y) "" los;



(* writing test cases *)
check_expect("longest_string1_test0", longest_string1 LOS0, ""); (* base case of an empty list returning an empty list *)
check_expect("longest_string1_test1", longest_string1 LOS3, "shubham"); (* compound case of two strings having same length but we return the string closer to list beginning *)
check_expect("longest_string1_test2", longest_string1 LOS2, "shubham"); (* case where only a single longest string in the list *)



(*------------------------------------------*)
(* Problem 3 : LONGEST_STRING2 *)
(* Signature , List of String  -> String  *)
(* Takes in a list of strings, and returns longest string in the list  *)
(* if there are strings with same length, return the one CLOSER to END of the list *)

(* defining the stub *)
(*fun longest_string1 (los: string list) = "";*)

(*template from foldl *)
(*
fun fn-for-foldl = List.Filter (fn: ('a 'a => 'a), 'a ,  'a los)
*) 



fun longest_string2 (los: string list) =
    List.foldl (fn (x,y) => if String.size x >= String.size y then x else y) "" los;



(* writing test cases *)
check_expect("longest_string2_test0", longest_string2 LOS0, ""); (* base case of an empty list returning an empty string*)
check_expect("longest_string2_test1", longest_string2 LOS3, "Sharvil"); (* compound case of two strings having same length but we return the string closer to list beginning *)
check_expect("longest_string2_test2", longest_string2 LOS2, "shubham"); (* case where only a single longest string in the list *)


(*------------------------------------------*)
(* Problem 4 : LONGEST_STRING HELPER, LONGEST_STRING3, LONGEST_STRING4 *)
(* Signature Longest String Helper-> (int*int -> bool) -> string list -> string *)
(* Takes in a function which takes in two ints and produces a bool , based on which we fold the function to produce the output *)
(* produce 2 functions here using longest_string_helper, longest_string_3 and longest_string_4 which behave like longest_string_1 and longest_string_2 earlier *)

(* defining the stub *)
(*fun longest_string_helper (f: (int, int -> bool), los, "" = ""*) 

(*template from function currying *)
(*
fun fn-for-currying x y x = fn x => fn y => fn z 
*) 

fun longest_string_helper (f: (int* int -> bool)) (los: string list) (s: string)   = List.foldl (fn (x,y) => if f (String.size x , String.size y) then x else y) s los;

val longest_string3 = longest_string_helper (fn (x,y) => x >y) ;
val longest_string4 = longest_string_helper (fn (x,y) => x >= y);

(* writing test cases *)
check_expect("longest_string3_test1", longest_string3 LOS3 "", "shubham"); (* case of using longest_string3 which returns the longest string closer to the start of list *)
check_expect("longest_string4_test1", longest_string4 LOS3 "", "Sharvil"); (* case of useing longest_string4 which returns the longest string closer to end of the list *)





(*------------------------------------------*)
(* Problem 5 : LONGEST_CAPITALIZED *)
(* Signature , List of String  -> String  *)
(* Takes in a list of strings, and returns longest string in the list which beigns with a capital letters *)
(* if there are strings with same length, return the one closer to END of the list *)
(* avoid unnecesasry function binding , use val operator and o operator for function composition*)

(* defining the stub *)
(*fun longest_capitalized (los: string list) = "";*) 

(*template from function composition *)
(*
val fn-for-x = f o g
*) 

val longest_capitalized = longest_string1 o only_capitals ;

(* writing test cases *)
check_expect("longest_string2_test0", longest_capitalized LOS0, ""); (* base case of an empty list returning an empty string *)
check_expect("longest_string2_test1", longest_capitalized LOS3, "Sharvil"); (* compound case of two strings having same length but one of them is not capitalised first char *)
check_expect("longest_string2_test2", longest_capitalized LOS2, ""); (* case where only no element in list is capitalised *)
check_expect("longest_string2_test3", longest_capitalized LOS1, "Shivansh"); (* case where few elements capitalized and we return string with longest lenght closer to start of the list *)


(*------------------------------------------*)
(* Problem 6 : REV_STRING *)
(* Signature , String  -> String  *)
(* Takes in a string and reverses it *)
(* produces empty string if we pass an empty string *)


(* defining the stub *)
(*fun rev_string (s: string) = "";*)

(*template from function composition *)
(*
fun fn-for-string(s) = f o g o h(s)
or equivalently
val fn-for-string = f o g o h
*) 

val rev_string  = String.implode o List.rev o String.explode;

(* writing test cases *)
check_expect("rev_string_test0", rev_string S0, ""); (* base case of an empty string returning an empty string *)
check_expect("rev_string_test1", rev_string S1, "mahbuhs"); (* standard case of returning a single string w/o whitespaces *)
check_expect("rev_string_test2", rev_string S2, "atpug mahbuhs"); (*compond case where we have whitspaces*)


(*------------------------------------------*)
(* Problem 7 : FIRST_ANSWER *)
(* Signature> ('a -> 'b option) -> 'a  list  -> 'b  *)
(* Takes in two arguments which are curried and first argument should be applied to elements of the second argument in order until the first time it returns SOME v for some v and then v is the result of the call to first_answer. *)
(*If the first argument returns NONE for all list elements, then first_answer should raise the exception for empty list that is *)



(* defining the stub *)
(*val first_answer : ('a -> 'b option) -> 'a list -> 'b;*)

(*template from curried functinos *)
(*
fun x y z = fn x => fn y => fn z = (...x) (... y) (...z)
*) 


(* writing the actual function below *)
fun first_answer (f: 'a -> 'b option) (los: 'a list) =
    case los of
        [] => raise NoAnswer
      | x::xs' => if isSome (f x)
                  then valOf (f x)
                  else first_answer f xs';
						     
(* writing test cases *)
check_expect("first_answer_test2", first_answer (fn x => if String.size x >= 6 then SOME x else NONE)  LOS2,  "shubham"); (* testing for case when we pass for list of strings *)
check_expect("first_answer_test1", first_answer (fn x => if x >= 3 then SOME x else NONE) [1,2,3,5,7] , 3); (* testing for case when we pass list of ints *)



(*------------------------------------------*)
(* Problem 8 : ALL_ANSWERS *)
(* Signature> ('a -> 'b list option) -> 'a  list  -> 'b list option *)
(* Takes in two arguments which are curried and first argument should be applied to elements of the second argument in order until the first time it returns SOME v for some v and then v is the result of the call to first_answer. *)
(*If the first argument returns NONE for all list elements, then first_answer should raise the exception for empty list that is *)



(* defining the stub *)
(*val all_answers : ('a -> 'b list option) -> 'a list -> 'b list option*)

(*template from curried functinos *)
(*
fun x y z = fn x => fn y => fn z = (...x) (... y) (...z)
*) 


(* writing the actual function below *)

fun all_answers (f: 'a -> 'b list option) (los: 'a list) =
    let fun helper (f: 'a -> 'b list option) (los: 'a list) (acc: 'b list option) =
	case los of
	    [] => acc
	  | x::xs' => if not (isSome (f x))
		      then NONE
		      else if not (isSome acc)
		      then  helper f xs' (SOME (valOf (f x)))
		      else helper f xs' (SOME ((valOf (f x)) @  (valOf acc)))
    in helper f los NONE end;



(* writing test cases *)
check_expect("all_answers_test1", all_answers (fn x => if x <>  "survi" then SOME (String.explode x) else NONE)  LOS2, NONE); (* testing for case when even one element returns NONE *)
check_expect("all_answers_test2", all_answers (fn x => if x <>  "sharvil" then SOME (String.explode x) else NONE)  LOS2,  SOME [ #"s", #"u", #"r", #"v" ,#"i",#"s",#"h",#"u",#"b",#"h",#"a",#"m"]); (* testing for case when we pass for list of strings and no element returns NONE *)


(*------------------------------------------*)
(*------------------------------------------*)
(* FUNCTIONS PATTERN MARCHING*)
(* Problem 9a : COUNT_WILDCARDS *)
(* Signature , Pattern  ->Int *)
(* Takes in a pattern and counts the no. of wild cards in it *)

(* defining the stub *)
(*fun count_wildcards (p: pattern ) = 0; *)

(*template from g and pattern*)


fun count_wildcards (p: pattern) = g (fn x=> 1) (fn x => 0) p;
   

(* writing test cases *)
check_expect("count_wildcards_test0", count_wildcards PV,0); (* base case where pattern does not have any wildcards *)
check_expect("count_wildcards_test1", count_wildcards PW,1); (* case we input a direct wildcard pattern *)
check_expect("count_wildcards_test2", count_wildcards PT, 3 ); (* compound case of a list with multiple wildcard elements *)

(*------------------------------------------*)
(* FUNCTIONS PATTERN MARCHING*)
(* Problem 9b : COUNT_WILD_AND_VARIABLE_LENGTHS *)
(* Signature , Pattern  ->Int *)
(* Takes in a pattern and counts the no. of wild cards + sum of lengths of all variable strings in  *)

(* defining the stub *)
(*fun count_wild_and_variable_lengths (p: pattern ) = 0;*)

(*template from g and pattern*)


fun count_wild_and_variable_lengths (p: pattern) =
    let fun helper p = g (fn x => 0) (fn x => String.size x) p
    in
	(count_wildcards p) + (helper p)
    end;

 

(* writing test cases *)
check_expect("count_wild_and_variable_lengths_test0", count_wild_and_variable_lengths PCT, 0); (* base case where we dont have either wildcard or a variable  *)
check_expect("count_wild_and_variable_lengths_test1", count_wild_and_variable_lengths PV,2); (* case with variable length but no wildcard *)
check_expect("count_wild_and_variable_lengths_test2", count_wild_and_variable_lengths PW,1); (* case with no variable length but 1 wildcard *)
check_expect("count_wild_and_variable_lengths_test3", count_wild_and_variable_lengths PT0,3); (* case with both varilable length and wildcard *)


(*------------------------------------------*)
(* FUNCTIONS PATTERN MARCHING*)
(* Problem 9c : count_some_var *)
(* Signature , (String, Pattern)  ->Int *)
(* Takes in a string and a pattern, and returns how many times that string appears as a variable in the pattern *)

(* defining the stub *)
(*fun count_some_var (s: string, (p: pattern)) = 0; *)

(*template from g and pattern*)


fun count_some_var (s:string, p: pattern) =
    g (fn x=> 0) (fn x => if x = s then 1 else 0) p ;

 

(* writing test cases *)
check_expect("count_some_var_test0", count_some_var ("PC0", PCT), 0); (* base case where there is no varaiable in the pattern  *)
check_expect("count_some_var_test1", count_some_var ("P1", TupleP [PV, PV, PW]), 0); (* compound case where there are varaibles but no matches *)
check_expect("count_some_var_test1", count_some_var ("P0", TupleP [PV, PV, PW]), 2); (* compound case where there are varaibles and also matches *)


(*------------------------------------------*)
(* FUNCTIONS PATTERN MARCHING*)
(* Problem 10 : CHECK_PAT *)
(* Signature , PAT -> Bool *)
(* Takes in pattern and returns true if there is no duplicate variable string in the pattern *)
(* -- FALSE - There are duplicates *)
(* -- TRUE - There are no duplicates *)

(* defining the stub *)
(*fun check_pat (p: pattern) = false ;*)

(*template from g and pattern*)


fun check_pat (p: pattern) =
    let fun helper_list_variables p = (* creatting a helper function for capturing all the string variables in a list throught the foldl function *)
	    case p of
		Wildcard          => []
	      | Variable x        => [x]
	      | TupleP ps         => List.foldl (fn (q,i) => (helper_list_variables q) @ i) [] ps
	      | ConstructorP(_,p') => helper_list_variables p'
	      | _                 => []
	fun helper_check_duplicates (los, acc) = (* another helper to check duplicates, using an acc for any element which we see and gets added in acc *)
	    case los of
		[] => true
	      | x::xs' => if List.exists (fn q => q = x) acc
			  then false
			  else helper_check_duplicates (xs', x:: acc)
    in helper_check_duplicates (helper_list_variables p, []) end;

(* writing test cases *)
check_expect("check_pat_test0", check_pat PW, true); (* base case when there are no variables in the pattern, so no duplicates hence return true *)
check_expect("check_pat_test1", check_pat PT1, false); (* compound case when there are duplicates in the pattern *)
check_expect("check_pat_test1", check_pat PT2, true); (* compound case when there are no duplicates in the pattern *)


(*------------------------------------------*)
(* FUNCTIONS PATTERN MARCHING*)
(* Problem 11 : MATCH *)
(* Signature , VALU, PAT -> (string, valu) list option *)
(* Identifies if a given pattern matches a given value based on the given condiions *)
(* -- Wildcard matches everything and produces the empty list of bindings.*)
(* -- Variable s matches any value v and produces the one-element list holding (s,v) *)
(* --UnitP matches only Unit and produces the empty list of bindings. *)
(* -- ConstP 17 matches only Const 17 and produces the empty list of bindings (and similarly for other integers) *)
(* -- TupleP ps matches a value of the form Tuple vs if ps and vs have the same length and for all i, the ith element of ps matches the ith element of vs. The list of bindings produced is all the lists from the nested pattern matches appended together *)
(* --ConstructorP(s1,p) matches Constructor(s2,v) if s1 and s2 are the same string (you can compare them with =) and p matches v. The list of bindings produced is the list from the nested pattern match. We call the strings s1 and s2 the constructor name.  *)
(* --Nothing else matches.*)
(* Once we have evaluated the above 7 params , we check *)
(* -- if the pattern does not match return NONE*)
(* -- If we do get some list of bindings, even if it is empty, we get SOME lst or SOME [] *)


(* defining the stub *)
(*fun match ((v: valu), (p: pattern)) = NONE ;*)

(*template from g and pattern*)


fun match (v: valu, p: pattern) =
    case (v,p) of
	(_, Wildcard) => SOME []
      | (x, Variable s) => SOME [(s, x)]
      | (Unit, UnitP) => SOME []
      | (Const y, ConstP z) => if y = z then SOME [] else NONE
      | (Tuple v', TupleP p') => if List.length v' = List.length p'
				 then all_answers (fn (x,y) => match (x,y)) (ListPair.zip (v', p'))
				 else NONE
      | (Constructor (s1, v''), ConstructorP (s2, p'')) => if s1= s2 andalso (isSome (match(v'', p'')))
							   then  (match (v'', p''))
							   else  NONE 
      | (_,_) => NONE;


(*
datatype pattern = Wildcard
		 | Variable of string
		 | UnitP
		 | ConstP of int
		 | TupleP of pattern list
		 | ConstructorP of string * pattern;

datatype valu = Const of int
	      | Unit
	      | Tuple of valu list
	      | Constructor of string * valu;
*)



	  
(* writing test cases *)
check_expect("match_test0", match (VC, PV), NONE); (* base case when there is no match *)
check_expect("match_test1", match (VC, PW), SOME []); (* case when there is a match but empty list *)
check_expect("match_test2", match (VT, PT0), SOME [("P0", Unit)]); (* case when there is a match but empty list *)

(*------------------------------------------*)
(* FUNCTIONS PATTERN MARCHING*)
(* Problem 12 : FIRST_MATCH *)
(* Signature , VALU, List of PAT -> (string, valu) list option *)
(* Identifies if a given value matches any pattern in a list of patterns*)
(* -- Returns NONE , if no match found *)
(* -- Returns SOME lst for list of bindings from the first match *)

(* defining the stub *)
(*fun first_match (v: valu, lop: pattern list) = NONE;*)

(*template from g and pattern*)


fun first_match (v: valu, lop: pattern list) =
    SOME (first_answer (fn p => match (v,p)) lop)
    handle NoAnswer => NONE;
	       

(* writing test cases *)
check_expect("first_match_test0", first_match (VC, [PU]), NONE); (* base case when there is no match *)
check_expect("first_match_test0", first_match (VC,LOP ), SOME []); (* there is a match and we prodyce first match *)


