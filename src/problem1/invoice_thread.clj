(ns problem1.invoice-thread
  )
(def invoice (clojure.edn/read-string (slurp "invoice.edn")))

(defn conditions? [item]
  (let [has-iva? (some #(= 19 (:tax/rate %)) (:taxable/taxes item))
        has-retention? (some #(= 1 (:retention/rate %)) (:retentionable/retentions item))]
    (and (or has-iva? has-retention?)
         (not (and has-iva? has-retention?))))
  )
(defn get-file-info [invoice]
  (->> (:invoice/items invoice)
       (filter conditions?))
  )

(def result (get-file-info invoice))

(defn run
  "Function to process invoice items and check conditions"
  [opt]
  (pr result)
  )
