package id.android.skeleton.features.auth.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.android.skeleton.base.presentation.BaseActivity
import id.android.skeleton.common.Constants
import id.android.skeleton.databinding.ActivityAuthBinding
import id.android.skeleton.services.notification.model.PushNotificationResult

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding>(ActivityAuthBinding::inflate) {

    private val authViewModel: AuthViewModel by viewModels()

    companion object {
        const val TAG = "Auth"

        fun launchScreen(
            context: Context,
            pushNotificationResult: PushNotificationResult? = null,
        ) {
            val intent = Intent(context, AuthActivity::class.java)
            intent.putExtra(Constants.NOTIFICATION_DATA, pushNotificationResult)
            context.startActivity(intent)
        }
    }

    override fun setupView(savedInstanceState: Bundle?) {
        val bundle = pushNotificationResult?.extraData?.bundle
        log.e(TAG, bundle.toString())
        binding.tvTest.text = pushNotificationResult?.extraData?.getBundleAsJson().toString()
    }

    override fun setupToolbar() {
    }

    override fun setupViewModel() {
    }
}