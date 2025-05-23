package org.aiml.user.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
  val httpStatus: HttpStatus,
  val message: String,
  val code: Int = 500 // TODO Specify Error code
) {
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
  USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "User Already Exists"),
  USER_EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "User email already exists"),
  USER_UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown User Error"),

  INVALID_CREDENTIALS(HttpStatus.BAD_REQUEST, "Invalid credentials"),
}

open class UserException(val errorCode: ErrorCode, override val message: String = errorCode.message) :
  RuntimeException(message)

class UserNotFoundException(message: String? = null) : UserException(ErrorCode.USER_NOT_FOUND)

class UserAlreadyExistsException(message: String? = null) : UserException(ErrorCode.USER_ALREADY_EXISTS)

class EmailAlreadyExistsException(message: String? = null) : UserException(ErrorCode.USER_EMAIL_ALREADY_EXISTS)

class UserUnknownError(message: String? = null) : UserException(ErrorCode.USER_UNKNOWN_ERROR)

class InvalidCredentialsException(message: String? = null) :
  UserException(ErrorCode.INVALID_CREDENTIALS, message ?: ErrorCode.INVALID_CREDENTIALS.message)
