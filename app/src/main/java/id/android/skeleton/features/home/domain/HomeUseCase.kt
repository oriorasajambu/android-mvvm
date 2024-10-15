package id.android.skeleton.features.home.domain

import id.android.skeleton.base.domain.BaseDomainObject
import id.android.skeleton.base.domain.BaseDtoRequest
import id.android.skeleton.base.presentation.BaseResultState
import id.android.skeleton.features.home.domain.model.response.PaymentDetails
import kotlinx.coroutines.flow.FlowCollector

interface HomeUseCase {
    fun clearDisposable()
    fun getPaymentDetails(
        request: BaseDtoRequest,
        callback: (BaseResultState<BaseDomainObject<PaymentDetails>>) -> Unit
    )
}