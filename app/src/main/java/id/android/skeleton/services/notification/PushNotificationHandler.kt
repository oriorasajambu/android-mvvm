package id.android.skeleton.services.notification

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import id.android.skeleton.common.Constants
import id.android.skeleton.common.Utils.getParcelable
import id.android.skeleton.features.auth.presentation.AuthActivity
import id.android.skeleton.features.home.presentation.HomeActivity
import id.android.skeleton.services.notification.model.PushNotificationResult

class PushNotificationHandler : Activity() {

    companion object {
        fun launchPendingHandler(
            context: Context,
            pushNotificationResult: PushNotificationResult,
        ): Intent {
            val intent = Intent(context, PushNotificationHandler::class.java)
            intent.putExtra(Constants.NOTIFICATION_DATA, pushNotificationResult)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            return intent
        }
    }

    private var pushNotificationResult: PushNotificationResult? = null

    private fun getDataIntent() = with(intent) {
        pushNotificationResult = getParcelable(
            Constants.NOTIFICATION_DATA, PushNotificationResult::class
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDataIntent()
        router()
        finish()
    }

    private fun router() {
        val data = pushNotificationResult?.extraData ?: return
        val target = data.target
        when (Route.checkTarget(target)) {
            Route.NOT_FOUND -> return
            Route.HOME -> HomeActivity.launchScreen(this, pushNotificationResult)
            Route.AUTH -> AuthActivity.launchScreen(this, pushNotificationResult)
        }
    }

    private enum class Route(val target: String) {
        NOT_FOUND(target = ""), HOME(target = HomeActivity.TAG), AUTH(target = AuthActivity.TAG);

        companion object {
            fun checkTarget(target: String?): Route {
                return when (target) {
                    HOME.target -> HOME
                    AUTH.target -> AUTH
                    else -> NOT_FOUND
                }
            }
        }
    }
}