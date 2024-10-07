package id.android.skeleton.features.startup.presentation

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.android.skeleton.base.presentation.BaseActivity
import id.android.skeleton.databinding.ActivityStartupBinding

@AndroidEntryPoint
class StartupActivity : BaseActivity<ActivityStartupBinding>(ActivityStartupBinding::inflate) {

    private val startupViewModel: StartupViewModel by viewModels()

    override fun setupView(savedInstanceState: Bundle?) {
    }

    override fun setupToolbar() {
    }

    override fun setupViewModel() {
        startupViewModel.initializeFirebaseToken()
    }
}