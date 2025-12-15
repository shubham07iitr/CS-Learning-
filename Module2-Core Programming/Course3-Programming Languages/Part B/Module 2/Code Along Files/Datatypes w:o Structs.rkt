#lang racket


; datatype exp = Const of int | Negate of exp | Add of exp*exp | Multiply of exp*exp

; just helper functions that make lists where first element is a
; symbol

(define (Const i) (list 'Const i)) ; 'Const means a tagged symbol 
(define (Negate e) (list 'Negate e))
(define (Add e1 e2) (list 'Add e1 e2))
(define (Multiply e1 e2) (list 'Multiply e1 e2))


;; just helper functions that test what "kind of exp"
;; Note: More robust could raise better errors for non-exp values

(define (Const? x) (eq? (car x) 'Const)) ;; eq? is a built in operator to compare symblos
(define (Negate? x) (eq? (car x) 'Negate))
(define (Add? x) (eq? (car x) 'Add))
(define (Multiply? x) (eq? (car x) 'Multiply))

;; just hehlper functions that get the pieces for "one kind of exp"

(define (Const-int e) (car (cdr e)))
(define (Negate-e e) (car (cdr e)))
(define (Add-e1 e) (car (cdr e)))
(define (Add-e2 e) (car (cdr (cdr e))))
(define (Multiply-e1 e) (car (cdr e)))
(define (Multiply-e2 e) (car (cdr (cdr e))))

;; defiining the interpreter

(define (eval-exp e)
  (cond [(Const? e) e]
        [(Negate? e) (Const (- (Const-int (eval-exp (Negate-e e)))))]
        [(Add? e) (let ([v1 (Const-int (eval-exp (Add-e1 e)))]
                        [v2 (Const-int (eval-exp (Add-e2 e)))])
                    (Const (+ v1 v2)))]
        [(Multiply? e) (let ([v1 (Const-int (eval-exp (Multiply-e1 e)))]
                        [v2 (Const-int (eval-exp (Multiply-e2 e)))])
                    (Const (* v1 v2)))]
        [#t (error "eval-exp expected an exp")]))
                        





