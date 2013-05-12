(ns leiningen.dire
  (:refer-clojure :exclude [read-string])
  (:require [clojure.edn :refer [read-string]]
            [leiningen.core.eval :refer [eval-in-project]]))

(defn middleware
  "Load the project's Dire load sites into the JVM."
  [project]
  (let [deps (apply concat (vals (read-string (slurp (clojure.java.io/resource (:dire project))))))
        reqs (map (fn [x] (conj (list (symbol x)) 'require)) deps)]
    (update-in project [:injections] concat reqs)))

