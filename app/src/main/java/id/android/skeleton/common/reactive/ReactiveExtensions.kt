package id.android.skeleton.common.reactive

import android.util.Log
import id.android.skeleton.base.presentation.BaseResultState
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

object ReactiveExtensions {
    fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(this)
    }

    /**
     * Extension function for `Observable<T>` that subscribes to the observable and provides a callback with
     * a `BaseResultState<T>` representing the current state of the operation (loading, success, error).
     *
     * @param T The type of the emitted items.
     * @param callback A lambda function that receives updates in the form of `BaseResultState<T>`.
     * @param mapper A function that maps the emitted items of type `T` to a `BaseResultState<T>`. Defaults to mapping each item to `BaseResultState.Success(it)`.
     *
     * @return A `Disposable` that can be used to dispose the subscription when it's no longer needed.
     *
     * This function does the following:
     * - Subscribes on the `subscribeSchedulers` (defaults to I/O thread).
     * - Observes on the `observeSchedulers` (defaults to Android main thread).
     * - Maps each emitted item to a `BaseResultState<T>` using the provided `mapper` function.
     * - Emits a `BaseResultState.Loading` before any items are emitted.
     * - Handles errors by emitting a `BaseResultState.Error` with the encountered throwable.
     * - Calls the `callback` with the resulting `BaseResultState<T>` (either Loading, Success, or Error).
     *
     * Example usage:
     * ```
     * observable.subscribeAsState(
     *     callback = { state ->
     *         when (state) {
     *             is BaseResultState.Loading -> showLoading()
     *             is BaseResultState.Success -> showData(state.data)
     *             is BaseResultState.Error -> showError(state.error)
     *         }
     *     }
     * )
     * ```
     */
    fun <T: Any> Observable<T>.subscribeAsState(
        schedulerProvider: SchedulerProvider,
        callback: (data: BaseResultState<T>) -> Unit,
        onComplete: () -> Unit = {},
        mapper: (T) -> BaseResultState<T> = { BaseResultState.Success(it) },
    ): Disposable {
        return subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .map(mapper)
            .startWithItem(BaseResultState.Loading)
            .onErrorReturn { BaseResultState.Error(it) }
            .doOnComplete(onComplete)
            .subscribe(callback)
    }


    /**
     * Subscribes safely to a Single, handling exceptions that might occur during the success callback.
     *
     * @param onSuccess Function to be called when the Single emits a success value.
     * @param onError Function to be called when the Single emits an error.
     * @return Disposable object representing the subscription.
     */
    fun <T : Any> Single<T>.subscribeSafely(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return subscribe({
            runCatching {
                onSuccess(it)
            }.onFailure { error ->
                Log.e("Exception", error.toString())
            }
        }, onError)
    }
}