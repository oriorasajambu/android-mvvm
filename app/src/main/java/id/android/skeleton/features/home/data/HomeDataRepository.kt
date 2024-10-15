package id.android.skeleton.features.home.data

import id.android.skeleton.base.domain.BaseDomainObject
import id.android.skeleton.base.domain.BaseDtoRequest
import id.android.skeleton.features.home.data.model.Mapper
import id.android.skeleton.features.home.domain.model.response.PaymentDetails
import id.android.skeleton.services.session.SessionUseCase
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class HomeDataRepository @Inject constructor(
    private val session: SessionUseCase,
    private val apiServices: HomeApiServices,
) : HomeRepository {
    override fun getPaymentDetails(
        request: BaseDtoRequest
    ): Observable<BaseDomainObject<PaymentDetails>> {
        return apiServices.getPaymentDetails(
            version = request.version,
            request = request.body
        ).map(Mapper::asPaymetDetails)
    }
}