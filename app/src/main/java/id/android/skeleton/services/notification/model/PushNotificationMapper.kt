package id.android.skeleton.services.notification.model

import id.android.skeleton.common.Utils.orDefault
import id.android.skeleton.services.notification.model.PushNotificationKey.BUNDLE
import id.android.skeleton.services.notification.model.PushNotificationKey.TARGET
import id.android.skeleton.services.notification.model.PushNotificationKey.TYPE

object PushNotificationMapper {
    fun mapToNotificationResult(
        notificationTitle: String?,
        notificationBody: String?,
        notificationData: Map<String, String?>,
    ): PushNotificationResult {
        return PushNotificationResult(
            title = notificationTitle.orEmpty(),
            body = notificationBody.orEmpty(),
            extraData = mapExtraData(notificationData)
        )
    }

    private fun mapExtraData(
        notificationData: Map<String, String?>,
    ): PushNotificationResult.ExtraData? {
        return PushNotificationResult.ExtraData(
            type = PushNotificationType.checkType(notificationData[TYPE.key].orDefault(TYPE.default)),
            target = notificationData[TARGET.key].orDefault(TARGET.default),
            bundle = notificationData[BUNDLE.key].orDefault(BUNDLE.default),
        ).takeIf { notificationData.isNotEmpty() }
    }
}