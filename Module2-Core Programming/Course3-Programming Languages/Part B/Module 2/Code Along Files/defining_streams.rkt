#lang racket

; 1 1 1 1 1 1 1 1 1 ... infinite sequrnce of 1s

(define ones (lambda () (cons 1 ones)))

; 1 2 3 4 5 6.....
(define nats
  (letrec ([f (lambda (x) (cons x (lambda () (f (+ x 1)))))])
    (lambda () (f 1))))

; 2 4 8 16....
(define power-of-two
  (letrec ([f (lambda (x) (cons x (lambda () (f (* x 2)))))])
    (lambda () (f 2))))

