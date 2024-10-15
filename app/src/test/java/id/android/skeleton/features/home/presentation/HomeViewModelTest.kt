package id.android.skeleton.features.home.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import id.android.skeleton.base.domain.BaseDomainObject
import id.android.skeleton.base.presentation.BaseResultState
import id.android.skeleton.common.Constants
import id.android.skeleton.features.home.domain.HomeUseCase
import id.android.skeleton.features.home.domain.model.response.PaymentDetails
import id.android.skeleton.features.home.presentation.states.PaymentDetailsState
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.invoke
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifySequence
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.HttpException

class HomeViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    private lateinit var useCase: HomeUseCase

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(useCase)
    }

    @Test
    fun `onCleared Should Call clearDisposable`() {
        //GIVEN
        every { useCase.clearDisposable() } returns Unit

        //WHEN
        viewModel.onCleared()

        //THEN
        verify(exactly = 1) { useCase.clearDisposable() }
    }

    @Test
    fun `getPaymentDetails Should return OnErrorState when Error`() {
        //GIVEN
        val error = mockk<HttpException>(relaxed = true)
        val expected = PaymentDetailsState.OnError(error)

        every { useCase.getPaymentDetails(any(), captureLambda()) } answers {
            lambda<(BaseResultState<BaseDomainObject<PaymentDetails>>) -> Unit>().invoke(
                BaseResultState.Loading
            )
            lambda<(BaseResultState<BaseDomainObject<PaymentDetails>>) -> Unit>().invoke(
                BaseResultState.Error(error)
            )
        }

        val observer = mockk<Observer<PaymentDetailsState>>(relaxed = true)
        viewModel.paymentDetailsState.observeForever(observer)

        //WHEN
        viewModel.getPaymentDetails()

        verifySequence {
            observer.onChanged(PaymentDetailsState.OnLoading)
            observer.onChanged(expected)
        }
        confirmVerified(observer)

        //THEN
        verify(exactly = 1) { useCase.getPaymentDetails(any(), any()) }
        assertThat(viewModel.paymentDetailsState.value).isEqualTo(expected)

        viewModel.paymentDetailsState.removeObserver(observer)
    }

    @Test
    fun `getPaymentDetails Should return OnFailed when responseKey FAILED, content Null`() {
        //GIVEN
        val content = null
        val expected = PaymentDetailsState.OnFailed

        every { useCase.getPaymentDetails(any(), captureLambda()) } answers {
            lambda<(BaseResultState<BaseDomainObject<PaymentDetails>>) -> Unit>().invoke(
                BaseResultState.Loading
            )
            lambda<(BaseResultState<BaseDomainObject<PaymentDetails>>) -> Unit>().invoke(
                BaseResultState.Success(
                    BaseDomainObject(
                        responseKey = Constants.FAILED, content = content
                    )
                )
            )
        }

        val observer = mockk<Observer<PaymentDetailsState>>(relaxed = true)
        viewModel.paymentDetailsState.observeForever(observer)

        //WHEN
        viewModel.getPaymentDetails()

        verifySequence {
            observer.onChanged(PaymentDetailsState.OnLoading)
            observer.onChanged(expected)
        }
        confirmVerified(observer)

        //THEN
        verify(exactly = 1) { useCase.getPaymentDetails(any(), any()) }
        assertThat(viewModel.paymentDetailsState.value).isEqualTo(expected)

        viewModel.paymentDetailsState.removeObserver(observer)
    }

    @Test
    fun `getPaymentDetails Should return OnFailed when responseKey FAILED, content Not Null`() {
        //GIVEN
        val content = mockk<PaymentDetails>()
        val expected = PaymentDetailsState.OnFailed

        every { useCase.getPaymentDetails(any(), captureLambda()) } answers {
            lambda<(BaseResultState<BaseDomainObject<PaymentDetails>>) -> Unit>().invoke(
                BaseResultState.Loading
            )
            lambda<(BaseResultState<BaseDomainObject<PaymentDetails>>) -> Unit>().invoke(
                BaseResultState.Success(
                    BaseDomainObject(
                        responseKey = Constants.FAILED, content = content
                    )
                )
            )
        }

        val observer = mockk<Observer<PaymentDetailsState>>(relaxed = true)
        viewModel.paymentDetailsState.observeForever(observer)

        //WHEN
        viewModel.getPaymentDetails()

        verifySequence {
            observer.onChanged(PaymentDetailsState.OnLoading)
            observer.onChanged(expected)
        }
        confirmVerified(observer)

        //THEN
        verify(exactly = 1) { useCase.getPaymentDetails(any(), any()) }
        assertThat(viewModel.paymentDetailsState.value).isEqualTo(expected)

        viewModel.paymentDetailsState.removeObserver(observer)
    }

    @Test
    fun `getPaymentDetails Should return OnFailed when responseKey SUCCESS, content null`() {
        //GIVEN
        val content = null
        val expected = PaymentDetailsState.OnFailed

        every { useCase.getPaymentDetails(any(), captureLambda()) } answers {
            lambda<(BaseResultState<BaseDomainObject<PaymentDetails>>) -> Unit>().invoke(
                BaseResultState.Loading
            )
            lambda<(BaseResultState<BaseDomainObject<PaymentDetails>>) -> Unit>().invoke(
                BaseResultState.Success(
                    BaseDomainObject(
                        responseKey = Constants.SUCCESS, content = content
                    )
                )
            )
        }

        val observer = mockk<Observer<PaymentDetailsState>>(relaxed = true)
        viewModel.paymentDetailsState.observeForever(observer)

        //WHEN
        viewModel.getPaymentDetails()

        verifySequence {
            observer.onChanged(PaymentDetailsState.OnLoading)
            observer.onChanged(expected)
        }
        confirmVerified(observer)

        //THEN
        verify(exactly = 1) { useCase.getPaymentDetails(any(), any()) }
        assertThat(viewModel.paymentDetailsState.value).isEqualTo(expected)

        viewModel.paymentDetailsState.removeObserver(observer)
    }

    @Test
    fun `getPaymentDetails Should return OnSuccessState when responseKey SUCCESS, content Not Null`() {
        //GIVEN
        val content = mockk<PaymentDetails>()
        val expected = PaymentDetailsState.OnSuccess(content)

        every { useCase.getPaymentDetails(any(), captureLambda()) } answers {
            lambda<(BaseResultState<BaseDomainObject<PaymentDetails>>) -> Unit>().invoke(
                BaseResultState.Loading
            )
            lambda<(BaseResultState<BaseDomainObject<PaymentDetails>>) -> Unit>().invoke(
                BaseResultState.Success(
                    BaseDomainObject(
                        responseKey = Constants.SUCCESS, content = content
                    )
                )
            )
        }

        val observer = mockk<Observer<PaymentDetailsState>>(relaxed = true)
        viewModel.paymentDetailsState.observeForever(observer)

        //WHEN
        viewModel.getPaymentDetails()

        verifySequence {
            observer.onChanged(PaymentDetailsState.OnLoading)
            observer.onChanged(expected)
        }
        confirmVerified(observer)

        //THEN
        verify(exactly = 1) { useCase.getPaymentDetails(any(), any()) }
        assertThat(viewModel.paymentDetailsState.value).isEqualTo(expected)

        viewModel.paymentDetailsState.removeObserver(observer)
    }
}