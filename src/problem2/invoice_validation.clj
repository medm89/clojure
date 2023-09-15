(ns problem2.invoice-validation
  (:require [clojure.spec.alpha :as s]
            [clojure.data.json :as json]
            [clojure.walk :refer [keywordize-keys]]
            [problem2.invoice-spec :as spec])
  (:import (java.text SimpleDateFormat)
           (java.util Date)))

(defn parse-json-to-map [json-str]
  (-> json-str
      (json/read-str)
      keywordize-keys))
(defn parse-date [date-str]
  (let [date-formatter (SimpleDateFormat. "dd/MM/yyyy")
        parsed-date (.parse date-formatter date-str)]
    (Date. (.getTime parsed-date))))
(defn load-invoice [file-name]
  (let [file-content (slurp file-name)
        invoice-map (parse-json-to-map file-content)
        invoice-json (:invoice invoice-map)
        ]
    {:invoice/issue-date (parse-date (:issue_date invoice-json))
     :invoice/customer {:customer/name (:company_name (:customer invoice-json))
                        :customer/email (:email (:customer invoice-json))}
     :invoice/items (vec (map (fn [item]
                                {:invoice-item/price (:price item)
                                 :invoice-item/quantity (:quantity item)
                                 :invoice-item/sku (:sku item)
                                 :invoice-item/taxes (vec (map (fn [tax]
                                                                 {:tax/category (keyword (clojure.string/lower-case (:tax_category tax)))
                                                                  :tax/rate (-> (:tax_rate tax) double)})
                                                               (:taxes item)))})
                              (:items invoice-json)))}
    )
  )

(defn validate-invoice [file-name]
  (if (s/valid? ::spec/invoice (load-invoice file-name))
    (println true)
    (println false)
    )
  ;  (s/explain ::spec/invoice (load-invoice file-name))
  )

(defn run [opt]
  (validate-invoice "invoice.json")
  )
