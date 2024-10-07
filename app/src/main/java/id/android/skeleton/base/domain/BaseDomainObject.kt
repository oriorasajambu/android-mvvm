package id.android.skeleton.base.domain

data class BaseDomainObject<T>(
    val responseKey: String,
    val traceId: String,
    val sourceSystem: String,
    val message: Message,
    val content: T? = null,
) {
    data class Message(
        val titleEng: String = "",
        val titleInd: String = "",
        val descEng: String = "",
        val descInd: String = "",
    )
}
