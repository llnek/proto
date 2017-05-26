;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
(defproject io.czlab/proto "1.0.0"

  :license {:url "http://www.eclipse.org/legal/epl-v10.html"
            :name "Eclipse Public License"}

  :description "Mock servers for email and jms transports."
  :url "https://github.com/llnek/proto"

  :dependencies [[org.apache.geronimo.specs/geronimo-jms_1.1_spec "1.1.1"]
                 [com.sun.mail/javax.mail "1.5.6"]]

  :plugins [[cider/cider-nrepl "0.14.0"]
            [lein-javadoc "0.3.0"]
            [lein-codox "0.10.3"]
            [lein-cprint "1.2.0"]]

  :profiles {:provided {:dependencies
                        [[org.clojure/clojure "1.8.0" :scope "provided"]
                         [junit/junit "4.12" :scope "test"]]}
             :uberjar {:aot :all}}

  :javadoc-opts {:package-names ["czlab.proto"]
                 :output-dir "docs"}

  :global-vars {*warn-on-reflection* true}
  :target-path "out/%s"
  :aot :all

  :coordinate! "czlab"
  :omit-source true

  :java-source-paths ["src/main/java" "src/test/java"]
  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]

  :jvm-opts ["-Dlog4j.configurationFile=file:attic/log4j2.xml"]
  :javac-options ["-source" "8"
                  "-Xlint:unchecked" "-Xlint:-options" "-Xlint:deprecation"])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;EOF

