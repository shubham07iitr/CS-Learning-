(*------------------------------------------*)
(*------------------------------------------*)
(* CONSTANTS *)

exception IllegalMove ;

val LOI1 = [2,6,3,4,1]; (*random test case *)
val LOC0 = []; (* empty list of cards *)
val LOS0 = []; (*empty list of strings relevant for problem 1 in substitution strings *)
val LOS1 = ["shubham", "sharvil", "survi"] ; (* non empty list of strings relevant for problem 1 in SS strings *)
val LOLOS0 = [[]] (* empty list of list of strings  for problem 2 in SS strings*)
val LOLOS1 = [["Fred","Fredrick"],["Elizabeth","Betty"],["Freddie","Fred","F"]] (* List of List of strings, useful for problem 2 in SS strings *)
val LOLOS2 = [["Fred","Fredrick"],["Jeff","Jeffrey"],["Geoff","Jeff","Jeffrey"]]; (* List of List of strings, useful for problem 2 in SS strings *)
val full_name1 = {first="Fred", middle="W", last="Smith"} ; (* full name useful for Problem 4 in SS strings *)
val full_name2  = {first="Jeff", middle="", last="Anderson"}; (* full name useful for Problem 4 in SS strings *)
val list_full_names1 = [{first="Fredrick", last="Smith", middle="W"},  {first="Freddie", last="Smith", middle="W"},  {first="F", last="Smith", middle="W"}, {first="Fred", last="Smith", middle="W"}]; (* return value from problem 4 of SS strings *)
val list_full_names2 = [{first="Jeffrey", middle="", last="Anderson"}, {first="Geoff", middle="", last="Anderson"}, {first="Jeffrey", middle="", last="Anderson"}, {first="Jeff", middle="", last="Anderson"}]; (*return value from problem 4 of SS strings *)
			   
		 

(* Other key Constants defined in Data definitions, picked here for ease of use
(* Suits *)
val suit_C = Clubs;
val suit_D = Diamonds;
val suit_H = Hearts;
val suit_S = Spades;

(* Ranks *)
val rank_J = Jack;
val rank_Q = Queen;
val rank_K = King;
val rank_A = Ace;
val rank_Num = Num 1;

(* Colors*)
val color_R = Red;
val color_B = Black;

(* Cards *)
val card_H_Q = (Hearts, Queen);
val card_S_J = (Spades, Jack);
val card_S_5 = (Spades, Num 5);
val card_D_A = (Diamonds, Ace);
val LOC1 = [card_H_Q, card_S_J, card_S_5, card_D_A];
val LOC2 = [card_H_Q, card_S_J, card_S_5, card_S_5, card_D_A];
val LOC3 = [card_H_Q, card_D_A]; (* useful for problem 4 in cards problem set *)

(* Move*)
val move_Dis = Discard card_H_Q;
val move_Draw = Draw;
*)
		      
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

(* Function STRING_SAME *)
(* Signature -> String, String -> bool *)
(* checks if 2 strings are exactly same, returns true if it is *)

fun same_string(s1 : string, s2 : string) =
    s1 = s2;

(*------------------------------------------*)
(*------------------------------------------*)
(* DATA DEFINITIONS *)

(* DATA SUIT *)
datatype suit = Clubs | Diamonds | Hearts | Spades ;
(* suit is one of: *)
(* -- Clubs *)
(* -- Diamonds *)
(* -- Hearts *)
(* -- Spades *)
(* Interp. as suit of cards in a deck of cards *)
(* Examples below *)
val suit_C = Clubs;
val suit_D = Diamonds;
val suit_H = Hearts;
val suit_S = Spades;

(* Template for enumeration *)
(*
fun fn-for-suits s =
    case s of
         Clubs => (...)
       | Diamonds  =>  (...)
       | Hearts  =>  (...)
       | Spades  =>  (...)
*)						

(*------------------------------------------*)
(* DATA RANK *)
datatype rank = Jack | Queen | King | Ace | Num of int;
(* rank is one of: *)
(* -- Jack *)
(* -- Queen *)
(* -- King *)
(* -- Ace *)
(* -- Natural [1,10) *)
(* Interp. as rank of a card in a deck of cards *)
(* Examples below *)
val rank_J = Jack;
val rank_Q = Queen;
val rank_K = King;
val card_A = Ace;
val card_Num = Num 1;

