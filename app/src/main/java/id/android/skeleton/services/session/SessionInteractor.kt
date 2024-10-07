package id.android.skeleton.services.session

import android.content.SharedPreferences
import id.android.skeleton.common.Utils.orDefault
import javax.inject.Inject

class SessionInteractor @Inject constructor(
    private val sharedPreferences: SharedPreferences,
): SessionUseCase {

    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    override fun batch(): SharedPreferences {
        return sharedPreferences
    }

    override fun putString(key: SessionKeys, value: String): Boolean {
        return editor.putString(key.name, value).commit()
    }

    override fun putInt(key: SessionKeys, value: Int): Boolean {
        return editor.putInt(key.name, value).commit()
    }

    override fun putLong(key: SessionKeys, value: Long): Boolean {
        return editor.putLong(key.name, value).commit()
    }

    override fun putBoolean(key: SessionKeys, value: Boolean): Boolean {
        return editor.putBoolean(key.name, value).commit()
    }

    override fun getString(key: SessionKeys, defaultValue: String): String {
        return sharedPreferences.getString(key.name, defaultValue).orDefault(defaultValue)
    }

    override fun getInt(key: SessionKeys, defaultValue: Int): Int {
        return sharedPreferences.getInt(key.name, defaultValue).orDefault(defaultValue)
    }

    override fun getLong(key: SessionKeys, defaultValue: Long): Long {
        return sharedPreferences.getLong(key.name, defaultValue).orDefault(defaultValue)
    }

    override fun getBoolean(key: SessionKeys, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key.name, defaultValue).orDefault(defaultValue)
    }

    override fun remove(key: SessionKeys): Boolean {
        return editor.remove(key.name).commit()
    }

    override fun logOut(): Boolean {
        editor.clear()
        return editor.commit()
    }
}