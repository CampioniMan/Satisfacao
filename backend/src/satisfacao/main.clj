(ns satisfacao.main
  (:require
   [satisfacao.core :as core]
   [compojure.core :refer :all]
   [org.httpkit.server :as srvr] 
   [clj-http.client :as client]
   [clojure.walk :refer [keywordize-keys]]
   [ring.util.codec :refer [form-decode]]
   [cheshire.core :refer :all])) ; httpkit is a server

(defonce server (atom nil))

(defn handle-get
  [tabela args] 
  (when (core/tem-tabela? tabela)
    
    (if (empty? args)
      (json/write-str
       (core/selecionar! tabela))
      (let [where-vector-fn (fn [[where-string & values] k v] 
                              (let [values (conj values v)
                                    where-string (->>
                                                  (->
                                                   (str k)
                                                   (.substring 1)
                                                   (str " = ? "))
                                                  (.concat where-string))]
                                (concat [where-string] values))) 
            where-vector (into [] (reduce-kv
                                   where-vector-fn
                                   [""]
                                   args))]
        (generate-string
         (core/selecionar! tabela where-vector))))))

(defroutes app
  (GET "/" [] "API SATISFACAO")
  (GET "/:tabela/:args" [tabela args]  
       (handle-get tabela (keywordize-keys
                           (form-decode args))))
  (GET "/:tabela" [tabela]  
       (handle-get tabela [])))

(defn -main []
  (reset! server (srvr/run-server app {:port 5000})))

(defn stop-server! []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil)))

(defn restart-server! []
  (do
    (stop-server!)
    (-main)))
