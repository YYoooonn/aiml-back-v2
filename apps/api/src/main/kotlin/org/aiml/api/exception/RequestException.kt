package org.aiml.api.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
  val httpStatus: HttpStatus,
  val message: String,
  val code: Int = 500 // TODO Specify Error code
) {
  REQUEST_INVALID(HttpStatus.BAD_REQUEST, "Invalid request"),
  REQUEST_TIMEOUT(HttpStatus.BAD_REQUEST, "Request timeout"),
}

open class RequestException(val errorCode: ErrorCode, override val message: String = errorCode.message) :
  RuntimeException(message)

class RequestInvalidException(message: String? = null) :
  RequestException(ErrorCode.REQUEST_INVALID, message ?: ErrorCode.REQUEST_INVALID.message)
