;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname |File2-Area HtdF|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
; Design a function which takes in one side of the square and gives back the area

(require 2htdp/image)

; Number -> Number
; Takes in an integer represeting square side and returns the area of square

(check-expect (area 50) 2500)
(check-expect (area 10) 100)

;(define (area n) 0) ;stub

;(define (area n)
;  (...n))

(define (area n)
  (* (n n))
