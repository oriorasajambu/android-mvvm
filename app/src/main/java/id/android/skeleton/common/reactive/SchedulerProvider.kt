package id.android.skeleton.common.reactive

import io.reactivex.rxjava3.core.Scheduler

/**
 * Provides different types of schedulers for RxJava operations.
 *
 * This interface defines methods for obtaining various schedulers, such as
 * UI, computation, IO, trampoline, and new thread. Implementations of this
 * interface can be used to abstract away the specific scheduler implementations
 * and provide a consistent way of accessing schedulers throughout the application.
 *
 * This is particularly useful for testing, as it allows you to easily swap
 * out the real schedulers with test schedulers for synchronous execution.
 */
interface SchedulerProvider {

    /**
     * Returns the scheduler associated with the main thread (UI thread).
     *
     * @return The UI scheduler.
     */
    fun ui(): Scheduler

    /**
     * Returns the scheduler intended for computational work.
     *
     * @return The computation scheduler.
     */
    fun computation(): Scheduler

    /**
     * Returns the scheduler intended for I/O-bound work.
     *
     * @return The IO scheduler.
     */
    fun io(): Scheduler

    /**
     * Returns the scheduler that queues work on the current thread to be executed after the current work completes.
     *
     * @return The trampoline scheduler.
     */
    fun trampoline(): Scheduler

    /**
     * Returns the scheduler that creates a new thread for each unit of work.
     *
     * @return The new thread scheduler.
     */
    fun newThread(): Scheduler
}