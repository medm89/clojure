(ns test
 )

(use 'clojure.test)


(deftest addition
  (is (= 4 (+ 2 2)))
  (is (= 7 (+ 3 4))))


(deftest subtraction
  (is (= 1 (- 4 3)))
  (is (= 3 (- 7 4))))

(deftest- operation
          (is (= 110 (* 10 10 (- 1 (/ -10 100.0))) ) )
          )
;composing tests
(deftest arithmetic
  (addition)
  (subtraction)
  (operation))


(run-tests 'test)
