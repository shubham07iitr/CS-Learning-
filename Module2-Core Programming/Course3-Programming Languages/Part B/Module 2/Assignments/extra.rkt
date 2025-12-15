
#lang racket

(provide (all-defined-out)) ;; so we can put tests in a second file
(require rackunit) ;; useful for testing functions
;; put your code below


;; ==========================================
;; CONSTANTS
;; ==========================================
(define LON0 null) ; useful for P1
(define LON1 (list 1 2 4 8)) ; useful for P1
(define LON2 (list -2 4 16)) ; useful for P1 

;; ==========================================
;; DATA DEFINTIONS
;; ==========================================

;; ==========================================
;; FUNCTIONS
;; ==========================================

;; ------------------------------------------
;; PROBLEM 1 - SEQUENCE
;; Signature -> List of Nums -> List of Nums
;; Takes in a list of nums and returns another list of nums, where each element is transformed to sum of itslef + element on index of (n-1-index of first element )
;; E.g. (list 1 2 4 8) -> (list 9 6 6 9)

;; defining the stub

;;(define (palindrome lon) null) ;; this is the base stub

;; Defining the template for a list


;; defining the actual function
(define (palindrome lon)
  (letrec ([len_list (length lon)]
           [helper (lambda (lon acc wl) ;; setting up a context preserving accumulator here
                     (cond [(> acc len_list) wl] ;; returning worklist accumulator when we reach end of the list 
                           [#t
                            (helper lon (+ 1 acc) (cons (+ (list-ref lon (- acc 1)) (list-ref lon (- len_list acc))) wl))]))])
    (helper lon 1 null)))

;; Defining the check-expects
(check-equal? (palindrome LON0) null) ;; base case of an empty list to produce an empty list
(check-equal? (palindrome LON1) (list 9 6 6 9)) ;; compound case of non empty list but length of lis is even and all positives
(check-equal? (palindrome LON2) (list 14 8 14)) ;; case of non empty list and odd length list with some elements negative


;; ------------------------------------------
;; PROBLEM 2 - FIBONACCI
;; Signature -> No arguments -> Stream of pairs where 1st element in pair is a number, 2nd one is a stream
;; Creating a thunk which produces a stream of nos. from the fibonacii series


; difficult to define the stub here
;(define fibonacci (lambda () (list 0 2 3))) ;; this is the stub producing a thunk

;; defining the template for creating a thunk stream
#;
(define fn-for-stream
  (letrec ([f (lambda (x) (cons x (lambda () (f (new x)))))])
    (lambda () (f x))))
           
  
;; defining the actual function using the template

(define fibonacci
  (letrec ([fib_helper (lambda (n)
                         (cond [(= n 0) 0]
                               [(= n 1) 1]
                               [#t
                                (+ (fib_helper (- n 1)) (fib_helper (- n 2)))]))]
           [f (lambda (x)
                (cons (fib_helper x) (lambda () (f (+ 1 x)))))])
    (lambda () (f 0))))
    
;; defining check-equals here
(check-equal? (car (fibonacci)) 0) ;; base case of testing for first elemnet
(check-equal? (car ((cdr ((cdr ((cdr ((cdr (fibonacci)))))))))) 3) ;; testing for 5th element


