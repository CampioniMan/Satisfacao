(ns satisfacao.config
  (:require
   [clojure.java.jdbc :as j]))

(def db-path
  "database/index.db")

(def sqltdb
  {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :character-encoding "UTF-8"
   :subname db-path})



(def tabelas {:Atendimento
              #{:ID :Tipo :IDUsuario :Mensagem :Data}

              :TipoAtendimento
              #{:ID :Label}

              :Usuario
              #{:Nome :ID}})
