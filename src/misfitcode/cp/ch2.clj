(ns misfitcode.cp.ch2
  (:require [clojure.string :as str]))



(reduce
 (fn [m v]
   (assoc m v (* v v)))
 {}
 [1 2 3 4])

(def only-strings (partial filter string?))
(only-strings ["a" 5 "b" 6])

(def camel->keyword (comp keyword
                          str/join
                          (partial interpose \-)
                          (partial map str/lower-case)
                          #(str/split % #"(?<=[a-z])(?=[A-Z])")))

(camel->keyword "CamelCase")

(defn camel-keyword [s]
  (->> (str/split s #"(?<=[a-z])(?=[A-Z])")
     (map str/lower-case)
     (interpose \-)
     str/join
     keyword))
(use 'clojure.tools.trace)
(camel-keyword "HuluPower")
(trace camel-keyword "HuluPower")

(trace/trace-form (camel->keyword "VoodooHoodoo") )
(clojure.tools.trace/trace-ns *ns*)

(defn adder
  [n]
  (fn [x] (+ n x)))
((adder 4) 2)

(def names {:first-name "Jamal" :last-name "Burgess"})

(type )
(vals  names)
