;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname |Helper File Max Exits to |) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
;; ======================================================
;; CONSTANTS

(define LEMPTY empty)
(define L0 (list "a"))
(define L1 (list "a" "a" "a" "b" "b" "b" "c" "c" "c" "c"))
(define L2 (list "a" "a" "b" "b" "c" "c" "c"))


;; ======================================================
;; FUNCTION MAX-OCCUR
;;Signature -> ListofElement -> Element
;; Takes in a list of elements and produces the most frequently occuring element
;; If there is a tie, the produce the element which came in later

(check-expect (max-occur L0) "a") ;; base case of element only once
(check-expect (max-occur L1) "c") ;; compound case where every element occurs more than once
(check-expect (max-occur L2) "c") ;; compound case where there is a tie and we produce the element which comes in later
(check-expect (max-occur LEMPTY) empty) ;; empty list produces empty as the output 

;;(define (max-occur loe) "") ;; this is the stub

;; template for standard List of Element

#;
(define (fn-for-loe loe)
  (cond [(empty? loe) empty]
        [else
         (...(first loe)
             (fn-for-loe (rest loe)))]))




(define (max-occur loe)
  (local [
          ;;count-element will take in an loe and an element and produce count of how many times that element occured in the list
          (define (count-element loe e)
            (cond [(empty? loe) 0]
                  [else
                   (if (string=? (first loe) e)
                       (+ 1 (count-element (rest loe) e))
                       (count-element (rest loe) e))]))

          ;;count-list will take in an loe will produce for each element, how many times the element occurs in the list
          (define (count-list loer loe) ;;both same loer and loe, however one will be recursed over and one will stay constant
            (cond [(empty? loer) empty]
                  [else
                   (cons (count-element loe (first loer)) (count-list (rest loer) loe))]))

          ;; max-count loe will take in a list of numbers  and produces the largest number in the list
          ;; acc to be used to capture the max element till now
          (define (max-count loe acc)
            (cond [(empty? loe) acc]
                  [else
                   (if (> (first loe) acc)
                       (max-count (rest loe) (first loe))
                       (max-count (rest loe) acc))]))
          
          ;; captures the max occurence of any element in a given list
          (define COUNT-MAX (max-count (count-list loe loe) 0)) 
          
          ;; main function which will produce the most frequently occuring element
          (define (max-occur loer loe) ;; loer will recurse, loe will stay constant
            (cond [(empty? loer) empty]
                  [else
                   (if (= (count-element loe (first loer)) COUNT-MAX)
                       (first loer)
                       (max-occur (rest loer) loe))]))]
    (max-occur loe loe)))



;; Testing each of the local functions individually

;; FUNCTION MAX-COUNT
;; List of Number , Acc -> Number
;; max-count loe will take in a list of numbers  and produces the largest number in the list
;; acc to be used to capture the max element till now
(check-expect (max-count (list 3 4 2 1 5 1) 0) 5) 
(check-expect (max-count (list 3 20 2 1 5 1) 0) 20)

(define (max-count loe acc)
  (cond [(empty? loe) acc]
        [else
         (if (> (first loe) acc)
             (max-count (rest loe) (first loe))
             (max-count (rest loe) acc))]))


  
;; FUNCTION COUNT-ELEMENT
;; Listof Element, Element -> Number
;; will take in an loe and an element and produce count of how many times that element occured in the list
(check-expect (count-element L2 "c") 3)
(check-expect (count-element L2 "d") 0)

(define (count-element loe e)
  (cond [(empty? loe) 0]
        [else
         (if (string=? (first loe) e)
             (+ 1 (count-element (rest loe) e))
             (count-element (rest loe) e))]))

  

;; FUNCTION COUNT-LIST
;; List of Element -> List of Number
;;count-list will take in an loe will produce for each element, how many times the element occurs in the list

(check-expect (count-list L2 L2) (list 2 2 2 2 3 3 3))

(define (count-list loer loe) ;; will take in 2 elements both same, one will stay constant and one will be recursed over
  (cond [(empty? loer) empty]
        [else
         (cons (count-element loe (first loer)) (count-list (rest loer) loe))]))



