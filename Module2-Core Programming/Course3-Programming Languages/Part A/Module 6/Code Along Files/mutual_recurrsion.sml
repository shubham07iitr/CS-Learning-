(* function to check whether we have a list of type 1,2,1,2 not ending with 1 *)

fun match xs =
    let fun s_need_one xs =
	    case xs of
		[] =>
	      | 1 :: x' => s_need_two_xs'
	      | _  =>  false
	and s_need_two xs =
	    case xs of
		[] => false
	      | 2::xs' =>  s_need_one xs'
	      |  _  => false

    in
	s_need_one xs
    end;
