package id.android.skeleton.features.home.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import id.android.skeleton.base.data.BaseDataObject
import id.android.skeleton.base.domain.BaseDtoRequest
import id.android.skeleton.features.home.data.model.Mapper.asPaymetDetails
import id.android.skeleton.features.home.data.model.PaymentDetailsResponse
import id.android.skeleton.services.session.SessionUseCase
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class HomeDataRepositoryTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    private lateinit var sessionUseCase: SessionUseCase

    @RelaxedMockK
    private lateinit var apiServices: HomeApiServices

    private lateinit var homeDataRepository: HomeDataRepository

    @Before
    fun setup() {
        homeDataRepository = HomeDataRepository(sessionUseCase, apiServices)
    }

    @Test
    fun `getPaymentDetails should return Observable BaseDomainObject PaymentDetails 1`() {
        //GIVEN
        val expected = BaseDataObject<PaymentDetailsResponse>()
        every { apiServices.getPaymentDetails(any(), any()) } returns Observable.just(expected)

        //WHEN
        val actual = homeDataRepository.getPaymentDetails(BaseDtoRequest()).blockingFirst()

        verify(exactly = 1) { apiServices.getPaymentDetails(any(), any()) }
        assertThat(actual).isEqualTo(asPaymetDetails(expected))
    }

    @Test
    fun `getPaymentDetails should return Observable BaseDomainObject PaymentDetails 2`() {
        //GIVEN
        val expected = BaseDataObject(
            timeStamp = "test",
            responseKey = "test",
            traceId = "test",
            sourceSystem = "test",
            message = BaseDataObject.Message(),
            result = PaymentDetailsResponse()
        )
        every { apiServices.getPaymentDetails(any(), any()) } returns Observable.just(expected)

        //WHEN
        val actual = homeDataRepository.getPaymentDetails(BaseDtoRequest()).blockingFirst()

        verify(exactly = 1) { apiServices.getPaymentDetails(any(), any()) }
        assertThat(actual).isEqualTo(asPaymetDetails(expected))
    }

    @Test
    fun `getPaymentDetails should return Observable BaseDomainObject PaymentDetails 3`() {
        //GIVEN
        val expected = BaseDataObject(
            timeStamp = "test",
            responseKey = "test",
            traceId = "test",
            sourceSystem = "test",
            message = BaseDataObject.Message(
                titleEng = "test",
                titleInd = "test",
                descEng = "test",
                descInd = "test"
            ),
            result = PaymentDetailsResponse(
                transactionId = "test",
                paymentType = "test",
                paymentRefId = "test",
                paymentRefNumber = "test",
                totalAmount = "test",
                totalAmountCurrency = "test",
                exchangeRate = PaymentDetailsResponse.ExchangeRate(),
                poinXtra = PaymentDetailsResponse.PoinXtra(),
                tid = PaymentDetailsResponse.Tid()
            )
        )
        every { apiServices.getPaymentDetails(any(), any()) } returns Observable.just(expected)

        //WHEN
        val actual = homeDataRepository.getPaymentDetails(BaseDtoRequest()).blockingFirst()

        verify(exactly = 1) { apiServices.getPaymentDetails(any(), any()) }
        assertThat(actual).isEqualTo(asPaymetDetails(expected))
    }

    @Test
    fun `getPaymentDetails should return Observable BaseDomainObject PaymentDetails 4`() {
        //GIVEN
        val expected = BaseDataObject(
            timeStamp = "test",
            responseKey = "test",
            traceId = "test",
            sourceSystem = "test",
            message = BaseDataObject.Message(
                titleEng = "test",
                titleInd = "test",
                descEng = "test",
                descInd = "test"
            ),
            result = PaymentDetailsResponse(
                transactionId = "test",
                paymentType = "test",
                paymentRefId = "test",
                paymentRefNumber = "test",
                totalAmount = "test",
                totalAmountCurrency = "test",
                exchangeRate = PaymentDetailsResponse.ExchangeRate(
                    fromCurrency = "test",
                    fromAmount = "test",
                    toCurrency = "test",
                    toAmount = "test"
                ),
                poinXtra = PaymentDetailsResponse.PoinXtra(
                    currency = "test",
                    poin = "test",
                    equivalentTo = "test",
                    poinEquivalentTo = "test"
                ),
                tid = PaymentDetailsResponse.Tid(mpan = "test", cpan = "test")
            )
        )
        every { apiServices.getPaymentDetails(any(), any()) } returns Observable.just(expected)

        //WHEN
        val actual = homeDataRepository.getPaymentDetails(BaseDtoRequest()).blockingFirst()

        verify(exactly = 1) { apiServices.getPaymentDetails(any(), any()) }
        assertThat(actual).isEqualTo(asPaymetDetails(expected))
    }
}