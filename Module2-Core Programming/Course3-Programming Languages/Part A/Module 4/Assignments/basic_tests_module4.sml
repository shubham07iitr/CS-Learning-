(*------------------------------------------*)
(* CONSTANTS *)

val LOI0 = [] (* base case for an empty list of integers *)
val LOI1 = [2,4,5,2,5,10]; (* test data for list*)
val LOI2 = [~2, ~4, 6, 3, ~5]; (* LOI with negative integers to test Problem 1 *)
val LOI3 = [1,3,5]; (* LOI for problem 3 *)
val LOI4 = [1, ~4, ~5]; (* LOI for problem 3 *)
val LOI5 = [~1, ~4, ~5]; (* LOI for problem 14 *)
val LOI6 = [6,3,1] ; (*useful for profblem 17 where we find isAnySorted*)
val LOIO1 = [NONE, NONE, NONE] ; (* List of Int Options, useful for problem 7 *)
val LOIO2 = [NONE, SOME 5, SOME 10, SOME 15, NONE]; (* list of Int options, usful for testing problem 7 *)
val LOB1 = [false , false , false] ; (* list of bools with only false useful for testing problem 8 *)
val LOB2 = [false , false , true] ; (* list of bools with onlysome false useful for testing problem 8 *)		
val LOB3 = [] ; (* empty list of bools  useful for testing problem 8 *)
val LOB4 = [true, true, true] ; (* all true bool list, useful for Problem 9 *)
val LOP1 = [("shubham", 5) , ("survi", 10), ("sharvil" , 15)] ; (* list of pairs useful for problem 13 *)
val LOP2 = [] ; (* empty list for problem 13 *)


(*------------------------------------------*)
(* HELPER FUNCTION *)

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
(* FUNCTIONS *)
(* Problem 1 : ALTERNATE *)
(* Signature , List of Integers -> Int *)
(* Takes in a list of integers, positive or negative, and adds them up but with alternate sign like [1,2,3,4] = 1-2+3-4 = 2 *)

(* defining the stub *)
(*fun alternate (l1: int list) = 0 ; *)

(*template for loi *)

(*
fun fn-for-loi (l1: int list) =
    if null loi then (...)
    else (... (hd loi) (fn-for-loi (tl loi))) *)



