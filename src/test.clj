(ns test
  (:require [clojure.spec.alpha :as s]
            [problem2.invoice-spec :as spec])
  (:import (java.util Date)))


(def invoice {
              :invoice/issue-date
              :invoice/customer
              :invoice/items
             })
(s/def ::invoice
  (s/keys :req [:invoice/issue-date
                :invoice/customer
                :invoice/items]))
(s/explain ::invoice invoice)
(if (s/valid? ::invoice invoice)
  (println true)
  (println false)
  )

