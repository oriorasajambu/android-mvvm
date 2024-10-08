package id.android.skeleton.services.lifecycle

interface AppLifeCycleUseCase {
    fun isAppOnForeground(): Boolean
    fun isAppOnBackground(): Boolean
}