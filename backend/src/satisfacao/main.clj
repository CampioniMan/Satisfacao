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

(defn gen-where-vector
  [map]
  (let [where-vector-fn
        (fn [[where-string & values] k v] 
          (let [values (conj (into [] values) v)
                where-string (->>
                              (->
                               (str " AND " k) 
                               (str " = ?"))
                              (.concat where-string))]
            (concat [where-string] values)))] 
    (into [] (reduce-kv
              where-vector-fn
              ["1=1"]
              map))))

(defn handle-get
  ([tabela args] 
   (->>
    (if (empty? args)
      (core/selecionar! tabela)
      (let [where-vector (gen-where-vector args)]
        (core/selecionar! tabela where-vector)))
    (generate-string)))
  ([tabela]
   (generate-string (core/selecionar! tabela))))

(defn handle-put
  [tabela args]
  (if (core/inserir! tabela args)
    (->>
     (handle-get tabela args))
    "[]"))

(defn handle-delete
  [tabela args]
  (let [where-vector (gen-where-vector args)]
    (if-let [ret (core/selecionar! tabela where-vector)]
      (do (core/deletar! tabela where-vector)
          (generate-string ret))
      "[]")))


(defn map-url-params
  [s]
  (form-decode s))

(defroutes app-routes
  (GET "/" [] "API SATISFACAO")
  
  (GET "/:tabela/:args" [tabela args]  
    (handle-get tabela (map-url-params args)))
  
  (GET "/:tabela" [tabela]
    (handle-get tabela {}))
  
  (PUT "/:tabela/:args" [tabela args]
    (handle-put tabela (map-url-params args)))
  
  (DELETE "/:tabela/:args" [tabela args]
    (handle-delete tabela (map-url-params args))))


(def app
  (-> app-routes))


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
