;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname |File3 - Interval HtdD|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
; seat num problem - design a data definition to represent seat number in
; a row of a theatre where each row has 32 seats

;; SeatNum is Integer[1,32]
;; interp. seat nos. in a row, 1 and 32 are aisle seats

(define SN1 1) ; aisle
(define SN1 12) ;middle
(define SN1 32) ; aisle

#;
(define (fn-for-seat_num sn)
  (... sn)
  )

;; Templates rule used:
;; -atomic non distinct: Natural[1, 32]