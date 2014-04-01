(ns helloclojure.core
	(:use compojure.core)
	(:require
	  [ring.adapter.jetty :as jetty]
	  [compojure.route :as route]))

(defroutes main-routes
	(GET "/" [] 
		(fn [req] {:status 200
           :body "{\"text\":\"Hello World\"}"
           :headers {"Content-Type" "application/json"}})))

(defn -main []
	(jetty/run-jetty 
		main-routes
		{:port 5000}))
