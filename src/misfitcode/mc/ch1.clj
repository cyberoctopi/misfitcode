(ns misfitcode.mc.ch1)

(defn fibo
  ([n]
   (fibo [0N 1N] n))
  ([xs n]
   (if (<= n (count xs))
     xs
     (let [x'  (+ (last xs)
                 (nth xs (- (count xs) 2)))
           xs' (conj xs x')]
       (fibo xs' n)))))

(fibo 1)
(last (fibo 100))
(cons 0 '(1 2 3))
(conj [2 113] 5)
