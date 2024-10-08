package id.android.skeleton.services.session

/**
 * Represents keys used for storing session data in shared preferences.
 *
 * This enum class defines the keys used to identify and retrieve session-related
 * information stored in shared preferences.
 */
enum class SessionKeys {
    /**
     * Key for storing the access token.
     */
    ACCESS_TOKEN,

    /**
     * Key for storing the logged-in status.
     */
    IS_LOGGED_IN,

    /**
     * Key for storing the Firebase token.
     */
    FIREBASE_TOKEN,
}