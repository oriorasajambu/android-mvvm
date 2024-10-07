package id.android.skeleton.base.domain

import com.google.gson.JsonObject
import id.android.skeleton.common.Constants

data class BaseDtoRequest(
    val version: String = Constants.Versions.V1,
    val feature: String? = null,
    val xSecret: String? = null,
    val deviceId: String? = null,
    val osType: String? = null,
    val body: JsonObject? = null,
)