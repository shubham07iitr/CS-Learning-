;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname |File3-Itemization HtdD|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;; Consider designing a system for controlling NY display which displays current state of countdounw:
;; not yet started
;; from 10 to 1 before Midnight
;; Complete ("hppy new year")

;; CountDown is one of:
;; - false (Countdown hasnt started)
;; - Natural[1,10]
;; - "complete"
;; interp.
;; false means countdown not started
;; Natural[1,10] means countdown is running and how many seconds left
;; "complete" means cd is over

(define CD1 false)
(define CD2 10) ; just started running
(define CD3 "complete")

(define (fn-for-countdown c)
  (cond [(false? c] (...)]
        [(and (number? c) (<= 1 c) (<= c 10)) (...c)]
        [else (...)]))

;; template rules used:
;; - one of : 3 cases
;; - atomic distinct: false
;; - atomic non distinct: Natural[1,10]
;; - atomic distinct: "complete"
