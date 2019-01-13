package co.uk.youtube.error

import javax.inject.Inject

/**
 * Encapsulates all HTTP exceptions with a HTTP response code and the message.
 */
class HttpException @Inject constructor(responseCode: Int, errorMessage: String) : Throwable("$responseCode - $errorMessage")