#lang racket

(provide (all-defined-out))

; w/o memoizaiton
(define (fibonacci x)
  (if (or (= x 1) (= x 2))
      1
      (+ (fibonacii (- x 1))
         (fibonacii (- x 2)))))

; with memoization
(define fibonacci2
  (letrec ([memo null] ; list of pairs (arg, result)
           [f (lambda (x)
                (let ([ans (assoc x memo)]) ; we look up the value of fi
                  (if ans 
                      (cdr ans)
                      (let ([new-ans (if (or (= x 1) (= x 2))
                                         1
                                         (+ (f (- x 1))
                                            (f (- x 2))))])
                        (begin
                          (set! memo (cons (ons x new-ans) memo))
                          new-ans)))))])
    f))