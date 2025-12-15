;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Abstraction-quiz-completed) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(require 2htdp/image)
;  PROBLEM 1:
;
;  Design an abstract function called arrange-all to simplify the
;  above-all and beside-all functions defined below. Rewrite above-all and
;  beside-all using your abstract function.


;; (listof Image) -> Image
;; combines a list of images into a single image, each image above the next one
(check-expect (above-all empty) empty-image)
(check-expect (above-all (list (rectangle 20 40 "solid" "red") (star 30 "solid" "yellow")))
              (above (rectangle 20 40 "solid" "red") (star 30 "solid" "yellow")))
(check-expect (above-all (list (circle 30 "outline" "black") (circle 50 "outline" "black") (circle 70 "outline" "black")))
              (above (circle 30 "outline" "black") (circle 50 "outline" "black") (circle 70 "outline" "black")))

;(define (above-all loi) empty-image)  ;stub

#;
(define (above-all loi)
  (cond [(empty? loi) empty-image]
        [else
         (above (first loi)
                (above-all (rest loi)))]))


;; defining above-all using arrange-all

(define (above-all loi)
  (arrange-all above loi))


;; (listof Image) -> Image
;; combines a list of images into a single image, each image beside the next one
(check-expect (beside-all empty) (rectangle 0 0 "solid" "white"))
(check-expect (beside-all (list (rectangle 50 40 "solid" "blue") (triangle 30 "solid" "pink")))
              (beside (rectangle 50 40 "solid" "blue") (triangle 30 "solid" "pink")))
(check-expect (beside-all (list (circle 10 "outline" "red") (circle 20 "outline" "blue") (circle 10 "outline" "yellow")))
              (beside (circle 10 "outline" "red") (circle 20 "outline" "blue") (circle 10 "outline" "yellow")))

;(define (beside-all loi) empty-image)  ;stub
#;
(define (beside-all loi)
  (cond [(empty? loi) (rectangle 0 0 "solid" "white")]
        [else
         (beside (first loi)
                 (beside-all (rest loi)))]))

;; defining beside-all using arrange-all

(define (beside-all loi)
  (arrange-all beside loi))




;; FUNCITON - ARRANGE ALL

;; Listof Image -> Image
;; Consumes a list of image and produces a combined image based on a function operated on a single image

(check-expect (arrange-all above empty) empty-image)
(check-expect (arrange-all above (list (rectangle 20 40 "solid" "red") (star 30 "solid" "yellow")))
              (above (rectangle 20 40 "solid" "red") (star 30 "solid" "yellow")))
(check-expect (arrange-all beside (list (circle 10 "outline" "red") (circle 20 "outline" "blue") (circle 10 "outline" "yellow")))
              (beside (circle 10 "outline" "red") (circle 20 "outline" "blue") (circle 10 "outline" "yellow")))


;;(define (arrange-all loi) empty-image) ;; this is stub

;; taking Listof X as a template

(define (arrange-all fn loi)
  (cond [(empty? loi) empty-image]
        [else
         (fn (first loi)
             (arrange-all fn (rest loi)))]))
  


;  PROBLEM 2:
;
;  Finish the design of the following functions, using built-in abstract functions.
;


;; Function 1: LENGTHS
;; ==========

;; (listof String) -> (listof Natural)
;; produces a list of the lengths of each string in los
(check-expect (lengths empty) empty)
(check-expect (lengths (list "apple" "banana" "pear")) (list 5 6 4))

;;(define (lengths lst) empty); this is the stub

;; template for Listof String along with map abstract function

(define (lengths los)
  (map string-length los))



;; Function 2 : ODD-ONLY
;; ==========

;; (listof Natural) -> (listof Natural)
;; produces a list of just the odd elements of lon
(check-expect (odd-only empty) empty)
(check-expect (odd-only (list 1 2 3 4 5)) (list 1 3 5))

;(define (odd-only lon) empty); this is stub

;; template for Listof Number along with filter abstract function and odd primitive function

(define (odd-only lon)
  (filter odd? lon))


;; Function 3
;; ==========

