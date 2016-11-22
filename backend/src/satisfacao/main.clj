(ns satisfacao.main
  (:require
   [satisfacao.core :as core]
   [compojure.core :refer :all]
   [org.httpkit.server :as srvr]
   [clojure.data.json :as json]
   [clj-http.client :as client]
   [clojure.walk :refer [keywordize-keys]]
   [ring.util.codec :refer [form-decode]])) ; httpkit is a server

(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil)))

(defn restart-server []
  (do
    (stop-server)
    (-main)))

(defn handle-get
  [tabela args] 
  (when (core/tem-tabela? tabela)
    
    (if (empty? args)
      (json/write-str
       (core/selecionar! tabela))
      (let [where-vector [(reduce-kv (fn [acc k v] 
                                       (str acc " "
                                            (.substring (str k) 1) "=" v))
                                     ""
                                     args)]]
        (json/write-str
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

