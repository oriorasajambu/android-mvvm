package id.android.skeleton.services.firebase.states

/**
 * Represents the state of a Firebase messaging operation.
 */
sealed interface FirebaseMessagingState {
    /**
     * Indicates successful retrieval of the Firebase messaging token.
     *
     * @property token The retrieved token.
     */
    data class OnSuccessGetToken(val token: String): FirebaseMessagingState

    /**
     * Indicates failure in retrieving the Firebase messaging token.
     *
     * @property exception The exception that caused the failure.
     */
    data class OnFailedGetToken(val exception: Exception?): FirebaseMessagingState
}