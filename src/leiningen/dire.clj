(ns leiningen.dire
  (:refer-clojure :exclude [read-string])
  (:require [clojure.edn :refer [read-string]]
            [leiningen.core.eval :refer [eval-in-project]]))

(defn reduce-vals [coll]
  (mapcat (fn [x]
            (if (map? x)
              (reduce-vals x)
              x))
          (vals coll)))

(defn form-require [x]
  (conj (list (symbol x)) 'require))

(defn middleware
  "Load the project's Dire load sites into the JVM."
  [project]
  (let [dependency-map (read-string (slurp (clojure.java.io/resource (:dire project))))
        dependency-seq (reduce-vals dependency-map)
        reqs (map form-require dependency-seq)]
    (update-in project [:injections] concat reqs)))


