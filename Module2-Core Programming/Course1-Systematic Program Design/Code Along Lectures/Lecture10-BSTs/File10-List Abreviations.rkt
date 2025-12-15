;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-abbr-reader.ss" "lang")((modname |File10-List Abreviations|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(cons "a" (cons "b" (cons "c" empty))) ;; this is same as below:

(list "a" "b" "c")

(list (+ 1 2) (+ 3 4))

(define L1 (list "b" "c"))
(define L2 (list "d" "e" "f"))

;; Using (cons "a" L1) will L1 to the list
(cons "a" L1)

;; Using (list "a" L1) will prodce a nested list
(list "a" L1)


(append L1 L2) ; it combines the two lists together