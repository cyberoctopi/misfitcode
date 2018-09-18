(ns misfitcode.cftbat.ch5
  (:require [clojure.string :as s]))

(defn wisdom
  [words]
  (str words ", Daniel-san"))

(wisdom "watch yourself")


(defn year-end-evaluation
  []
  (if (> (rand) 0.5)
    "You get a raise!"
    "Better luck next time, sucker!"))

(year-end-evaluation)

(defn analysis
  [text]
  (str "Character count: " (count text)))

(defn analyze-file
  [filename]
  (analysis (slurp filename)))
(analyze-file "/Users/aku/Documents/xxx.txt")

(def great-baby-name "Rosanthony")

(let [great-baby-name "Bloodthunder"]
  great-baby-name)
great-baby-name


(defn sum
  ([vals] (sum vals 0))
  ([vals accumulating-total]
   (if (empty? val)
     accumulating-total
     (sum (rest vals) (+ (first vals) accumulating-total)))))


(sum [39 5 1])
(sum [39 5 1] 25)
(sum [5 1] 39)

(defn sum
  ([vals]
   (sum vals 0))
  ([vals accumulating-total]

   (if (empty? vals)
     accumulating-total
     (recur (rest vals) (+ (first vals) accumulating-total)))))

(defn clean
  [text]
  (s/replace (s/trim text) #"lol" "LOL"))

(clean "Oh my god lol!")

;;-- comp ------------------
;; Composing functions...

((comp inc *) 5 3)

(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})

(def c-int (comp :intelligence :attributes))
(def c-str (comp :strength :attributes))
(def c-dex (comp :dexterity :attributes))


(c-int character)
(c-str character)
(c-dex character)

(def c-test (fn [c] (:strength (:attributes c))))

(c-test character)

(defn spell-slots
  [char]
  (int (inc (/ (c-int char) 2))))
(spell-slots character)


(defn two-comp
  [f g]
  (fn [& args]
    (f (apply g args))))


(defn sleep-identity
  "Returns the given value after 1 second"
  [x]
  (Thread/sleep 5000) x)

(sleep-identity "Mr. Fantastico")


(def memo-sleepy-identity (memoize sleep-identity))
(memo-sleepy-identity "Mr. Fantastico")
(memo-sleepy-identity "Mr. Fantastio") ;; so if you change the input value it checks and will go back to the original wait period


(defn tri*
  "Generates a lazy sequence of triangular numbers"
  ([] (tri* 0 1))
  ([sum n]
   (let [new-sum (+ sum n)]
     (cons new-sum (lazy-seq (tri* new-sum (inc n)))))))

(def tri (tri*))
(take 20 tri)

(defn triangular?
  "Is the number triangular? e.g 1, 3, 6, 10, 15, etc"
  [n]
  (= n (last (take-while #(>= n %) tri))))

(triangular? 5)
                                        ; => false

(triangular? 6)
                                        ; => true
(defn row-tri
  "The triangular number at the end of row n"
  [n]
  (last (take n tri)))
(row-tri 1)
(row-tri 2)
(row-tri 5)

(defn row-num
  "Returns row number the position belongs to: pos1 in row 1, positions 2 and 3 in row 2, etc"
  [pos]
  (inc (count (take-while #(> pos %) tri))))
(row-num 1)
(row-num 15)

(defn connect
  "Form a mutual connection between two positions"
  [board max-pos pos neighbor destination]
  (if (<= destination max-pos)
    (reduce (fn [new-board [p1 p2]]
              (assoc-in new-board [p1 :connections p2] neighbor))
            board
            [[pos destination] [destination pos]])
    board))

(connect {} 15 1 2 4)

(assoc-in {} [:cookie :monster :vocals] "Finntroll")
(last (get-in {} {:cookie {:monster {:vocals "Finntroll"}}} [:cookie :monster]))
(assoc-in {} [1 :connections 4] 2)

(defn connect-right
  [board max-pos pos]
  (let [neighbor (inc pos)
        destination (inc neighbor)]
    (if-not (or (triangular? neighbor) (triangular? pos))
      (connect board max-pos pos neighbor destination) board)))

(defn connect-down-left
  [board max-pos pos]
  (let [row (row-num pos)
        neighbor (+ 1 row pos)
        destination (+ 2 row neighbor)]
    (connect board max-pos pos neighbor destination)))

(defn connect-down-right
  [board max-pos pos]
  (let [row (row-num pos)
        neighbor (+ 1 row pos)
        destination (+ 2 row neighbor)]
    (connect board max-pos pos neighbor destination)))

(connect-down-left {} 15 1)
(connect-down-right {} 12 2)

(defn add-pos
  "Pegs the position and performs connections"
  [board max-pos pos]
  (let [pegged-board (assoc-in board [pos :pegged] true)]
    (reduce (fn [new-board connection-creation-fn]
              (connection-creation-fn new-board max-pos pos))
            pegged-board
            [connect-right connect-down-left connect-down-right])))

(add-pos {} 15 1)

