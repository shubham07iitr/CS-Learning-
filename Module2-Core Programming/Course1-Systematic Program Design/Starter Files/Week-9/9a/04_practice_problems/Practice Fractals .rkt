;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname |Practice Fractals |) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
(require 2htdp/image)

(beside (circle 10 "solid" "blue") (above (circle 10 "solid" "blue") (above (circle 25 "solid" "blue") (circle 10 "solid" "blue"))) (circle 10 "solid" "blue"))

(above (rectangle 30 10 "solid" "blue")
       (above (rectangle 30 5 "solid" "white")
              (beside (rectangle 10 10 "solid" "blue") (rectangle 10 10 "solid" "white") (rectangle 10 10 "solid" "blue"))))