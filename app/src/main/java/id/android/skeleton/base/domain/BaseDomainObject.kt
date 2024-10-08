package id.android.skeleton.base.domain

/**
 * Represents a base domain object used for communication between systems.
 *
 * @param T The type of the content held by this object.
 * @property responseKey A unique key identifying the response.
 * @property traceId A unique identifier for tracking the request across systems.
 * @property sourceSystem The system originating this object.
 * @property message A [Message] object containing title and description in English and Indonesian.
 * @property content The actual content of the object, which can be of type [T]. Defaults to null.
 */
data class BaseDomainObject<T>(
    val responseKey: String,
    val traceId: String,
    val sourceSystem: String,
    val message: Message,
    val content: T? = null,
) {
    /**
     * Represents a message with title and description in English and Indonesian.
     *
     * @property titleEng The title of the message in English. Defaults to an empty string.
     * @property titleInd The title of the message in Indonesian. Defaults to an empty string.
     * @property descEng The description of the message in English. Defaults to an empty string.
     * @property descInd The description of the message in Indonesian. Defaults to an empty string.
     */
    data class Message(
        val titleEng: String = "",
        val titleInd: String = "",
        val descEng: String = "",
        val descInd: String = "",
    )
}
