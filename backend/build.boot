(require 'boot.repl)

(set-env!
 :source-paths #{"src"}
 :target #{"target"}
 :dependencies    '[[org.clojure/clojure "1.9.0-alpha14"]
                    [compojure "1.5.1"]
                    [ring "1.5.0"]
                    [enlive "1.1.6"]
                    [clj-http "3.4.1"] 
                    [http-kit "2.2.0"]
                    [cheshire "5.6.3"]
                    [org.clojure/java.jdbc "0.7.0-alpha1"]
                    [org.xerial/sqlite-jdbc "3.15.1"]])

(deftask dev
  "Profile setup for development."
  []
  (println "Dev running...")
  (set-env!
    :init-ns 'user
    :dependencies (conj (get-env :dependencies) '[org.clojure/tools.namespace "0.2.10"]
                                                '[org.clojure/test.check "0.9.0"])
    ;; :source-paths #(into % ["dev"])
    ) 
  identity)

(deftask build
  "This is used for creating an optimized uberjar "
  []
  (comp
   (aot :all true)
   (uber :exclude #{#"(?i)^META-INF/[^/]*\.(MF|SF|RSA|DSA)$"
                    #"(?i)^META-INF\\[^/]*\.(MF|SF|RSA|DSA)$"
                    #"(?i)^META-INF/INDEX.LIST$"
                    #"(?i)^META-INF\\INDEX.LIST$"})
   (jar :main 'satisfacao.main)
   (target)))
