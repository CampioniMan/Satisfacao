(ns satisfacao.main
  (:require
   [satisfacao.core :as core]
   [compojure.core :refer :all]
   [org.httpkit.server :as srvr] 
   [clj-http.client :as client]
   [clojure.walk :refer [keywordize-keys stringify-keys]]
   [ring.util.codec :refer [form-decode]] 
   [cheshire.core :refer :all])
  (:gen-class)
  (:import
   [java.net BindException]))

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

(def cors-headers 
  { "Access-Control-Allow-Origin" "*"
   "Access-Control-Allow-Headers" "Content-Type"
   "Access-Control-Allow-Methods" "GET,POST,OPTIONS" })
(defn allow-origin
  [handler]
  (fn [request]
    (let [response (handler request)]
      (update-in response [:headers]
                 merge cors-headers ))))

(defroutes app-routes
  (GET "/" [] "API SATISFACAO")
  
  (GET "/:tabela" [tabela & args]  
    (if args
      (handle-get tabela (map-url-params
                          (stringify-keys args)))
      (handle-get tabela {})))
  
  (PUT "/:tabela" [tabela & args]
    (handle-put tabela (map-url-params
                        (stringify-keys args))))
  
  (DELETE "/:tabela" [tabela & args]
    (handle-delete tabela (map-url-params
                           (stringify-keys args)))))


(def app
  (-> app-routes
      allow-origin))


(defn -main [& [port :as args]]
  (let [port (if (empty? args) 4242 port)] 
    (try
      (do (reset! server (srvr/run-server app {:port port}))
          (println (str "Servidor iniciado na porta " port)))
      (catch BindException e (println "Porta já está em uso!")))))

(defn stop-server! []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil)))

(defn restart-server! []
  (do
    (stop-server!)
    (-main)))
