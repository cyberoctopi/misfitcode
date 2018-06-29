(ns misfitcode.joy.ch1)

(for [x [:a :b], y (range 5) :when (odd? y)] [x y])
(doseq [x [:a :b], y (range 5) :when (odd? y)]
  (prn x y))

(defn r->lfix
  ([a op b]              (op a b))
  ([a op1 b op2 c]       (op1 a (op2 b c)))
  ([a op1 b op2 c op3 d] (op1 a (op2 b (op3 c d)))))

(r->lfix 1 + 2)
(r->lfix 1 + 2 + 3)
(r->lfix 1 + 2 * 3)

(defn l->rfix
  ([a op b]              (op a b))
  ([a op1 b op2 c]       (op2 c (op1 a b)))
  ([a op1 b op2 c op3 d] (op3 d (op2 c (op1 a b)))))

(l->rfix 10 * 2 + 3)
(l->rfix 1 + 2 + 3)
(l->rfix 1 + 2 * 3) ;; BREAKS of course

;; A better approach
(def order {+ 0  - 0
            * 1  / 1})

(defn infix3 [a op1 b op2 c]
  (if (< (get order op1) (get order op2))
    (r->lfix a op1 b op2 c)
    (l->rfix a op1 b op2 c)))


(infix3 1 + 2 * 3)
(infix3 10 * 2 + 3)

(def numbers [1 2 3 4 5])
(apply + numbers)


(defprotocol Concatenatable
  (cat [this other]))

(extend-type String
  Concatenatable
  (cat [this other]
    (.concat this other)))

(cat "House" " of Leaves")

(extend-type java.util.List
  Concatenatable
  (cat [this other]
    (concat this other)))
(cat [1 2 3] [4 5])
