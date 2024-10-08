package id.android.skeleton.common.reactive

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * Provides different types of schedulers for RxJava operations.
 *
 * This class implements the [SchedulerProvider] interface and provides concrete implementations
 * of different schedulers, such as UI, computation, IO, trampoline, and new thread.
 *
 * It is typically used in dependency injection to provide a consistent way of accessing
 * schedulers throughout the application.
 */
class AppSchedulerProvider @Inject constructor() : SchedulerProvider {
    /**
     * Returns the scheduler associated with the main thread (UI thread).
     *
     * @return The UI scheduler.
     */
    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    /**
     * Returns the scheduler intended for computational work.
     *
     * @return The computation scheduler.
     */
    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    /**
     * Returns the scheduler intended for I/O-bound work.
     *
     * @return The IO scheduler.
     */
    override fun io(): Scheduler {
        return Schedulers.io()
    }

    /**
     * Returns the scheduler that queues work on the current thread to be executed after the current work completes.
     *
     * @return The trampoline scheduler.
     */
    override fun trampoline(): Scheduler {
        return Schedulers.trampoline()
    }

    /**
     * Returns the scheduler that creates a new thread for each unit of work.
     *
     * @return The new thread scheduler.
     */
    override fun newThread(): Scheduler {
        return Schedulers.newThread()
    }
}