package id.android.skeleton.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewbinding.ViewBinding
import id.android.skeleton.services.lifecycle.AppLifeCycleInteractor
import javax.inject.Inject

abstract class BaseActivity<T : ViewBinding>(
    private val bindingInflater: (layoutInflater: LayoutInflater) -> T
): AppCompatActivity() {

    @Inject
    lateinit var lifeCycleInteractor: AppLifeCycleInteractor

    private lateinit var _binding: T

    protected val binding get() = _binding

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
        setupView(savedInstanceState)
        setupToolbar()
        setupViewModel()
    }

    abstract fun setupView(savedInstanceState: Bundle?)

    abstract fun setupToolbar()

    abstract fun setupViewModel()
}