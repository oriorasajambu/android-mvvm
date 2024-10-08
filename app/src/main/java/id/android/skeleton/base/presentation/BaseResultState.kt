package id.android.skeleton.base.presentation

/**
 * Represents the state of a result operation.
 *
 * This sealed class defines three possible states:
 * - **Loading:** Indicates that the operation is in progress.
 * - **Error:** Indicates that the operation failed with an error.
 * - **Success:** Indicates that the operation completed successfully with a result.
 *
 * @param T The type of the result data.
 */
sealed class BaseResultState<out T : Any> {
    /**
     * Represents the loading state.
     */
    data object Loading : BaseResultState<Nothing>()

    /**
     * Represents the error state.
     *
     * @property error The [Throwable] representing the error that occurred.
     */
    data class Error(val error: Throwable) : BaseResultState<Nothing>()

    /**
     * Represents the success state.
     *
     * @property data The result data of type [T].
     */
    data class Success<out T : Any>(val data: T) : BaseResultState<T>()
}