package id.android.skeleton.features.home.domain

import id.android.skeleton.base.domain.BaseDomainObject
import id.android.skeleton.base.domain.BaseDtoRequest
import id.android.skeleton.base.presentation.BaseResultState
import id.android.skeleton.common.reactive.ReactiveExtensions.addTo
import id.android.skeleton.common.reactive.ReactiveExtensions.subscribeAsState
import id.android.skeleton.common.reactive.SchedulerProvider
import id.android.skeleton.features.home.data.HomeRepository
import id.android.skeleton.features.home.domain.model.response.PaymentDetails
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class HomeInteractor @Inject constructor(
    private val disposable: CompositeDisposable,
    private val repository: HomeRepository,
    private val schedulerProvider: SchedulerProvider,
) : HomeUseCase {
    override fun clearDisposable() {
        disposable.clear()
    }

    override fun getPaymentDetails(
        request: BaseDtoRequest,
        callback: (BaseResultState<BaseDomainObject<PaymentDetails>>) -> Unit,
    ) {
        repository.getPaymentDetails(request)
            .subscribeAsState(schedulerProvider, callback)
            .addTo(disposable)
    }
}