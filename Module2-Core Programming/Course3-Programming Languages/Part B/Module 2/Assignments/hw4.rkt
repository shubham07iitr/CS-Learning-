
#lang racket
(require rackunit) ;; useful for testing functions

(provide (all-defined-out)) ;; so we can put tests in a second file

;; put your code below

;; ==========================================
;; CONSTANTS
;; ==========================================

(define LON0 (list 0)) ;; useful for testing P1
(define LON1 (list 3 5 7 9 11)) ;; useful for tesitng P1 P3
(define LON2 (list 3 6)) ; useful for testing P1
(define LON3 null) ;; useful for testing P1

(define LOS0 null) ;; useful for testing P2
(define LOS1 (list "shubham" "survi" "sharvil")) ;; useful for testing P2 and P3
(define LOS1S (list "mrshubham" "mrsurvi" "mrsharvil")) ;; useful for testing P2
(define LOS2 (list "shuhbham" "" "sharvil")) ; useful for testing P2
(define LOS2S (list "mrshuhbham" "mr" "mrsharvil")) ; useful for testing P2

(define LOV0 (vector)) ;; defining an empty vector useful for Problem 9
(define LOV1 (vector (cons "shubham" 1) "survi" (cons "sharvil" 2))) ;; defining non empty vector useful for tesintg P(

(define LOP1 (list (cons "shubham" 1) (cons "sharvil" 2) (cons "mummy" 3) (cons "daddy" 4))) ;; useful for testing P10


;; ==========================================
;; DATA DEFINTIONS
;; ==========================================

;; ==========================================
;; FUNCTIONS
;; ==========================================

;; ------------------------------------------
;; PROBLEM 1 - SEQUENCE
;; Signature -> Num1, Num2, Num3 -> List of Nums
;; Takes in 3 arguments - Low, High and Stride, and produces a list of nums
;; List of nums will start from Low, and will continue untl High is reached

;; defining the stub

;;(define (sequence n1 n2 n3) null) ;; this is the base stub

;; Defining the template

#;
(define (fn-for-num n1 n2 n3)
  (...n1)
  (...n2)
  (...n3))

;; defining the actual function

(define (sequence low high stride)
  (cond [(< high low) null]
        [(= high low) (list low)]
        [#t
         (cons low (sequence (+ low stride) high stride))]))
             

;; Defining the check-expects
(check-equal? (sequence 0 0 0) LON0) ;; case when we pass all 0 as arguments
(check-equal? (sequence 3 11 2) LON1) ;; simple case of increasing strides and high > low and ending at high
(check-equal? (sequence 3 8 3) LON2) ;; cse when we dont end on high
(check-equal? (sequence 3 2 1) LON3);; case when high < low

;; ------------------------------------------
;; PROBLEM 2 - STRING-APPEND-MAP
;; Signature -> List of Strings, String -> List of Strings
;; Takes in a list of strings, and a suffix and produces a list of strings where each element in original list has had suffix appended to it 


; defining the stub
;;(define (string-append-map los s) los) ;; takes in LOS and string and produces LOS

;; defining the template for LOS
#;
(define (fn-for-los los s)
  (cond [(null? los) (...)]
        [#t
         (...s)
         (...car los)
         (fn-for-los (cdr los) s)]))

;; defining the actual function

(define (string-append-map los s)
  (map (let [(f (lambda(x) (string-append s x)))] f) los))

;; defining the check-equals

(check-equal? (string-append-map LOS0 "mr") LOS0) ;base case of passing an empty list
(check-equal? (string-append-map LOS1 "mr") LOS1S) ;; compound case of non empty list 
(check-equal? (string-append-map LOS2 "mr") LOS2S) ;; compound case of non empty list but empty strings

;; ------------------------------------------
;; PROBLEM 3 - LIST-NTH-MOD
;; Signature -> List of Elements, Number -> Element
;; Takes in a list of elements and a number and produces ith element of the list where i is the remainder of n divided by length of list and counting starts from 0 
;; -- Number negative , terminate with (error "list-nth-mod: negative number")
;; -- Empty list, terminate with (error "list-nth-mod: empty list")


; defining the stub
;;(define (list-nth-mod loe n) "") ;; stub

;; defining the template for LOS
#;
(define (fn-for-loe loe n)
  (cond [(null? loe) (...)]
        [#t
         (...n)
         (...car loe)
         (fn-for-loe (cdr loe) n)]))

;; defining the actual function

(define (list-nth-mod loe n)
  (cond
    [(null? loe) (error "list-nth-mod: empty list")]
    [(< n 0) (error "list-nth-mod: negative number")]
    [else
     (let* ([len_list (length loe)]
            [i (remainder n len_list)])
       (letrec ([helper (lambda (loe acc)
                          (cond [(= i acc) (car loe)]
                                [else (helper (cdr loe) (+ acc 1))]))])
         (helper loe 1)))]))

                       
#;
(define (string-append-map los s)
  (map (let [(f (lambda(x) (string-append s x)))] f) los))

;; defining the check-equals
;; can't test for errors

(check-equal? (list-nth-mod LON1 3) 7) ; case of passing list of numbers
(check-equal? (list-nth-mod LOS1 2) "survi") ;; case of list of strings

;; ------------------------------------------
;; PROBLEM 5 - FUNNY-NUMBER-STREAM
;; Signature -> NO Arguments -> Produces a stream wen called
;; Creating a thunk which produces a stream of natural nos when called such that
;; -- multiples of 5 are negated


; difficult to define the stub here 

;; defining the template for creating a thunk stream
#;
(define fn-for-stream
  (letrec ([f (lambda (x) (cons x (lambda () (f (new x)))))])
    (lambda () (f x))))
           
  
;; defining the actual function using the template

(define funny-number-stream
  (letrec ([f (lambda (x)
                (cond [(= (remainder x 5) 0) (cons (* -1 x) (lambda () (f (+ 1 x))))]
                      [#t
                       (cons x (lambda () (f (+ 1 x))))]))])
    (lambda () (f 1))))


;; defining check-equals here
(check-equal? (car (funny-number-stream)) 1) ;; base case of testing for first elemnet
(check-equal? (car ((cdr ((cdr ((cdr ((cdr (funny-number-stream)))))))))) -5) ;; testing for 5th element

;; ------------------------------------------
;; PROBLEM 4 - STREAM-FOR-N-STEPS
;; Signature -> Stream of elements, Number -> List of Elements
;; Takes in a stream of elements and a number and produces first n list of elements from the stream

; defining the stub
;;(define (stream-for-n-steps s n) null) ;; takes in a stream and a number and produces a list of n first elements from the stream in order 

;; defining the template for creating a thunk stream
;; defining the template
#; 
(define (fn-for-stream stream n)
  (cond [(= n 0) (...)]
        [#t
         (..car (stream))
         (fn-for-stream (cdr (stream)) (new n))]))
           
  
;; defining the actual function using the template
(define (stream-for-n-steps stream n)
  (cond [(= n 0) null]
        [#t
         (cons (car (stream))
               (stream-for-n-steps (cdr (stream)) (- n 1)))]))


;; defining check-equals

(check-equal? (stream-for-n-steps funny-number-stream 0) null) ; testing the base case where nmber is 0
(check-equal? (stream-for-n-steps funny-number-stream 3) (list 1 2 3)) ; testing the compound case where it produces first 3 eleemnts of the stream in order


;; ------------------------------------------
;; PROBLEM 6 - DAN-THEN-DOG
;; Signature -> NO Arguments -> Produces a stream when called
;; Creating a thunk which produces a stream of alternate dan.jpg and a thunk of dog.jpg

; difficult to define the stub here 

;; defining the template for creating a thunk stream
#;
(define fn-for-stream
  (letrec ([f (lambda (x) (cons x (lambda () (f (new x)))))])
    (lambda () (f x))))
           
  
;; defining the actual function using the template

(define dan-then-dog
  (letrec ([f (lambda (x)
                (cond [(= (remainder x 2) 1) (cons "dan.jpg" (lambda () (f (+ 1 x))))]
                      [#t
                       (cons "dog.jpg" (lambda () (f (+ 1 x))))]))])
    (lambda () (f 1))))

;; defining the check-equals
(check-equal? (car (dan-then-dog)) "dan.jpg") ;; testing the case for first element
(check-equal? (car ((cdr (dan-then-dog)))) "dog.jpg") ;; testing the case for second element




;; ------------------------------------------
;; PROBLEM 7 - STREAM-ADD-ZERO
;; Signature -> Stream -> Stream
;; Takes in a stream and returns a stream by transforming each element of the given into a pair (0, e)
;; -- stream would look liek ((0,e), stream)

; difficult to define the stub here 
;(define (stream-add-zero s) s) ;; takes in a stream and produces a stream

;; defining the template for creating a thunk stream
#;
(define fn-for-stream
  (letrec ([f (lambda (x) (cons x (lambda () (f (new x)))))])
    (lambda () (f x))))
           
  
;; defining the actual function using the template

(define (stream-add-zero stream)
  (letrec ([f (lambda (s)
                (cons (cons 0 (car (s))) (lambda () (f (cdr (s))))))])
    (lambda () (f stream))))

;; defining the check-equals
(check-equal? (car ((stream-add-zero dan-then-dog))) (cons 0 "dan.jpg")) ;; testing the case for first element in dan and dog stream
(check-equal? (car ((cdr ((stream-add-zero funny-number-stream))))) (cons 0 2)) ;; testing the case for 2nd element in funny-number-stream



;; ------------------------------------------
;; PROBLEM 7 - cycle-lists
;; Signature -> LoE1, LoE2 -> Stream
;; Takes in 2 non empty lists and produce a stream where
;; -- first element is pair of first eleements of two lists
;; -- 2nd element in stream is 2nd element in list 1 + 2nd eleent in list 2
;; -- if 2nd element was zero, then we restart from 1st place again
;; -- For example, if xs is ’(1 2 3) and ys is ’("a" "b"), then the stream would produce, (1 . "a"), (2 . "b"), (3 . "a"), (1 . "b"), (2 . "a"), (3 . "b"), (1 . "a"), (2 . "b"), etc.
;; two one of cases
;; -- case 1 - when both acc1 and acc2 > lenght of respective lists
;; -- case 2 - when acc1 > length of respective list
;; -- case 3 - when acc2 > length of respective list


; difficult to define the stub here 
;(define (cycle-lists loe1 loe2) funny-number-stream) ;; takes in 2 LOE and produces a stream

;; defining the template for creating a thunk stream
#;
(define fn-for-stream
  (letrec ([f (lambda (x) (cons x (lambda () (f (new x)))))])
    (lambda () (f x))))
           
  
;; defining the actual function using the template


(define (cycle-lists loe1 loe2)
  (letrec ([f (lambda (loe1 loe2 acc1 acc2)
                (cond [(and (> acc1 (length loe1)) (> acc2 (length loe2))) (cons (cons (list-ref loe1 0) (list-ref loe2 0)) (lambda () (f loe1 loe2 1 1)))]
                      [(> acc1 (length loe1)) (cons (cons (list-ref loe1 0) (list-ref loe2 (- acc2 1)) (lambda () (f loe1 loe2 1 1))))]
                      [ (> acc2 (length loe2)) (cons (cons (list-ref loe1 (- acc1 1)) (list-ref loe2 0)) (lambda () (f loe1 loe2 1 1)))]
                      [#t
                       (cons (cons (list-ref loe1 (- acc1 1)) (list-ref loe2 (- acc2 1))) (lambda () (f loe1 loe2 (+ 1 acc1) (+ 1 acc2))))]))])
    (lambda () (f loe1 loe2 1 1))))

  
;; defining the check-equals
(check-equal? (car ((cycle-lists LOS1 LON1))) (cons "shubham" 3)) ; testing the case for 1st element
(check-equal? (car ((cdr ((cycle-lists LOS1 LON1))))) (cons "survi" 5)) ;; testing the case for 2nd element for LOS1 and LON1



;; ------------------------------------------
;; PROBLEM 9 - VECTOR-ASSOC
;; Signature -> Vector, Element -> Pair or #f
;; Takes in a vector of pairs or other elements and a key and :
;; -- returns the first pair if a match with the key is found
;; -- returns false if it reaches the end of the list

;; defining the stub

;;(define (vector-assoc v key) #f) ;; this is the base stub

;; Defining the template for recursion on vector

#;
(define (fn-for-v v)
  (cond [(= (vector-length v) 0) (...)]
        [#t
         (... (vector-ref v 0)
              (fn-for-v (smaller v)))]))


;; defining the actual function
;; will need to define a local function which takes in a vector and returns the tail or cdr of a vector
(define (vector-assoc v key)
  (letrec ([tail-vector (lambda (v)
                (list->vector (cdr (vector->list v))))]
           [helper (lambda (v key)
                     (cond [(= (vector-length v) 0) #f] ;; if vector is empty or if we reached the end of vector
                           [(pair? (vector-ref v 0)) ;; checking if the element being evaluated is a pair
                                   (cond [(equal? key (car (vector-ref v 0))) (vector-ref v 0)] ;; if it is a pair , then we check if first element of pair equlas the key, if yes we retufn the pair
                                         [#t (helper (tail-vector v) key)])] ;; if no , we move on
                           [#t(helper (tail-vector v) key)]))])
  (helper v key))) ;; tramploining the helper function 
                
;; Defining the check-expects
(check-equal? (vector-assoc LOV0 "shubham") #f) ;; base case of empty vector producing false
(check-equal? (vector-assoc LOV1 "mummy") #f) ;; compound case of operating on non empty vector but key not found
(check-equal? (vector-assoc LOV1 "sharvil") (cons "sharvil" 2)) ;; compound case of operating on non empty vector and key is found


;; ------------------------------------------
;; PROBLEM 10 - VECTOR-ASSOC
;; Signature -> Listof (E1, E2) , Number > (E1->(E1, E2))
;; Takes in a list of pair of elements and a number and returns a function which is a faster mode for assoc
;; First it creates a vector cache which will be empty to begin with, and the length will be smaller
;; Then it creates the function which will first check the key in the vector assoc, if found returns,
;; If key not found then will search in the list normally, and if found returns the pair + updates the vector cache as well


;; defining the stub

;;(define (cached-assoc xs n) (lambda (key) #f)) ;; this is the base stub

;; Standard template for opeating on a list


;; defining the actual function
;; will need to define a local function which takes in a vector and returns the tail or cdr of a vector

(define (cached-assoc xs n)
  (letrec ([vector-cache (make-vector n)]
           [counter (box 1)]
           [f (lambda (key)
             (let ([ans (vector-assoc vector-cache key)])
               (if ans
                   ans
                   (let ([new_ans (assoc key xs)])
                     (if new_ans
                         (begin
                           (if (<= (unbox counter) n)
                               (begin
                                 (vector-set! vector-cache (- (unbox counter) 1) new_ans)
                                 (set-box! counter (+ 1 (unbox counter)))
                                 new_ans)
                               (begin
                                 (vector-set! vector-cache 0 new_ans)
                                 (set-box! counter 1)
                                 new_ans)))
                         #f)))))])
    f))
                         

 
                
;; Defining the check-expects
(check-equal? ((cached-assoc LOS0 3) "shubham") #f) ;; base case of empty list producing false
(check-equal? ((cached-assoc LOP1 4) "shivansh") #f) ;; compound case of operating on non empty list but key not found
(check-equal? ((cached-assoc LOP1 5) "mummy") (cons "mummy" 3)) ;; compound case of operating on non empty list and key is found









