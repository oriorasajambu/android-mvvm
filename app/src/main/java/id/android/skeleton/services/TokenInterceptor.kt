package id.android.skeleton.services

import id.android.skeleton.base.annotation.Authorized
import id.android.skeleton.common.Constants
import id.android.skeleton.services.session.SessionKeys
import id.android.skeleton.services.session.SessionUseCase
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Invocation
import javax.inject.Inject

/**
 * A network interceptor responsible for adding access token to requests targeting a specific domain.
 * It intercepts outgoing requests and adds the access token to the request headers if certain conditions are met.
 *
 * @property sessions The session manager responsible for retrieving the access token.
 */
class TokenInterceptor @Inject constructor(
    private val sessions: SessionUseCase
) : Interceptor {

    companion object {
        private val EXCLUDED_HEADERS = setOf(Constants.Header.AUTHORIZATION)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = sessions.getString(SessionKeys.ACCESS_TOKEN)
        val request = chain.request()
        val newRequest = if (shouldIntercept(request, accessToken)) {
            request.newBuilder()
                .header(Constants.Header.AUTHORIZATION, accessToken)
                .build()
        } else {
            request
        }
        return chain.proceed(newRequest)
    }

    /**
     * Checks if the request should be intercepted and modified.
     * The request is intercepted if it has Authorized annotation, targets the domain
     * does not have excluded header and access token is not blank
     *
     * @param request The request to be checked.
     * @param accessToken The access token to be added to the request headers.
     * @return True if the request should be intercepted, false otherwise.
     */
    private fun shouldIntercept(request: Request, accessToken: String): Boolean {
        if (!request.markedForInjection()) return false
        if (EXCLUDED_HEADERS.any { it in request.headers.names() }) return false
        if (accessToken.isBlank() || accessToken.isEmpty()) return false
        return true
    }

    /**
     * Check if request is annotated with `@Authorized` annotation,
     * If it is, it mean it's marked for `Authorization` injection
     */
    private fun Request.markedForInjection(): Boolean = tag<Invocation>()?.method()
        ?.annotations?.toSet()?.find { it is Authorized } != null
}