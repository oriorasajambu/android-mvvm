package id.android.skeleton.common.reactive

import io.reactivex.rxjava3.core.Scheduler

interface SchedulerProvider {
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun io(): Scheduler
    fun trampoline(): Scheduler
    fun newThread(): Scheduler
}