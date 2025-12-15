(*----------------------------------------------------------------------------*)
(* Defining CONSTATNS*)

val D1 = (1989,9,25);
val D2 = (1989,11,18);
val D3 = (1990,9,25);
val D4 = (1955,1,18);
val LOD1 = []; (*empty list*)
val LOD2 = [D1, D2, D3, D4]; (*compound list of multiple dates*)
val LOM1 = [] (*empty list of months*)
val LOM2 = [2,3,9, 11] (*non empty list of nos*)
val LOM3 = [2,3,4] (*non empty list of nos but none overlaps with LOD2*)
val LOS1 = [] (*empty list of strings*)
val LOS2 = ["shubham" , "sharvil", "survi"] (*non empty list of strings*)
val LOMS = ["January", "February", "March" , "April" , "May", "June", "July", "August", "September", "October", "November", "December"] (*list of months in string format*)
val LOI1 = [] (*empty list to test problem 8*)
val LOI2 = [5,6,4,8,9] (* to test problem 8*)
val LOMSTRING = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31] (* days in each month for 12 months, relevant for problem 12*)


(*----------------------------------------------------------------------------*)
(*HELPER FUNCTIONS*)
(*Helper Function to convert a list of dates to string so it can be printed as pass or fail*)
fun dateListToString lst =
  "[" ^ String.concatWith ", "
         (List.map (fn (y, m, d) => "(" ^ Int.toString y ^ ", " ^ Int.toString m ^ ", " ^ Int.toString d ^ ")") lst)  ^ "]";


(*Helper Function to convert a list of ints to string so it can be printed as pass or fail*)
fun intListToString lst =
  "[" ^ String.concatWith ", " (List.map Int.toString lst) ^ "]";

	       
(*Implementing the check-expect function to test our functions*)
(* Signature -> String, Function with argument, Expected Value -> String*)
(*defining check-expect function which takes in a string (test name), and a value expected from a function and param, and the expected value, and prints pass or fail *)

fun check_expect (name: string, actual: ''a, expected: ''a, toStr: ''a -> string) =
  if actual = expected then
    print (name ^ " passed\n")
  else
    print (name ^ " failed: expected " ^ toStr expected ^ ", got " ^ toStr actual ^ "\n");


(*----------------------------------------------------------------------------*)
(* DATA DEFINITION*)
(*Date is int*int*int*)
(* interp. as year, month, day*)
(* -- year is natural [0, 3000), so only for AD *)
(* -- month is natural [1,12]*)
(* -- day is natural [1,31] *)
(* Implemented as tuple*)

(* Template *)

