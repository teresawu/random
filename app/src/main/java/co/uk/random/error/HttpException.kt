package co.uk.random.error

/**
 * Encapsulates all HTTP exceptions with a HTTP response code and the message.
 */
class HttpException(val responseCode: Int, val errorMessage: String) : Throwable("$responseCode - $errorMessage")