package id.android.skeleton.features.home.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.android.skeleton.R
import id.android.skeleton.base.presentation.BaseActivity
import id.android.skeleton.common.Constants
import id.android.skeleton.common.Converter.formatCurrency
import id.android.skeleton.common.Utils.checkEmpty
import id.android.skeleton.common.Utils.decimalPart
import id.android.skeleton.databinding.ActivityHomeBinding
import id.android.skeleton.features.home.domain.model.response.PaymentDetails
import id.android.skeleton.features.home.presentation.states.PaymentDetailsState
import id.android.skeleton.services.notification.model.PushNotificationResult
import id.android.skeleton.ui.components.details.Detail

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {

    private val homeViewModel: HomeViewModel by viewModels()

    companion object {
        const val TAG = "Home"

        fun launchScreen(
            context: Context,
            pushNotificationResult: PushNotificationResult? = null,
        ) {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtra(Constants.NOTIFICATION_DATA, pushNotificationResult)
            context.startActivity(intent)
        }
    }

    override fun setupView(savedInstanceState: Bundle?) {
        binding.cdPaymentDetails.setLabel(getString(R.string.label_payment_details))
    }

    override fun setupToolbar() {
    }

    override fun setupViewModel() {
        homeViewModel.paymentDetailsState.observe(this, ::handlePaymentDetails)
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.getPaymentDetails()
    }

    private fun handlePaymentDetails(state: PaymentDetailsState) {
        when (state) {
            PaymentDetailsState.OnLoading -> {
                binding.cdPaymentDetails.setShimmer(true)
            }

            is PaymentDetailsState.OnError -> {
                binding.cdPaymentDetails.setShimmer(false)
            }

            PaymentDetailsState.OnFailed -> {
                binding.cdPaymentDetails.setShimmer(false)
            }

            is PaymentDetailsState.OnSuccess -> {
                binding.cdPaymentDetails.setShimmer(false)
                val listDetails = createDetails(state.result)
                binding.cdPaymentDetails.setList(listDetails)
            }
        }
    }

    private fun createDetails(content: PaymentDetails): List<Detail> {
        return listOf(
            Detail(
                id = 1,
                label = getString(R.string.label_payment_ref_number),
                value1 = content.paymentRefId.checkEmpty(),
                value2 = content.paymentRefNumber.checkEmpty(),
            ), Detail(
                id = 2,
                label = getString(
                    R.string.label_total_amount
                ), value1 = getString(
                    R.string.format_amount,
                    content.totalAmountCurrency.checkEmpty(),
                    content.totalAmount.formatCurrency(),
                    content.totalAmount.decimalPart(),
                ), altValue1 = getString(
                    R.string.format_exchange_rate,
                    content.exchangeRate?.fromCurrency.checkEmpty(),
                    content.exchangeRate?.fromAmount.formatCurrency(),
                    content.exchangeRate?.fromAmount.decimalPart(),
                    content.exchangeRate?.toCurrency.checkEmpty(),
                    content.exchangeRate?.toAmount.formatCurrency(),
                    content.exchangeRate?.toAmount.decimalPart()
                ), altValue2 = getString(
                    R.string.format_amount_from_poin,
                    content.poinXtra?.currency.checkEmpty(),
                    content.poinXtra?.poin.formatCurrency(),
                    content.poinXtra?.poin.decimalPart(),
                    getString(R.string.fixed_poin_xtra)
                )
            ), Detail(
                id = 3,
                label = getString(R.string.label_payment_type),
                value1 = content.paymentType.checkEmpty()
            ), Detail(
                id = 4,
                label = getString(R.string.label_tid),
                value1 = getString(
                    R.string.format_mpan, content.tid?.mpan.checkEmpty()
                ),
                value2 = getString(
                    R.string.format_cpan, content.tid?.cpan.checkEmpty()
                ),
            )
        )
    }
}