;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname |File3-Basics HtdD|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;; Data Definitions:

;; TLColor is one of:
;; 0 - red or 1 - yellow or 2 - green

;; given below is the function without data definition
#;
(define (fn-for-tlcolor c)
  (cond [(= c 0) (...)]
        [(= c 1) (...)]
        [(= c 2) (...)]))

;; Functions - actual starts from below
;; TLColor -> TLColor - notice how we change the type of input and output
;; produce next color of traffic light
(check-expect (next-color 0) 2)
(check-expect (next-color 1) 0)
(check-expect (next-color 2) 1)

; (define (next-color c) 0) ; stub

; template from TLcolor

(define (fn-for-tlcolor c)
  (cond [(= c 0) (2)]
        [(= c 1) (0)]
        [(= c 2) (1)]))