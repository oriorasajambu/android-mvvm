package id.android.skeleton.base.presentation

sealed class BaseResultState<out T : Any> {
    data object Loading : BaseResultState<Nothing>()
    data class Error(val error: Throwable) : BaseResultState<Nothing>()
    data class Success<out T : Any>(val data: T) : BaseResultState<T>()
}