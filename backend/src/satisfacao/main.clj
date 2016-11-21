(ns satisfacao.main
  (:require
   [satisfacao.core :as core]
   [compojure.core :refer :all]
   [org.httpkit.server :refer [run-server]])) ; httpkit is a server
 
(defroutes app
  (GET "/" [] "API SATISFACAO")
  (GET "/:tabela/:args" [tabela args]
       (handle-tabela-args tabela args)))

(defn handle-tabela-args
  [tabela args])

(defn -main []
  (run-server app {:port 5000}))
