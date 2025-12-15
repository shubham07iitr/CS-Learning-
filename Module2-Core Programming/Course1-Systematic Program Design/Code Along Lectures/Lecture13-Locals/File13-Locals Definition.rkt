;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-reader.ss" "lang")((modname |File13-Locals Definition|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))


(local [(define a 1)
        (define b 2)]

  (+ a b))


(local [(define p "accio ")
        (define (fetch n) (string-append p n))]
  (fetch "portkey"))



(define a 1)
(define b 2)

(+ a
   (local [(define b 3)]
     (+ a b))
   b)

(define b 1)

(+ b
   (local [(define b 2)]
     (* b b))
   b)

(+ 2
   (local [(define b 2)]
     (* b b))
   b)


   