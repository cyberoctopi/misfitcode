(ns misfitcode.joy.ch2)

((fn [x y]
   (println "Making a set")
   #{x y})
 1 2)
(vec)
(vect)

(vector 1 2 3)
(def x 42)
x

(def make-set
  (fn [x y]
    (println "Making a set")
    #{x y}))

(make-set 1 5)

(def make-list0 #(list))
(def make-list2 #(list %1 %2))
(make-list2 1 "blue")

(def make-list2+ #(list %1 %2 %&))

(make-list2+ 1 2 3 4 "new" "mew")


(defn print-down-from [x]
  (when (pos? x)
    (println x)
    (recur (dec x))))

(print-down-from 50)

(defn sum-down-from [sum x]
  (if (pos? x)
    (recur (+ sum x) (dec x))
    sum))

(sum-down-from 0 13)

(defn sum-down-from [initial-x]
  (loop [sum 0, x initial-x]
    (if (pos? x)
      (recur (+ sum x) (dec x))
      sum)))
