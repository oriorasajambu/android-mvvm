package id.android.skeleton.features.home.domain

import id.android.skeleton.features.home.data.HomeRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class HomeInteractor @Inject constructor(
    private val disposable: CompositeDisposable,
    private val repository: HomeRepository,
) : HomeUseCase {
    override fun clearDisposable() {
        disposable.clear()
    }
}