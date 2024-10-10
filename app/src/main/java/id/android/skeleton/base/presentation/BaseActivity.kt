package id.android.skeleton.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewbinding.ViewBinding
import id.android.skeleton.common.Constants
import id.android.skeleton.common.Utils.getParcelable
import id.android.skeleton.common.logger.LogUseCase
import id.android.skeleton.services.lifecycle.AppLifeCycleUseCase
import id.android.skeleton.services.notification.model.PushNotificationResult
import javax.inject.Inject

abstract class BaseActivity<T : ViewBinding>(
    private val bindingInflater: (layoutInflater: LayoutInflater) -> T
): AppCompatActivity() {

    @Inject
    lateinit var log: LogUseCase

    @Inject
    lateinit var lifeCycleUseCase: AppLifeCycleUseCase

    private lateinit var _binding: T

    protected val binding get() = _binding

    protected var pushNotificationResult: PushNotificationResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getCommonDataIntent()
        setupView(savedInstanceState)
        setupToolbar()
        setupViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        pushNotificationResult = null
    }

    abstract fun setupView(savedInstanceState: Bundle?)

    abstract fun setupToolbar()

    abstract fun setupViewModel()

    private fun getCommonDataIntent() = with(intent) {
        if (hasExtra(Constants.NOTIFICATION_DATA)) {
            pushNotificationResult = getParcelable(
                Constants.NOTIFICATION_DATA, PushNotificationResult::class
            )
        }
    }

    protected fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}