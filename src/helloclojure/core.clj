(ns helloclojure.core
	(:use compojure.core)
	(:require
	  [ring.adapter.jetty :as jetty]
	  [clostache.parser :as clostache]
	  [compojure.route :as route]))

(defn read-template [template-name]
	(slurp (clojure.java.io/resource
		(str "templates/" template-name ".mustache"))))

(defn render-template [template-file params]
	(clostache/render 
		(read-template template-file) params))

;views
(defn index []
	(render-template "index" {:greeting "OPA"}))

;routes
(defroutes main-routes
	(GET "/json" [] 
		(fn [req] {:status 200
           :body "{\"text\":\"Hello World\"}"
           :headers {"Content-Type" "application/json"}}))

	(GET "/" [] (index))
	(route/resources "/")
  	(route/not-found "404 Not Found"))

;main
(defn -main []
	(jetty/run-jetty 
		main-routes
		{:port 5000}))
