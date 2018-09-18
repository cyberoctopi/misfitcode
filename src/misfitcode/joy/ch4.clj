(ns misfitcode.joy.ch4)

(def population {:zombies 2700, :humans 9})

(get population :zombies)
(println (/ (get population :zombies)
            (get population :humans))
         "zombies per capita")


(:zombies population)

(println (/ (:zombies population)
            (:humans population))
         "zombies per capita")


(defn pour [lb ub]
  (cond
    (= ub :toujours) (iterate inc lb)
    :else (range lb ub)))

(pour 1 10)

::not-in-ns
(defn do-blowfish [directive]
  (case directive
    :aquarium/blowfish (println "feed the fish")
    :crypto/blowfish   (println "encore the message")
    :blowfish          (println "not sure what to do")))

(ns aquarium)
(misfitcode.joy.ch4/do-blowfish ::blowfish)
