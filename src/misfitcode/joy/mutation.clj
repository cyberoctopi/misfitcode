(ns misfitcode.joy.mutation
  (:import java.util.concurrent.Executors))

(def thread-pool
  (Executors/newFixedThreadPool
   (+ 2 (.availableProcessors (Runtime/getRuntime)))))

(defn dothreads!
  [f & {thread-count :threads
        exec-count   :times
        :or {thread-count 1 exec-count 1}}]
  (dotimes [t thread-count]
    (.submit thread-pool
             #(dotimes [_ exec-count] (f)))))

(dothreads! #(.print System/out "Hi") :threads 2 :times 2)

(def initial-board
  [[:- :k :-]
   [:- :- :-]
   [:- :K :-]])

(defn board-map [f board]
  (vec (map #(vec (for [s %] (f s))) board)))


(defn reset-board!
  "Resets the board state. Generally these types of functions are a bad idea, but matters of page count force our hand"
  []
  (def board (board-map ref initial-board))
  (def to-move (ref [[:K [2 1] [:k [0 1]]]]))
  (def num-moves (ref 0)))


(defn neighbors
  ([size yx]
     (neighbors [[-1 0] [1 0] [0 -1] [0 1]] size yx))
  ([deltas size yx]
     (filter (fn [new-yx] (every? #(< -1 % size)
                                  new-yx))
             (map #(vec (map + yx %)) deltas))))
(def king-moves
    (partial neighbors
             [[-1 -1] [-1 0] [-1 1] [0 1] [1 -1] [1 0] [1 1]] 3))
  
(defn good-move?
  [to enemy-sq]
  (when (not= to enemy-sq) to))

(defn choose-move
  "randomly choose a legal move"
  [[[mover mpos] [_ enemy-pos]]]
  [mover (some #(good-move? % enemy-pos)
               (shuffle [king-moves mpos]))])

(defn place [from to] to)

(defn move-piece [[piece dest] [[_ src] _]]
  ((alter (get-in board dest) place piece)
   (alter get-in board src) place :-)
  (alter num-moves inc))



;;-- Action ---------------
(reset-board!)
(take 5 (repeatedly #(choose-move @to-move)))





;;-- eric normand ---------------

(def sum (atom 0))
(def count (atom 0))

(defn current-average []
  (/ @sum @count))

;; alternative
(def average (atom [0 0]))
(defn add-to-average [num]
  (swap! average (fn [[n d]] [(+ n num) (+ d 1)])))
