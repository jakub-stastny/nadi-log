(ns ^:figwheel-hooks js.nadi-log
  (:require
   [goog.dom :as gdom]
   [goog.i18n.DateTimeFormat]
   [reagent.core :as reagent :refer [atom]]
   [reagent.dom :as rdom]))

(println "This is console.log")

(defn multiply [a b] (* a b))

;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {:text "Hello world!"}))

(defn get-app-element []
  (gdom/getElement "app"))

(defn test-component [] [:p "Test."])

(defn main-app []
  [:div
   [:h1
(.format
 (goog.i18n.DateTimeFormat. "EEEE d/M")
 (js/Date.))
    ]

                                        ;[:h1 (:text @app-state)]
   [test-component]
   [:p.someclass
    "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red "] "text."]])

(defn mount [el]
  (rdom/render [main-app] el))

(defn mount-app-element []
  (when-let [el (get-app-element)]
    (mount el)))

;; conditionally start your application based on the presence of an "app" element
;; this is particularly helpful for testing this ns without launching the app
(mount-app-element)

;; specify reload hook with ^:after-load metadata
(defn ^:after-load on-reload []
  (mount-app-element)
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
