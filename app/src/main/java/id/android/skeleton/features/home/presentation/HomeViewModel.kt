package id.android.skeleton.features.home.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.android.skeleton.features.home.domain.HomeUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        homeUseCase.clearDisposable()
    }
}