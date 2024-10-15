package id.android.skeleton.features.home.domain.model.request

import com.google.gson.annotations.SerializedName

data class PaymentDetailsRequest(
    @SerializedName("transaction_id")
    val transactionId: String,
)
