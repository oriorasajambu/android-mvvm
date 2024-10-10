package id.android.skeleton.services.notification.model

enum class PushNotificationType {
    DEFAULT, DIRECT, PENDING;

    companion object {
        fun checkType(type: String): PushNotificationType {
            return when (type) {
                "pending" -> PENDING
                "direct" -> DIRECT
                else -> DEFAULT
            }
        }
    }
}