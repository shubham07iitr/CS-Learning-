;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname Untitled) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define-struct pos (x y)) ;pos is struct name and x n y are field names

(define P1 (make-pos 3 6)) ; constructor
(define P2 (make-pos 3 6))

(pos-x P1); will give back 3 - is called selector
(pos-y P2) ; will give back 8

(pos? P1) ; to check if its true or false
(pos? "hello")