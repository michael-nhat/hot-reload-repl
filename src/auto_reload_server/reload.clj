(ns auto-reload-server.reload
  (:require
   [ns-tracker.core :as tracker]
   [auto-reload-server.server :as server]))


(defn start
  [& [opts]]
  (server/-main)
  (println "start service"))

(defn stop
  []
  (println "stop service"))

(defn restart
  []
  (stop)
  (start))

(defn- ns-reload [track]
  (try
    (doseq [ns-sym (track)]
      (require ns-sym :reload))
    (catch Throwable e (.printStackTrace e))))

(defn watch
  ([] (watch ["src"]))
  ([src-paths]
   (let [track (tracker/ns-tracker src-paths)
         done (atom false)]
     (doto
       (Thread. (fn []
                  (while (not @done)
                    ;; (println "ksjlfsd")
                    (ns-reload track)
                    (Thread/sleep 500))))
       (.setDaemon true)
       (.start))
     (fn []  (swap! done not)))))

(defn -main
  [& args]
  (println "in -main of reload")
  (start)
  (watch)
  (println "stop ??"))
