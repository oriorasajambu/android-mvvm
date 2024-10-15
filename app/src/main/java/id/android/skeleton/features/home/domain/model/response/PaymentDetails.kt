package id.android.skeleton.features.home.domain.model.response

data class PaymentDetails(
    val transactionId: String? = null,
    val paymentType: String? = null,
    val paymentRefId: String? = null,
    val paymentRefNumber: String? = null,
    val totalAmount: String? = null,
    val totalAmountCurrency: String? = null,
    val exchangeRate: ExchangeRate? = null,
    val poinXtra: PoinXtra? = null,
    val tid: Tid? = null,
) {
    data class ExchangeRate(
        val fromCurrency: String? = null,
        val fromAmount: String? = null,
        val toCurrency: String? = null,
        val toAmount: String? = null,
    )

    data class PoinXtra(
        val currency: String? = null,
        val poin: String? = null,
        val equivalentTo: String? = null,
        val poinEquivalentTo: String? = null,
    )

    data class Tid(
        val mpan: String? = null,
        val cpan: String? = null,
    )
}
