package id.android.skeleton.features.startup.domain

import id.android.skeleton.features.startup.data.StartupRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class StartupInteractor @Inject constructor(
    private val disposable: CompositeDisposable,
    private val repository: StartupRepository,
) : StartupUseCase {
    override fun clearDisposable() {
        disposable.clear()
    }
}