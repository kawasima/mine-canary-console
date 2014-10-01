(defproject net.unit8/mine-canary-console "0.1.0-SNAPSHOT"
  :description "Mine canary console"
  :url "https://github.com/kawasima/mine-canary-console"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring "1.3.1"]
                 [org.clojure/data.fressian "0.2.0"]
                 [net.unit8/ulon-colon "0.2.0-SNAPSHOT"]

                 [compojure "1.1.9"]
                 [ring/ring-defaults "0.1.2"]
                 [hiccup "1.0.5"]
                 [org.clojure/clojurescript "0.0-2342"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [om "0.7.3"]
                 [sablono "0.2.22"]
                 [prismatic/om-tools "0.3.3"]]
  :ring {:handler mine-canary.console.core/app }

  :source-paths ["src/clj"]
  :plugins [[lein-ring "0.8.11"]
            [lein-cljsbuild "1.0.3"]]

  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]]}}
  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src/cljs"]
                :compiler {:output-to "resources/public/js/main.js"
                           :optimizations :simple }}]})


