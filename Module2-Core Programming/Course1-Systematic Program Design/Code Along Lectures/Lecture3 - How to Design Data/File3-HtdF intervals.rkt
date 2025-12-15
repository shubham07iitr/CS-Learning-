;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname |File3-HtdF intervals|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;; Design a function that produces true if given seat is on aisle

; seat num problem - design a data definition to represent seat number in
; a row of a theatre where each row has 32 seats

;; SeatNum is Integer[1,32]
;; interp. seat nos. in a row, 1 and 32 are aisle seats

(define SN1 1) ; aisle
(define SN2 12) ;middle
(define SN3 32) ; aisle

#;
(define (fn-for-seat_num sn)
  (... sn)
  )

;; Templates rule used:
;; -atomic non distinct: Natural[1, 32]

;; Functions

;; SeatNum -> Boolean
;; produce true if given seat number is on the aisle
(check-expect (aisle? 1) true)
(check-expect (aisle? 16) false)
(check-expect (aisle? 32) true)

;;(define (aisle? sn) false) ;stub

;; <Use tempalte from SeatNum>
(define (aisle? sn)
  (or (= sn 1)
      (= sn 32)))