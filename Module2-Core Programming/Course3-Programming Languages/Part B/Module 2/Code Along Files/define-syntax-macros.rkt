#lang racket

(provide (all-defined-out))

;; a cosmetic macro -- adds then, else
;; will help us write (my-if foo then bar else baz) > (if foo bar baz)

(define-syntax my-if
  (syntax-rules (then else) ;; list of other keywords other than my-if
          [(my-if e1 then e2 else e3) ;; if you see something like this
           (if e1 e2 e3)])) ;; expand it to like this



;; a macro to repplace an expresison with another one
;; (comment-out (car null) (+ 3 4)) -> (+ 3 4)

(define-syntax comment-out
  (syntax-rules ()
    [(comment-out ignore instead) instead]))


