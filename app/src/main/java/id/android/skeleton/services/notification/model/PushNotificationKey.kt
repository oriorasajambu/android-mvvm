package id.android.skeleton.services.notification.model

enum class PushNotificationKey(val key: String, val default: String) {
    TYPE(key = "type", default = "default"),
    TARGET(key = "target", default = ""),
    BUNDLE(key = "bundle", default = "{}"),
}