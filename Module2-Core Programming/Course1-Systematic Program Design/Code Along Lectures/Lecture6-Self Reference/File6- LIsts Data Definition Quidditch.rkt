;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname |File6- LIsts Data Definition Quidditch|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
; Design a data definition to represent a list of quidditch team

; Info:
;UBC
;McGill
;Team who must not be named
empty

(cons "UBC"
      (cons "McGill"
            empty))


;; ListofString is one of:
;; - empty
;; - (cons String ListofString)
;; interp. a list of strings
(define LOS1 empty)
(define LOS2 (cons "McGill" empty))
(define LOS3 (cons "UBC" (cons "McGill" empty)))

#;
(define (fn-for-los los)
  (cond [(empty? los) (...)]
        [else
         (... (first los) ; String
              (fn-for-los (rest los)))]))

;; Templates rules used:
;; - one of: 2 cases
;; - atomic distinct: empty


;; Design a function that consumes a ListofString and produces true if list includes "UBC"

;; ListofString -> Boolean
;; produce true if los includes "UBC"

(check-expect (contains-ubc? empty) false)
(check-expect (contains-ubc? (cons "McGill" empty)) false)
(check-expect (contains-ubc? (cons "UBC" empty)) true)
(check-expect (contains-ubc? (cons "McGill" (cons "UBC" empty))) true)


;(define (contains-ubc? los) false) ; stub


(define (contains-ubc? los)
  (cond [(empty? los) false]
        [else
         (if (string=? (first los) "UBC") ; String
             true
             (contains-ubc? (rest los)))]))