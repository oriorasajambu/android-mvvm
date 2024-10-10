package id.android.skeleton.services.notification

import id.android.skeleton.services.notification.model.PushNotificationResult

interface PushNotificationUseCase {
    fun processNotification(
        pushNotificationId: Int,
        pushNotificationResult: PushNotificationResult,
        pushNotificationChannelId: String,
    )
}