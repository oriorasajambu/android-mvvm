package id.android.skeleton

import android.app.Activity
import android.app.Application
import android.os.Bundle
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(), Application.ActivityLifecycleCallbacks {

    private var activityLifeCycle: LifeCycle = LifeCycle.CREATED

    enum class LifeCycle {
        CREATED, STARTED, RESUMED, PAUSED, STOPPED, SAVED, DESTROYED
    }

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        activityLifeCycle = LifeCycle.CREATED
    }

    override fun onActivityStarted(activity: Activity) {
        activityLifeCycle = LifeCycle.STARTED
    }

    override fun onActivityResumed(activity: Activity) {
        activityLifeCycle = LifeCycle.RESUMED
    }

    override fun onActivityPaused(activity: Activity) {
        activityLifeCycle = LifeCycle.PAUSED
    }

    override fun onActivityStopped(activity: Activity) {
        activityLifeCycle = LifeCycle.STOPPED
    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {
        activityLifeCycle = LifeCycle.SAVED
    }

    override fun onActivityDestroyed(activity: Activity) {
        activityLifeCycle = LifeCycle.DESTROYED
    }
}