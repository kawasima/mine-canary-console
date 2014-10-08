(ns mine-canary.console.core
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:use [ulon-colon.consumer :only [make-consumer consume consume-sync]])
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [sablono.core :as html :refer-macros [html]]
            [cljs.core.async :refer [put! <! chan]]
            [goog.events :as events])
  (:import [goog.i18n DateTimeFormat]))

(enable-console-print!)

(defn format-times [account times]
  (let [dt-format (DateTimeFormat. "yyyy/MM/dd HH:mm:ss")]
    (str (.format dt-format (js/Date. (* (last times) 1000)))
         "から"
         (.format dt-format (js/Date. (* (first times) 1000)))
         "まで"
         account
         "から"
         (count times)
         "回のログイン失敗がありました.")))

(defcomponent main-app [app owner]
  (will-mount
   [_]
    (consume (:consumer app)
      (fn [msg]
        (println msg)
        (om/transact! app :alerts #(conj % msg)))))

  (render
   [_]
   (html
     [:div.ui.list
      (for [{:keys [account times]} (:alerts app)]
       [:div.item
        [:i.icon.frown]
        [:div.content
         [:div.header "Suspicious attack"]
         [:div.description (format-times account times)]]])])))

(om/root main-app {:alerts [] :consumer (make-consumer "ws://localhost:56293")}
         {:target (.getElementById js/document "app")})

