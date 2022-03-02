(ns app
  (:require ["express$default" :as express]))

(def app (express))

(def port 3000)

(.set app "view engine" "pug")

(.get app "/"
      (fn [req res next]
        (.render res "index" #js {:title "Express"})))

(.get app "/users"
      (fn [req res next]
        (.send res "respond with a resource")))

(.get app "/me"
      (fn [req res next]
        (.send res "respond with meeee")))

(.listen app port
         (fn []
           (println "Example app listening on port" port)))