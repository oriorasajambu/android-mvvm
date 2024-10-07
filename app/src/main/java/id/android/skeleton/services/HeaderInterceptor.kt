package id.android.skeleton.services

import id.android.skeleton.common.BuildConfigUtils
import id.android.skeleton.common.Constants
import id.android.skeleton.common.Utils
import id.android.skeleton.common.Utils.orDefault
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor private constructor(
    private val applicationId: String,
    private val appVersionCode: String,
    private val appVersionName: String,
    private val flavor: String,
    private val buildType: String,
    private val deviceInfo: String,
    private val phoneType: String,
    private val phoneBrand: String,
    private val phoneOs: String,
): Interceptor {

    class Builder {
        private val applicationId: String? = null
        private val appVersionCode: String? = null
        private val appVersionName: String? = null
        private val flavor: String? = null
        private val buildType: String? = null
        private var deviceInfo: String? = null
        private var phoneType: String? = null
        private var phoneBrand: String? = null
        private var phoneOs: String? = null

        fun build(): HeaderInterceptor = HeaderInterceptor(
            applicationId.orDefault(BuildConfigUtils.getAppId()),
            appVersionCode.orDefault(BuildConfigUtils.getAppVersionCode()),
            appVersionName.orDefault(BuildConfigUtils.getAppVersionName()),
            flavor.orDefault(BuildConfigUtils.getFlavor()),
            buildType.orDefault(BuildConfigUtils.getBuildType()),
            deviceInfo.orDefault(Constants.DEVICE_INFO),
            phoneType.orDefault(Utils.getPhoneType()),
            phoneBrand.orDefault(Utils.getPhoneBrand()),
            phoneOs.orDefault(Utils.getOSversion()),
        )
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(Constants.Header.APP_ID, applicationId)
            .addHeader(Constants.Header.APP_VERSION_CODE, appVersionCode)
            .addHeader(Constants.Header.APP_VERSION_NAME, appVersionName)
            .addHeader(Constants.Header.FLAVOR, flavor)
            .addHeader(Constants.Header.BUILD_TYPE, buildType)
            .addHeader(Constants.Header.DEVICE_INFO, deviceInfo)
            .addHeader(Constants.Header.PHONE_TYPE, phoneType)
            .addHeader(Constants.Header.PHONE_BRAND, phoneBrand)
            .addHeader(Constants.Header.OS, phoneOs)
            .build()
        return chain.proceed(request)
    }
}