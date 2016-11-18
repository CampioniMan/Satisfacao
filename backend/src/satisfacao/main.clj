(ns satisfacao.main
  (:require
   [satisfacao.core :as core]
   [compojure.core :refer :all]
   [org.httpkit.server :refer [run-server]])) ; httpkit is a server
 
 (defroutes app
   (GET "/" [] "API SATISFACAO")
   (GET "/Atendimento" [] "API SATISFACAO"))
 
 (defn -main []
   (run-server app {:port 5000}))
