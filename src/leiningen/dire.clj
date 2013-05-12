(ns leiningen.dire
  (:refer-clojure :exclude [read-string])
  (:require [clojure.edn :refer [read-string]]
            [leiningen.core.eval :refer [eval-in-project]]))

(defn middleware
  "Load the project's Dire load sites into the JVM."
  [project]
  (let [deps (read-string (slurp (clojure.java.io/resource (:dire project))))]
    (assoc project :injections deps)))

