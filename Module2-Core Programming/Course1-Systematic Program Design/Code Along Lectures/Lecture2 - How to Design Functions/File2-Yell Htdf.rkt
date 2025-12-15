;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname |File2-Yell Htdf|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
; Design a function which takes in string and adds ! to it

; String -> String
; To take in a string and add "!" to the end of the string

(check-expect (yell "hello") "hello!")
(check-expect (yell "Hi") "Hi!")

; (define (yell str) "a")

;(define (yell str)
;  (...str))

(define (yell str)
  (string-append str "!"))