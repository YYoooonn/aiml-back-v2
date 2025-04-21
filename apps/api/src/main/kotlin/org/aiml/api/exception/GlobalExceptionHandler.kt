package org.aiml.api.exception

import org.aiml.api.security.exception.AuthException
import org.aiml.user.domain.exception.UserException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.NoHandlerFoundException


@ControllerAdvice
class GlobalExceptionHandler {

  @ExceptionHandler(UserException::class)
  fun handleUserException(ex: UserException): ResponseEntity<Any> {
    val errorCode = ex.errorCode
    return ResponseEntity
      .status(ex.errorCode.httpStatus)
      .body(ErrorResponse(errorCode.code, errorCode.message))
  }

  @ExceptionHandler(AuthException::class)
  fun handleAuthException(ex: AuthException): ResponseEntity<Any> {
    val errorCode = ex.errorCode
    return ResponseEntity
      .status(ex.errorCode.httpStatus)
      .body(ErrorResponse(errorCode.code, errorCode.message))
  }

  @ExceptionHandler(NoHandlerFoundException::class)
  fun handleNoHandlerExceptions(e: NoHandlerFoundException): ResponseEntity<ErrorResponse> {
    val errorResponse = ErrorResponse("NO_HANDLER", e.message ?: "Handler Not Found")
    return ResponseEntity
      .status(HttpStatus.NOT_FOUND)
      .body(errorResponse)
  }

  @ExceptionHandler(Exception::class)
  fun handleAllExceptions(e: Exception): ResponseEntity<ErrorResponse> {
    val errorResponse = ErrorResponse("GENERAL_ERROR", e.message ?: "Unknown internal error")
    return ResponseEntity
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(errorResponse)
  }
}

