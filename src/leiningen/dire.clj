(ns leiningen.dire
  (:refer-clojure :exclude [read-string])
  (:require [clojure.edn :refer [read-string]]))

(defn dire
  "Load the project's Dire load sites into the JVM."
  [project & args]
  (let [deps (clojure.java.io/resource "resources/dire/dependencies.edn")]
    (doseq [namespace (read-string (slurp deps))]
      (require namespace))))

