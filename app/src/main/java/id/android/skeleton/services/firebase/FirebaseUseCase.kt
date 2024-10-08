package id.android.skeleton.services.firebase

import id.android.skeleton.services.firebase.states.FirebaseMessagingState
import java.lang.Exception

/**
 * Defines the use cases for interacting with Firebase services.
 */
interface FirebaseUseCase {
    /**
     * Handles the completion of getting a Firebase messaging token.
     *
     * @param onComplete A callback function to be invoked with the [FirebaseMessagingState] result.
     */
    fun onCompleteGetToken(onComplete: (FirebaseMessagingState) -> Unit)

    /**
     * Logs an error to Firebase Crashlytics.
     *
     * @param exception The exception to be logged.
     */
    fun logError(exception: Exception?)
}