;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname |File3-City Name HTDD|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;; Design a data definition to represent the name of the city

; Given below is the data definition of city name data type

; CityName is a String - this is type comment
; Interpret as the name of the city - this is the interpretation

(define CN1 "Boston") ; this is an example of the data type

; data driven template

(define (fn-for-city-name cn)
  (...cn)
  )

;; Template rules used:
;; -atomic non distinct: String