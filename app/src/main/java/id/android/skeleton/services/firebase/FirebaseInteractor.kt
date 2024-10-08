package id.android.skeleton.services.firebase

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.messaging.FirebaseMessaging
import id.android.skeleton.services.firebase.states.FirebaseMessagingState

/**
 * Interacts with Firebase services.
 *
 * This class provides methods for getting the Firebase messaging token and logging errors to Firebase Crashlytics.
 */
class FirebaseInteractor(
    private val messaging: FirebaseMessaging,
    private val crashlytics: FirebaseCrashlytics,
) : FirebaseUseCase {
    /**
     * Handles the completion of getting a Firebase messaging token.
     *
     * @param onComplete A callback function to be invoked with the [FirebaseMessagingState] result.
     */
    override fun onCompleteGetToken(onComplete: (FirebaseMessagingState) -> Unit) {
        messaging.token.addOnCompleteListener { task ->
            if (task.isSuccessful) onComplete.invoke(FirebaseMessagingState.OnSuccessGetToken(task.result))
            else onComplete.invoke(FirebaseMessagingState.OnFailedGetToken(task.exception))
        }
    }

    /**
     * Logs an error to Firebase Crashlytics.
     *
     * @param exception The exception to be logged.
     */
    override fun logError(exception: Exception?) {
        crashlytics.log(exception?.message.takeIf { it != null } ?: "Error")
    }
}