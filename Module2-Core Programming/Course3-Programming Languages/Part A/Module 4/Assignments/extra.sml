(*------------------------------------------*)
(*------------------------------------------*)
(* CONSTANTS *)

exception Negative (*useful for P10 in funcitons for NAT *)
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


(* DATA STUDENT_ID *)
type student_id = int;
(* student_id is an Int *)
(* interp. as the unique number for each student enrolled in the uni *)

(* few examples *)
val SID0 = 10001;
val SID1 = 10002;
val SID2 = 10003;
val SID3 = 10004;
val SID4 = 10005;
	       

(* template for grade *)
(*
fun fn-for-id i= (....i)		 
*)	

(*------------------------------------------*)
(* DATA GRADE *)
type grade = int; (* must be in 0 to 100 range *)
(*grade is an Int [0,100]*)
(*interp. as the grade of a student in a university *)

(* few examples *)
val G0 = 0;
val G1 = 100;
val G2 = 60;
val G3 = 80;
	     
(* template for grade *)
(*
fun fn-for-grade g= (....g)		 
*)			

(*------------------------------------------*)
(* DATA FINAL_GRADE *)
type final_grade = { id : student_id, grade : grade option };

(* final_grade is a record of two fields*)
(* -- id is of student_id *)
(* -- grade is of option grade , SOME grade if grade available else NONE *)

(* few examples *)

val FG0 = {id= SID0, grade= SOME G0 };
val FG1 = {id= SID1, grade= SOME G1 };
val FG2 = {id= SID2, grade= SOME G2 };
val FG3 = {id= SID3, grade= SOME G3 };
val FG4 = {id= SID4, grade=  NONE};
val LFG0 = [] ; (* empty list of final grades, useful for P3*)
val LFG1 = [FG0, FG1, FG2, FG3, FG4] ; (* list of FG for P3 *)

(* template for final_grade *)

(*
fun fn-for-fg (id = x, grade = y)
   (...x)
   (...y)
*)

(*------------------------------------------*)
(* DATA PASS_FAIL *)

datatype pass_fail = pass | fail;
(* Pass_fail is one of *)
(* -- pass - representing a student has passed the exam*)
(* -- fail - representing a student has failed the exam*)

val PF0 = pass;
val PF1 = fail;
	      
val LOT0 = [] ; (*list of pass/fail and final_grade tuple useful for P4 *)
val LOT1 = [(pass, FG0), (fail, FG1), (fail, FG2), (pass, FG3)] ; (*list of pass/fail and final_grade tuple useful for P4 *)


(* Template for enumeration *)
(*
fun fn-for-pf pf =
    case pf of
         pass => (...)
       | fail  =>  (...)
*)						

(*------------------------------------------*)
(* DATA NATL *)

datatype nat = ZERO | SUCC of nat;
(* Nat is one of *)
(* -- ZERO- representing first element in a series equivalent to an empty list*)
(* -- Succ of nat - any element in the series, equivalent to cons of list*)

val N0  = ZERO;
val N1 = SUCC N0;
val N2 = SUCC N1;
val N3 = SUCC N2;
val N4 = SUCC N3;
val N5 = SUCC N4;
	      

(* Template for self-refenretial data type *)
(*
fun fn-for-nat n =
    case pf of
         ZERO => (...)
       | SUCC SUCC N   =>  (fn-for-nat N)
*)					


