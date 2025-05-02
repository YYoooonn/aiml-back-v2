package org.aiml.api.security.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
  val httpStatus: HttpStatus,
  val message: String,
  val code: Int = 500, // TODO specify error code
) {
  TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "Token Invalid"),
  TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "Token Expired"),
  AUTH_UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown Authentication Error")
}

open class AuthException(val errorCode: ErrorCode, override val message: String = errorCode.message) :
  RuntimeException(message)

class InvalidTokenException(message: String?) :
  AuthException(ErrorCode.TOKEN_INVALID, message ?: ErrorCode.TOKEN_INVALID.message)

class TokenExpiredException(message: String?) :
  AuthException(ErrorCode.TOKEN_EXPIRED, message ?: ErrorCode.TOKEN_EXPIRED.message)
