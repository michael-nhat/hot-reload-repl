(ns auto-reload-server.repl
  (:refer-clojure :exclude [send])
  (:require [cider.nrepl :refer [cider-nrepl-handler]]
            [nrepl.server :as nrepl-server]
            [nrepl.transport :as transport]
            [auto-reload-server.reload :as reload]
            [rebel-readline.main :as rebel]))
;; (pedestal-hello1.pedestal-hello1/-main)

(def conn)

(defn greeting-fn [t]
  (print "wtf, gretting")
  ;; (ns pedestal-hello1.pedestal-hello1)
  (transport/send t {:op "eval" :code "(ns 'pedestal-hello1.pedestal-hello1)"})
  (transport/send t {:op "eval" :code "(def conn 3)" :ns "pedestal-hello1.pedestal-hello1"})
  (transport/send t {:out (str ";; nREPL " "wtf anh nhat oi")})
  ;; (nrepl/message t {:out "(+ 2 3)"})
  ;; (require 'pedestal-hello1.pedestal-hello1)
  )


(defn -main [& _]
;;   (nrepl-server/start-server :port 7889 :handler cider-nrepl-handler)
  (nrepl-server/start-server :port 7885 :handler cider-nrepl-handler :greeting-fn greeting-fn)
  (reload/-main)
  (ns auto-reload-server.server)
  (println "my nrepl server ")
  (rebel/-main)
  (System/exit 0))
