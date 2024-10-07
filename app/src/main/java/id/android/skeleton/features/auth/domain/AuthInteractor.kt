package id.android.skeleton.features.auth.domain

import id.android.skeleton.features.auth.data.AuthRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val disposable: CompositeDisposable,
    private val repository: AuthRepository,
) : AuthUseCase {
    override fun clearDisposable() {
        disposable.clear()
    }
}