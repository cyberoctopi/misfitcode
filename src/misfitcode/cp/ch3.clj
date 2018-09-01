(ns misfitcode.cp.ch3
  (:require [clojure.string :as str]))

'(a b :name 12.5) ;; list
['a 'b :name 12.5] ;; vector
{:name "Chas" :age 34} ;; map
#{1 2 3} ;; set

(def lst '(1 2 3))
(seq lst)

(defn swap-pairs
  [sequential]
  (into (empty sequential)
        (interleave
         (take-nth 2 (drop 1 sequential))
         (take-nth 2 sequential))))

(swap-pairs (apply list (range 10)))

(drop 1 lst)
(empty lst)
(dr)
(defn map-map
  [f m]
  (into (empty m)
        (for [[k v] m]
          [k (f v)])))

(map-map inc (hash-map :z 4 :t 2 :l 34))
(map-map str (hash-map :z 4 :t 2 :l 34))



(let [r (range 3)
      rst (rest r)]
  (prn (map str rst))
  (prn (map #(+ 100 %) r))
  (prn (conj r -1) (conj rst 42)))


(let [s (range 1e6)]
  (time (count s)))

(+ 3 3)


