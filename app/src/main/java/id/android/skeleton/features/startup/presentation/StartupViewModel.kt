package id.android.skeleton.features.startup.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.android.skeleton.features.startup.domain.StartupUseCase
import id.android.skeleton.services.firebase.FirebaseMessagingState
import id.android.skeleton.services.firebase.FirebaseUseCase
import javax.inject.Inject

@HiltViewModel
class StartupViewModel @Inject constructor(
    private val startupUseCase: StartupUseCase,
    private val firebaseUseCase: FirebaseUseCase,
) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        startupUseCase.clearDisposable()
    }

    fun initializeFirebaseToken() {
        firebaseUseCase.onCompleteGetToken { state ->
            when (state) {
                is FirebaseMessagingState.OnFailedGetToken -> {
                    Log.e("Failed Get Token", state.exception?.message ?: "")
                }

                is FirebaseMessagingState.OnSuccessGetToken -> {
                    Log.i("Firebase Token", state.token)
                }
            }
        }
    }
}