import tester.*;

/* The 3n+1 problem
 *  f(n) = n/2 - when n even
 *       = 3n+1 when n is odd
 *   for any given number if we do f(f(n))....indefinitely we wil always reach 1 
 */


class Util {
	Util() {}
	
	boolean _3nPlus1Problem(int n) {
		while (n > 1) {
			if (n %2 == 0) {n =  n/2;}
			else {n = 3*n+1;}
		}
		return true;	
	}
	
}
