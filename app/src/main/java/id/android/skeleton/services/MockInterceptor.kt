package id.android.skeleton.services

import android.content.Context
import android.util.Log
import id.android.skeleton.base.annotation.Mock
import id.android.skeleton.base.data.BaseDataObject
import id.android.skeleton.common.Constants
import id.android.skeleton.common.Utils.removeWhiteSpaces
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Invocation
import java.io.BufferedReader

class MockInterceptor (
    private val context: Context,
): Interceptor {

    companion object {
        private const val CONTENT_TYPE_RESPONSE_BODY = "application/json; charset=utf-8"

        private const val TITLE_SUCCESS_MOCK = "Success Mocked"
        private const val TITLE_FAILED_MOCK = "Failed Mocked"
        private const val TITLE_FAILED_READ_FILE = "Failed Read File"
        private const val TITLE_FAILED_NOT_JSON = "Failed Not Json"
        private const val TITLE_FAILED_TIME_OUT = "Failed Request Time Out"
        private const val RESPONSE_FAILED_504 = "Failed Request 504 Gateway Timeout"

        private const val MOCK_TRACE_ID = "12345678910"
        private const val MOCK_SOURCE_SYSTEM = "ANDROID_DEVICE"
        private const val MOCK_RESPONSE_KEY_FAILED = "FAILED"
        private const val MOCK_RESPONSE_KEY_TIME_OUT = "TIME_OUT"
    }

    private val defaultFailedResponse = BaseDataObject(
        responseKey = MOCK_RESPONSE_KEY_FAILED,
        traceId = MOCK_TRACE_ID,
        sourceSystem = MOCK_SOURCE_SYSTEM,
        message = BaseDataObject.Message(
            titleEng = TITLE_FAILED_MOCK,
            titleInd = TITLE_FAILED_MOCK,
            descEng = TITLE_FAILED_READ_FILE,
            descInd = TITLE_FAILED_READ_FILE
        ),
        result = null
    )

    private val defaultTimeOutResponse = BaseDataObject(
        responseKey = MOCK_RESPONSE_KEY_TIME_OUT,
        traceId = MOCK_TRACE_ID,
        sourceSystem = MOCK_SOURCE_SYSTEM,
        message = BaseDataObject.Message(
            titleEng = TITLE_FAILED_MOCK,
            titleInd = TITLE_FAILED_MOCK,
            descEng = TITLE_FAILED_TIME_OUT,
            descInd = TITLE_FAILED_TIME_OUT
        ),
        result = null
    )

    private var fileName: String = ""
    private var httpCode: Int = 200
    private var delay: Long = 0L

    override fun intercept(chain: Interceptor.Chain): Response {
        val chainRequest = chain.request()
        val invocation = chain.request().tag(Invocation::class.java) ?: return chain.proceed(chainRequest)
        val annotation = invocation.method().annotations.toSet().firstOrNull { annotation ->
            annotation is Mock
        } as? Mock ?: return chain.proceed(chainRequest)

        val headers = Headers.Builder()
        fileName = annotation.fileName
        httpCode = annotation.httpCode
        delay = annotation.delay

        if (fileName.isEmpty()) {
            Log.w("MockInterceptor", "fileName cannot be empty, proceeding with the chain request")
            return chain.proceed(chainRequest)
        }

        if (httpCode !in 100..599) {
            return buildResponse(
                message = RESPONSE_FAILED_504,
                headers = headers.build(),
                statusCode = 504,
                chainRequest = chainRequest,
                content = { RESPONSE_FAILED_504.toResponseBody() }
            )
        }

        if (delay >= Constants.Network.CONNECT_TIMEOUT) {
            return buildResponse(
                message = TITLE_FAILED_TIME_OUT,
                headers = headers.build(),
                statusCode = 408,
                chainRequest = chainRequest,
                content = { Json.encodeToString(defaultTimeOutResponse).createResponseBody() }
            )
        }

        if (delay < 0L) delay = 0L

        val asset = fileName.readAssetFileWithMessageException(context)

        if (annotation.headers.isNotEmpty()) {
            annotation.headers.forEach { header ->
                val (key, value) = header
                    .removeWhiteSpaces()
                    .split(":", limit = 2)
                headers.add(key, value)
            }
        }

        return if (isJson(asset)) buildResponse(
            message = TITLE_SUCCESS_MOCK,
            headers = headers.build(),
            statusCode = httpCode,
            chainRequest = chainRequest,
            content = { asset.createResponseBody() }
        ) else buildResponse(
            message = TITLE_FAILED_NOT_JSON,
            headers = headers.build(),
            statusCode = 404,
            chainRequest = chainRequest,
            content = { Json.encodeToString(defaultFailedResponse).createResponseBody() }
        )
    }

    private fun isJson(input: String): Boolean = try {
        Json.parseToJsonElement(input)
        true
    } catch (e: Exception) {
        false
    }

    private fun buildResponse(
        message: String,
        headers: Headers,
        statusCode: Int,
        chainRequest: Request,
        content: () -> ResponseBody,
    ): Response {
        return Response.Builder().apply {
            sentRequestAtMillis(System.currentTimeMillis())
            receivedResponseAtMillis(System.currentTimeMillis() + (delay * 1000L))
            protocol(Protocol.HTTP_2)
            headers(headers)
            code(statusCode)
            message(message)
            body(content.invoke())
            request(chainRequest)
        }.build()
    }

    private fun String.createResponseBody(): ResponseBody {
        return this.toResponseBody(CONTENT_TYPE_RESPONSE_BODY.toMediaType())
    }

    private fun String.readAssetFileWithMessageException(context: Context): String {
        return context.assets
            .open(this)
            .bufferedReader()
            .use(BufferedReader::readText)
    }
}