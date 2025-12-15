#lang racket

(define (number-until stream tester)
  (letrec ([f (lambda (stream ans)
                (let ([pr (stream ams)])
                  (if (tester (car pr))
                      ans
                      (f (cdr pr) (+ ans 1)))))])
    (f stream 1)))

(number-until powers-of-two (lambda (x) (= x 16)))