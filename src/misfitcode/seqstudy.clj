(ns misfitcode.seqstudy)

; basic seq functionality
(first '(1 2 3))
(rest '(1 2 3))
(cons 0 '(1 2 3))
(conj 4 '(1 2 3 4)) ;; can't
(conj  '(1 2 3 4) 5) ;; add to end
;; remember that cons returns sequences while conj returns collections
;; conj also akes any number of arguments while cons takes one
;; https://stackoverflow.com/questions/3008411/clojure-consseq-vs-conjlist
