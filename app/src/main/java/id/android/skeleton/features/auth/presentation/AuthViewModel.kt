package id.android.skeleton.features.auth.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.android.skeleton.features.auth.domain.AuthUseCase
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        authUseCase.clearDisposable()
    }
}