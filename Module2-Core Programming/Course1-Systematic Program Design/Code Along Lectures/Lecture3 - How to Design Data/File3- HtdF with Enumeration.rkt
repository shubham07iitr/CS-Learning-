;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname |File3- HtdF with Enumeration|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;; Design a data definition to represent letter grade in a course which is one of A,B,C

;; LetterGrade is one of:
;; - "A"
;; - "B"
;; - "C"
;; interp. the letter grade in a course

;; <examples are redundant for enumeration>


;(define (fn-for-letter-grade lg)
 ; (cond [(string=? lg "A"] (...)]
 ;       [(string=? lg "B"] (...)]
 ;       [(string=? lg "C"] (...)]
 ; )
;; Template rules used:
;; one of: 3 cases
;; - atomic distinct value: "A"
;; - atomic distinct value: "B"
;; - atomic distinct value: "C"


;; Design a function with takes in lettergrade and produces next highest letter grade

;; Functions:
;; LetterGrade -> LetterGrade
;; produce next highest letter grade (no change for A)
(check-expect (bump-up "A") "A")
(check-expect (bump-up "B") "A")
(check-expect (bump-up "C") "B")


;(define (bump-up lg) "A") ;stub
; <use template from LetterGrade>


(define (bump-up lg)
  (cond [(string=? lg "A") "A"]
        [(string=? lg "B") "A"]
        [(string=? lg "C") "B"]))
