package id.android.skeleton.services.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import id.android.skeleton.R
import id.android.skeleton.common.BuildConfigUtils
import id.android.skeleton.common.Constants
import id.android.skeleton.services.lifecycle.AppLifeCycleUseCase
import id.android.skeleton.services.notification.model.PushNotificationResult
import id.android.skeleton.services.notification.model.PushNotificationType.DIRECT
import id.android.skeleton.services.notification.model.PushNotificationType.PENDING
import javax.inject.Inject

class PushNotificationManager @Inject constructor(
    private val context: Context,
    private val lifeCycleUseCase: AppLifeCycleUseCase,
) : PushNotificationUseCase {

    override fun processNotification(
        pushNotificationId: Int,
        pushNotificationResult: PushNotificationResult,
        pushNotificationChannelId: String,
    ) {
        val data = pushNotificationResult.extraData
        val intent = PushNotificationHandler.launchPendingHandler(
            context,
            pushNotificationResult
        )

        if (data?.type == DIRECT && lifeCycleUseCase.isAppOnForeground()) {
            context.startActivity(intent)
            return
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            pushNotificationId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val notification = context.basicNotification().apply {
            setTicker(pushNotificationResult.title)
            setContentTitle(pushNotificationResult.title)
            setContentText(pushNotificationResult.body)
            setPriority(NotificationCompat.PRIORITY_DEFAULT)
            val isContentIntent = arrayOf(
                data?.type == PENDING,
                data?.type == DIRECT,
            ).any { true }
            if (isContentIntent) setContentIntent(pendingIntent)
        }

        val notificationChannel = NotificationChannel(
            pushNotificationChannelId,
            Constants.NOTIFICATION_CHANNEL,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            enableLights(true)
            enableVibration(true)
        }

        with(NotificationManagerCompat.from(context)) {
            createNotificationChannel(notificationChannel)
            cancel(pushNotificationId)
            notify(pushNotificationId, notification.build())
        }
    }

    private fun Context.basicNotification(
        builder: NotificationCompat.Builder.() -> NotificationCompat.Builder = { this },
    ): NotificationCompat.Builder {
        return NotificationCompat.Builder(this, BuildConfigUtils.getChannelId()).apply {
            setWhen(System.currentTimeMillis())
            setAutoCancel(true)
            setShowWhen(true)
            setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            setSmallIcon(R.mipmap.ic_launcher_round)
            setLargeIcon(
                BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_round)
            )
            builder(this)
        }
    }
}