(* Template for enumeration *)
(*
fun fn-for-rank r =
    case r of
         Jack => (...)
       | Queen  =>  (...)
       | King  =>  (...)
       | Ace  =>  (...)
       | Num x  =>  (...x)
*)			

(*------------------------------------------*)
(* DATA COLOR *)
datatype color = Red | Black ;
(* color is one of: *)
(* -- Red *)
(* -- Black *)
(* Interp. as color of a card in a regular deck of cards *)
(* Examples below *)
val color_R = Red;
val color_B = Black;

(* Template for enumeration *)
(*
fun fn-for-color c =
    case c of
         Red => (...)
       | Black  =>  (...)
*)			

(*------------------------------------------*)
(* DATA CARD *)
type card = suit * rank;
(* card is type synonym which uniquely identifies a card in a deck of cards *)

val card_H_Q = (Hearts, Queen);
val card_S_J = (Spades, Jack);
val card_S_5 = (Spades, Num 5);
val card_D_A = (Diamonds, Ace);
val LOC1 = [card_H_Q, card_S_J, card_S_5, card_D_A];
val LOC2 = [card_H_Q, card_S_J, card_S_5, card_S_5, card_D_A];
val LOC3 = [card_H_Q, card_D_A];

(* Template for card *)

(*
fun fn-for-card c
    case c of
         (Heart, Jack) => (...)
       | (Spades, Queen) => (...)
       | ....  => (...) *)

(*------------------------------------------*)
(* DATA MOVE *)
datatype move = Discard of card | Draw ;
(* move is one of: *)
(* -- Discard of type card *)
(* -- Draw *)
(* Interp. as different types of moves in a game of blackjack *)
(* Examples below *)
val move_Dis = Discard card_H_Q;
val move_Draw = Draw;

(* Template for enumeration *)
(*
fun fn-for-move m =
    case m of
         Discard c => (...fn-for-card c)
       | Draw  =>  (...)
*)	


(*------------------------------------------*)
(*------------------------------------------*)
(* FUNCTIONS CARD GAME*)
(* Problem 1 : CARD_COLOR *)
(* Signature , Card -> Color *)
(* Takes in a card and returns its color *)

(* defining the stub *)
(*fun card_color (c: card) = Red; *)

(*template from card *)


fun card_color (c: card) =
    case c of
	(Spades, _) => Black (* spades and clubs are black, any other is red*)
      | (Clubs, _) => Black
      | _ => Red;

(* writing test cases *)
check_expect("card_color_test1", card_color card_H_Q , Red); (* case of card color is red *)
check_expect("card_color_test2", card_color card_S_J , Black); (* case of card color being black *)

(*------------------------------------------*)
(* Problem 2 : CARD_VALUE *)
(* Signature , Card -> Integer *)
(* Takes in a card and returns its Value *)
(* Num cards have their num as value, Aces are 11, everything else is 10 *)

(* defining the stub *)
(*fun card_value (c: card) = 0; *)

(*template from card *)


fun card_value (c: card) =
    case c of
	(_, Ace) => 11
      | (_, Num x) => x
      | _ => 10; 

(* writing test cases *)
check_expect("card_value_test1", card_value card_H_Q , 10); (* case of face card being evaluated *)
check_expect("card_value_test2", card_value card_S_5 , 5); (* case of a numbered card being evaluaed *)
check_expect("card_value_test3", card_value card_D_A , 11); (* case of an ace being evaluated *)


(*------------------------------------------*)
(* Problem 3 : REMOVE_CARD *)
(* Signature , ListofCard, Card, Exception -> ListofCard *)
(* Takes in a list of card, and removes a given card (passed as param) from the list - only the first one is removed - raises an exception if card is not found *)
(* raise exception if list is empty as well *)

(* defining the stub *)
(*fun remove_card (loc: card list, c: card, e:exn) = loc; *)

(*template from list_of_card *)
(*
fun fn-for-loc loc =
    case loc of
         [] => (...)
       | card::list_of_cards  => (...card) (list_of_cards)
*)


fun remove_card (loc: card list, c: card, e:exn) =
    case loc of
	[] => raise e (* case of empty list, we will raise exception, because we did not find the card *)
      | card :: smaller_list => if card = c
				then smaller_list (* if first element is match, then we just return the remaining smaller list, or else we cons head to the output of the recursive call*)
			        else card :: remove_card (smaller_list, c, e);
				

