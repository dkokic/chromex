(ns chromex.developer-private
  "developerPrivate API.
   This is a private API exposing developing and debugging functionalities for
   apps and extensions.
   
     * available since Chrome 31
     * https://developer.chrome.com/extensions/developerPrivate"

  (:refer-clojure :only [defmacro defn apply declare meta let])
  (:require [chromex-lib.wrapgen :refer [gen-wrap-from-table]]
            [chromex-lib.callgen :refer [gen-call-from-table gen-tap-all-call]]
            [chromex-lib.config :refer [get-static-config gen-active-config]]))

(declare api-table)
(declare gen-call)

; -- functions --------------------------------------------------------------------------------------------------------------

(defmacro auto-update
  "Runs auto update for extensions and apps immediately.
   
     |callback| - Called with the boolean result, true if autoUpdate is successful.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([#_callback] (gen-call :function ::auto-update &form)))

(defmacro get-extensions-info
  "Returns information of all the extensions and apps installed.
   
     |options| - Options to restrict the items returned.
     |callback| - Called with extensions info.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([options #_callback] (gen-call :function ::get-extensions-info &form options))
  ([] `(get-extensions-info :omit)))

(defmacro get-extension-info
  "Returns information of a particular extension.
   
     |id| - The id of the extension.
     |callback| - Called with the result.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([id #_callback] (gen-call :function ::get-extension-info &form id)))

(defmacro get-items-info
  "Returns information of all the extensions and apps installed.
   
     |includeDisabled| - include disabled items.
     |includeTerminated| - include terminated items.
     |callback| - Called with items info.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([include-disabled include-terminated #_callback] (gen-call :function ::get-items-info &form include-disabled include-terminated)))

(defmacro get-profile-configuration
  "Returns the current profile's configuration.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([#_callback] (gen-call :function ::get-profile-configuration &form)))

(defmacro update-profile-configuration
  "Updates the active profile.
   
     |update| - The parameters for updating the profile's configuration.  Any     properties omitted from |update| will not
                be changed.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([update #_callback] (gen-call :function ::update-profile-configuration &form update)))

(defmacro show-permissions-dialog
  "Opens a permissions dialog.
   
     |extensionId| - The id of the extension to show permissions for.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([extension-id #_callback] (gen-call :function ::show-permissions-dialog &form extension-id)))

(defmacro reload
  "Reloads a given extension.
   
     |extensionId| - The id of the extension to reload.
     |options| - Additional configuration parameters.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([extension-id options #_callback] (gen-call :function ::reload &form extension-id options))
  ([extension-id] `(reload ~extension-id :omit)))

(defmacro update-extension-configuration
  "Modifies an extension's current configuration.
   
     |update| - The parameters for updating the extension's configuration.     Any properties omitted from |update| will not
                be changed.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([update #_callback] (gen-call :function ::update-extension-configuration &form update)))

(defmacro load-unpacked
  "Loads a user-selected unpacked item.
   
     |options| - Additional configuration parameters.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([options #_callback] (gen-call :function ::load-unpacked &form options))
  ([] `(load-unpacked :omit)))

(defmacro load-directory
  "Loads an extension / app.
   
     |directory| - The directory to load the extension from.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([directory #_callback] (gen-call :function ::load-directory &form directory)))

(defmacro choose-path
  "Open Dialog to browse to an entry.
   
     |selectType| - Select a file or a folder.
     |fileType| - Required file type. For example, pem type is for private key and load type is for an unpacked item.
     |callback| - called with selected item's path.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([select-type file-type #_callback] (gen-call :function ::choose-path &form select-type file-type)))

(defmacro pack-directory
  "Pack an extension.
   
     |privateKeyPath| - The path of the private key, if one is given.
     |flags| - Special flags to apply to the loading process, if any.
     |callback| - called with the success result string.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([path private-key-path flags #_callback] (gen-call :function ::pack-directory &form path private-key-path flags))
  ([path private-key-path] `(pack-directory ~path ~private-key-path :omit))
  ([path] `(pack-directory ~path :omit :omit)))

(defmacro is-profile-managed
  "Returns true if the profile is managed.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([#_callback] (gen-call :function ::is-profile-managed &form)))

(defmacro request-file-source
  "Reads and returns the contents of a file related to an extension which caused an error.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([properties #_callback] (gen-call :function ::request-file-source &form properties)))

(defmacro open-dev-tools
  "Open the developer tools to focus on a particular error.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([properties #_callback] (gen-call :function ::open-dev-tools &form properties)))

(defmacro delete-extension-errors
  "Delete reported extension erors.
   
     |properties| - The properties specifying the errors to remove.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([properties #_callback] (gen-call :function ::delete-extension-errors &form properties)))

(defmacro repair-extension
  "Repairs the extension specified.
   
     |extensionId| - The id of the extension to repair.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([extension-id #_callback] (gen-call :function ::repair-extension &form extension-id)))

(defmacro show-options
  "Shows the options page for the extension specified.
   
     |extensionId| - The id of the extension to show the options page for.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([extension-id #_callback] (gen-call :function ::show-options &form extension-id)))

(defmacro show-path
  "Shows the path of the extension specified.
   
     |extensionId| - The id of the extension to show the path for.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([extension-id #_callback] (gen-call :function ::show-path &form extension-id)))

(defmacro set-shortcut-handling-suspended
  "(Un)suspends global shortcut handling.
   
     |isSuspended| - Whether or not shortcut handling should be suspended.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([is-suspended #_callback] (gen-call :function ::set-shortcut-handling-suspended &form is-suspended)))

(defmacro update-extension-command
  "Updates an extension command.
   
     |update| - The parameters for updating the extension command.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([update #_callback] (gen-call :function ::update-extension-command &form update)))

(defmacro enable
  "
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([id enabled #_callback] (gen-call :function ::enable &form id enabled)))

(defmacro allow-incognito
  "
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([extension-id allow #_callback] (gen-call :function ::allow-incognito &form extension-id allow)))

(defmacro allow-file-access
  "
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([extension-id allow #_callback] (gen-call :function ::allow-file-access &form extension-id allow)))

(defmacro inspect
  "
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([options #_callback] (gen-call :function ::inspect &form options)))

; -- events -----------------------------------------------------------------------------------------------------------------
;
; docs: https://github.com/binaryage/chromex/#tapping-events

(defmacro tap-on-item-state-changed-events
  "Fired when a item state is changed.
   Events will be put on the |channel|.
   
   Note: |args| will be passed as additional parameters into Chrome event's .addListener call."
  ([channel & args] (apply gen-call :event ::on-item-state-changed &form channel args)))
(defmacro tap-on-profile-state-changed-events
  "Fired when the profile's state has changed.
   Events will be put on the |channel|.
   
   Note: |args| will be passed as additional parameters into Chrome event's .addListener call."
  ([channel & args] (apply gen-call :event ::on-profile-state-changed &form channel args)))

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
  {:namespace "chrome.developerPrivate",
   :since "31",
   :functions
   [{:id ::auto-update,
     :name "autoUpdate",
     :callback? true,
     :params
     [{:name "callback", :optional? true, :type :callback, :callback {:params [{:name "result", :type "boolean"}]}}]}
    {:id ::get-extensions-info,
     :name "getExtensionsInfo",
     :since "43",
     :callback? true,
     :params
     [{:name "options", :optional? true, :type "object"}
      {:name "callback",
       :optional? true,
       :type :callback,
       :callback {:params [{:name "result", :type "[array-of-developerPrivate.ExtensionInfos]"}]}}]}
    {:id ::get-extension-info,
     :name "getExtensionInfo",
     :since "43",
     :callback? true,
     :params
     [{:name "id", :type "string"}
      {:name "callback",
       :optional? true,
       :type :callback,
       :callback {:params [{:name "result", :type "developerPrivate.ExtensionInfo"}]}}]}
    {:id ::get-items-info,
     :name "getItemsInfo",
     :since "43",
     :deprecated "Use getExtensionsInfo",
     :callback? true,
     :params
     [{:name "include-disabled", :type "boolean"}
      {:name "include-terminated", :type "boolean"}
      {:name "callback", :type :callback, :callback {:params [{:name "result", :type "[array-of-objects]"}]}}]}
    {:id ::get-profile-configuration,
     :name "getProfileConfiguration",
     :since "44",
     :callback? true,
     :params
     [{:name "callback", :type :callback, :callback {:params [{:name "info", :type "developerPrivate.ProfileInfo"}]}}]}
    {:id ::update-profile-configuration,
     :name "updateProfileConfiguration",
     :since "44",
     :callback? true,
     :params [{:name "update", :type "object"} {:name "callback", :optional? true, :type :callback}]}
    {:id ::show-permissions-dialog,
     :name "showPermissionsDialog",
     :callback? true,
     :params [{:name "extension-id", :type "string"} {:name "callback", :optional? true, :type :callback}]}
    {:id ::reload,
     :name "reload",
     :callback? true,
     :params
     [{:name "extension-id", :type "string"}
      {:name "options", :optional? true, :type "object"}
      {:name "callback", :optional? true, :type :callback}]}
    {:id ::update-extension-configuration,
     :name "updateExtensionConfiguration",
     :since "43",
     :callback? true,
     :params [{:name "update", :type "object"} {:name "callback", :optional? true, :type :callback}]}
    {:id ::load-unpacked,
     :name "loadUnpacked",
     :callback? true,
     :params [{:name "options", :optional? true, :type "object"} {:name "callback", :optional? true, :type :callback}]}
    {:id ::load-directory,
     :name "loadDirectory",
     :since "34",
     :callback? true,
     :params
     [{:name "directory", :type "DirectoryEntry"}
      {:name "callback", :type :callback, :callback {:params [{:name "path", :type "string"}]}}]}
    {:id ::choose-path,
     :name "choosePath",
     :callback? true,
     :params
     [{:name "select-type", :type "unknown-type"}
      {:name "file-type", :type "unknown-type"}
      {:name "callback", :type :callback, :callback {:params [{:name "path", :type "string"}]}}]}
    {:id ::pack-directory,
     :name "packDirectory",
     :callback? true,
     :params
     [{:name "path", :type "string"}
      {:name "private-key-path", :optional? true, :type "string"}
      {:name "flags", :optional? true, :type "integer"}
      {:name "callback", :optional? true, :type :callback, :callback {:params [{:name "response", :type "object"}]}}]}
    {:id ::is-profile-managed,
     :name "isProfileManaged",
     :callback? true,
     :params [{:name "callback", :type :callback, :callback {:params [{:name "result", :type "boolean"}]}}]}
    {:id ::request-file-source,
     :name "requestFileSource",
     :since "34",
     :callback? true,
     :params
     [{:name "properties", :type "object"}
      {:name "callback", :type :callback, :callback {:params [{:name "response", :type "object"}]}}]}
    {:id ::open-dev-tools,
     :name "openDevTools",
     :since "34",
     :callback? true,
     :params [{:name "properties", :type "object"} {:name "callback", :optional? true, :type :callback}]}
    {:id ::delete-extension-errors,
     :name "deleteExtensionErrors",
     :since "43",
     :callback? true,
     :params [{:name "properties", :type "object"} {:name "callback", :optional? true, :type :callback}]}
    {:id ::repair-extension,
     :name "repairExtension",
     :since "44",
     :callback? true,
     :params [{:name "extension-id", :type "string"} {:name "callback", :optional? true, :type :callback}]}
    {:id ::show-options,
     :name "showOptions",
     :since "44",
     :callback? true,
     :params [{:name "extension-id", :type "string"} {:name "callback", :optional? true, :type :callback}]}
    {:id ::show-path,
     :name "showPath",
     :since "44",
     :callback? true,
     :params [{:name "extension-id", :type "string"} {:name "callback", :optional? true, :type :callback}]}
    {:id ::set-shortcut-handling-suspended,
     :name "setShortcutHandlingSuspended",
     :since "45",
     :callback? true,
     :params [{:name "is-suspended", :type "boolean"} {:name "callback", :optional? true, :type :callback}]}
    {:id ::update-extension-command,
     :name "updateExtensionCommand",
     :since "45",
     :callback? true,
     :params [{:name "update", :type "object"} {:name "callback", :optional? true, :type :callback}]}
    {:id ::enable,
     :name "enable",
     :since "43",
     :deprecated "Use management.setEnabled",
     :callback? true,
     :params
     [{:name "id", :type "string"}
      {:name "enabled", :type "boolean"}
      {:name "callback", :optional? true, :type :callback}]}
    {:id ::allow-incognito,
     :name "allowIncognito",
     :since "43",
     :deprecated "Use updateExtensionConfiguration",
     :callback? true,
     :params
     [{:name "extension-id", :type "string"}
      {:name "allow", :type "boolean"}
      {:name "callback", :optional? true, :type :callback}]}
    {:id ::allow-file-access,
     :name "allowFileAccess",
     :since "43",
     :deprecated "Use updateExtensionConfiguration",
     :callback? true,
     :params
     [{:name "extension-id", :type "string"}
      {:name "allow", :type "boolean"}
      {:name "callback", :optional? true, :type :callback}]}
    {:id ::inspect,
     :name "inspect",
     :since "43",
     :deprecated "Use openDevTools",
     :callback? true,
     :params [{:name "options", :type "object"} {:name "callback", :optional? true, :type :callback}]}],
   :events
   [{:id ::on-item-state-changed, :name "onItemStateChanged", :params [{:name "response", :type "object"}]}
    {:id ::on-profile-state-changed,
     :name "onProfileStateChanged",
     :since "45",
     :params [{:name "info", :type "developerPrivate.ProfileInfo"}]}]})

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