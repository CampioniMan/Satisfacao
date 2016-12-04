(defproject satisfacao "0.1.0"
  :description "Melhor projeto que não deu certo"
  :dependencies [[org.clojure/clojure "1.9.0-alpha14"]
                 [compojure "1.5.1"]
                 [ring "1.5.0"]
                 [enlive "1.1.6"]
                 [clj-http "3.4.1"] 
                 [http-kit "2.2.0"]
                 [cheshire "5.6.3"]
                 [org.clojure/java.jdbc "0.7.0-alpha1"]
                 [org.xerial/sqlite-jdbc "3.15.1"]]
  
  :plugins [[lein-ring "0.10.0"]]
  :profiles {:user
             {:dependencies
              [[org.clojure/tools.namespace "0.2.10"]
               [org.clojure/test.check "0.9.0"]]}}
  :ring {:handler satisfacao.main/app})