(* writing test cases *)
(* check_expect("remove_card_test1", remove_card (LOC0, card_S_5, IllegalMove), []); (* base case of an empty list will return an empty list, but can't be tested *)  *)
check_expect("remove_card_test2", remove_card (LOC1, card_S_5, IllegalMove),[card_H_Q, card_S_J, card_D_A]); (* case when we do get a card match *)
check_expect("remove_card_test3", remove_card (LOC2, card_S_5, IllegalMove),LOC1); (* case when we do get a card match and there are multiple mtches but only 1st one is removed*)



(*------------------------------------------*)
(* Problem 4 : ALL_SAME_COLOR *)
(* Signature , ListofCard -> Bool *)
(* Takes in a list of card, and returns true if all the cards have the same color *)
(* returns true for an empty list *)

(* defining the stub *)
(*fun all_same_color (loc: card list) = false ; *)

(*template from list_of_card *)
(*
fun fn-for-loc loc =
    case loc of
         [] => (...)
       | card::list_of_cards  => (...card) (list_of_cards)
*)


fun all_same_color (loc: card list) =
    case loc of
	[] => true (* case of empty list we define will return true *)
      | first_card :: [] => true (*case when there is only a single element in the list*)
      | first_card :: second_card ::  smaller_list => if card_color (first_card) = card_color (second_card)  (* for multiple elements we check if color o 1st is same as 2nd and then call the function recursively*)
						      then all_same_color (second_card :: smaller_list)
						      else false ;
				

(* writing test cases *)
check_expect("all_same_color_test1", all_same_color (LOC0), true); (* base case of an empty list will return true*)
check_expect("all_same_color_test2", all_same_color (LOC1),false); (* case of non empty list where not all card is of same color *)
check_expect("all_same_color_test3", all_same_color (LOC3), true); (* case of non empty list where all cards of same colro *)




(*------------------------------------------*)
(* Problem 5 : SUM_CARDS *)
(* Signature , ListofCard -> Int *)
(* Takes in a list of card, and returns sum of values of all individual cards *)
(* returns 0 for an empty list *)

(* defining the stub *)

(*fun sum_cards (loc: card list) = 0 ;*)

(*template from list_of_card *)
(*
fun fn-for-loc loc =
    case loc of
         [] => (...)
       | card::list_of_cards  => (...card) (list_of_cards)
*)


fun sum_cards (loc: card list) =
    let fun sum_cards0 (loc, acc) = (* we use an accumualtor to make the function tail recursive *)
	    case loc of
		[] => acc (* case of empty list willr eturn value of accumulator *)
	      | first_card::smaller_list => sum_cards0 (smaller_list, acc + card_value (first_card))
    in sum_cards0(loc, 0) end;


(* writing test cases *)
check_expect("sum_cards_test1", sum_cards (LOC0), 0); (* base case of an empty list will return true*)
check_expect("sum_cards_test2", sum_cards (LOC1),36); (* case of non empty list where we add up values of each card *)



(*------------------------------------------*)
(* Problem 6 : SCORE *)
(* Signature , ListofCard, Int -> Int *)
(* Takes in a list of card, and a goal, and returns the score as per below rules *)
(* -- if sum of all cards > goal , prelim score = sum cards - goal *)
(* -- if sum of all cards <= goal , prelim score = goal - sum cards *)
(* -- final score = prelim score/2 if all same color , else prelim score *)

(* defining the stub *)

(*fun score (loc: card list, goal: int) = 0 ;*)

(*template from list_of_card *)
(*
fun fn-for-loc loc =
    case loc of
         [] => (...)
       | card::list_of_cards  => (...card) (list_of_cards)
*)

fun score (loc: card list, goal: int) =
    
    let val sum_cards = sum_cards(loc)
	val prelim_score = if sum_cards > goal
			   then sum_cards - goal
			   else goal - sum_cards
    in if all_same_color (loc)
       then prelim_score div 2
       else prelim_score end;
   

(* writing test cases *)
check_expect("score_test1", score (LOC0, 10 ), 5); (* base case of an empty list will amount to all same color and hence (goal - sum )/2 *)
check_expect("score_test2", score (LOC1, 10),26); (* case of non empty list where all cards are not of same color *)
check_expect("score_test3", score (LOC3, 10),5); (* case of non empty list where all cards are of the same color and result is sum of (cards - goal)/2 *)


(*------------------------------------------*)
(* Problem 7 : OFFICIATE *)
(* Signature , ListofCard, List of Moves, Goal:Int -> Score:Int *)
(* Takes in a card list, and a move list, and the goal target, and returns the score once the game has ended as per below rules *)
(* -- start with held-cards being empty list - make it an accumulator in helper functinon *)
(* --if no more moves or list of moves is empty then calculate and return score *)
(* -- if move is discard card c, remove from held_cards or raise exception if not found and recurse *)
(* -- If move is draw and card list is empty , return score *)
(* -- if move is draw and card_list non empty , and sum of cards >= goal , return score (including the new card) *)
(* -- if move is draw and card list is non empty and sum of cards < goal , recurse with adding card to held_cards, and removing card from smaller_card_list *)

(* defining the stub *)

(*fun officiate (loc: card list, lom: move list, goal: int) = 0 ; *)

(*template from list_of_card *)
(*
fun fn-for-loc loc =
    case loc of
         [] => (...)
       | card::list_of_cards  => (...card) (list_of_cards)
*)

fun officiate (loc: card list, lom: move list , goal: int) =
    let fun helper (loc: card list, lom: move list,lohc: card list ,goal: int) = (* we define a context preserving acumualtor to keep track of the held cards *)
	    case (loc, lom, lohc, goal) of
		(_, [], _ , _) => score(lohc, goal)
	      | ( _, Discard c::list_of_moves, _, _)   => helper(loc, list_of_moves, remove_card (lohc, c, IllegalMove), goal)
              | ( [], Draw::list_of_moves, _, _)   => score(lohc, goal)
              | (first_card::list_of_cards , Draw::list_of_moves, lohc, goal) => if sum_cards(first_card::lohc) >= goal
										then score (first_card::lohc, goal)
										else helper (list_of_cards, list_of_moves, first_card::lohc, goal)
    in helper (loc, lom, [], goal) end;

   

(* we will remove test cases from this situation because it is difficult write test cases *)

(*------------------------------------------*)
(*------------------------------------------*)
(* FUNCTIONS STRING SUBSTITUTION*)
(* Problem 1 : ALL_EXCEPT_OPTION *)
(* Signature , List of String, String -> List of String Option *)
(* Takes in a LOS and a S, and drops that string from the list of string and returns the new list as an option, reutrns None if no string match is found *)

(* defining the stub *)
(* fun all_except_option (los, s) = NONE ;  *)

(*template from List of Elements *)

(*
fun fn-for-los los =
    case los of
         [] => (...)
       | first_string::list_of_strings  => (...first_string) (list_of_strings)
*)

fun all_except_option (los: string list, s: string) =
    case los of
	[]  => NONE (* for an empty list we reutrn NONE *)
      | first_string :: []   => NONE
      | first_string :: smaller_list => if same_string (first_string , s)
					then SOME smaller_list
					else case all_except_option(smaller_list, s) of
						 NONE => NONE
					       | SOME rest => SOME (first_string :: rest);


(* writing test cases *)
check_expect("all_except_option_test1", all_except_option(LOS0, "shubham") , NONE); (* case of empty list will return NONE *)
check_expect("all_except_option_test2", all_except_option (LOS1, "mummy"), NONE); (* non empty list but string not in the list, will return NONE *) 
check_expect("all_except_option_test3", all_except_option (LOS1, "shubham"), SOME ["sharvil", "survi"]); (* non empty list and we do find a match *)



(*------------------------------------------*)
(* Problem 2 : GET_SUBSTITUTIONS1 *)
(* Signature , List of List of String, String -> List of String*)
(* Takes in a list of list of strings, and a single string, and if a match is found in sub-list , then capture every element of sub-list except the match appended over the remaining sub-lists recursed*)

(* defining the stub *)
(*fun get_substitutions1 (lolos: string list list, s) = [] ; *)

(*template from List of Elements *)

(*
fun fn-for-los los =
    case los of
         [] => (...)
       | first_string::list_of_strings  => (...first_string) (list_of_strings)
*)


fun get_substitutions1 (lolos: string list list, s: string) =
    case lolos of
	[] => [] (* single list with single empty list  will return an empty list *)
      | first_list :: list_of_lists =>  case all_except_option (first_list, s) of
					    NONE => get_substitutions1(list_of_lists, s)
					  | SOME rest => rest @ get_substitutions1(list_of_lists, s);


(* writing test cases *)
check_expect("get_substitutions1_test1", get_substitutions1(LOLOS0, "Fred") , []); (* case of empty list of list of strings to produce empty list *)
check_expect("get_substitutions1_test2", get_substitutions1 (LOLOS1, "Fred"), ["Fredrick","Freddie","F"]); (* non empty list but and there are no duplicates in sub-lists*)
check_expect("get_substitutions1_test3", get_substitutions1 (LOLOS2, "Jeff"),["Jeffrey","Geoff","Jeffrey"]); (* non empty list and there are duplicates in sub-lists*)




(*------------------------------------------*)
(* Problem 3 : GET_SUBSTITUTIONS2 *)
(* Signature , List of List of String, String -> List of String*)
(* Same as get_substitutions1 , just will be tail recursive*)

(* defining the stub *)
(*fun get_substitutions1 (lolos: string list list, s) = [] ; *) (* no need to separately test out stub or check_expects *)

(*template from List of Elements *)

(*
fun fn-for-los los =
    case los of
         [] => (...)
       | first_string::list_of_strings  => (...first_string) (list_of_strings)
*)


fun get_substitutions2 (lolos: string list list, s: string) =
    let fun helper (lolos: string list list, s: string, los: string list) = (*using a lsit of string as an accumualtor here to make fn tail recursive *)
	    case lolos of
		[] => los (* single list with single empty list  will return an empty list *)
	      | first_list :: list_of_lists => case all_except_option (first_list, s) of
						   NONE => helper(list_of_lists, s, los)
						 | SOME rest => helper (list_of_lists, s, los @ rest)
    in helper (lolos, s, []) end;


(* writing test cases *)
check_expect("get_substitutions2_test1", get_substitutions2(LOLOS0, "Fred") , []); (* case of empty list of list of strings to produce empty list *)
check_expect("get_substitutions2_test2", get_substitutions2 (LOLOS1, "Fred"),  ["Fredrick","Freddie","F"]); (* non empty list but and there are no duplicates in sub-lists*)
check_expect("get_substitutions2_test3", get_substitutions2 (LOLOS2, "Jeff"), ["Jeffrey","Geoff","Jeffrey"]); (* non empty list and there are duplicates in sub-lists*)


(*------------------------------------------*)
(* Problem 4 : SIMILAR_NAMES *)
(* Signature , List of List of String, Full Name (String * String * String) -> List of Full Names*)
(* For a given full name, produce list of all full_names as produced by list of substitutions *)

(* defining the stub *)
(*fun similar_names (lolos: string list list, name:  {first: string , middle: string, last: string}) = [] ; *)

(*template from List of Elements *)

(*
fun fn-for-los (los, name) =
    case los of
         [] => (...fn-for-name (name))
       | first_list::list_of_lists  => (...first_list ....fn-for-name(name)) (list_of_lists)
*)


fun similar_names (lolos: string list list,  {first= a, middle= b, last= c}) =
    let fun replace_first ({first= x , middle= y, last= z}, new_first: string) =
	   {first=  new_first, middle= y, last= z}
	val list_substitutions = get_substitutions1 (lolos, a) (* we create a list of all substitutions we want to replace the first name with *)
	fun similar_names0 (los: string list, {first= p, middle = q, last = r }) = (* creating a helper function which operates on los with substitute names, and the original record *)
	    case los of
		[] => [{first = p, middle = q, last = r}]
	     |  first_string:: list_strings => replace_first({first= p, middle = q, last = r}, first_string) ::  similar_names0(list_strings, {first= p, middle = q, last = r })
    in similar_names0 (list_substitutions,  {first= a, middle= b, last= c}) end; (* trampolining the helper function with local variable list)_sub*)

	



(* writing test cases *)
check_expect("similar_names_test1", similar_names (LOLOS0, full_name1) , [full_name1]); (* case of empty list of list of strings to produce single name list *)
check_expect("similar_names_test2", similar_names (LOLOS1, full_name1),  list_full_names1); (* non empty list producing multiple entries 1 *)
check_expect("similar_names_test3", similar_names (LOLOS2, full_name2), list_full_names2);  (* non empty list producing multiple entries 2 *)
