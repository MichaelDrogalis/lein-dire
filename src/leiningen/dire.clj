(ns leiningen.dire
  (:refer-clojure :exclude [read-string add-classpath])
  (:require [clojure.edn :refer [read-string]]
            [cemerick.pomegranate :refer [add-classpath]]))

(defn dire
  "Load the project's Dire load sites into the JVM."
  [project & args]
  (let [deps (clojure.java.io/resource "resources/dire/dependencies.edn")]
     (apply add-classpath (:source-paths project))
    (doseq [namespace (read-string (slurp deps))]
      (require namespace))))