(*------------------------------------------*)
(*------------------------------------------*)
(* FUNCTIONS GRADES*)
(* Problem 1 : PASS_OR_FAIL *)
(* Signature , {grade: int option, id: 'a } -> pass_fail *)
(* Takes in a record, or some general field , returns pass if grade field contians int >= 75 *)
(* If grade is NONE then it should be FAIL *)

(* defining the stub *)
(*fun pass_or_fail fg = pass ; *)

(*template from fg *)


fun pass_or_fail ({grade = x, id = y}) =
    case x of
	NONE => fail
      | SOME p => if p >= 75 then pass else fail;


(* writing test cases *)
check_expect("pass_or_fail_test1", pass_or_fail FG3 , pass); (* case when grade is >= 75 *)
check_expect("pass_or_fail_test2", pass_or_fail FG4 , fail); (* case when grade is NONE *)
check_expect("pass_or_fail_test3", pass_or_fail FG2 , fail); (* case when grade is <= 75  *)


(*------------------------------------------*)
(* FUNCTIONS GRADES*)
(* Problem 2 : HAS_PASSED *)
(* Signature , {grade: int option, id: 'a } -> BOOL *)
(* Takes in a record, or some general field , returns true if the user passed, else false *)


(* defining the stub *)
(*fun has_passed fg = true ; *)

(*template from fg *)


fun has_passed fg =
    let val result = pass_or_fail fg
    in	
	case result of
	    pass => true
	  | fail => false
    end;


(* writing test cases *)
check_expect("has_passed_test1", has_passed FG3 , true); (* case when grade is >= 75 *)
check_expect("has_passed_test2", has_passed FG4 , false); (* case when grade is NONE *)
check_expect("has_passed_test3", has_passed FG2 , false); (* case when grade is <= 75  *)


(*------------------------------------------*)
(* FUNCTIONS GRADES*)
(* Problem 3 : NUMBER_PASSED *)
(* Signature , {grade: int option, id: 'a } list -Int *)
(* Takes in a a list of Final Grades, and identifies the students who have passed *)


(* defining the stub *)
(*fun number_passed lfg = 0 ; *)

(*template from List of Elements *)
(*
fun fn-for-loe loe =
   [] => (...)
   | x::xs' => (...x) (fn_for_loe xs')
*)


fun number_passed lfg =
    case lfg of
	[] => 0
      | x::xs' => if (has_passed x)
		  then (1 + number_passed xs')
		  else number_passed xs';

(* writing test cases *)
check_expect("number_passed_test1", number_passed LFG0 , 0); (* case when list is empty *)
check_expect("number_passed_test2", number_passed LFG1 , 2); (* case when list is non empty *)

(*------------------------------------------*)
(* FUNCTIONS GRADES*)
(* Problem 4 : NUMBER_MISGRADED *)
(* Signature , List of Tuple (pass_fail, final_grade) -> Int *)
(* Takes in a a list of tuples for pass/fail and final grade and counts no. of elements which are misgraded, that is written pass, but actually fail and vice versa *)


(* defining the stub *)
(*fun number_misgraded lot = 0 *)

(*template from List of Tuples from previous quesiton *)


fun number_misgraded lot =
    case lot of
	[] => 0
      | (result, fg)::remaining_list => if result = pass_or_fail(fg)
					then number_misgraded (remaining_list)
					else 1 + number_misgraded (remaining_list);


(* writing test cases *)
check_expect("number_misgraded_test1", number_misgraded LOT0 , 0); (* case when list is empty *)
check_expect("number_misgraded_test2", number_misgraded LOT1 , 2); (* case when list is non empty *)

(*------------------------------------------*)
(*------------------------------------------*)
(* FUNCTIONS NAT*)
(* Problem 9 IS_POSITIVE *)
(* Signature ,NAT -> Bool *)
(* Takes in a natural number and identifies if it is ZERO or non zero *)

(* defining the stub *)
(*fun is_positive n = true;*)

(*template from NAT *)


fun is_positive n =
    case n of
	ZERO => false
      | _ => true;

(* writing test cases *)
check_expect("is_positive_test1", is_positive N0 , false); (* case when nat is ZERO *)
check_expect("is_positive_test2", is_positive N1  , true); (* case when NAT is non zero*)

(*------------------------------------------*)
(* Problem 10 PRED *)
(* Signature ,NAT -> NAT *)
(* Takes in a natural number and identifies its successor, raises exception if nat is ZERO *)

(* defining the stub *)
(*fun pred n = ZERO;*)

(*template from NAT *)


fun pred n =
    case n of
	ZERO => raise Negative
      | (SUCC N) => N;


(* writing test cases *)
check_expect("pred_test1", pred N1 , ZERO); (* case when nat is ZERO *)
check_expect("pred_test2", pred N2  , N1); (* case when NAT is  SUCC (SUCC 0)*)


(*------------------------------------------*)
(* Problem 11 NAT_TO_INT *)
(* Signature ,NAT -> INT *)
(* Takes in a natural number and returns the corresponding value in Int *)

(* defining the stub *)
(*fun nat_to_int n = 0;*)

(*template from NAT *)


fun nat_to_int n =
    case n of
	ZERO => 0
      | (SUCC N) => 1+ (nat_to_int N);


(* writing test cases *)
check_expect("nat_to_int_test1",  nat_to_int N0 , 0); (* case when nat is zero *)
check_expect("nat_to_int_test2", nat_to_int N2 , 2); (* case when NAT is SUCC (SUCC 0)*)


(*------------------------------------------*)
(* Problem 12 INT_TO_NAT *)
(* Signature , INT -> NAT *)
(* Takes in a int >=0  and returns the corresponding value in NAT *)

(* defining the stub *)
(*fun int_to_nat n = ZERO;*)

(*template from INT *)



fun int_to_nat n =
    case n of
	0 => ZERO
      | _ => SUCC (int_to_nat (n-1));


(* writing test cases *)
check_expect("int_to_nat_test1",  int_to_nat 0 , N0); (* case when int  is 0 *)
check_expect("int_to_nat_test2", int_to_nat 2 , N2); (* case when int is non zero *)

(*------------------------------------------*)
(* Problem 13 ADD *)
(* Signature , NAT, NAT -> NAT *)
(* Returns sum of two NATs *)
(* Two one of problems *)
(* -- n1 zero - return n2*)
(* -- n2 zero - return n1*)
(* -- both non zero - run recursion on one of the two *)


(* defining the stub *)
(*fun add (n1, n2) = ZERO;*)

(*template from INT *)



fun add (n1, n2) =
    case (n1, n2) of
	(ZERO, ZERO) => ZERO
      | (ZERO, _) => n2
      | (_, ZERO) => n1
      | (SUCC N, n2) => (add (SUCC N, SUCC n2));



(* writing test cases *)
check_expect("add_test0",  add (N0, N0) , N0); (* base case when both of them are zero *)
check_expect("add_test1",  add (N0, N1) , N1); (* case when one of them is zero *)
check_expect("add_test2", add (N2, N3) , N5); (* case when both of them are non zero *)


(*------------------------------------------*)
(* Problem 14 SUB *)
(* Signature , NAT, NAT -> NAT *)
(* Returns difference  of two NATs where n1 > n2 *)
(* Two one of problems *)
(* -- n1 zero - return n2*)
(* -- n2 zero - return n1*)
(* -- both non zero - run recursion on one of the two *)


(* defining the stub *)
(*fun sub (n1, n2) = ZERO;*)

(*template from INT *)



fun sub (n1, n2) =
    case (n1, n2) of
	(ZERO, ZERO) => ZERO
      | (ZERO, _) => n2
      | (_, ZERO) => n1
      | (n1, SUCC N) => (sub (pred n1, SUCC N))



(* writing test cases *)
check_expect("sub_test0",  sub (N0, N0) , N0); (* base case when both of them are zero *)
check_expect("sub_test1",  sub (N0, N1) , N1); (* case when one of them is zero *)
check_expect("sub_test2", sub (N5, N2) , N3); (* case when both of them are non zero *)
