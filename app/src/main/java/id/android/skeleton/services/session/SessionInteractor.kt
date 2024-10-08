package id.android.skeleton.services.session

import android.content.SharedPreferences
import id.android.skeleton.common.Utils.orDefault
import javax.inject.Inject

/**
 * Interacts with shared preferences to manage session data.
 *
 * This class implements the [SessionUseCase] interface and provides concrete
 * implementations for storing and retrieving session-related information
 * using shared preferences.
 */
class SessionInteractor @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : SessionUseCase {

    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    /**
     * Returns the SharedPreferences instance for batch operations.
     *
     * @return The SharedPreferences instance.
     */
    override fun batch(): SharedPreferences {
        return sharedPreferences
    }

    /**
     * Stores a string value in shared preferences.
     *
     * @param key The key to identify the value.
     * @param value The string value to store.
     * @return True if the value was successfully stored, false otherwise.
     */
    override fun putString(key: SessionKeys, value: String): Boolean {
        return editor.putString(key.name, value).commit()
    }

    /**
     * Stores an integer value in shared preferences.
     *
     * @param key The key to identify the value.
     * @param value The integer value to store.
     * @return True if the value was successfully stored, false otherwise.
     */
    override fun putInt(key: SessionKeys, value: Int): Boolean {
        return editor.putInt(key.name, value).commit()
    }

    /**
     * Stores a long value in shared preferences.
     *
     * @param key The key to identify the value.
     * @param value The long value to store.
     * @return True if the value was successfully stored, false otherwise.
     */
    override fun putLong(key: SessionKeys, value: Long): Boolean {
        return editor.putLong(key.name, value).commit()
    }

    /**
     * Stores a boolean value in shared preferences.
     *
     * @param key The key to identify the value.
     * @param value The boolean value to store.
     * @return True if the value was successfully stored, false otherwise.
     */
    override fun putBoolean(key: SessionKeys, value: Boolean): Boolean {
        return editor.putBoolean(key.name, value).commit()
    }

    /**
     * Retrieves a string value from shared preferences.
     *
     * @param key The key to identify the value.
     * @param defaultValue The default value to return if the key is not found.
     * @return The stored string value or the default value if not found.
     */
    override fun getString(key: SessionKeys, defaultValue: String): String {
        return sharedPreferences.getString(key.name, defaultValue).orDefault(defaultValue)
    }

    /**
     * Retrieves an integer value from shared preferences.
     *
     * @param key The key to identify the value.
     * @param defaultValue The default value to return if the key is not found.
     * @return The stored integer value or the default value if not found.
     */
    override fun getInt(key: SessionKeys, defaultValue: Int): Int {
        return sharedPreferences.getInt(key.name, defaultValue).orDefault(defaultValue)
    }

    /**
     * Retrieves a long value from shared preferences.
     *
     * @param key The key to identify the value.
     * @param defaultValue The default value to return if the key is not found.
     * @return The stored long value or the default value if not found.
     */
    override fun getLong(key: SessionKeys, defaultValue: Long): Long {
        return sharedPreferences.getLong(key.name, defaultValue).orDefault(defaultValue)
    }

    /**
     * Retrieves a boolean value from shared preferences.
     *
     * @param key The key to identify the value.
     * @param defaultValue The default value to return if the key is not found.
     * @return The stored boolean value or the default value if not found.
     */
    override fun getBoolean(key: SessionKeys, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key.name, defaultValue).orDefault(defaultValue)
    }

    /**
     * Removes a value from shared preferences.
     *
     * @param key The key to identify the value to remove.
     * @return True if the value was successfully removed, false otherwise.
     */
    override fun remove(key: SessionKeys): Boolean {
        return editor.remove(key.name).commit()
    }

    /**
     * Logs out the user by clearing session data.
     *
     * @return True if the logout was successful, false otherwise.
     */
    override fun logOut(): Boolean {
        editor.clear()
        return editor.commit()
    }
}