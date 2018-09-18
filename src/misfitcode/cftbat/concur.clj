(ns misfitcode.cftbat.concur)

 (let [result (future (Thread/sleep 5000) 
                           (+ 11))]
        (println "deref: " @result)
        (println  "Waiting on you...."))
