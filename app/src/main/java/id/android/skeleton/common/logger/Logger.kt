package id.android.skeleton.common.logger

import android.util.Log
import id.android.skeleton.common.BuildConfigUtils

class Logger: LogUseCase {
    private val isDebug = BuildConfigUtils.isDebug()
    override fun w(tag: String, message: String) {
        if (isDebug) Log.w(tag, message)
    }

    override fun e(tag: String, message: String) {
        if (isDebug) Log.e(tag, message)
    }

    override fun d(tag: String, message: String) {
        if (isDebug) Log.d(tag, message)
    }

    override fun i(tag: String, message: String) {
        if (isDebug) Log.i(tag, message)
    }
}