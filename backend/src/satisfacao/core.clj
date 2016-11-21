(ns satisfacao.core
  (:require
   [clojure.java.jdbc :as j]
   [satisfacao.config :refer :all]
   [clojure.spec :as s]))

(def date-reg
  #"^([\+-]?\d{4}(?!\d{2}\b))((-?)((0[1-9]|1[0-2])(\3([12]\d|0[1-9]|3[01]))?|W([0-4]\d|5[0-2])(-?[1-7])?|(00[1-9]|0[1-9]\d|[12]\d{2}|3([0-5]\d|6[1-6])))([T\s]((([01]\d|2[0-3])((:?)[0-5]\d)?|24\:?00)([\.,]\d+(?!:))?)?(\17[0-5]\d([\.,]\d+)?)?([zZ]|([\+-])([01]\d|2[0-3]):?([0-5]\d)?)?)?)?$")

(s/def ::Tabela (s/and string? not-every?))
(s/def ::Row s/map-spec)

(s/fdef inserir!
        :args (s/cat :tabela ::Tabela
                     :row ::Row))

(s/def ::Where-Vector (s/* (s/cat :select string?
                                  :value #(not (nil? %)))))

(s/fdef deletar!
        :args (s/cat :tabela ::Tabela
                     :where-vector ::Where-Vector))
(defn inserir!
  "Registra o map args no bd"
  [tabela row]
  (j/insert! sqltdb
             tabela
             row))

(defn deletar!
  "Deleta row de acordo com o parâmetro de seleção"
  [tabela where-vector]
  (j/delete! sqltdb
             tabela
             where-vector))

(defn selecionar!
  "Seleciona linha(s) da tabela com base no parâmetro de seleção"
  ([tabela
    where-vector] 
   (let [s-string (str "SELECT * FROM `" tabela "` WHERE "
                       (get where-vector 0))
         values (rest where-vector)]
     (j/query sqltdb
              (into [s-string] values))))
  ([tabela]
   (j/query sqltdb
            [(str "SELECT * FROM `" tabela "`")])))
