(ns mine-canary.console.core
  (:use [compojure.core :only [GET defroutes]]
        [hiccup.element :only [javascript-tag]])
  (:require [compojure.route :as route]
            [hiccup.page :as p]
            [ring.middleware.defaults :refer :all]))

(defn index []
  (p/html5
   [:head
    [:title "Mine Canary control panel"]
    (p/include-css "/css/semantic.min.css"
                   "/css/console.css")]
   [:body#example
    [:div.ui.fixed.transparent.inverted.main.menu
     [:div.container
      [:div.title.item
       [:b "Mine canary console"]]]]
    [:div.segment
     [:div.container
      [:h2.ui.red.dividing.header
       [:i.icon.unhide]
       "Security alert"]
      [:div#app.row]]]
     (p/include-js "http://fb.me/react-0.11.2.js"
                 "/js/main.js")
     (javascript-tag "goog.require('mine-canary.console.core');")]))

(defroutes main-routes
  (GET "/" [] (index))
  (route/resources "/"))

(def app
  (wrap-defaults main-routes site-defaults))
