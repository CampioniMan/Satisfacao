(ns satisfacao.config
  (:require
   [clojure.java.jdbc :as j]))

(def db-path
  "index.db")

(def sqltdb
  {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname db-path})

(def Atendimento
  [:ID :Tipo :IDUsuario :Mensagem :Data])

(def TipoAtendimento
  [:ID :Label])

(def Usuario
  [:Nome :ID])
