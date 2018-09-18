(ns misfitcode.pc.six)

(def counter (agent 0 :validator number?))

(send counter (fn [_] "boo"))

@counter
(agent-error counter)
(restart-agent counter 0)
@counter
(send counter inc)
@counter 


(defn handler [agent err]
  (println "ERR!" (.getMessage err)))

(def counter2 (agent 0 :validator number? error-handler handler))

(send counter2 (fn [_] "boo"))
(restart-agent counter2 0)
(send counter2 inc)
@counter2
