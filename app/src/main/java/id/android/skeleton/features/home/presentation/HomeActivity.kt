package id.android.skeleton.features.home.presentation

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.android.skeleton.base.presentation.BaseActivity
import id.android.skeleton.databinding.ActivityHomeBinding

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun setupView(savedInstanceState: Bundle?) {
    }

    override fun setupToolbar() {
    }

    override fun setupViewModel() {
    }
}