package id.android.skeleton.common

/**
 * Holds constant values used throughout the application.
 *
 * This object provides a centralized location for storing various constants,
 * such as status codes, device information, header keys, network timeouts,
 * and API versions etc.
 */
object Constants {
    const val SUCCESS = "SUCCESS"
    const val FAILED = "FAILED"
    const val DEVICE_INFO = "Android"

    /**
     * Contains constants related to HTTP headers.
     */
    object Header {
        const val APP_ID = "ApplicationId"
        const val APP_VERSION_CODE = "ApplicationVersion"
        const val APP_VERSION_NAME = "ApplicationVersionName"
        const val FLAVOR = "Flavor"
        const val BUILD_TYPE = "BuildType"
        const val DEVICE_INFO = "DeviceInfo"
        const val PHONE_TYPE = "PhoneType"
        const val PHONE_BRAND = "PhoneBrand"
        const val OS = "OS"

        const val AUTHORIZATION = "Authorization"
        const val VERSION = "Content-Type"
        const val X_SECRET = "X-SECRET"
        const val DEVICE_ID = "DEVICE_ID"
    }

    /**
     * Contains constants related to network timeouts.
     */
    object Network {
        const val CONNECT_TIMEOUT = 80_000L / 1000
        const val READ_TIMEOUT = 80_000L / 1000
        const val WRITE_TIMEOUT = 15_000L / 1000
    }

    /**
     * Contains constants representing API versions.
     */
    object Versions {
        const val V1 = "application/vnd.api.v1+json"
        const val V2 = "application/vnd.api.v2+json"
        const val V3 = "application/vnd.api.v3+json"
        const val V4 = "application/vnd.api.v4+json"
    }
}