(ns app
  (:require ["express$default" :as express]
            ["path" :as path]
            ["mongoose$default" :as mongoose]
            ["./routes/index$default" :as indexRouter]
            ["./routes/users$default" :as usersRouter]
            ["./routes/catalog$default" :as catalogRouter]))

(def app (express))
(def port 3000)

(def mongoDB "mongodb://127.0.0.1/my_database")
(.connect mongoose mongoDB
 #js {:useNewUrlParser true, :useUnifiedTopology true})

(def db (.-connection mongoose))
(.on db "error" (.bind js/console.error "MongoDB connection error:"))

(.set app "view engine" "pug")
(.use app (.static express (.join path (.cwd js/process) "public")))

(.use app "/" indexRouter)
(.use app "/users" usersRouter)
(.use app "/catalog" catalogRouter)

(.listen app port
         (fn [] (println "Example app listening on port" port)))
