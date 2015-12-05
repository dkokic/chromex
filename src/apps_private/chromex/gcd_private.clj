(ns chromex.gcd-private
  "Use the chrome.gcdPrivate API to discover GCD APIs and register
   them.
   
     * available since Chrome 40
     * https://developer.chrome.com/extensions/gcdPrivate"

  (:refer-clojure :only [defmacro defn apply declare meta let])
  (:require [chromex-lib.wrapgen :refer [gen-wrap-from-table]]
            [chromex-lib.callgen :refer [gen-call-from-table gen-tap-all-call]]
            [chromex-lib.config :refer [get-static-config gen-active-config]]))

(declare api-table)
(declare gen-call)

; -- functions --------------------------------------------------------------------------------------------------------------

(defmacro get-device-info
  "Returns local device information.
   
     |serviceName| - The mDns service name of the device.
     |callback| - Called when the device info is available or on error.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([service-name #_callback] (gen-call :function ::get-device-info &form service-name)))

(defmacro create-session
  "Create new pairing.
   
     |serviceName| - The mDns service name of the device.
     |callback| - Called when device starts to establish a secure session. If |status| is |success| app should call
                  |startPairing|.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([service-name #_callback] (gen-call :function ::create-session &form service-name)))

(defmacro start-pairing
  "Start pairing with selected method. Should be called after |establishSession|.
   
     |sessionId| - The ID of the session created with |establishSession|.
     |pairingType| - The value selected from the list provided in callback of |establishSession|.
     |callback| - Generic callback for session calls, with status only.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([session-id pairing-type #_callback] (gen-call :function ::start-pairing &form session-id pairing-type)))

(defmacro confirm-code
  "Confirm pairing code. Should be called after |startPairing|.
   
     |sessionId| - The ID of the session created with |establishSession|.
     |code| - The string generated by pairing process and available to the user.
     |callback| - Generic callback for session calls, with status only.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([session-id code #_callback] (gen-call :function ::confirm-code &form session-id code)))

(defmacro send-message
  "Send an encrypted message to the device. If the message is a setup message with a wifi SSID specified but no password, the
   password cached by |prefetchWifiPassword| will be used and the call will fail if it's not available. For open networks use
   an empty string as the password.
   
     |sessionId| - The ID of the session created with |establishSession|.
     |api| - The Privet API name to call.
     |input| - Input data for |api|.
     |callback| - Called when the response to the message sent is available or on error.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([session-id api input #_callback] (gen-call :function ::send-message &form session-id api input)))

(defmacro terminate-session
  "Terminate the session with the device.
   
     |sessionId| - The ID of the session created with |establishSession|."
  ([session-id] (gen-call :function ::terminate-session &form session-id)))

; -- convenience ------------------------------------------------------------------------------------------------------------

(defmacro tap-all-events
  "Taps all valid non-deprecated events in this namespace."
  [chan]
  (let [static-config (get-static-config)
        config (gen-active-config static-config)]
    (gen-tap-all-call static-config api-table (meta &form) config chan)))

; ---------------------------------------------------------------------------------------------------------------------------
; -- API TABLE --------------------------------------------------------------------------------------------------------------
; ---------------------------------------------------------------------------------------------------------------------------

(def api-table
  {:namespace "chrome.gcdPrivate",
   :since "40",
   :functions
   [{:id ::get-device-info,
     :name "getDeviceInfo",
     :since "44",
     :callback? true,
     :params
     [{:name "service-name", :type "string"}
      {:name "callback",
       :type :callback,
       :callback {:params [{:name "status", :type "gcdPrivate.Status"} {:name "device-info", :type "object"}]}}]}
    {:id ::create-session,
     :name "createSession",
     :since "43",
     :callback? true,
     :params
     [{:name "service-name", :type "string"}
      {:name "callback",
       :type :callback,
       :callback
       {:params
        [{:name "session-id", :type "integer"}
         {:name "status", :type "gcdPrivate.Status"}
         {:name "pairing-types", :type "[array-of-gcdPrivate.PairingTypes]"}]}}]}
    {:id ::start-pairing,
     :name "startPairing",
     :callback? true,
     :params
     [{:name "session-id", :type "integer"}
      {:name "pairing-type", :type "gcdPrivate.PairingType"}
      {:name "callback", :type :callback, :callback {:params [{:name "status", :type "gcdPrivate.Status"}]}}]}
    {:id ::confirm-code,
     :name "confirmCode",
     :callback? true,
     :params
     [{:name "session-id", :type "integer"}
      {:name "code", :type "string"}
      {:name "callback", :type :callback, :callback {:params [{:name "status", :type "gcdPrivate.Status"}]}}]}
    {:id ::send-message,
     :name "sendMessage",
     :callback? true,
     :params
     [{:name "session-id", :type "integer"}
      {:name "api", :type "string"}
      {:name "input", :type "object"}
      {:name "callback",
       :type :callback,
       :callback {:params [{:name "status", :type "gcdPrivate.Status"} {:name "response", :type "object"}]}}]}
    {:id ::terminate-session, :name "terminateSession", :params [{:name "session-id", :type "integer"}]}]})

; -- helpers ----------------------------------------------------------------------------------------------------------------

; code generation for native API wrapper
(defmacro gen-wrap [kind item-id config & args]
  (let [static-config (get-static-config)]
    (apply gen-wrap-from-table static-config api-table kind item-id config args)))

; code generation for API call-site
(defn gen-call [kind item src-info & args]
  (let [static-config (get-static-config)
        config (gen-active-config static-config)]
    (apply gen-call-from-table static-config api-table kind item src-info config args)))