;; ------------------------------------------
;; PROBLEM 3 - STREAM_UNTIL
;; Signature ->  ('a -> bool), stream of 'a -> Number
;; Takes in a stream and a function and returns the count of times until we receive a false by operating f on the car of stream


;; defining the stub here
;;(define (stream-until f stream) 0) ;; this is the stub

;; defining the template for creating a thunk stream
#;
(define (fn-for-stream stream n)
  (cond [(= n 0) (...)]
        [#t
         (..car (stream))
         (fn-for-stream (cdr (stream)) (new n))]))
           
  
;; defining the actual function using the template
(define (stream-until f stream)
  (if (not (f (car (stream))))
      0
      (+ 1 (stream-until f (cdr (stream))))))


    
;; defining check-equals here
(check-equal? (stream-until (lambda (x) (< x 3)) fibonacci) 4) ;; testing for fibonacii stream

;; ------------------------------------------
;; PROBLEM 4 - STREAM_MAP
;; Signature ->  ('a -> 'a), stream of 'a -> Stream of 'a
;; Takes in a stream and a function and returns another function where each element is transformed by function f


;; defining the stub here
;;(define (stream-map f stream) stream) ;; this is the stub

;; defining the template for creating a thunk stream
#;
(define fn-for-stream
  (letrec ([f (lambda (x) (cons x (lambda () (f (new x)))))])
    (lambda () (f x))))
           
  
;; defining the actual function using the template

(define (stream-map f stream)
  (letrec ([helper (lambda (f stream)
             (cons (f (car (stream)))
                   (lambda () (helper f (cdr (stream))))))])
    (lambda () (helper f stream))))


    
;; defining check-equals here
(check-equal? (car ((stream-map (lambda (x) (* 2 x)) fibonacci))) 0) ;; testing for 1st element which should be 0
(check-equal? (car ((cdr ((stream-map (lambda (x) (* 2 x)) fibonacci))))) 2) ;; testing for 2nd element which should be 2


;; ------------------------------------------
;; PROBLEM 5 - STREAM_ZIP
;; Signature ->  Stream of 'a, Stream of 'b -> Stream of ('a, 'b)
;; Takes in two streams and produces another stream where (car (stream)) would be a pair of elements from 1st and 2nd stream at the same position


;; defining the stub here
;;(define (stream-zip stream1 stream2) stream1) ;; this is the stub

;; defining the template for creating a thunk stream
#;
(define fn-for-stream
  (letrec ([f (lambda (x) (cons x (lambda () (f (new x)))))])
    (lambda () (f x))))
           
  
;; defining the actual function using the template

(define (stream-zip stream1 stream2)
  (letrec ([helper (lambda (stream1 stream2)
             (cons (cons (car (stream1)) (car (stream2)))
                   (lambda () (helper (cdr (stream1)) (cdr (stream2))))))])
    (lambda () (helper stream1 stream2))))


    
;; defining check-equals here
(check-equal? (car ((stream-zip fibonacci fibonacci))) (cons 0 0)) ;; testing for 1st element which should be pair of 0
(check-equal? (car ((cdr ((stream-zip fibonacci fibonacci))))) (cons 1 1)) ;; testing for 2nd element which should be pair of 1s


;; ------------------------------------------
;; PROBLEM 7 - INTERLEAVE
;; Signature ->  List of Streams -> Stream
;; Takes in a list of streams and produces a new stream where 1st element of new stream is 1st element of 1st stream in list, then 1st element of 2nd stream in the list and so on
;; if we reach end of list, then we start from 0 again


;; defining the stub here
;;(define (interleave los) (lambda () 0)) ;; this is the stub

;; defining the template for creating a thunk stream
#;
(define fn-for-stream
  (letrec ([f (lambda (x) (cons x (lambda () (f (new x)))))])
    (lambda () (f x))))
           
  
;; defining the actual function using the template

(define (interleave los)
  (letrec ([len_list (length los)]
           [helper (lambda (los acc)
                     (cond [(<= acc len_list) (cons (car ((list-ref los (- acc 1)))) (lambda () (helper los (+ 1 acc))))]
                           [#t
                            (cons (car ((list-ref los 0))) (lambda () (helper los 1)))]))])
    (lambda () (helper los 1))))


    
;; defining check-equals here
(check-equal? (car ((interleave (list fibonacci fibonacci)))) 0) ;; testing for 1st element which should be 0 as list is fibonacci streams for all list elements
(check-equal? (car ((cdr ((interleave (list fibonacci fibonacci)))))) 0) ;; testing for 2nd element which should 






