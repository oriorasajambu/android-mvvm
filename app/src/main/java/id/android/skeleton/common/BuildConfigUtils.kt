package id.android.skeleton.common

import id.android.skeleton.BuildConfig

object BuildConfigUtils {
    fun getAppId() = BuildConfig.APPLICATION_ID

    fun getAppVersionCode() = BuildConfig.VERSION_CODE.toString()

    fun getAppVersionName() = BuildConfig.VERSION_NAME

    fun getBuildType() = BuildConfig.BUILD_TYPE

    fun getFlavor() = BuildConfig.FLAVOR

    fun getBaseUrl() = BuildConfig.BASE_URL

    fun getChannelId() = BuildConfig.CHANNEL_ID

    fun getHostName() = BuildConfig.HOSTNAME

    fun getMapApiKey() = BuildConfig.MAPS_API_KEY
}