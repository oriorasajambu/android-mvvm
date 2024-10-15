package id.android.skeleton.features.home.data

import id.android.skeleton.base.domain.BaseDomainObject
import id.android.skeleton.base.domain.BaseDtoRequest
import id.android.skeleton.features.home.domain.model.response.PaymentDetails
import io.reactivex.rxjava3.core.Observable

interface HomeRepository {
    fun getPaymentDetails(
        request: BaseDtoRequest
    ): Observable<BaseDomainObject<PaymentDetails>>
}