fun fold (f, acc, xs) =
    case xs of
	[] => acc
      | x::xs' => fold (f, f(acc,x), xs');

(* closures not using private data *)
fun f1 xs = fold ((fn (x,y) => x+y), 0 , xs); (*sum list*)

fun f2 xs = fold ((fn (x,y) => x andalso y >= 0), true, xs); (*checks for all elements in list are non negative *)



(* closures using private data *)

(* this function captures count of elements within a list, and notice how it uses lexical scope to capture value of lo and hi inside a local function *)
fun f3 (xs, lo, hi) =
    fold ((fn (x,y) => x + (if y >= lo andalso y <= hi then 1 else 0)), 0, xs);


fun f4(xs, s) =
    let val i = String.size s
    in
	fold((fn (x,y) => x andalso String.size y < i), true , xs);

	 
		      
