package id.android.skeleton.common.logger

interface LogUseCase {
    fun w(tag: String, message: String)
    fun e(tag: String, message: String)
    fun d(tag: String, message: String)
    fun i(tag: String, message: String)
}