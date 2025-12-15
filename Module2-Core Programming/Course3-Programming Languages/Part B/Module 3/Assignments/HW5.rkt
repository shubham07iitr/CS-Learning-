;; Programming Languages, Homework 5

#lang racket
(provide (all-defined-out)) ;; so we can put tests in a second file
(require rackunit) ;; useful for testing functions

;; ======================================================================================================
;; DATA DEFINITIONS for our MUPL

;; definition of structures for MUPL programs - Do NOT change
;; ------------------------------------------------------------------------------------------------------
;; VAR Definition
(struct var  (string) #:transparent)  ;; a variable, e.g., (var "foo")
;; TypeComment - var is of type (string String)
;; -- string is of type string in Racket
;; Interp as a string which maps to an expression in our MUPL, like (Define foo 5) in Racket

;; Example
(define MVAR1 (var "test-var")) ;; just some random string ecample
(define MVAR2 (var "some-other-test-var")) ;; another random string example

;; Template
#;
(define (fn-for-var v)
  (... var-string v))

;; ------------------------------------------------------------------------------------------------------
;; INT Definition
(struct int  (num) #:transparent)  ;; a constant number, e.g., (int 17)
;; TypeComment - int is of type (num Int)
;; -- num is of type int in Racket
;; Interp as a integers in our MUPL

;; Example
(define MINT1 (int 1))
(define MINT2 (int 2))

;; Template
#;
(define (fn-for- i)
  (... var-num i))

;; ------------------------------------------------------------------------------------------------------
;; ADD Definition
(struct add  (e1 e2)  #:transparent)  ;; add two expressions
;; Add is of type (e1 Exp e2 Exp):
;; -- e1 and e2 are both expressions which means they are one of
;; var, int, cosure, aunit, pair, or some other construct in the MUPL
;; Interp. as the add function which
;; -- if both e1 and e2 evaluate to ints, will return the sum of the ints, else will return an error
;; interp. as the addition of two expressions in our MUPL

;;Examples
(define MADD1 (add (int 3) (int 4)))
(define MADD2 (add (var 3) (int 5))) ;; will return an error


;; Template
#;
(define (fn-for-add a)
  (... add-e1)
  (... add-e2))


;; ------------------------------------------------------------------------------------------------------
;; IFGREATER Definition
(struct ifgreater (e1 e2 e3 e4)    #:transparent) ;; if e1 > e2 then e3 else e4

;; Ifgreater is of type (e1 Exp e3 Exp e3 Exp e4 Exp)
;; -- e1 and e2 are both expressions which means they are one of
;; var, int, cosure, aunit, pair or some other construct in our MUPL
;; Interp. as the ifgreater function which
;; -- if both e1 and e2 evaluate to ints and e1 > e2 returns e3 else returns e4

;;Examples
(define MIFGREATER1 (ifgreater (int 3) (int 4) (int 3) (int 4)))
(define MIFGREATER2 (ifgreater (int 4) (int 3) (int 3) (int 4)))

;; Template
#;
(define (fn-for-ifgreater if)
  (... ifgreater-e1)
  (... ifgreater-e2))


;; ------------------------------------------------------------------------------------------------------
;; FUN Definition
(struct fun  (nameopt formal body) #:transparent) ;; a recursive(?) 1-argument function

;; TypeComment - fun is of type (nameopt-String, formal-String, body - Exp)
;; -- nameopt is Racket string representing the name of the function but is optional
;; -- formal is the argument string passed in function
;; -- body is the Expression or syntax for the function in MUPL 
;; Interp. as a function in our MUPL which has 3 pieces, name of function, arguments it takes, and the body of the function

;; Examples

(define MFUN1 (fun "test-fun" "test-arg" (add (int 3) (int 4))))

;; Template
#;
(define (fn-for-fun fun)
  (... fun-nameopt fun)
  (... fun-formal fun)
  (... fun-body fun))


;; ------------------------------------------------------------------------------------------------------
;; CLOSURE Definition
;; a closure is not in "source" programs but /is/ a MUPL value; it is what functions evaluate to
(struct closure (env fun) #:transparent)

;; Closure is of type (List of Pairs, Fun)
;; - Env refers to list of pairs for new funcs/variables defined in our MUPL
;; - Fun refers to the struct fun in our MUPL

;; Examples

(define MCLOSURE (closure null MFUN1)) ; defining a closure

;; Template
#;
(define (fn-for-closure c)
  (... closure-env c)
  (... closure-fun fun))


;; ------------------------------------------------------------------------------------------------------
;; CALL Definition
(struct call (funexp actual)       #:transparent) ;; function call

;; TypeComment - Call is of type (funexp-Closure, actual - Exp)
;; -- Funexp is a closure containing a function and an env
;; -- Actual is the param value to be passed to the function argument
;; Interp. as a call to the function with some value of argument

;; Examples

(define MCALL (call MCLOSURE (int 3))) ; just defining a random call function
;; can't define a call variable w/o defining a closure first 

;; Template
#;
(define (fn-for-call c)
  (... call-funexp c)
  (... call-actual c))
  

;; ------------------------------------------------------------------------------------------------------
;; MLET Definition
(struct mlet (var e body) #:transparent) ;; a local binding (let var = e in body)

;; TypeComment - Mlet is of type (var-Racket String, e-Exp - body-Exp)
;; -- var is a string which will be bound with e 
;; -- Body is the expression which will be evaluated based on MUPL syntax
;; Interp. as a sub-function definiton inside another function 

;; Examples

(define MLET1 (mlet "x" (add (int 3) (int 4)) (add (int 3) (int 4) ))) ; just defining a random mlet function


;; Template
#;
(define (fn-for-mlet m)
  (... mlet-var c)
  (... mlet-e c)
  (... mlet-body c))

;; ------------------------------------------------------------------------------------------------------
;; APAIR Definition
(struct apair (e1 e2)     #:transparent) ;; make a new pair

;; apair is of type (e1 Exp e2 Exp)
;; -- e1 and e2 are both expressions which means they are one of
;; var, int, cosure, aunit, pair or some other construct in our MUPL
;; Interp. as a pair of exp in MUPL

;;Example
(define MPAIR1 (apair MINT1 MINT2))



;; Template
#;
(define (fn-for-apair p)
  (... apair-e1)
  (... apair-e2))

;; ------------------------------------------------------------------------------------------------------
;; FST Definition
(struct fst  (e)    #:transparent) ;; get first part of a pair

;; fst is of type (e1 Exp)
;; -- e1 is an expression which means they are one of
;; var, int, cosure, aunit, pair or some other construct in our MUPL
;; Interp. as an in-built function which takes in an expression, and if it is a pair, returns the 1st element, if not returns an error

(define MFST (fst MPAIR1))

;; Template
#;
(define (fn-for-fst p)
  (... fst-e1))

;; ------------------------------------------------------------------------------------------------------
;; SND Definition
(struct snd  (e)    #:transparent) ;; get second part of a pair

;; snd is of type (e1 Exp)
;; -- e1 is an expression which means they are one of
;; var, int, cosure, aunit, pair or some other construct in our MUPL
;; Interp. as an in-built function which takes in an expression, and if it is a pair, returns the 2nd element, if not returns an error

(define MSND (snd MPAIR1))

;; Template
#;
(define (fn-for-snd p)
  (... snd-e1))


;; ------------------------------------------------------------------------------------------------------
;; AUNIT Definition
(struct aunit ()    #:transparent) ;; unit value -- good for ending a list

;; AUNIT is of type () - no type 
;; Interp. as an in-built datatype represnting a null list

(define MUNIT (aunit))

;; Template
#;
(define (fn-for-aunit unit)
  (... unit?))


;; ------------------------------------------------------------------------------------------------------
;; ISAUNIT Definition
(struct isaunit (e) #:transparent) ;; evaluate to 1 if e is unit else 0

;; isunit is of type (e Exp)
;; -- e1 is an expression which means they are one of
;; var, int, cosure, aunit, pair or some other construct in our MUPL
;; Interp. as an in-built function which takes in an expression, and if it is a unit returns (int 1), else (int 0)

(define MISUNIT (isaunit MUNIT))

;; Template
#;
(define (fn-for-isunit is)
  (... isunit-e1))



;; ======================================================================================================
;; CONSTANT DEFINITIONS

(define RL0 (list)) ;; base case of an empty list in Racket
(define ML0 MUNIT) ;; base case of an empty list in MUPL
(define RL1 (list (int 1) (int 2))) ;; useful for P1
(define ML1 (apair (int 1) (apair (int 2) (aunit)))) ;; useful for P1
(define test-env-empty null) ;; empty env for passing through other tests in P2
(define test-env (list (cons (var-string MVAR1) MINT1) (cons (var-string MVAR2) MINT2))) ;; defining a sample test env to test var case for MUPL in P2
(define TESTEXP-ADD (add (int 3) (add (int 4) (int 5)))) ;; deifning a test exp for testing add case for MUPL

(define TESTIFG1 MIFGREATER1) ;; useful for testing ifgreater construct for 1 case
(define TESTIFG2 MIFGREATER2) ;; useful for testing ifgreater construct for 2 case

(define TESTAPAIR (apair (int 3) TESTEXP-ADD)) ;; useful for testing a pair

(define TESTFST (fst TESTAPAIR)) ;; useful for testing the fst funciton on a pair
(define TESTSND (snd TESTAPAIR)) ;; useful for testing the snd function on a pair

(define TESTISUNIT1 (isaunit MUNIT)) ;; case of succesfully testing isunit 
(define TESTISUNIT2 (isaunit TESTAPAIR)) ;; case when testing of isunit is failure

(define TESTFUN MFUN1) ;; defining a test fun for testing of fun code in MUPL

(define TESTCALL MCALL) ;; useful for testing the call construct in MUPL

(define TESTMLET MLET1) ;; usefl for testing MLET function in MUPL

;; ======================================================================================================
;; FUNCTION DEFINITIONS for our MUPL

;; ------------------------------------------------------------------------------------------------------

;; CHANGE (put your solutions here)

;; Problem 1a - RACKETLIST->MUPLLIST
;; Signature -> (list of elements from Racket) -> (list of elements in MUPL)
;; Purpose - takes in a list of elements in Racket and produces another list of elements in MUPL

;; defining the stub
;;(define (racketlist->mupllist lor) (aunit)) ;; this is the stub

;; template for a list
#;
(define (fn-for-loe loe)
  (cond [(null? loe) (...)]
        [#t
         (... (car loe)
              (fn-for-loe (cdr loe)))]))

;; defining the actual funciton

(define (racketlist->mupllist lor) ;; we take a list from racket
  (cond [(null? lor) (aunit)] ;; base case we convert an empty list in Racket to (aunit) in MUPL
        [#t
         (apair (car lor)
               (racketlist->mupllist (cdr lor)))]))


;; defining the check-equals

(check-equal? (racketlist->mupllist RL0) ML0) ;; case of testng for empty list
(check-equal? (racketlist->mupllist RL1) ML1) ;; case of testng for non empty list


;; ------------------------------------------------------------------------------------------------------
;; Problem 1b - MUPLLIST->RACKETLIST
;; Signature -> (list of elements from MUPL) -> (list of elements in Racket)
;; Purpose - takes in a list of elements in MUPL and produces another list of elements in Racket

;; defining the stub
;;(define (mupllist->racketlist lom) null) ;; this is the stub

;; template for a list
#;
(define (fn-for-loe loe)
  (cond [(null? loe) (...)]
        [#t
         (... (car loe)
              (fn-for-loe (cdr loe)))]))

;; defining the actual funciton

(define (mupllist->racketlist lom) ;; we take a list from MUPL
  (cond [(aunit? lom) null] ;; base case we
        [#t
         (cons (apair-e1 lom)
               (mupllist->racketlist (apair-e2 lom)))]))



;; defining the check-equals

(check-equal? (mupllist->racketlist ML0) RL0) ;; case of testng for empty list
(check-equal? (mupllist->racketlist ML1) RL1) ;; case of testng for non empty list


;; ------------------------------------------------------------------------------------------------------
;; Problem 2 - DEFINING our Interpreter

;; lookup a variable in an environment
;; Do NOT change this function
(define (envlookup env str)
  (cond [(null? env) (error "unbound variable during evaluation" str)]
        [(equal? (car (car env)) str) (cdr (car env))]
        [#t (envlookup (cdr env) str)]))

;; Do NOT change the two cases given to you.  
;; DO add more cases for other kinds of MUPL expressions.
;; We will test eval-under-env by calling it directly even though
;; "in real life" it would be a helper function of eval-exp.


(define (eval-under-env e env)
  (cond [(var? e)  ;; Case 1 of defining a variable
         (envlookup env (var-string e))]
  
        ;; Case 2 of defining an int
        [(int? e) e]
        ;; Case 3 of defining the add function
        [(add? e)  ;; 
         (let ([v1 (eval-under-env (add-e1 e) env)]
               [v2 (eval-under-env (add-e2 e) env)])
           (if (and (int? v1)
                    (int? v2))
               (int (+ (int-num v1) 
                       (int-num v2)))
               (error "MUPL addition applied to non-number")))]

        ;; Case 4 of defining ifgreater
        [(ifgreater? e)
         (let ([v1 (eval-under-env (ifgreater-e1 e) env)]
               [v2 (eval-under-env (ifgreater-e2 e) env)])
           (if (and (int? v1)
                    (int? v2))
               (if (> (int-num v1) (int-num v2))
                   (eval-under-env (ifgreater-e3 e) env)
                   (eval-under-env (ifgreater-e4 e) env))
               (error "MUPL addition applied to non-number")))]

        ;; Case5 of defining a pair in our MUPL
        [(apair? e)
         (apair (eval-under-env (apair-e1 e) env) (eval-under-env (apair-e2 e) env))]

        ;; Case6 of defining function fst in our MUPL
        [(fst? e)
         (if (apair? (eval-under-env (fst-e e) env))
             (eval-under-env (apair-e1 (fst-e e)) env)
             (error "MUPL ast applied to a non pair"))]

        ;; Case7 of defining function snd in our MUPL
        [(snd? e)
         (if (apair? (eval-under-env (snd-e e) env))
             (eval-under-env (apair-e2 (snd-e e)) env)
             (error "MUPL snd applied to a non pair"))]
        
        ;; Case 8 of defining aunit in our MUPL
        [(aunit? e) e]
        
        ;;Case 9 of defining isaunit in our MUPL
        [(isaunit? e)
         (let ([v (eval-under-env (isaunit-e e) env)])
           (if (aunit? v)
               (int 1)
               (int 0)))]

        ;; Case 10 evaluating for closures
        [(closure? e) e]

        ;; Case 11 of deifining a function in our MUPL
        [(fun? e)
         (closure env e)]

        ;; Case 12 of defining call expression in our MUPL
        [(call? e)
         (let ([fval (eval-under-env (call-funexp e) env)]
               [v1 (eval-under-env (call-actual e) env)])
           (if (closure? fval)
               (let ([f (closure-fun fval)])
                 (if (equal? (fun-nameopt f) #f)
                     (eval-under-env (fun-body f) (cons (cons (fun-formal f) v1) (closure-env fval)))
                     (eval-under-env (fun-body f) (cons (cons (fun-nameopt f) fval) (cons (cons (fun-formal f) v1) (closure-env fval))))))
               (error "MUPL call applied to a non closure argument")))]
         

        ;; Case 13 of defining a MLET expression in our MUPL
        [(mlet? e)
         (let ([v1 (eval-under-env (mlet-e e) env)])
           (eval-under-env (mlet-body e) (cons (cons (mlet-var e) v1) env)))]

        
        ;; CHANGE add more cases here
        [#t (error (format "bad MUPL expression: ~v" e))]))



;; defininf the check-equals?
(check-equal? (eval-under-env MVAR1 test-env) MINT1) ; testing for variable construc in our MUPL
(check-equal? (eval-under-env TESTEXP-ADD null) (int 12)) ; testing for add construct in our MUPL
(check-equal? (eval-under-env TESTIFG1 null) (int 4)) ; testing for ifgreater construct in our MUPL where e1 <= e2
(check-equal? (eval-under-env TESTIFG2 null) (int 3)) ; testing for ifgreater construct in our MUPL where e1 > e2
(check-equal? (eval-under-env TESTAPAIR null) (apair (int 3) (int 12))) ;; testin for apair in our MUPL
(check-equal? (eval-under-env TESTFST null) (int 3)) ;; testin for FST in our MUPL
(check-equal? (eval-under-env TESTSND null) (int 12)) ;; testin for FST in our MUPL
(check-equal? (eval-under-env TESTISUNIT1 null) (int 1)) ;; case of successfully testing isunit
(check-equal? (eval-under-env TESTISUNIT2 null) (int 0)) ;; case when isunit fails
(check-equal? (eval-under-env TESTFUN null) MCLOSURE);; test case for defining a function
(check-equal? (eval-under-env TESTCALL null) (int 7)) ;; testing case for call construct of MUPL
(check-equal? (eval-under-env TESTMLET null) (int 7)) ;; testing case for call construct of MUPL

;; Do NOT change

(define (eval-exp e)
  (eval-under-env e null))
        
;; ------------------------------------------------------------------------------------------------------
;; Problem 3 - MACROS
;; Problem 3a - IFAUNIT
;; Signature (E1, E2, E3 -> E4)
;; Takes in 3 exp from our MUPL and produces a 4th one which when evaluated
;; -- First evaluates e1, if it is aunit then returns result of e2 , else returns e3

;; defining the stub
;;(define (ifaunit e1 e2 e3) (int 1))

(define (ifaunit e1 e2 e3)
  (ifgreater (isaunit e1) (int 0) e2 e3))


;; Defining check-equal
(check-equal? (eval-under-env (ifaunit (aunit) (int 3) (int 4)) null) (int 3)) ;; case when e1 is aunit
(check-equal? (eval-under-env (ifaunit MPAIR1 (int 3) (int 4)) null) (int 4)) ;; case when e1 is not aunit

;; ------------------------------------------------------------------------------------------------------
;; Problem 3b - MLET*
;; Signature -> (list (s1, E1) (s2, E2)....(sn, En)), E(n+1) -> E
;; Takes in a list of pairs where each pair has a string (or variable name) and an expression in our MUPL + another final expression
;; Final expression is an expression which evalates E(n+1) in an environment where s1 mapped to E1, s2 mapped to E2 and so on

;; defining the stub
;;(define (mlet* lop e2) e2) ;; this is the stub

(define (mlet* lop e)
  (cond [(null? lop) e]
        [#t
         (mlet (car (car lop)) (cdr (car lop)) (mlet* (cdr lop) e))]))


;; Hard to define check equal here
;;(check-equal? (eval-under-env (ifaunit (aunit) (int 3) (int 4)) null) (int 3)) ;; case when e1 is aunit
;;(check-equal? (eval-under-env (ifaunit MPAIR1 (int 3) (int 4)) null) (int 4)) ;; case when e1 is not aunit


;; ------------------------------------------------------------------------------------------------------
;; Problem 3c - IFEQ*
;; Signature -> E1 E2 E3 E4 -> E5
;; Takes in 4 expressions and if e1 = e2 then produces e3 else produces e4

;; defining the stub
;;(define (iseq e1 e2 e3 e4) e3) ;; this is the stub

(define (ifeq e1 e2 e3 e4)
  (mlet* (list (cons "_x" e1) (cons "_y" e2))
         (ifgreater (var "_x") (var "_y") 
                    e4  ; if _x > _y, then they're not equal
                    (ifgreater (var "_y") (var "_x") 
                               e4  ; if _y > _x, then they're not equal  
                               e3)))) ; if neither > the other, they're equal
  


;; Hard to define check equal here
(check-equal? (eval-under-env (ifeq (int 3) (int 3) (int 5) (int 6)) null) (int 5)) ;; case when e1 e2 are equla
(check-equal? (eval-under-env (ifeq (int 3) (int 4) (int 5) (int 6)) null) (int 6)) ;; case when e1 e2 are not equal

;; ------------------------------------------------------------------------------------------------------
;; Problem 4
;; Problem 4a - mupl-map
;; Signature -> MUPL Fun (s1 s2 body) -> MUPL Fun (MUPL List -> MUPL LIst)
;; MUPL-MAP takes in a function which operates on one element and produces some result and produces a fuction which works on an entire list

;; defining the stub
;(define (mupl-map e) MFUN1)

(define mupl-map
  (fun "map" "elemental-fun"
       (fun "helper-fun" "loe"
            (ifeq (isaunit (var "loe"))
                  (int 1)
                  (aunit)
                  (apair (call (var "elemental-fun") (fst (var "loe")))
                         (call (var "helper-fun") (snd (var "loe"))))))))
                               
       
;; Will define check-equal later

(define mupl-mapAddN 
  (mlet "map" mupl-map
        (fun "add_n" "n"
             (call (var "map") (fun #f "x" (add (var "n") (var "x")))))))
    

;; Challenge Problem
#;
(struct fun-challenge (nameopt formal body freevars) #:transparent) ;; a recursive(?) 1-argument function

;; We will test this function directly, so it must do
;; as described in the assignment
#;
(define (compute-free-vars e) "CHANGE")

;; Do NOT share code with eval-under-env because that will make
;; auto-grading and peer assessment more difficult, so
;; copy most of your interpreter here and make minor changes
#;
(define (eval-under-env-c e env) "CHANGE")

;; Do NOT change this
#;
(define (eval-exp-c e)
  (eval-under-env-c (compute-free-vars e) null))
