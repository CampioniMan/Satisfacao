(ns user.core)

(defn start []
  (require '[clojure.java.io :as io]
           '[clojure.string :as str]
           '[clojure.pprint :refer (pprint)]
           '[clojure.repl :as repl]
           '[clojure.test :as test]
           '[clojure.tools.namespace.repl :refer (refresh refresh-all)]
           
           '[satisfacao.main :as main]))
