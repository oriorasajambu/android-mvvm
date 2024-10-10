package id.android.skeleton.base.annotation

/**
 * Annotation used to mark a function as a mock for testing purposes.
 * This annotation provides information about the mock response, such as the file containing the mock data,
 * the HTTP status code, the delay to simulate network latency, and HTTP headers.
 *
 * @property fileName The name of the file containing the mock response data.
 * @property httpCode The HTTP status code to return. Defaults to 200 (OK).
 * @property delay The delay in seconds to simulate network latency. e.g 2L (will delay for 2 seconds) Defaults to 0 (no delay).
 * @property headers An array of HTTP headers to include in the response. Each header should be in the format "Header-Name: Header-Value". Defaults to an empty array (no headers).
 *
 * @Retention AnnotationRetention.RUNTIME
 * @Target AnnotationTarget.FUNCTION
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Mock(
    val fileName: String,
    val httpCode: Int = 200,
    val delay: Long = 0L,
    vararg val headers: String = [],
)

/**
 * Annotation used to indicate that a Retrofit endpoint requires authorization.
 * When applied to a function, the `TokenInterceptor` will automatically inject
 * the `Authorization` header into the request.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Authorized