;; (listof Natural -> Boolean
;; produce true if all elements of the list are odd
(check-expect (all-odd? empty) true)
(check-expect (all-odd? (list 1 2 3 4 5)) false)
(check-expect (all-odd? (list 5 5 79 13)) true)

;;(define (all-odd? lon) empty) ;; this is the stub

;; template for Listof Naturral along with andmap abstract function

(define (all-odd? lon)
  (andmap odd? lon))

;; Function 4
;; ==========

;; (listof Natural) -> (listof Natural)
;; subtracts n from each element of the list
(check-expect (minus-n empty 5) empty)
(check-expect (minus-n (list 4 5 6) 1) (list 3 4 5))
(check-expect (minus-n (list 10 5 7) 4) (list 6 1 3))

;;(define (minus-n lon n) empty) ;; this is stub


;; tempalate for Listof Number with local encapsulation for a new function along with map abstract function

(define (minus-n lon n)
  (local [(define (subn x)
            (- x n))]
    (map subn lon)))




;  PROBLEM 3
;
;  Consider the data definition below for Region. Design an abstract fold function for region,
;  and then use it do design a function that produces a list of all the names of all the
;  regions in that region.
;
;  For consistency when answering the multiple choice questions, please order the arguments in your
;  fold function with combination functions first, then bases, then region. Please number the bases
;  and combination functions in order of where they appear in the function.
;
;  So (all-regions CANADA) would produce
;  (list "Canada" "British Columbia" "Vancouver" "Victoria" "Alberta" "Calgary" "Edmonton")


(define-struct region (name type subregions))
;; Region is (make-region String Type (listof Region))
;; interp. a geographical region

;; Type is one of:
;; - "Continent"
;; - "Country"
;; - "Province"
;; - "State"
;; - "City"
;; interp. categories of geographical regions

(define VANCOUVER (make-region "Vancouver" "City" empty))
(define VICTORIA (make-region "Victoria" "City" empty))
(define BC (make-region "British Columbia" "Province" (list VANCOUVER VICTORIA)))
(define CALGARY (make-region "Calgary" "City" empty))
(define EDMONTON (make-region "Edmonton" "City" empty))
(define ALBERTA (make-region "Alberta" "Province" (list CALGARY EDMONTON)))
(define CANADA (make-region "Canada" "Country" (list BC ALBERTA)))

#;
(define (fn-for-region r)
  (local [(define (fn-for-region r)
            (... (region-name r)
                 (fn-for-type (region-type r))
                 (fn-for-lor (region-subregions r))))

          (define (fn-for-type t)
            (cond [(string=? t "Continent") (...)]
                  [(string=? t "Country") (...)]
                  [(string=? t "Province") (...)]
                  [(string=? t "State") (...)]
                  [(string=? t "City") (...)]))

          (define (fn-for-lor lor)
            (cond [(empty? lor) (...)]
                  [else
                   (... (fn-for-region (first lor))
                        (fn-for-lor (rest lor)))]))]
    (fn-for-region r)))


;; FOLD FUNCTION
;; Consumes a Region and traverses the region to produce a single element/list
;; Takes in a function, a base value, and a multi element data structure
;; (Region Y->Y) Y Region -> Y

;;(check-expect (fold cons-name append empty empty) empty) ;; base case of an empty region ; not necessary
(check-expect (fold cons-name append empty EDMONTON) (list "Edmonton")) ;; case of single region with no sub-tree
(check-expect (fold cons-name append empty CANADA) (list "Canada" "British Columbia" "Vancouver" "Victoria" "Alberta" "Calgary" "Edmonton")) ;; compound case of traversing thruogh the entire arity tree

;(define (fold-region fn base r) base);; this is stub

;; <Using template for region along with encapsulation for local>
(define (fold fn1 fn2 base r)
  (local [(define (fn-for-region r)
            (fn1 r (fn-for-lor (region-subregions r))))
          (define (fn-for-lor lor)
            (cond [(empty? lor) base]
                  [else
                   (fn2 (fn-for-region (first lor)) (fn-for-lor (rest lor)))]))]
    (fn-for-region r)))


;; FUNCTION LIST-REGIONS
(define (list-regions r)
  (fold cons-name empty r))
            


;; FUNCTION CONS-NAME - Helper
;; Region Listof String -> Listof String
;; Appends a single region name to a list of region names

(check-expect (cons-name CALGARY empty) (list "Calgary")) ;base case of appending a single eleemnt to an empty list
(check-expect (cons-name CALGARY (list "Vancouver")) (list "Calgary" "Vancouver")) ;;appending a single elelemtn to a non empty list

;;(define (cons-name r lor) empty) ;; this is the stub

;; <Using template from region>

(define (cons-name r l)
  (cons (region-name r) l))
          





















