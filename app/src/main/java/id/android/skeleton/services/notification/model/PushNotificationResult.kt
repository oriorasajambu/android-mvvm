package id.android.skeleton.services.notification.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

@Parcelize
data class PushNotificationResult(
    val title: String = "",
    val body: String = "",
    val extraData: ExtraData? = null,
) : Parcelable {
    @Parcelize
    data class ExtraData(
        val type: PushNotificationType = PushNotificationType.DEFAULT,
        val target: String = "",
        val bundle: String = "{}",
    ) : Parcelable {
        fun getBundleAsJson(): JsonObject {
            return Json.parseToJsonElement(bundle).jsonObject
        }
    }
}
