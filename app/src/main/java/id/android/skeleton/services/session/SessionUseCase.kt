package id.android.skeleton.services.session

import android.content.SharedPreferences

interface SessionUseCase {
    fun batch(): SharedPreferences

    fun putString(key: SessionKeys, value: String): Boolean

    fun putInt(key: SessionKeys, value: Int): Boolean

    fun putLong(key: SessionKeys, value: Long): Boolean

    fun putBoolean(key: SessionKeys, value: Boolean): Boolean

    fun getString(key: SessionKeys, defaultValue: String = ""): String

    fun getInt(key: SessionKeys, defaultValue: Int = 0): Int

    fun getLong(key: SessionKeys, defaultValue: Long = 0L): Long

    fun getBoolean(key: SessionKeys, defaultValue: Boolean = false): Boolean

    fun remove(key: SessionKeys): Boolean

    fun logOut(): Boolean
}