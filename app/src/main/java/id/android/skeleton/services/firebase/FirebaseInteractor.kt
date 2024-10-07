package id.android.skeleton.services.firebase

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.messaging.FirebaseMessaging

class FirebaseInteractor(
    private val messaging: FirebaseMessaging,
    private val crashlytics: FirebaseCrashlytics,
) : FirebaseUseCase {
    override fun onCompleteGetToken(onComplete: (FirebaseMessagingState) -> Unit) {
        messaging.token.addOnCompleteListener { task ->
            if (task.isSuccessful) onComplete.invoke(FirebaseMessagingState.OnSuccessGetToken(task.result))
            else onComplete.invoke(FirebaseMessagingState.OnFailedGetToken(task.exception))
        }
    }

    override fun logError(exception: Exception?) {
        crashlytics.log(exception?.message.takeIf { it != null } ?: "Error")
    }
}