(ns mine-canary.console.core
  (:use [compojure.core :only [GET defroutes]])
  (:require [compojure.route :as route]
            [hiccup.page :as p]
            [ring.middleware.defaults :refer :all]))

(defn index []
  (p/html5
   [:head
    [:title "Mine Canary control panel"]
    (p/include-css "//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css")]
   [:body
    [:div.container
     [:div#app.row]]
     (p/include-js "http://fb.me/react-0.11.2.js"
                 "/js/main.js")]))
(defroutes main-routes
  (GET "/" [] (index))
  (route/resources "/"))

(def app
  (wrap-defaults main-routes site-defaults))
