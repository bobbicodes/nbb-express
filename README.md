Begin just like the [other one]([https://developer.mozilla.org/en-US/docs/Learn/Server-side/Express_Nodejs/skeleton_website](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Express_Nodejs/skeleton_website#creating_the_project)).
It assumes node/npm are installed, as well as the express application generator:
`npm install express-generator -g`
## Creating the project
This command will create a directory named nbb-express using the Pug template library:
`express nbb-express --view=pug`
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
`$ENV:DEBUG = "nbb-express:*"; npm start`
``` sh
	  > nbb-express@0.0.0 start
	  > node ./bin/www
	  
	    nbb-express:server Listening on port 3000 +0ms
```
Then load http://localhost:3000/:
![image.png](../assets/image_1646230365343_0.png)
Ok so already we need to back up because we did too much. Stop the server. Start an nbb REPL:
``` clojure
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
	  
	  (.listen app port
	           (fn []
	             (println "Example app listening on port" port)))
```
It works, and you can even define new routes without restarting the server. Progress!