package org.aiml.api.exception

import org.aiml.api.common.response.*
import org.aiml.api.security.exception.AuthException
import org.aiml.user.exception.UserException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.NoHandlerFoundException


@ControllerAdvice
class GlobalExceptionHandler {

  @ExceptionHandler(RequestException::class)
  fun handleUserException(ex: RequestException): ResponseEntity<ApiResponse<Nothing>> {
    val errorCode = ex.errorCode
    return errorFromModule(errorCode.message, errorCode.httpStatus, errorCode.code)
  }

  @ExceptionHandler(UserException::class)
  fun handleUserException(ex: UserException): ResponseEntity<ApiResponse<Nothing>> {
    val errorCode = ex.errorCode
    return errorFromModule(errorCode.message, errorCode.httpStatus, errorCode.code)
  }

  @ExceptionHandler(AuthException::class)
  fun handleAuthException(ex: AuthException): ResponseEntity<ApiResponse<Nothing>> {
    val errorCode = ex.errorCode
    return errorFromModule(errorCode.message, errorCode.httpStatus, errorCode.code)
  }

  @ExceptionHandler(NoSuchMethodException::class)
  fun handleInvalidMethodExceptions(e: NoSuchMethodException): ResponseEntity<ApiResponse<Nothing>> {
    return notFound(e.message ?: "method not found")
  }

  @ExceptionHandler(NoHandlerFoundException::class)
  fun handleNoHandlerExceptions(e: NoHandlerFoundException): ResponseEntity<ApiResponse<Nothing>> {
    return notFound(e.message ?: "handler not found")
  }

  @ExceptionHandler(Exception::class)
  fun handleAllExceptions(e: Exception): ResponseEntity<ApiResponse<Nothing>> {
    return error(e.message ?: "unknown internal error")
  }
}

