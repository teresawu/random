package co.uk.room.error

import javax.inject.Inject

/**
 * Encapsulates all HTTP exceptions with a HTTP response code and the message.
 */
class RoomHttpException @Inject constructor(responseCode: Int, errorMessage: String) : Throwable("$responseCode - $errorMessage")