fun alternate (loi : int list) =
    let fun alternate0 (loi:int list, acc: int) = (* we use accumulator to keep track of the index value *)
	if null loi then 0
        else if acc mod 2 = 1 (* we check if accumulator or the index is odd then we don't multiply by -1 else we do*)
	then (hd loi + alternate0 (tl loi, acc + 1)) 
	else (~1* (hd loi) + alternate0 (tl loi, acc+1))
    in alternate0 (loi, 1) end ;
		

(* writing test cases *)
check_expect("alternate_test1", alternate LOI0, 0); (* base case of adding up an empty list *)
check_expect("alternate_test2", alternate LOI1, ~4); (* case of adding up elements with only positive integers *)
check_expect("alternate_test3", alternate LOI2, 0); (* case of adding up elements with only positive integers *)



(*------------------------------------------*)
(* Problem 2 : MIN_MAX *)
(* Signature , List of Integers -> (Int*Int) *)
(* Takes in a list of integers, positive or negative,and returns a pair where firs value is the min value in the list , and second value is max in the list *)

(* defining the stub *)

(*fun min_max (l1: int list) = (0,0) ;*)

(*template for loi *)

(*
fun fn-for-loi (l1: int list) =
    if null loi then (...)
    else (... (hd loi) (fn-for-loi (tl loi))) *)


fun min_max (loi : int list) =
    if null loi then (0,0) else if null (tl loi) then (hd loi, hd loi) else  (* for a single element list we define themin max value as the same value on the head of the list *)
    let val tail_min_max = min_max (tl loi) (* defining a recursive variable here so as to not have to calulate it multiple times and raise the  inefficiency *)
    in 
	if hd loi < #1 tail_min_max  (* check if the first element is lower than 1st element on  the result on tail loi, if yes, replace 1st element which is the min in tail *)
        then (hd loi, #2 tail_min_max)
	else if hd loi > #2 tail_min_max  (* check if the first element is lowerhigher than the 2nd element on  result on tail loi, if yes, replace 2nd  element which is the min in tail *)
	then (#1 tail_min_max, hd loi)
	else tail_min_max
    end;
		

(* writing test cases *)
check_expect("min_max_test1", min_max LOI0, (0,0)); (* base case of an empty list returning 0,0*)
check_expect("min_max_test2", min_max LOI1, (2,10)); (* compound case of only positive integers *)
check_expect("min_max_test3", min_max LOI2, (~5,6)); (* compound case of positive + negative integers *)


(*------------------------------------------*)
(* Problem 3 : CUMSUM *)
(* Signature , List of Integers -> List of Integers *)
(* Takes in a list of integers, and returns a list of same length, where each element is replaced by the cumulative sum till that element in the original list *)
(* for e.g. [1,3,5] => [1,4,9] *)

(* defining the stub *)

(* fun cumsum (l1: int list) = l1 ; *)

(*template for loi *)

(*
fun fn-for-loi (l1: int list) =
    if null loi then (...)
    else (... (hd loi) (fn-for-loi (tl loi))) *)



fun cumsum (loi : int list) =

    let fun cumsum0 (loi: int list , acc: int) = (* defining an acumulatro which will carry the sum of the list till the previous index *)
	    if null loi then []
	    else (acc + hd loi) :: (cumsum0 (tl loi, acc + (hd loi))) (*we add the cumulative sum to the recursive call at the head position *)
    in cumsum0 (loi, 0) end; 

		

(* writing test cases *)

check_expect("cumsum_test1", cumsum LOI0, []); (* base case of adding up an empty list *)
check_expect("cumsum_test2", cumsum LOI3, [1,4,9]); (* case of only positive integer list *)
check_expect("cumsum_test3", cumsum LOI4, [1,~3, ~8]); (* case of positive and negative integers list *)


(*------------------------------------------*)
(* Problem 4 : GREETING *)
(* Signature , String Option -> String *)
(* Takes in a string option , returns "Hello there, you" if it is None, else returns "Hello, there, valOf <variable>" *)


(* defining the stub *)

(*fun greeting (name: string option) = "" ;*)

(*template for option *)

(*
fun fn-for-option (s : string option
    if NONE then (....)
    else isSome then (.... valof s)
    else (....) *)


fun greeting (name : string option) =
    if isSome name then ("Hello" ^ " " ^ "there" ^ "," ^ " "^ valOf name)
    else ("Hello" ^ " " ^ "there" ^ "," ^ " " ^ "you")	;	

(* writing test cases *)

check_expect("greeting_test1", greeting NONE, "Hello there, you"); (* case when we pass null value *)
check_expect("greeting_test2", greeting (SOME "shubham"), "Hello there, shubham"); (* case when we pass some value *)


(*------------------------------------------*)
(* Problem 5 : REPEAT *)
(* Signature , List of Integers, List of Integers -> List of Integers *)
(* Takes in two list of integers of same length , and returns a single list of integers, which has the same elements as L1, but each element repeated n times that of corresponding element in L2) *)
(* for e.g. [1,3,5], [3,0,2] => [1,1,1, 5,5] *)
(* assume lenght of both lists being empty *)
(* two one of cases - will produce empty list if both lists are empty *)

(* defining the stub *)

(*fun repeat (l1: int list, l2:int list) = [] ;*)

(*template for loi *)

(*
fun fn-for-loi (l1: int list) =
    if null loi then (...)
    else (... (hd loi) (fn-for-loi (tl loi))) *)



fun repeat (l1 : int list, l2: int list) =

    let fun make_list (i1: int  , i2: int) = (* defining a helper function which will return a list based on two ints*)
	    if i2 = 0 then [] else i1 :: make_list (i1, i2-1)
	fun repeat (l1: int list, l2: int list) =
		   if null l1 andalso null l2 then [] 
		   else make_list(hd l1, hd l2) @ repeat (tl l1, tl l2) 
    in repeat (l1, l2) end;

(* writing test cases *)

check_expect("repeat_test1", repeat (LOI0,LOI0), []); (* base case of both lists being empty  *)
check_expect("repeat_test2", repeat (LOI3, LOI3), [1,3,3,3,5,5,5,5,5]); (* case of both lists being non empty but all elements being non zero in 2nd list *)
check_expect("repeat_test3", repeat (LOI3,[1,0,2]), [1,5,5]); (* case of 0 elements in the second list *)


(*------------------------------------------*)
(* Problem 6 : ADDOPT *)
(* Signature , Int Option, Int Option -> Int Option *)
(* Takes in two options, sums them up if both are not None, , else returns None if either is None *)
(* Two one of case, either of two NONE, we produce None, else produce SOME *)


(* defining the stub *)

(*fun addopt (i1: int option, i2) = NONE ; *)

(*template for option *)

(*
fun fn-for-option (i1 : 'a option, i2: 'a option) = 
    if i1 NONE then (....)
    else if i2 NONE then (....)
    else (...valOf i1) (....valOf i2) *)



fun addopt (i1: int option, i2: int option) =
    if isSome i1 andalso isSome i2 then SOME ((valOf i1) + (valOf i2))
    else NONE;


(* writing test cases *)

check_expect("addopt_test1", addopt (NONE,NONE), NONE); (*base case of both values being NONE *)
check_expect("addopt_test2", addopt (SOME 5,NONE), NONE); (*case of 2nd value being NONE *)
check_expect("addopt_test3", addopt (NONE,SOME 5), NONE); (*case of 1st  value being NONE *)
check_expect("addopt_test2", addopt (SOME 5, SOME 10),SOME 15); (* case when both values are not NONE *)


(*------------------------------------------*)
(* Problem 7 : ADDALLOPT *)
(* Signature ,List of Int Option -> Int Option *)
(* Takes in two options, sums them up if both are not None, , else returns None if either is None *)
(* Two one of case, either of two NONE, we produce None, else produce SOME *)
(* Produce NONE if the list is empty *)


(* defining the stub *)

(* fun addallopt (l1: int option list) = NONE ; *)

(*template for List of Int option as above*)


fun addallopt (l1: int option list) =
  if null l1 then
    NONE
  else if isSome (hd l1) then
    let
      val rest = addallopt (tl l1)
    in
      if isSome rest then
        SOME (valOf (hd l1) + valOf rest)
      else
        hd l1
    end
  else
    addallopt (tl l1);


(* writing test cases *)

check_expect("addallopt_test1", addallopt LOIO1, NONE); (*base case where all values in list are NONE *)
check_expect("addallopt_test2", addallopt LOIO2, SOME 30); (* compound case where some values are non NONE *)



(*------------------------------------------*)
(* Problem 8 : ANY *)
(* Signature , List of Bools -> Bools *)
(* Takes in a list of bools, and returns if there is at least one true in the list, else false if no true, or empty list *)

(* defining the stub *)

(* fun any (l1: bool list) = false *)

(*template for loi *)

(*
fun fn-for-loi (l1: int list) =
    if null loi then (...)
    else (... (hd loi) (fn-for-loi (tl loi))) *)


fun any (loi : bool list) =
    if null loi then false
    else if (hd loi) = true then true
    else any (tl loi);
   
   
		

(* writing test cases *)
check_expect("any_test1", any LOB3, false); (* base case of an empty list returning false *)
check_expect("any_test2", any LOB1, false); (* compound case of all false values *)
check_expect("any_test3", any LOB2, true); (* compound case of some true values *)


(*------------------------------------------*)
(* Problem 8 : ALL *)
(* Signature , List of Bools -> Bools *)
(* Takes in a list of bools, and returns true if all values are true in the list, else false, returns true for empty list as well *)

(* defining the stub *)

(*fun all (l1: bool list) = false ; *)

(*template for loi *)


(*
fun fn-for-loi (l1: int list) =
    if null loi then (...)
    else (... (hd loi) (fn-for-loi (tl loi))) *)


fun all (loi : bool list) =
    if null loi then true
    else if (hd loi) = false then false
    else all (tl loi); 
   
(* writing test cases *)
check_expect("all_test1", all LOB3, false); (* base case of an empty list returning false *)
check_expect("all_test2", all LOB1, false); (* compound case of all false values *)
check_expect("all_test3", all LOB2, false); (* compound case of some true values *)
check_expect("all_test3", all LOB4, true); (* compound case of all true values *)





(*------------------------------------------*)
(* Problem 13 : LOOKUP *)
(* Signature => List of (string, int), String -> int Option *)
(* Takes in a list of pair where 1st element is string, 2nd is int, and another string, if param string matches with 1st element in any pair in the list, return SOME 2nd element of list, if not found return NONE in complete list *)

(* defining the stub *)

(*fun lookup (l1: (string*int) list, s: string) = NONE ; *)

(*template for loi on pairs *)


(*
fun fn-for-loi (l1: int list) =
    if null loi then (...)
    else (... (hd loi) (fn-for-loi (tl loi))) *)


fun lookup (lop : (string*int) list, s: string) =
    if null lop then NONE
    else if (#1(hd lop)) = s then SOME (#2 (hd lop))
    else lookup (tl lop, s); 
   
(* writing test cases *)
check_expect("lookup_test1", lookup (LOP2,"shubham"),  NONE); (* base case of an empty list returning false *)
check_expect("lookup_test2", lookup (LOP1, "mummy"), NONE); (* case when non empty list , but no string match found *)
check_expect("lookup_test3",lookup (LOP1, "sharvil"), SOME 15);  (* compound case of some true values *)





(*------------------------------------------*)
(* Problem 14 : SPLITUP *)
(* Signature => List of Integers -> Pair of List of Ints *)
(* Takes in a list of integers, and produces a pair of list of ints, where 1st part of the pair has all non-negative entries, and other containing negative entries *)
(* Use empty list if either no negative or no positive entries *)
(* defining the stub *)

(*fun splitup (l1: int list) = (l1, []) ; *)

(*template for loi on pairs *)


(*
fun fn-for-loi (l1: int list) =
    if null loi then (...)
    else (... (hd loi) (fn-for-loi (tl loi))) *)


fun splitup (loi: int list) = 
    if null loi then ([], [])
    else let val rest_split = splitup(tl loi)
	 in
	     if (hd loi) >= 0 then (((hd loi)::(#1 rest_split)), (#2 rest_split))
	     else ((#1 rest_split), (hd loi) :: (#2 rest_split))
	 end;

   
(* writing test cases *)
check_expect("splitup_test1", splitup (LOI0), ([],[])); (* base case of an empty list returning 2 empty lists *)
check_expect("splitup_test2", splitup (LOI3), (LOI3, [])); (* case when all elements are non negative *)
check_expect("splitup_test3",splitup (LOI5), ([], LOI4));  (* case when all elements are negative *)
check_expect("splitup_test4",splitup (LOI2), ([6,3], [~2, ~4, ~5]));  (* case when some elements are non negative and some are negative *)



(*------------------------------------------*)
(* Problem 15 : SPLITAT *)
(* Signature => List of Integers, Integer -> Pair of List of Ints *)
(* Takes in a list of integers and another integer, and produces a pair of list of ints, where 1st part of the pair has values > passed in param, and other list has values less than the param *)
(* defining the stub *)

(*fun splitat (l1: int list, num: int) = (l1, []) ; *)

(*template for loi on pairs *)


(*
fun fn-for-loi (l1: int list) =
    if null loi then (...)
    else (... (hd loi) (fn-for-loi (tl loi))) *)


fun splitat (loi: int list, num: int) = 
    if null loi then ([], [])
    else let val rest_split = splitat(tl loi, num) (*defining the result of the recursion to avoid multiple calls and exponential growth*)
	 in
	     if (hd loi) >= num then (((hd loi)::(#1 rest_split)), (#2 rest_split)) (*appending the first element to first list of the recursive result if it is greater than the passed param *)
	     else ((#1 rest_split), (hd loi) :: (#2 rest_split))
	 end;  

   
(* writing test cases *)
check_expect("splitat_test1", splitat (LOI0,2), ([],[])); (* base case of an empty list returning 2 empty lists *)
check_expect("splitat_test2", splitat (LOI3, 6), ([], [1,3,5])); (* case when all elements are less than the passed param *)
check_expect("splitat_test3",splitat (LOI3, 0), ([1,3,5], []));  (* case when all elements are greater thank the passed param *)
check_expect("splitat_test4",splitat (LOI3, 4), ([5], [1,3]));  (* case when some elements are greater than param, and some are less than param *)



(*------------------------------------------*)
(* Problem 16 : isSorted *)
(* Signature => List of Integer -> Boolean *)
(* Takes in a list of integers and returns true if it is in increasing order else return false *)
(* will return true for an empty list *)

(* defining the stub *)

(*fun isSorted (l1: int list) = false ;*)

(*template for loi on integers *)


(*
fun fn-for-loi (l1: int list) =
    if null loi then (...)
    else (... (hd loi) (fn-for-loi (tl loi))) *)


fun isSorted (loi: int list) = (* will use the min_max function for this use case from problem 2*)
    if null loi then true
    else if (hd loi) <= (#1 (min_max(tl loi))) then isSorted (tl loi) (* we check if it first element is less than min value of the remaining list and then call recursive call *)
    else false ;

   
(* writing test cases *)
check_expect("isSorted_test1", isSorted (LOI0), true); (* returns true for an empty list *)
check_expect("isSorted_test2", isSorted (LOI3), true); (* case where list is sorted already *)
check_expect("isSorted_test3",isSorted (LOI1), false);  (* case when list is not sorted *)




(*------------------------------------------*)
(* Problem 17 : isAnySorted *)
(* Signature => List of Integer -> Boolean *)
(* Takes in a list of integers and returns true if it is either in increasing or decreasing order *)
(* will return true for an empty list *)

(* defining the stub *)

(*fun isAnySorted (l1: int list) = false ; *)

(*template for loi on integers *)


(*
fun fn-for-loi (l1: int list) =
    if null loi then (...)
    else (... (hd loi) (fn-for-loi (tl loi))) *)


fun isAnySorted (loi: int list) = (* will use the min_max function for this use case from problem 2*)
    let 
	fun isSortedDecreasing (loi:int list) =
	    if null loi then true
	    else if (hd loi) >= (#2 (min_max(tl loi))) then isSortedDecreasing (tl loi)
	    else false
    in isSorted(loi) orelse isSortedDecreasing(loi) end;



   
(* writing test cases *)
check_expect("isAnySorted_test1", isAnySorted (LOI0), true); (* returns true for an empty list *)
check_expect("isAnySorted_test2", isAnySorted (LOI3), true); (* case where list is sorted in increasing  already *)
check_expect("isAnySorted_test3", isAnySorted (LOI1), false);  (* case when list is not sorted *)
check_expect("isAnySorted_test4", isAnySorted (LOI6), true); (* case where list is sorted in decreasing order  already *)
