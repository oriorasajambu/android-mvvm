package id.android.skeleton.features.home.presentation.states

import id.android.skeleton.features.home.domain.model.response.PaymentDetails

sealed interface PaymentDetailsState {
    data object OnLoading: PaymentDetailsState
    data class OnError(val error: Throwable): PaymentDetailsState
    data object OnFailed: PaymentDetailsState
    data class OnSuccess(val result: PaymentDetails): PaymentDetailsState
}