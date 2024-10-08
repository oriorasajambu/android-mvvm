package id.android.skeleton.common.reactive

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Provides trampoline schedulers for testing purposes.
 *
 * This class implements the [SchedulerProvider] interface and provides
 * [Schedulers.trampoline] for all scheduler types. This is useful for testing
 * RxJava operations in a synchronous and predictable manner.
 *
 * By using trampoline schedulers, you can avoid the need for asynchronous
 * execution during tests, making it easier to write and debug test cases.
 */
class TestSchedulerProvider : SchedulerProvider {

    /**
     * Returns a trampoline scheduler for the UI thread.
     *
     * @return A trampoline scheduler.
     */
    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }

    /**
     * Returns a trampoline scheduler for computation operations.
     *
     * @return A trampoline scheduler.
     */
    override fun computation(): Scheduler {
        return Schedulers.trampoline()
    }

    /**
     * Returns a trampoline scheduler for I/O operations.
     *
     * @return A trampoline scheduler.
     */
    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    /**
     * Returns a trampoline scheduler.
     *
     * @return A trampoline scheduler.
     */
    override fun trampoline(): Scheduler {
        return Schedulers.trampoline()
    }

    /**
     * Returns a trampoline scheduler for new thread operations.
     *
     * @return A trampoline scheduler.
     */
    override fun newThread(): Scheduler {
        return Schedulers.trampoline()
    }
}