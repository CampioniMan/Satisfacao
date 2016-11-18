(ns satisfacao.config
  (:require
   [clojure.java.jdbc :as j]))

(def db-path
  "database.db")

(def sqltdb
  {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname db-path})

