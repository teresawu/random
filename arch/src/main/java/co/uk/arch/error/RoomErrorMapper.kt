package co.uk.arch.error

import co.uk.arch.util.extension.parseJSONToMap
import com.google.gson.JsonSyntaxException
import java.net.HttpURLConnection
import javax.inject.Inject

/**
 * Maps all Retrofit errors to Exceptions we define in this module. Takes into account the
 * HTTP Response code and the message.
 */
class RoomErrorMapper @Inject constructor() {

    companion object {
        private const val ERROR_KEY = "error"
        private const val MESSAGE = "message"

        fun transform(responseCode: Int, rawErrorBodyString: String): RoomHttpException =
                when (responseCode) {
                    HttpURLConnection.HTTP_CONFLICT -> {
                        buildExceptionFromSkylarkApi(rawErrorBodyString, HttpURLConnection.HTTP_CONFLICT)
                    }
                    HttpURLConnection.HTTP_BAD_REQUEST -> {
                        buildExceptionFromSkylarkApi(rawErrorBodyString, HttpURLConnection.HTTP_BAD_REQUEST)
                    }
                    HttpURLConnection.HTTP_UNAUTHORIZED -> {
                        buildExceptionFromSkylarkApi(rawErrorBodyString, HttpURLConnection.HTTP_UNAUTHORIZED)
                    }
                    else -> {
                        RoomHttpException(responseCode, "")
                    }
                }

        private fun buildExceptionFromSkylarkApi(rawErrorBodyString: String, httpStatusCode: Int): RoomHttpException {
            if (!rawErrorBodyString.isEmpty()) {
                val map = rawErrorBodyString.parseJSONToMap()
                val errorMessage: Any? = map[ERROR_KEY]
                return when {
                    errorMessage != null -> {
                        try {
                            val skylarkErrorMessageMap = errorMessage.toString().parseJSONToMap()
                            val realMessageFromApi = skylarkErrorMessageMap[MESSAGE]
                            if (realMessageFromApi != null) {
                                return RoomHttpException(httpStatusCode, realMessageFromApi.toString())
                            }
                        } catch (e: JsonSyntaxException) {
                            return RoomHttpException(httpStatusCode, errorMessage.toString())
                        }
                        RoomHttpException(httpStatusCode, errorMessage.toString())
                    }
                    else -> RoomHttpException(httpStatusCode, "")
                }
            } else {
                return RoomHttpException(httpStatusCode, "")
            }
        }
    }
}