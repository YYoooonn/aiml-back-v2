package org.aiml.user.domain.exception

import org.springframework.http.HttpStatus

open class UserException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)

enum class ErrorCode(
  val httpStatus: HttpStatus,
  val code: String,
  val message: String
) {
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", "사용자를 찾을 수 없습니다."),
  USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "USER_NAME_EXISTS", "이미 사용 중인 사용자 이름입니다."),
  USER_EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "USER_EMAIL_EXISTS", "이미 사용 중인 이메일입니다."),
  USER_UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "USER_UNKNOWN_ERROR", "알 수 없는 사용자 에러")
}

class UserNotFoundException(message: String? = null) : UserException(ErrorCode.USER_NOT_FOUND)
class UserAlreadyExistsException(message: String? = null) : UserException(ErrorCode.USER_ALREADY_EXISTS)
class UserUnknownError(message: String? = null) : UserException(ErrorCode.USER_UNKNOWN_ERROR)
class EmailAlreadyExistsException(message: String? = null) : UserException(ErrorCode.USER_EMAIL_ALREADY_EXISTS)
