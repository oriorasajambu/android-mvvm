package id.android.skeleton.services.notification

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import id.android.skeleton.common.BuildConfigUtils
import id.android.skeleton.services.notification.model.PushNotificationMapper.mapToNotificationResult
import id.android.skeleton.services.session.SessionKeys
import id.android.skeleton.services.session.SessionUseCase
import java.util.Date
import javax.inject.Inject

@AndroidEntryPoint
class PushNotificationService : FirebaseMessagingService() {
    @Inject
    lateinit var sessionUseCase: SessionUseCase

    @Inject
    lateinit var pushNotificationUseCase: PushNotificationUseCase

    override fun onNewToken(token: String) {
        sessionUseCase.putString(SessionKeys.FIREBASE_TOKEN, token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        val notificationId = Date().time.toInt()
        val notificationChannelId = BuildConfigUtils.getChannelId()
        val notificationResult = mapToNotificationResult(
            notificationTitle = message.notification?.title,
            notificationBody = message.notification?.body,
            notificationData =  message.data,
        )
        pushNotificationUseCase.processNotification(
            pushNotificationId = notificationId,
            pushNotificationResult = notificationResult,
            pushNotificationChannelId = notificationChannelId
        )
    }
}