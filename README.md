Translating from https://developer.mozilla.org/en-US/docs/Learn/Server-side/Express_Nodejs/skeleton_website#creating_the_project

It assumes node/npm are installed, as well as the express application generator:

``` sh
npm install express-generator -g
```

## Creating the project

This command will create a directory named nbb-express using the Pug template library:

``` sh
express nbb-express --view=pug
```

The generator will create (and list) the project's files:

``` sh
	     create : nbb-express\
	     create : nbb-express\public\
	     create : nbb-express\public\javascripts\
	     create : nbb-express\public\images\
	     create : nbb-express\public\stylesheets\
	     create : nbb-express\public\stylesheets\style.css
	     create : nbb-express\routes\
	     create : nbb-express\routes\index.js
	     create : nbb-express\routes\users.js
	     create : nbb-express\views\
	     create : nbb-express\views\error.pug
	     create : nbb-express\views\index.pug
	     create : nbb-express\views\layout.pug
	     create : nbb-express\app.js
	     create : nbb-express\package.json
	     create : nbb-express\bin\
	     create : nbb-express\bin\www
	  
	     change directory:
	       > cd nbb-express
	  
	     install dependencies:
	       > npm install
	  
	     run the app:
	       > SET DEBUG=nbb-express:* & npm start
```

Since I'm in Powershell my command is a bit different:

``` sh
`$ENV:DEBUG = "nbb-express:*"; npm start`
```

``` sh
	  > nbb-express@0.0.0 start
	  > node ./bin/www
	  
	    nbb-express:server Listening on port 3000 +0ms
```

Then load http://localhost:3000/

Now we will replace `app.js` with `app.cljs`:

``` clojure
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

```

It works, plus you can define new routes without restarting the server, though you can't redefine existing ones. Progress!