(*
fun fn-for-date (date_type : int* int*int) =
    (....#1 date_type) ; represents year
    (....#2 date_type) ; represents month
    (....#3 date_type) ; repesents day*)

(* List of Date is one of*)
(* --Empty*)
(* --Date:: Listof Date*)

(* Template*)

(*
fun fn-for-lod (lod : (int*int*int) list) =
    if null = lod
    then (...)
    else (.... (hd lod) (fn-for-lod (tl lod))) *)

(*----------------------------------------------------------------------------*)
(*FUNCTION DEFINTIONs*)

(* Problem 1 - IS_OLDER*)
(* Signature-> Date, Date -> Boolean*)
(* Produces true if Date 1 is < Date 2 mathematically*)

(* defining the stub*)
(*fun is_older (d1: (int*int*int), d2: (int*int*int)) = false;*)


(*defining the actual function*)
(*template from Date*)

fun is_older (d1: (int*int*int), d2: (int*int*int))=
    if (#1 d1) < (#1 d2) (* case when year 1 is less than year 2*)
    then true
    else if (#1 d1) > (#1 d2) (* case when year 2 is less than year 1*)
    then false
    else if (#2 d1) < (#2 d2) (* case when both years same, but month is less for 1*)
    then true
    else if (#2 d1) > (#2 d2) (* case when both years same, but month is less for 2*)
    then false
    else if (#3 d1) < (#3 d2) (* case when both years same,both months same, but date is older for 1*)
    then true
    else if (#3 d1) > (#3 d2) (* case when both years same, both months same, but date is older for 2*)
    then false
    else false; (* case when both dates are same*)


(*tests for the function *)
check_expect("Isolder_Tes1", is_older(D1, D2), true, Bool.toString) (*case when Date1 is older*);
check_expect("Isolder_Tes2", is_older(D3, D2), false, Bool.toString) (*case when Date2 is older*);
check_expect("Isolder_Tes3", is_older(D3, D3), false, Bool.toString) (*case when Date2 is same as Date 1 is olde and we say Date 1 is older*);


(*----------------------------------------------------------------------------*)
(* Problem 2 - NUMBER_IN_MONTH*)
(* Signature-> ListofDate, Int -> int*)
(* Produces count of no. of dates for which month mathces the month passed in param*)

(* defining the stub*)
(*fun number_in_month (d1: (int*int*int) list, d2: int) = 0 *)

(*defining the actual function*);
(*template from ListofDate*);

fun number_in_month (lod: (int*int*int) list, m: int) =
    if null lod  (*base case of empty list of dates producing 0*)
    then 0
    else if (#2 (hd lod)) = m
    then (1 + number_in_month((tl lod), m)) (* case when match is found*)
    else number_in_month((tl lod), m); (*case when match is not found*)
(*tests for the function*)

check_expect("Number_in_month_test1", number_in_month(LOD1,2), 0, Int.toString); (*case when Date list is empty and should return 0*)
check_expect("Number_in_month_test2", number_in_month(LOD2,2), 0, Int.toString); (*case when Date list is non empty but no match found*)
check_expect("Number_in_month_test3", number_in_month(LOD2,9), 2, Int.toString); (*case when Date list non empty and some months match with passed param*)


(*----------------------------------------------------------------------------*)
(* Problem 3 - NUMBER_IN_MONTHS*)
(* Signature-> ListofDate, Listof Int -> int*)
(* Produces count of no. of dates for which month mathces any of the months passed in the list of months as param*)
(* Two on-of problem*)
(* -- when either list empty will produce 0*)

(* defining the stub*)
(*fun number_in_months (d1: (int*int*int) list, d2: int list) = 0 ; *)

(*defining the actual function*);
(*template from ListofDate*);


fun number_in_months (lod: (int*int*int) list, lom: int list) =
    if null lod orelse null lom (*base case of either empty list of months, or empty list of dates*)
    then 0
    else (number_in_month (lod, (hd lom)) + number_in_months (lod, (tl lom))); (* in other cases we add the result for 1st element in LOM, to the recursive call*)


	
(*tests for the function*)

check_expect("Number_in_months_test1", number_in_months(LOD1,LOM1), 0, Int.toString); (*case when both date list and month list empty*)
check_expect("Number_in_months_test2", number_in_months(LOD1,LOM2), 0, Int.toString); (*case when only date list is empty*)
check_expect("Number_in_months_test3", number_in_months(LOD2,LOM1), 0, Int.toString); (*case when only months list is empty*)
check_expect("Number_in_months_test4", number_in_months(LOD2,LOM3), 0, Int.toString); (*case when both non empty but there is no match*)
check_expect("Number_in_months_test3", number_in_months(LOD2,LOM2), 3, Int.toString); (*case when both Date and monhths list non empty and some months match with passed param*)



(*----------------------------------------------------------------------------*)
(* Problem 4 - DATES_IN_MONTH*)
(* Signature-> ListofDate, Listof In Intt -> List of Dates*)
(* Produces list of all dates for which we find a match in month for the month param passed *)
(* should return list in order in which it was originally aligned*)

(* defining the stub*)
(*fun number_in_months (d1: (int*int*int) list, d2: int) = [] ;*) 

(*defining the actual function*);
(*template from ListofDate*);


fun dates_in_month (lod: (int*int*int) list, m:int) =
    if null lod  (* base case when empty list will return an empty list *)
    then []
    else if  (#2 (hd lod)) = m 
    then (hd lod)::dates_in_month((tl lod), m) (* if there is a match in month passed , then we cons to the recursive call, else call as is the recursive call*)
    else dates_in_month((tl lod), m);


	
(*tests for the function*)

check_expect("dates_in_month_test1", dates_in_month(LOD1,2), [], dateListToString); (*case when an empty date list is passed *)
check_expect("dates_in_month_test2", dates_in_month(LOD2,2), [], dateListToString); (*case when non empty list is passed but there is no match found*)
check_expect("dates_in_month_test3", dates_in_month(LOD2,9), [D1,D3], dateListToString); (*case when there are partial matches found in the list of dates*)


(*----------------------------------------------------------------------------*)
(* Problem 5 - DATES_IN_MONTHS*)
(* Signature-> ListofDate, Listof Int -> List of Dates*)
(* Produces list of all dates for which we find a match for any  month in the list of months passed as param *)
(* should return list in order in which it was originally aligned*)
(* Two on-of problem*)
(* -- when either list empty will produce 0*)

(* defining the stub*)
(*fun dates_in_months (d1: (int*int*int) list, d2: int list) = [] *)

								  
(*defining the actual function*);
(*template from ListofDate*);


fun dates_in_months (lod: (int*int*int) list, lom: int list) =
    if null lod orelse null lom (*two one of cross section case when either list is empty we return an empty list *)
    then []
    else dates_in_month(lod, (hd lom)) @ dates_in_months(lod, (tl lom)); (*we append the result of dates_in_month which returns a list to the recursive call*)


	
(*tests for the function*)

check_expect("dates_in_months_test1", dates_in_months(LOD1,LOM1), [], dateListToString); (*case when both list of dates and list of months are empty*)
check_expect("dates_in_months_test2", dates_in_months(LOD1,LOM2), [], dateListToString); (*case when list of date empty but list of month non empty*)
check_expect("dates_in_months_test3", dates_in_months(LOD2,LOM1), [], dateListToString); (*case when list of months empty but LoD non empty*)
check_expect("dates_in_months_test4", dates_in_months(LOD2,LOM3), [], dateListToString); (*case when both LoM and LoD non empty but no match found*)
check_expect("dates_in_months_test5", dates_in_months(LOD2,LOM2), [D1,D3, D2], dateListToString); (*case when there are partial matches found in the list of dates and list of months*)

			
			
(*----------------------------------------------------------------------------*)
(* Problem 6 - GET_NTH*)
(* Signature-> ListofString, Int -> String*)
(* Takes in an integer and produces the nth element of a list based on the integer param passed*)
(* Assume n <= length of the list *)

(* defining the stub*)
(* fun get_nth (d1: string list, d2: int) = "" ; *)

(*defining the actual function*);
(*template from ListofString*);


fun get_nth (lod:string list, i:int) =
    let fun get_nth0 (lod: string list, i: int, acc: int) = (* acc is a context preserving accumulator*)
	if null lod (* base case when empty list, then we return an empty string*)
	then ""
	else if acc = i then (hd lod)
        else get_nth0((tl lod), i , acc+1)
    in get_nth0(lod, i, 1) end;


	
(*tests for the function*)

check_expect("get_nth_test1", get_nth(LOS1,2), "", (fn x => x)); (*case when an empty date list is passed hten we return an empty string *)
check_expect("get_nth_test2", get_nth(LOS2,1), "shubham", (fn x => x)); (*case when we check for 1st element of the list *)
check_expect("get_nth_test3", get_nth(LOS2,2),"sharvil" ,(fn x => x)); (*case when we check for middle element of the list *)
check_expect("get_nth_test4", get_nth(LOS2,3),"survi" ,(fn x => x)); (*case for last element of thelist *)
			  



(*----------------------------------------------------------------------------*)
(* Problem 7 - DATE_TO_STRING*)
(* Signature-> Date  -> String*)
(* Takes in a date and returns string version of the date in form of <Month><space><Date><comma><space><Year> *)

(* defining the stub*)
(*fun date_to_string (d1: (int*int*int)) = ""; *)

(*defining the actual function*);
(*template from Date*);


fun date_to_string (d: (int*int*int)) =
    get_nth(LOMS, (#2 d)) ^ " " ^ Int.toString(#3 d) ^ "," ^ " " ^ Int.toString(#1 d); (*using the ge_nth function to capture the string of month and then concatenating to relevant fields*)
    
	
(*tests for the function*)

check_expect("date_to_string_test1", date_to_string D1, "September 25, 1989", (fn x => x)); (* No base case here as no recursion, just normal concatenation*)
check_expect("date_to_string_test2", date_to_string D2, "November 18, 1989", (fn x => x)); (*case when we check for 1st element of the list *)

(*----------------------------------------------------------------------------*)
(* Problem 8 - NUMBER_BEFORE_REACHING_SUM*)
(* Signature-> Listof Integers, Int  -> Int *)
(* Takes in a list of number and a number, and gives the index of the list where sum of n elements < param passed  < sum of n+1 elements*)
(* index to be counted from 0*)

(* defining the stub*)

(*fun number_before_reaching_sum (d1: int list, d2: int) = 0;*)

(*defining the actual function*);
(*template from List of Ints*);


fun number_before_reaching_sum (loi: int list, sum: int) =
    let
	fun number_before_reaching_sum0 (loi: int list, sum : int, acc_index : int , acc_sum:int)= (* using 2 accumulators here , one for keeping track of index, another for keeping track off sum *)
	    if null loi
	    then acc_index
	    else if acc_sum < sum andalso (acc_sum + (hd loi)) >= sum (*check if condition is achieved already*)
	    then acc_index
	    else number_before_reaching_sum0(tl loi, sum , acc_index+1, acc_sum+(hd loi)) (* if not pass relevant values in accumulators*)
    in number_before_reaching_sum0 (loi, sum, 0,0) end;
					
(*tests for the function*)

check_expect("number_before_reaching_sum_test1", number_before_reaching_sum(LOI1, 24), 0, Int.toString); (*base case when we pass an empty string*)
check_expect("number_before_reaching_sum_test2", number_before_reaching_sum(LOI2,6),1, Int.toString); (*compound case when when n is in beginning of the list*)
check_expect("number_before_reaching_sum_test3", number_before_reaching_sum(LOI2,12),2, Int.toString); (*compound case when when n is in the middle of the list*)

(*----------------------------------------------------------------------------*)
(* Problem 9 - WHAT_MONTH*)
(* Signature-> Int -> Int *)
(* Takes in a day b/w 1 and 365 and gives back which month does the day belong to*)
(* index to be counted from 1*)

(* defining the stub*)

(*fun what_month (d1: int ) = 0; *)

(*defining the actual function*);
(*template from List of Ints*);


fun what_month (num: int) =
    number_before_reaching_sum(LOMSTRING, num) + 1; 
    
 
					
(*tests for the function*)

check_expect("what_month_test1", what_month(15), 1, Int.toString); (*1st case from beginning of the year*)
check_expect("what_month_test2", what_month(45),2, Int.toString); (*2nd case from the middle of the year*)
check_expect("what_month_test3", what_month(362),12, Int.toString); (*3rd case when day is in last month*)


(*----------------------------------------------------------------------------*)
(* Problem 10 - MONTH_RANGE*)
(* Signature-> Int, Int -> List of Ints*)
(* Takes in two days (b/w 1 and 365) and returns a list of all the months for all the days from day1 > day2, so the list will have day2-day1+1 elements*)	

(* defining the stub*)

(*fun month_range(d1: int, d2: int) = []; *)

(*defining the actual function*);
(*template from List of Ints*);


fun month_range (day1: int, day2: int) =
    if day1 > day2 then []
    else  number_before_reaching_sum(LOMSTRING, day1) + 1:: month_range(day1 + 1, day2);
    
					
(*tests for the function*)

check_expect("month_range_test1", month_range(29, 32), [1,1,1,2], intListToString); (* case of date range with multiple months*)

(*----------------------------------------------------------------------------*)
(* Problem 11 - oldest*)
(* Signature-> Listof Dates -> Option*)
(* Takes in a list of dates and returns option (None if it is an empty list, Some date for the oldest date in the list*)

(* defining the stub*)

(*fun month_range(d1: int, d2: int) = []; *)

(*defining the actual function*);
(*template from List of Ints*);


fun oldest (lod: (int*int*int) list) =
    
    let fun get_nth0 (lod: int list, i: int, acc: int) = (* acc is a context preserving accumulator*)
		if null lod (* base case when empty list, then we return 0*)
		then 0
		else if acc = i then (hd lod)
		else get_nth0((tl lod), i , acc+1)
			     
	fun days_till_month (month: int, acc_month: int, acc_days: int) = (* creating a helper function here to get the no. of days till the current month , for eg 31 if month is feb *)
	    if month-1 = acc_month then acc_days
            else days_till_month (month , acc_month+1 , acc_days + get_nth0(LOMSTRING, acc_month+1, 0));

        fun date_to_int (d: (int*int*int)) =
	    (#1 d)*365 + days_till_month((#2 d), 0,0) + (#3 d);

	fun oldest0 (lod: (int*int*int) list) =
	    if null lod then NONE
	    else
		let
		    val rest: (int * int * int) option = oldest0 (tl lod)
		    val current: (int * int * int) = hd lod
		in
		    if rest = NONE then
			SOME current
		    else
			let
			    val SOME r = rest
			in
			    if date_to_int current < date_to_int r then SOME current else SOME r
			end
		end
    in oldest0(lod) end;

		    
	    
					
(*No tests for this, as Option cant be printed usint the check_expect we implemented)


			  
	    


		 
    








	    



