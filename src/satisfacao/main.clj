(ns satisfacao.main
  (:require
   [satisfacao.core :as core] 
   [compojure.core :refer :all]
   [compojure.handler :as handler]
   [compojure.route :as route]
   
   [ring.adapter.jetty :as jetty]
   [ring.middleware.params :as params]
   [ring.util.response :as response]
   
   [clj-http.client :as client]
   [clojure.walk :refer [keywordize-keys stringify-keys]]
   [ring.util.codec :refer [form-decode]] 
   [cheshire.core :refer :all])
  (:gen-class)
  (:import
   [java.net BindException]))

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


(def cors-headers 
  {"Access-Control-Allow-Origin" "*"
   "Access-Control-Allow-Headers" "Content-Type"
   "Access-Control-Allow-Methods" "GET,PUT,DELETE" })
(defn allow-origin
  [handler]
  (fn [request]
    (let [response (handler request)]
      (update-in response [:headers]
                 merge cors-headers ))))

(defroutes api-routes 
  (context "/api" []
    (GET "/" [] "API SATISFACAO")
    
    (GET "/:tabela" [tabela & args]  
      (if args
        (handle-get tabela (stringify-keys args))
        (handle-get tabela {})))
    
    (PUT "/:tabela" [tabela & args]
      (handle-put tabela (stringify-keys args)))
    
    (DELETE "/:tabela" [tabela & args]
      (handle-delete tabela (stringify-keys args)))))

(defroutes site-routes
  (context "/" []
    (GET "/:pagina" [pagina] 
      (response/resource-response
       (str (.toLowerCase pagina) ".html")
       {:root "public/"})))

  (route/resources "/")
  (route/not-found "<em>Not found</em>"))

(def app
  (->
   (routes api-routes site-routes)
   params/wrap-params))

(defonce server (atom nil))

(defn -main [& [port :as args]]
  (let [port (if (empty? args) 4242 (int port))] 
    (try
      (do (reset! server (jetty/run-jetty app {:port port :join? false}))
          (println (str "Servidor iniciado na porta " port)))
      (catch BindException e (println "Porta já está em uso!")))))

(defn stop-server! []
  (when-not (nil? @server)
    (.stop @server)
    (reset! server nil)))

(defn restart-server! []
  (do
    (stop-server!)
    (-main)))
