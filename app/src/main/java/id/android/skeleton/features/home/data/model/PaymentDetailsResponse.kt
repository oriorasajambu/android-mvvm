package id.android.skeleton.features.home.data.model

import com.google.gson.annotations.SerializedName

data class PaymentDetailsResponse(
    @SerializedName("transactionId")
    val transactionId: String? = null,
    @SerializedName("paymentType")
    val paymentType: String? = null,
    @SerializedName("paymentRefId")
    val paymentRefId: String? = null,
    @SerializedName("paymentRefNumber")
    val paymentRefNumber: String? = null,
    @SerializedName("totalAmount")
    val totalAmount: String? = null,
    @SerializedName("totalAmountCurrency")
    val totalAmountCurrency: String? = null,
    @SerializedName("exchangeRate")
    val exchangeRate: ExchangeRate? = null,
    @SerializedName("poinXtra")
    val poinXtra: PoinXtra? = null,
    @SerializedName("tid")
    val tid: Tid? = null
) {
    data class ExchangeRate(
        @SerializedName("fromCurrency")
        val fromCurrency: String? = null,
        @SerializedName("fromAmount")
        val fromAmount: String? = null,
        @SerializedName("toCurrency")
        val toCurrency: String? = null,
        @SerializedName("toAmount")
        val toAmount: String? = null
    )

    data class PoinXtra(
        @SerializedName("currency")
        val currency: String? = null,
        @SerializedName("poin")
        val poin: String? = null,
        @SerializedName("equivalentTo")
        val equivalentTo: String? = null,
        @SerializedName("poinEquivalentTo")
        val poinEquivalentTo: String? = null
    )

    data class Tid(
        @SerializedName("mpan")
        val mpan: String? = null,
        @SerializedName("cpan")
        val cpan: String? = null
    )
}