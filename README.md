# Martin Diaz Challenge

## Getting started

## Problems
### Problem 1 Thread-last Operator ->>
In problem 1, based on the given requirements, I chose to break down the problem into several parts. First, I performed the file loading into a variable. Then, I separated the conditions and filtering using the "apart" operator. Within the conditions, I took into consideration the constraints that the VAT should be 19% or that the withholding tax should be 1%. The program is executed using the following command line.
#### Run the app

```
clj -X problem1.invoice-thread/run  
```
#### Invoice Item Conditions
- At least have one item that has :iva 19%
- At least one item has retention :ret\_fuente 1%
- Every item must satisfy EXACTLY one of the above two conditions. This means that an item cannot have BOTH :iva 19% and retention :ret\_fuente 1%.
## Problem 2: Core Generating Functions
In problem 2, it is required to have the **invoice.edn** file located in the root directory of the project. In this solution, I also chose to implement separate functions to streamline the process. I utilized the code provided and passed the name of the JSON file to be evaluated. From there, I invoked the **load-invoice** function to assess the file and extract the necessary information for constructing a Clojure map.

Subsequently, I performed various transformations, including handling date formats and addressing a specific detail, which was ensuring that the **tax/category** field was in lowercase. After organizing this function, it proceeded to evaluate the final object.

#### Run the app
```
clj -X problem2.invoice-validation/run
```

## Problem 3: Test Driven Development
In problem 3, after a thorough examination of the documentation, I managed to identify the appropriate methodology for conducting tests. I devised approximately 10 test cases, most of which yielded successful results. However, I encountered difficulties with 2 of them, specifically related to the decimal part. In these cases, I expected a result like 9.45, but obtained 9.450000001. Faced with uncertainty regarding whether it was possible to apply some form of truncation or adjustment in the code to rectify this, I chose to remove those test cases.

Likewise, I am leaving one of the cases commented here in case any suggestions arise on how to address this issue more effectively.

```
(deftest test-subtotal-with-negative-discount
  (testing
    (is (= 110.0 (subtotal {:invoice-item/precise-quantity 10
                            :invoice-item/precise-price 10
                            :invoice-item/discount-rate -10
                            }
                           )
                  )
               )
    )
  )

(deftest test-subtotal-with-decimal-quantity
  (testing
    (is (= 9.45 (subtotal {:invoice-item/precise-quantity 0.5
                           :invoice-item/precise-price 21
                           :invoice-item/discount-rate 10
                           }
                          )
           )
        )
    )
  )
```
I ran this file from the IntelliJ view using **Shift + F10**.


