(ns satisfacao.main
  (:require
   [satisfacao.core :as core]
   [compojure.core :refer :all]
   [org.httpkit.server :as server]
   [clojure.data.json :as json]
   [clj-http.client :as client]
   [clojure.walk :refer [keywordize-keys]]
   [ring.util.codec :refer [form-decode]])) ; httpkit is a server

(defroutes app
  (GET "/" [] "API SATISFACAO")
  (GET "/:tabela:args" [tabela args]  
       (handle-get tabela (keywordize-keys
                           (form-decode args))))

(defn handle-get
  [tabela args] 
  (when (core/tem-tabela? tabela)
    (let [where-vector [(reduce-kv (fn [acc k v] 
                                     (str acc " "
                                          (.substring (str k) 1) "=" v))
                                   ""
                                   args)]]
      (json/write-str
       (core/selecionar! tabela where-vector)))))

(defn -main []
  (server/run-server app {:port 5000}))

(-main)
