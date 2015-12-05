(ns chromex-lib.defaults
  (:require-macros [chromex-lib.config :refer [gen-default-config]]
                   [chromex-lib.support :refer [oget ocall]])
  (:require [cljs.core.async :refer [put! chan]]
            [goog.object :as gobj]))

; -- callback support -------------------------------------------------------------------------------------------------------
;
; async methods using core.async channels

(defn default-callback-fn-factory [_config chan]
  (fn [& args]
    (put! chan (vec args))))

(defn default-callback-channel-factory [_config]
  (chan))

(defn default-event-listener-factory [_config event-id chan]
  (fn [& args]
    (put! chan [event-id (vec args)])))

(defn default-chrome-storage-area-callback-fn-factory [config chan]
  (fn [& args]
    (let [last-error (oget (:root config) "chrome" "runtime" "lastError")]
      (put! chan [(vec args) last-error]))))

(defn default-chrome-storage-area-callback-channel-factory [_config]
  (chan))

; -- logging support --------------------------------------------------------------------------------------------------------

(defn console-log [& args]
  (.apply (.-log js/console) js/console (apply array args)))

(defn default-logger [& args]
  (apply console-log "[chromex]" args))

; -- missing API checks -----------------------------------------------------------------------------------------------------

(defn default-missing-api-check [api obj key]
  (if-not (gobj/containsKey obj key)
    (throw (js/Error. (str "Chromex library tried to access a missing Chrome API object '" api "'.\n"
                           "Your Chrome version might be too old or too recent for running this extension.\n"
                           "This is a failure which probably requires a software update.")))))

; -- default config ---------------------------------------------------------------------------------------------------------

(def default-config (gen-default-config))