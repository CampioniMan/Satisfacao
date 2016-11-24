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



(def tabelas {"Atendimento"
              #{"ID" "Tipo" "IDUsuario" "Mensagem" "Data"}

              "TipoAtendimento"
              #{"ID" "Label"}

              "Usuario"
              #{"Nome" "ID"}})

(def action-table-keys {"Atendimento"
                        {:insert #{"Tipo" "IDUsuario" "Mensagem" "Data"}
                         :delete :all
                         :select :all
                         :update #{"Tipo" "IDUsuario" "Mensagem" "Data"}}
                        "TipoAtendimento"
                        {:insert #{"Label"}
                         :delete :all
                         :select :all
                         :update #{"Label"}}
                        "Usuario"
                        {:insert #{"Nome"}
                         :delete :all
                         :select :all
                         :update #{"Nome"}}})
