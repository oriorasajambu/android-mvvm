package id.android.skeleton.features.home.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.android.skeleton.base.domain.BaseDomainObject
import id.android.skeleton.base.domain.BaseDtoRequest
import id.android.skeleton.base.presentation.BaseResultState
import id.android.skeleton.common.reactive.TestSchedulerProvider
import id.android.skeleton.features.home.data.HomeRepository
import id.android.skeleton.features.home.domain.model.response.PaymentDetails
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class HomeInteractorTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    private lateinit var disposable: CompositeDisposable

    @RelaxedMockK
    private lateinit var repository: HomeRepository

    private lateinit var interactor: HomeInteractor

    @Before
    fun setup() {
        interactor = HomeInteractor(disposable, repository, TestSchedulerProvider())
    }

    @Test
    fun `clearDisposable should call disposable clear()`() {
        //GiVEN
        every { disposable.clear() } returns Unit

        //WHEN
        interactor.clearDisposable()

        //THEN
        verify(exactly = 1) { disposable.clear() }
    }

    @Test
    fun `getPaymentDetails should call getPaymentDetails repository`() {
        //GIVEN
        val expected = BaseDomainObject<PaymentDetails>()
        val expectedCallback = BaseResultState.Success(expected)

        val mockCallback = mockk<(BaseResultState<BaseDomainObject<PaymentDetails>>) -> Unit>(relaxed = true)
        every { repository.getPaymentDetails(any()) } returns Observable.just(expected)

        //WHEN
        interactor.getPaymentDetails(BaseDtoRequest(), mockCallback)

        //THEN
        verify(exactly = 1) { repository.getPaymentDetails(any()) }
        verify(exactly = 1) { mockCallback(expectedCallback) }
    }
}