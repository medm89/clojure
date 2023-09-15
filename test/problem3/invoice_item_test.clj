(ns problem3.invoice-item-test
  (:require [clojure.test :refer :all]
            [problem3.invoice-item :refer :all]))
(deftest test-subtotal-with-discount
  (testing
    (is (= 90.0 (subtotal {:invoice-item/precise-quantity 10
                           :invoice-item/precise-price    10
                           :invoice-item/discount-rate    10}))
        )
    )
  )
(deftest test-subtotal-without-discount
  (testing
    (is (= 100.0 (subtotal {:invoice-item/precise-quantity 10
                            :invoice-item/precise-price 10
                            }
                           )
                  )
               )
    )
  )

(deftest test-subtotal-with-default-discount
  (testing
    (is (= 100.0 (subtotal {:invoice-item/precise-quantity 10
                            :invoice-item/precise-price 10
                            :invoice-item/discount-rate 0
                            }
                           )
           )
        )
    )
  )
(deftest test-subtotal-with-large-quantity
  (testing
    (is (= 1000.0 (subtotal {:invoice-item/precise-quantity 100
                             :invoice-item/precise-price 10
                             :invoice-item/discount-rate 0
                             }
                            )
           )
        )
    )
  )
(deftest test-subtotal-with-small-quantity
  (testing
    (is (= 0.1 (subtotal {:invoice-item/precise-quantity 0.01
                          :invoice-item/precise-price 10
                          :invoice-item/discount-rate 0
                          }
                         )
           )
        )
    )
  )
(deftest test-subtotal-with-zero-quantity
  (testing
    (is (= 0.0 (subtotal {:invoice-item/precise-quantity 0
                          :invoice-item/precise-price 10
                          :invoice-item/discount-rate 0
                          }
                         )
           )
        )
    )
  )
(deftest test-subtotal-with-zero-price
  (testing
    (is (= 0.0 (subtotal {:invoice-item/precise-quantity 10
                          :invoice-item/precise-price 0
                          :invoice-item/discount-rate 0
                          }
                         )
           )
        )
    )
  )
(deftest test-subtotal-with-negative-price
  (testing
    (is (= -10.0 (subtotal {:invoice-item/precise-quantity 10
                            :invoice-item/precise-price -1
                            :invoice-item/discount-rate 0
                            }
                           )
           )
        )
    )
  )

(run-tests 'problem3.invoice-item-test)