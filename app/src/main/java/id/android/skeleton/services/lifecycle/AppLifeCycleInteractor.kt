package id.android.skeleton.services.lifecycle

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import id.android.skeleton.common.logger.LogUseCase
import javax.inject.Inject

/**
 * Monitors the application's lifecycle and provides information about its visibility state.
 *
 * This class implements the [DefaultLifecycleObserver] and [AppLifeCycleUseCase] interfaces.
 * It observes the application's lifecycle events and updates the `isAppVisible` flag accordingly.
 *
 * You can use this class to determine whether the app is currently in the foreground or background.
 */
class AppLifeCycleInteractor @Inject constructor(
    private val appLifeCycle: Lifecycle,
    private val logger: LogUseCase,
) : DefaultLifecycleObserver, AppLifeCycleUseCase {

    companion object {
        private const val TAG = "LifeCycle"
        private var isAppVisible: Boolean = false
    }

    init {
        appLifeCycle.addObserver(this)
    }

    /**
     * Returns true if the app is currently in the foreground, false otherwise.
     *
     * @return True if the app is in the foreground, false otherwise.
     */
    override fun isAppOnForeground(): Boolean = isAppVisible

    /**
     * Returns true if the app is currently in the background, false otherwise.
     *
     * @return True if the app is in the background, false otherwise.
     */
    override fun isAppOnBackground(): Boolean = !isAppVisible

    override fun onCreate(owner: LifecycleOwner) {
        logger.i(TAG, Lifecycle.Event.ON_CREATE.name)
        isAppVisible = owner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
    }

    override fun onStart(owner: LifecycleOwner) {
        logger.i(TAG, Lifecycle.Event.ON_START.name)
        isAppVisible = owner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
    }

    override fun onPause(owner: LifecycleOwner) {
        logger.i(TAG, Lifecycle.Event.ON_PAUSE.name)
        isAppVisible = false
    }

    override fun onResume(owner: LifecycleOwner) {
        logger.i(TAG, Lifecycle.Event.ON_RESUME.name)
        isAppVisible = owner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
    }

    override fun onStop(owner: LifecycleOwner) {
        logger.i(TAG, Lifecycle.Event.ON_STOP.name)
        isAppVisible = false
    }

    override fun onDestroy(owner: LifecycleOwner) {
        logger.i(TAG, Lifecycle.Event.ON_DESTROY.name)
        appLifeCycle.removeObserver(this)
        isAppVisible = false
    }
}