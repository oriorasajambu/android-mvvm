package id.android.skeleton

import android.app.Activity
import android.app.Application
import android.os.Bundle
import dagger.hilt.android.HiltAndroidApp

/**
 * The main application class.
 *
 * This class extends the `Application` class and implements the
 * `Application.ActivityLifecycleCallbacks` interface to monitor the lifecycle
 * of activities in the application. It uses Hilt for dependency injection.
 *
 * It provides access to the current activity lifecycle state through the
 * `activityLifecycle` property.
 */
@HiltAndroidApp
class App : Application(), Application.ActivityLifecycleCallbacks {

    /**
     * Represents the different states of an activity's lifecycle.
     */
    enum class LifeCycle {
        CREATED, STARTED, RESUMED, PAUSED, STOPPED, SAVED, DESTROYED
    }

    private var _activityLifeCycle: LifeCycle = LifeCycle.CREATED
    val activityLifecycle get() = _activityLifeCycle

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        _activityLifeCycle = LifeCycle.CREATED
    }

    override fun onActivityStarted(activity: Activity) {
        _activityLifeCycle = LifeCycle.STARTED
    }

    override fun onActivityResumed(activity: Activity) {
        _activityLifeCycle = LifeCycle.RESUMED
    }

    override fun onActivityPaused(activity: Activity) {
        _activityLifeCycle = LifeCycle.PAUSED
    }

    override fun onActivityStopped(activity: Activity) {
        _activityLifeCycle = LifeCycle.STOPPED
    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {
        _activityLifeCycle = LifeCycle.SAVED
    }

    override fun onActivityDestroyed(activity: Activity) {
        _activityLifeCycle = LifeCycle.DESTROYED
    }
}