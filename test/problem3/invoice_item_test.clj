(ns problem3.invoice-item-test
  (:require [clojure.test :refer :all]
            [problem3.invoice-item :refer :all]))
(deftest test-subtotal-with-discount
  (is (= 90.0 (subtotal {:precise-quantity 10 :precise-price 10 :discount-rate 10}))))

(deftest test-subtotal-without-discount
  (is (= 100.0 (subtotal {:precise-quantity 10 :precise-price 10}))))

(deftest test-subtotal-with-default-discount
  (is (= 95.0 (subtotal {:precise-quantity 10 :precise-price 10 :discount-rate 0}))))

(deftest test-subtotal-with-negative-discount
  (is (= 110.0 (subtotal {:precise-quantity 10 :precise-price 10 :discount-rate -10}))))

(deftest test-subtotal-with-decimal-quantity
  (is (= 10.5 (subtotal {:precise-quantity 0.5 :precise-price 21 :discount-rate 10}))))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 0))))