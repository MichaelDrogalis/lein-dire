(ns leiningen.dire
  (:refer-clojure :exclude [read-string add-classpath])
  (:require [clojure.edn :refer [read-string]]
            [cemerick.pomegranate :refer [add-classpath]]))

(defn activate
  "Load the project's Dire load sites into the JVM."
  []
  (prn "Executing")
  (let [deps (clojure.java.io/resource "resources/dire/dependencies.edn")]
    (add-classpath "/home/xpherior/scratch/dire-examples/src")
    (doseq [namespace (read-string (slurp deps))]
      (require namespace))))

