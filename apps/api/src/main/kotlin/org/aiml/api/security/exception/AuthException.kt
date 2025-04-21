package org.aiml.api.security.exception

import org.springframework.http.HttpStatus

open class AuthException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)


enum class ErrorCode(
  val httpStatus: HttpStatus,
  val code: String,
  val message: String
) {
  TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "TOKEN_INVALID", "토큰이 유효하지 않습니다"),
  TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "TOKEN_EXPIRED", "토큰이 만료되었습니다."),
  AUTH_UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "AUTH_UNKNOWN_ERROR", "알 수 없는 인증 에러")
}

class InvalidTokenException(message: String?) : AuthException(ErrorCode.TOKEN_INVALID)
class ExpiredJwtException(message: String?) : AuthException(ErrorCode.TOKEN_EXPIRED)
