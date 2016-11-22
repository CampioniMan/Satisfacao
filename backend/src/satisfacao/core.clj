(ns satisfacao.core
  (:require
   [clojure.java.jdbc :as j]
   [satisfacao.config :refer :all]
   [clojure.spec :as s]))

(def date-reg
  #"^([\+-]?\d{4}(?!\d{2}\b))((-?)((0[1-9]|1[0-2])(\3([12]\d|0[1-9]|3[01]))?|W([0-4]\d|5[0-2])(-?[1-7])?|(00[1-9]|0[1-9]\d|[12]\d{2}|3([0-5]\d|6[1-6])))([T\s]((([01]\d|2[0-3])((:?)[0-5]\d)?|24\:?00)([\.,]\d+(?!:))?)?(\17[0-5]\d([\.,]\d+)?)?([zZ]|([\+-])([01]\d|2[0-3]):?([0-5]\d)?)?)?)?$")

(s/def ::FilledString (s/and string? not-empty))
(s/def ::Row s/map-spec)
(s/def ::Where-Vector (s/cat :where-string (s/* ::FilledString)
                             :values (s/* (s/spec #(-> %
                                                       (nil?)
                                                       (not))))))

(s/fdef inserir!
        :args (s/cat :tabela ::FilledString
                     :row ::Row))

(s/fdef deletar!
        :args (s/cat :tabela ::FilledString
                     :where-vector ::Where-Vector))

(s/fdef selecionar!
        :args (s/cat :tabela ::FilledString
                     :where-vector ::Where-Vector))

(s/fdef update!
        :args (s/cat :tabela ::FilledString
                     :set-map s/map-spec
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

(defn update!
  [tabela
   set-map
   where-selector]
  (j/update! sqltdb
             tabela
             set-map
             where-selector))


(defn tem-tabela?
  [tabela]
  (contains? tabelas
             (keyword tabela)))
