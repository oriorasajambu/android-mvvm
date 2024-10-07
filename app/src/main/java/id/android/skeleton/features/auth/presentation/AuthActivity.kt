package id.android.skeleton.features.auth.presentation

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.android.skeleton.base.presentation.BaseActivity
import id.android.skeleton.databinding.ActivityAuthBinding

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding>(ActivityAuthBinding::inflate) {

    private val authViewModel: AuthViewModel by viewModels()

    override fun setupView(savedInstanceState: Bundle?) {
    }

    override fun setupToolbar() {
    }

    override fun setupViewModel() {
    }
}