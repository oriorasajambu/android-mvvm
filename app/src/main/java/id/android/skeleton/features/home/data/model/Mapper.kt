package id.android.skeleton.features.home.data.model

import id.android.skeleton.base.data.BaseDataObject
import id.android.skeleton.base.domain.BaseDomainObject
import id.android.skeleton.common.Utils.orDefault
import id.android.skeleton.features.home.domain.model.response.PaymentDetails

object Mapper {
    fun asPaymetDetails(dataObject: BaseDataObject<PaymentDetailsResponse>): BaseDomainObject<PaymentDetails> {
        return BaseDomainObject(
            timeStamp = dataObject.timeStamp.orEmpty(),
            responseKey = dataObject.responseKey.orEmpty(),
            traceId = dataObject.traceId.orEmpty(),
            sourceSystem = dataObject.sourceSystem.orEmpty(),
            message = dataObject.message?.asMessage().orDefault(
                BaseDomainObject.Message()
            ),
            content = dataObject.result?.let { details ->
                PaymentDetails(transactionId = details.transactionId,
                    paymentType = details.paymentType.orEmpty(),
                    paymentRefId = details.paymentRefId.orEmpty(),
                    paymentRefNumber = details.paymentRefNumber.orEmpty(),
                    totalAmount = details.totalAmount.orEmpty(),
                    totalAmountCurrency = details.totalAmountCurrency.orEmpty(),
                    exchangeRate = details.exchangeRate?.let { exchangeRate ->
                        PaymentDetails.ExchangeRate(
                            fromCurrency = exchangeRate.fromCurrency.orEmpty(),
                            fromAmount = exchangeRate.fromAmount.orEmpty(),
                            toCurrency = exchangeRate.toCurrency.orEmpty(),
                            toAmount = exchangeRate.toAmount.orEmpty()
                        )
                    },
                    poinXtra = details.poinXtra?.let { poinXtra ->
                        PaymentDetails.PoinXtra(
                            currency = poinXtra.currency.orEmpty(),
                            poin = poinXtra.poin.orEmpty(),
                            equivalentTo = poinXtra.equivalentTo.orEmpty(),
                            poinEquivalentTo = poinXtra.poinEquivalentTo.orEmpty()
                        )
                    },
                    tid = details.tid?.let { tid ->
                        PaymentDetails.Tid(
                            mpan = tid.mpan.orEmpty(), cpan = tid.cpan.orEmpty()
                        )
                    })
            })
    }
}