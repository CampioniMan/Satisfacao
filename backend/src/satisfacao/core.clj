(ns satisfacao.core
  (:require
   [clojure.java.jdbc :as j]
   [satisfacao.config :refer :all]
   [clojure.spec :as s]))

(def date-reg
  #"^([\+-]?\d{4}(?!\d{2}\b))((-?)((0[1-9]|1[0-2])(\3([12]\d|0[1-9]|3[01]))?|W([0-4]\d|5[0-2])(-?[1-7])?|(00[1-9]|0[1-9]\d|[12]\d{2}|3([0-5]\d|6[1-6])))([T\s]((([01]\d|2[0-3])((:?)[0-5]\d)?|24\:?00)([\.,]\d+(?!:))?)?(\17[0-5]\d([\.,]\d+)?)?([zZ]|([\+-])([01]\d|2[0-3]):?([0-5]\d)?)?)?)?$")

(s/def ::Tipo int?)
(s/def ::Nome (s/and string? not-empty))
(s/def ::Mensagem ::Nome)
(s/def ::Data (s/and string? #(re-matches date-reg %)))


(s/def ::atendimento (s/keys :req-up [::Tipo ::Nome ::Mensagem ::Data]))

(s/fdef registrar-atendimento
        :args ::atendimento)

(defn registrar-atendimento
  "Registra o map args no bd"
  [atendimento]
  (j/insert! sqltdb
             "Atendimento"
             atendimento))
