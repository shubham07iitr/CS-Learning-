;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname |File4-Cat Starter HydW|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;; Code a function which simulates a cat image moving left to right on screen
;; In second part pressing the space key should move the cat to left edge of the screen




(require 2htdp/image)
(require 2htdp/universe)

;; My world program  (make this more specific)
;; A cat that walks from left to right across the screen

;; =================
;; Constants:

(define WIDTH 600)
(define HEIGHT 400)
(define CTR-Y (/ HEIGHT 2)) ; defining the y coordinate
(define SPEED 3)

(define MTS (overlay (rectangle WIDTH HEIGHT "solid" "blue") (empty-scene WIDTH HEIGHT)))
(define CAT-IMG (bitmap "cat.png"))


;; =================
;; Data definitions:

;; Cat is Number
;; interp. x position of the cat in screen
(define C1 0)  ; left edge
(define C2 (/ WIDTH 2)) ; middle
(define C3 WIDTH) ; right edge
#;
(define (fn-for-cat c) ; template for cat 
  (...c))

;; templates rules used:
;; - atomic non distinct: Number


;; =================
;; Functions:

;; Cat -> Cat
;; start the world with main 0
;; 
(define (main c)
  (big-bang c                           ; Cat
            (on-tick   advance-cat)     ; Cat -> WS
            (to-draw   render)          ; Cat -> Image
            (on-key    handle-key)))    ; Cat KeyEvent -> Cat

;; Cat -> Cat
;; produce the next cat, by advancing it 1 pixel to right
(check-expect (advance-cat 3) (+ 3 SPEED)) 

;(define (advance-cat c) 0) ; stub

; <use template from Cat>

(define (advance-cat c)
  (+ c SPEED))
  

;; Cat -> Image
;; render the cat image at appropriate place on MTS
(check-expect (render 4) (place-image CAT-IMG 4 CTR-Y MTS))
; (define (render c) MTS) ; stub
; <use template from Cat>

(define (render c)
  (place-image CAT-IMG c CTR-Y MTS))




; Cat KeyEvent -> Cat
;; reset cat to left edge when space key is pressed
(check-expect (handle-key 10 " ") 0) ;; here " " represents the space key
(check-expect (handle-key 10 "a") 10) ;; nothing is supposed to happen if any other key is presed
(check-expect (handle-key 0 " ") 0)
(check-expect (handle-key 0 "a") 0)

;(define (handle-key c ke) 0) ; stub

(define (handle-key c ke)
  (cond [(key=? ke " ") 0]
        [else c]))








