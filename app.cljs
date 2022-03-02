(ns app
  (:require ["express$default" :as express]
            ["path" :as path]
            ["mongoose$default" :as mongoose]))

(def app (express))
(def port 3000)

(def mongoDB "mongodb://127.0.0.1/my_database")

(.connect mongoose mongoDB
 #js {:useNewUrlParser true, :useUnifiedTopology true})

(def db (.-connection mongoose))

(.on db "error" (.bind js/console.error "MongoDB connection error:"))

(.set app "view engine" "pug")
(.use app (.static express (.join path (.cwd js/process) "public")))

(.get app "/"
      (fn [req res next]
        (.render res "index" #js {:title "nbb-express"})))

(.get app "/users"
      (fn [req res next]
        (.send res "respond with a resource")))

(.listen app port
         (fn [] (println "Example app listening on port" port)))
