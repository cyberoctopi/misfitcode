(ns misfitcode.mc.ch2.refs)

(def state (ref 0))

@state
(dosync (ref-set state 1))
@state
(dosync (alter state + 33))
(dosync (alter state + 2))
