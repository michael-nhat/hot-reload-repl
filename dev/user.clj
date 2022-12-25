;; (ns user)

(ns user
  (:require [reload :as reload]))
(defn -main
  [& args]
  (println "wtf user main")
  (require 'reload)
  (in-ns 'reload)
  (reload/-main))