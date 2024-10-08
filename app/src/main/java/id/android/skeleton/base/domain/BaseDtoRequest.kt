package id.android.skeleton.base.domain

import com.google.gson.JsonObject
import id.android.skeleton.common.Constants

/**
 * Represents a base data transfer object for API requests.
 *
 * This class encapsulates common request parameters used across different API endpoints.
 *
 * @property version The API version. Defaults to [Constants.Versions.V1].
 * @property feature The feature being accessed. Can be null.
 * @property xSecret A secret key for authentication. Can be null.
 * @property deviceId The unique identifier of the device making the request. Can be null.
 * @property osType The operating system type of the device. Can be null.
 * @property body The request body as a JSON object. Can be null.
 */
data class BaseDtoRequest(
    val version: String = Constants.Versions.V1,
    val feature: String? = null,
    val xSecret: String? = null,
    val deviceId: String? = null,
    val osType: String? = null,
    val body: JsonObject? = null,
)