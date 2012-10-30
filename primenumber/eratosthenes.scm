#!/usr/bin/gosh
;;
;; Copyright 2012 agwlvssainokuni
;;
;; Licensed under the Apache License, Version 2.0 (the "License");
;; you may not use this file except in compliance with the License.
;; You may obtain a copy of the License at
;;
;;     http://www.apache.org/licenses/LICENSE-2.0
;;
;; Unless required by applicable law or agreed to in writing, software
;; distributed under the License is distributed on an "AS IS" BASIS,
;; WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
;; See the License for the specific language governing permissions and
;; limitations under the License.
;;

(define (main args)
  (let ((max_num (string->number (cadr args)))
	(count 0)
	(not_prime (make-hash-table)))

    (do ((num 2 (+ num 1)))
	((> num max_num) count)
      (unless (hash-table-delete! not_prime num)
	      (print num)
	      (set! count (+ count 1))
	      (let ((imax (quotient max_num num)))
		(do ((i num (+ i 1)))
		    ((> i imax) imax)
		  (hash-table-put! not_prime (* i num) #t)))
	      ))
    ))
