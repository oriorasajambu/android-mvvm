package id.android.skeleton.common

import id.android.skeleton.BuildConfig

/**
 * Provides utility methods for accessing BuildConfig values.
 *
 * This object encapsulates access to various BuildConfig properties,
 * such as application ID, version code, version name, build type, flavor,
 * base URL, channel ID, hostname, and Maps API key.
 *
 * It provides a convenient way to retrieve these values without directly
 * referencing the BuildConfig class, improving code readability and maintainability.
 */
object BuildConfigUtils {
    /**
     * Returns the application ID.
     *
     * @return The application ID.
     */
    fun getAppId() = BuildConfig.APPLICATION_ID

    /**
     * Returns the application version code as a string.
     *
     * @return The application version code.
     */
    fun getAppVersionCode() = BuildConfig.VERSION_CODE.toString()

    /**
     * Returns the application version name.
     *
     * @return The application version name.
     */
    fun getAppVersionName() = BuildConfig.VERSION_NAME

    /**
     * Returns the build type.
     *
     * @return The build type.
     */
    fun getBuildType() = BuildConfig.BUILD_TYPE

    /**
     * Returns the product flavor.
     *
     * @return The product flavor.
     */
    fun getFlavor() = BuildConfig.FLAVOR

    /**
     * Returns the base URL.
     *
     * @return The base URL.
     */
    fun getBaseUrl() = BuildConfig.BASE_URL

    /**
     * Returns the channel ID.
     *
     * @return The channel ID.
     */
    fun getChannelId() = BuildConfig.CHANNEL_ID

    /**
     * Returns the hostname.
     *
     * @return The hostname.
     */
    fun getHostName() = BuildConfig.HOSTNAME

    /**
     * Returns the Maps API key.
     *
     * @return The Maps API key.
     */
    fun getMapApiKey() = BuildConfig.MAPS_API_KEY
}