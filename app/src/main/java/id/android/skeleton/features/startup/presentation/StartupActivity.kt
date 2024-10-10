package id.android.skeleton.features.startup.presentation

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.android.skeleton.base.presentation.BaseActivity
import id.android.skeleton.databinding.ActivityStartupBinding
import id.android.skeleton.features.home.presentation.HomeActivity
import id.android.skeleton.services.notification.PushNotificationHandler
import id.android.skeleton.services.notification.model.PushNotificationKey
import id.android.skeleton.services.notification.model.PushNotificationMapper
import id.android.skeleton.services.notification.model.PushNotificationResult

@AndroidEntryPoint
class StartupActivity : BaseActivity<ActivityStartupBinding>(ActivityStartupBinding::inflate) {

    private val startupViewModel: StartupViewModel by viewModels()

    companion object {
        const val TAG = "Startup"
    }

    override fun setupView(savedInstanceState: Bundle?) {
        handleNotification {
            initializeApp()
        }
    }

    override fun setupToolbar() {
    }

    override fun setupViewModel() {
    }

    private fun initializeApp() {
        startupViewModel.initializeFirebaseToken()
        goToHome()
    }

    private fun handleNotification(onFailed: () -> Unit) {
        val bundle = intent.extras ?: Bundle()
        if (!bundle.isEmpty) {
            runCatching {
                val notification = mutableMapOf<String, String?>().apply {
                    PushNotificationKey.entries.forEach { entry ->
                        if (intent.hasExtra(entry.key)) {
                            put(entry.key, intent.getStringExtra(entry.key))
                        }
                    }
                }
                PushNotificationMapper.mapToNotificationResult(
                    notificationTitle = null,
                    notificationBody = null,
                    notificationData = notification
                )
            }.onSuccess { result ->
                log.i(TAG, result.toString())
                goToNotificationHandler(result)
            }.onFailure { error ->
                log.e(TAG, error.message.toString())
                onFailed.invoke()
            }
        } else {
            onFailed.invoke()
        }
    }

    //Router
    private fun goToNotificationHandler(pushNotificationResult: PushNotificationResult) {
        val intent = PushNotificationHandler.launchPendingHandler(
            this, pushNotificationResult
        )
        startActivity(intent)
        finish()
    }

    private fun goToHome() {
        HomeActivity.launchScreen(this)
        finish()
    }
}