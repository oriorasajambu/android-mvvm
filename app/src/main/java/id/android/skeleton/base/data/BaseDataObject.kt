package id.android.skeleton.base.data

import com.google.gson.annotations.SerializedName
import id.android.skeleton.base.domain.BaseDomainObject

data class BaseDataObject<T>(
    @SerializedName("responseKey") val responseKey: String? = null,
    @SerializedName("traceId") val traceId: String? = null,
    @SerializedName("sourceSystem") val sourceSystem: String? = null,
    @SerializedName("message") val message: Message? = null,
    @SerializedName("result") val result: T? = null,
) {
    data class Message(
        @SerializedName("titleEng") val titleEng: String? = null,
        @SerializedName("titleInd") val titleInd: String? = null,
        @SerializedName("descEng") val descEng: String? = null,
        @SerializedName("descInd") val descInd: String? = null,
    )

    fun Message?.mapToDomain(): BaseDomainObject.Message {
        return BaseDomainObject.Message(
            titleEng = this?.titleEng.orEmpty(),
            titleInd = this?.titleInd.orEmpty(),
            descEng = this?.descEng.orEmpty(),
            descInd = this?.descInd.orEmpty()
        )
    }
}