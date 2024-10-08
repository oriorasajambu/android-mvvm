package id.android.skeleton.services.session

import android.content.SharedPreferences

/**
 * Defines use cases for managing session data.
 *
 * This interface provides methods for interacting with shared preferences to store and retrieve session-related information.
 */
interface SessionUseCase {

    /**
     * Returns the SharedPreferences instance for batch operations.
     *
     * @return The SharedPreferences instance.
     */
    fun batch(): SharedPreferences

    /**
     * Stores a string value in shared preferences.
     *
     * @param key The key to identify the value.
     * @param value The string value to store.
     * @return True if the value was successfully stored, false otherwise.
     */
    fun putString(key: SessionKeys, value: String): Boolean

    /**
     * Stores an integer value in shared preferences.
     *
     * @param key The key to identify the value.
     * @param value The integer value to store.
     * @return True if the value was successfully stored, false otherwise.
     */
    fun putInt(key: SessionKeys, value: Int): Boolean

    /**
     * Stores a long value in shared preferences.
     *
     * @param key The key to identify the value.
     * @param value The long value to store.
     * @return True if the value was successfully stored, false otherwise.
     */
    fun putLong(key: SessionKeys, value: Long): Boolean

    /**
     * Stores a boolean value in shared preferences.
     *
     * @param key The key to identify the value.
     * @param value The boolean value to store.
     * @return True if the value was successfully stored, false otherwise.
     */
    fun putBoolean(key: SessionKeys, value: Boolean): Boolean

    /**
     * Retrieves a string value from shared preferences.
     *
     * @param key The key to identify the value.
     * @param defaultValue The default value to return if the key is not found.
     * @return The stored string value or the default value if not found.
     */
    fun getString(key: SessionKeys, defaultValue: String = ""): String

    /**
     * Retrieves an integer value from shared preferences.
     *
     * @param key The key to identify the value.
     * @param defaultValue The default value to return if the key is not found.
     * @return The stored integer value or the default value if not found.
     */
    fun getInt(key: SessionKeys, defaultValue: Int = 0): Int

    /**
     * Retrieves a long value from shared preferences.
     *
     * @param key The key to identify the value.
     * @param defaultValue The default value to return if the key is not found.
     * @return The stored long value or the default value if not found.
     */
    fun getLong(key: SessionKeys, defaultValue: Long = 0L): Long

    /**
     * Retrieves a boolean value from shared preferences.
     *
     * @param key The key to identify the value.
     * @param defaultValue The default value to return if the key is not found.
     * @return The stored boolean value or the default value if not found.
     */
    fun getBoolean(key: SessionKeys, defaultValue: Boolean = false): Boolean

    /**
     * Removes a value from shared preferences.
     *
     * @param key The key to identify the value to remove.
     * @return True if the value was successfully removed, false otherwise.
     */
    fun remove(key: SessionKeys): Boolean

    /**
     * Logs out the user by clearing session data.
     *
     * @return True if the logout was successful, false otherwise.
     */
    fun logOut(): Boolean
}