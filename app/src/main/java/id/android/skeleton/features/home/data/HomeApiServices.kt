package id.android.skeleton.features.home.data

import com.google.gson.JsonObject
import id.android.skeleton.base.annotation.Mock
import id.android.skeleton.base.data.BaseDataObject
import id.android.skeleton.features.home.data.model.PaymentDetailsResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface HomeApiServices {
    @Mock(fileName = "mock/payment_details_200.json", httpCode = 200)
    @POST("/payment/details")
    fun getPaymentDetails(
        @Header("Content-Type") version: String,
        @Body request: JsonObject?,
    ): Observable<BaseDataObject<PaymentDetailsResponse>>
}