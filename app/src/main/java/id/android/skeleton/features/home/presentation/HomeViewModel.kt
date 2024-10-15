package id.android.skeleton.features.home.presentation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.android.skeleton.base.domain.BaseDtoRequest
import id.android.skeleton.base.presentation.BaseResultState
import id.android.skeleton.common.Constants
import id.android.skeleton.common.Utils.parse
import id.android.skeleton.features.home.domain.HomeUseCase
import id.android.skeleton.features.home.domain.model.request.PaymentDetailsRequest
import id.android.skeleton.features.home.presentation.states.PaymentDetailsState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
) : ViewModel() {
    private val _paymentDetailsState: MutableLiveData<PaymentDetailsState> = MutableLiveData()
    val paymentDetailsState: LiveData<PaymentDetailsState> get() = _paymentDetailsState

    fun getPaymentDetails() {
        homeUseCase.getPaymentDetails(
            request = BaseDtoRequest(
                version = Constants.Versions.V1,
                body = PaymentDetailsRequest(
                    transactionId = "123456789"
                ).parse()
            )
        ) { result ->
            when (result) {
                BaseResultState.Loading -> {
                    _paymentDetailsState.postValue(PaymentDetailsState.OnLoading)
                }

                is BaseResultState.Error -> {
                    _paymentDetailsState.postValue(PaymentDetailsState.OnError(result.error))
                }

                is BaseResultState.Success -> {
                    val responseKey = result.data.responseKey
                    val content = result.data.content
                    if (responseKey == Constants.SUCCESS && content != null) {
                        _paymentDetailsState.postValue(PaymentDetailsState.OnSuccess(content))
                    } else {
                        _paymentDetailsState.postValue(PaymentDetailsState.OnFailed)
                    }
                }
            }
        }
    }

    @VisibleForTesting
    public override fun onCleared() {
        super.onCleared()
        homeUseCase.clearDisposable()
    }
}