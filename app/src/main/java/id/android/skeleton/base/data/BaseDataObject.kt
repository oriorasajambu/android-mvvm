package id.android.skeleton.base.data

import com.google.gson.annotations.SerializedName
import id.android.skeleton.base.domain.BaseDomainObject

/**
 * A base data class representing a generic response object.
 *
 * This class encapsulates common properties found in API responses, such as response key, trace ID,
 * source system, message, and a generic result.
 *
 * @param T The type of the result object.
 * @property responseKey The key associated with the response.
 * @property traceId A unique identifier for tracking the request.
 * @property sourceSystem The system originating the response.
 * @property message A [BaseDataObject.Message] object containing information or status messages.
 * @property result The actual result data of the response.
 */
data class BaseDataObject<T>(
    @SerializedName("timestamp") val timeStamp: String? = null,
    @SerializedName("responseKey") val responseKey: String? = null,
    @SerializedName("traceId") val traceId: String? = null,
    @SerializedName("sourceSystem") val sourceSystem: String? = null,
    @SerializedName("message") val message: Message? = null,
    @SerializedName("result") val result: T? = null,
) {
    /**
     * A data class representing a message within the response.
     *
     * This class contains localized titles and descriptions for the message.
     *
     * @property titleEng The English title of the message.
     * @property titleInd The Indonesian title of the message.
     * @property descEng The English description of the message.
     * @property descInd The Indonesian description of the message.
     */
    data class Message(
        @SerializedName("titleEng") val titleEng: String? = null,
        @SerializedName("titleInd") val titleInd: String? = null,
        @SerializedName("descEng") val descEng: String? = null,
        @SerializedName("descInd") val descInd: String? = null,
    ) {
        /**
         * Maps a [BaseDataObject.Message] to a [BaseDomainObject.Message].
         *
         * This extension function converts a data message to a domain message, providing default
         * empty strings for optional properties.
         *
         * @return A [BaseDomainObject.Message] instance.
         */
        fun asMessage(): BaseDomainObject.Message {
            return BaseDomainObject.Message(
                titleEng = this.titleEng.orEmpty(),
                titleInd = this.titleInd.orEmpty(),
                descEng = this.descEng.orEmpty(),
                descInd = this.descInd.orEmpty()
            )
        }
    }
}