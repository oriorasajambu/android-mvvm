package id.android.skeleton.services.firebase

import android.graphics.BitmapFactory
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import id.android.skeleton.services.session.SessionKeys
import id.android.skeleton.services.session.SessionUseCase
import id.android.skeleton.R
import id.android.skeleton.common.BuildConfigUtils
import javax.inject.Inject

@AndroidEntryPoint
class AppFirebaseMessagingService: FirebaseMessagingService() {
    @Inject
    lateinit var sessionUseCase: SessionUseCase

    override fun onNewToken(token: String) {
        Log.i("onNewToken", token)
        sessionUseCase.putString(SessionKeys.FIREBASE_TOKEN, token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        Log.i("onMessageReceived", message.notification?.body.toString())
        NotificationCompat.Builder(this, BuildConfigUtils.getChannelId()).apply {
            setWhen(System.currentTimeMillis())
            setAutoCancel(true)
            setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            setShowWhen(true)
            setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_round))
            setContentText(message.notification?.body.toString())
            setStyle(NotificationCompat.BigTextStyle().bigText(message.data["body"]))
        }.build()
    }
}