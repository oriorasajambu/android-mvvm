package id.android.skeleton.services.firebase

sealed interface FirebaseMessagingState {
    data class OnSuccessGetToken(val token: String): FirebaseMessagingState
    data class OnFailedGetToken(val exception: Exception?): FirebaseMessagingState
}