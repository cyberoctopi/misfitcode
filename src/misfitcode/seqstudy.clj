(ns misfitcode.seqstudy)

; basic seq functionality
(first '(1 2 3))
(rest '(1 2 3))
(cons 0 '(1 2 3))
(conj 4 '(1 2 3 4)) ;; can't
(conj  '(1 2 3 4) 5) ;; add to end
;; remember that cons returns sequences while conj returns collections
;; conj also takes any number of arguments while cons takes one
;; https://stackoverflow.com/questions/3008411/clojure-consseq-vs-conjlist
;; rest/cons return seqstudy
(seq? (rest [1 34 "blue"]))
(seq? (rest {:1 "two"}))
;; sets as well
(rest #{1 2 3 4})
(seq? (rest #{1 2 3 4}))
(cons 4 #{1 2 3 4}) ;; note cons adds to beginning of a set...or possibly no specific order? Yes, no specific order!

;; sorted maps
;;Returns a new sorted map with supplied mappings.  If any keys are equal, they are handled as if by repeated uses of assoc.
(sorted-map :c 3 :b 2 :a 22)
;; conj - adds one or more items to a collections
(conj '(1 2 3) 4)
;; into - adds all items from one collection into another
(into '(1 2 3) '(:a :b :c))
(sort (into '(1 2 3) '(:a :b :c)))
(sorted-set (into '(1 2 3) '(:a :b :c))) ;; hmmm? =)
;; !!! for vectors, add items after the collection
(conj [ 1 2 3] 4)
(into [1 2 3] [:4 :5])
;; remember rest/cons etc return 'seqs'; seqs are logical list
(def test (agent 2))

(send test inc)
(f)
;; EXPLAINING THE SEQ LIBARY
;; first/rest/cons
;; all functions do the following
;; (1) - create sequences
;; (2) - filter sequences
;; (3) - seq predicates
;; (4) - transform sequences

;; COMMON SEQ functions
;; range, repeat, iterate, take, cycle, interleave, interpose, join,


;; Clojure Standard Library chapter 9 & 10
;; A sequence is an abstract data type (ADT)
;; Describes the behavior of a data stuctue without mandaing a specific implementation
;; ISeq is an interface.  PersistentList implements this interface (the building block for other clojure data structures)

;;__________________________________________________________________________________________________
;; “It’s iterated sequentially: you can’t access the nth element without first accessing the nth - 1.
;; Works like a stateless cursor: the iteration can only move forward and consumed by the same caller who started the iteration.
;; It’s commonly (but not necessarily) lazy: the next element is produced only if that element is requested.
;; It’s persistent and immutable: like all other core data structures, sequences cannot be altered once created, but changes are possible in terms of a new sequence based on the previous (with structural sharing).”
;; Excerpt From: Renzo Borgatti. “Clojure Standard Library: An annotated reference MEAP V15.” iBooks.
;;__________________________________________________________________________________________________
(split-at 5 (range 10))
(map #(format "<p>%s</p>" %) ["test" "tube" "blue"])
(reduce + (range 1 11))

(def x (range 1 5))
x

(defn foo [n]
  (str n))
(foo (first x))
(foo (rest x))
(foo (nth x 0))
