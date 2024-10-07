package id.android.skeleton.services.firebase

import java.lang.Exception

interface FirebaseUseCase {
    fun onCompleteGetToken(onComplete: (FirebaseMessagingState) -> Unit)
    fun logError(exception: Exception?)
}