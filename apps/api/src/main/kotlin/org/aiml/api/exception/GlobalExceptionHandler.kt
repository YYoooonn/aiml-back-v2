package org.aiml.api.exception

import org.aiml.user.domain.exception.UserNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.NoHandlerFoundException


@ControllerAdvice
class GlobalExceptionHandler {

  @ExceptionHandler(UserNotFoundException::class)
  fun handleUserNotFoundException(e: UserNotFoundException): ResponseEntity<ErrorResponse> {
    val errorResponse = ErrorResponse("USER_NOT_FOUND", e.message ?: "User not found")
    return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
  }

  @ExceptionHandler(InvalidTokenException::class)
  fun handleInvalidToken(e: InvalidTokenException): ResponseEntity<ErrorResponse> {
    val errorResponse = ErrorResponse("TOKEN_INVALID", e.message ?: "Invalid token")
    return ResponseEntity(errorResponse, HttpStatus.FORBIDDEN)
  }

  @ExceptionHandler(ExpiredJwtException::class)
  fun handleExpiredToken(e: ExpiredJwtException): ResponseEntity<ErrorResponse> {
    val errorResponse = ErrorResponse("TOKEN_EXPIRED", e.message ?: "Token Expired")
    return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
  }

  @ExceptionHandler(NoHandlerFoundException::class)
  fun handleNoHandlerExceptions(e: NoHandlerFoundException): ResponseEntity<ErrorResponse> {
    val errorResponse = ErrorResponse("NO_HANDLER", e.message ?: "Handler Not Found")
    return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
  }

  @ExceptionHandler(Exception::class)
  fun handleAllExceptions(e: Exception): ResponseEntity<ErrorResponse> {
    val errorResponse = ErrorResponse("GENERAL_ERROR", e.message ?: "Unknown internal error")
    return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
  }
}


data class ErrorResponse(val code: String, val message: String)
