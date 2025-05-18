package org.aiml.api.common.response

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun <T> ok(
  data: T,
  message: String = "Success",
  code: Int = 0,
  revalidate: Boolean? = null
): ResponseEntity<ApiResponse<T>> {
  return ResponseEntity.ok(
    ApiResponse(
      code = code,
      success = true,
      message = message,
      data = data,
      revalidate = revalidate
    )
  )
}

fun <T> created(data: T, message: String = "Created", code: Int = 0): ResponseEntity<ApiResponse<T>> {
  return ResponseEntity.status(HttpStatus.CREATED)
    .body(ApiResponse(code = code, success = true, message = message, data = data))
}

fun <T> deleted(data: T? = null, message: String = "Deleted", code: Int = 0): ResponseEntity<ApiResponse<T>> {
  return ResponseEntity.ok(ApiResponse(code = code, success = true, message = message, data = data))
}

/* failure */

fun <T> badRequest(message: String = "Bad Request", code: Int = 400): ResponseEntity<ApiResponse<T>> {
  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    .body(ApiResponse(code = code, success = false, message = message))
}

fun <T> notFound(message: String = "Not Found", code: Int = 404): ResponseEntity<ApiResponse<T>> {
  return ResponseEntity.status(HttpStatus.NOT_FOUND)
    .body(ApiResponse(code = code, success = false, message = message))
}

fun <T> error(message: String = "Error", code: Int = 500): ResponseEntity<ApiResponse<T>> {
  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    .body(ApiResponse(code = code, success = false, message = message))
}

fun <T> errorFromModule(
  message: String = "Error",
  status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
  code: Int,
  revalidate: Boolean? = null
): ResponseEntity<ApiResponse<T>> {
  return ResponseEntity.status(status).body(
    ApiResponse(
      code = code,
      success = false,
      message = message,
      revalidate = revalidate
    )
  )
}

fun tokenError(response: HttpServletResponse, message: String, objectMapper: ObjectMapper) {
  val errorResponse = mapOf(
    "code" to 400,
    "message" to message,
    "success" to false,
    "revalidate" to true
  )

  response.status = HttpServletResponse.SC_UNAUTHORIZED
  response.contentType = "application/json"
  response.characterEncoding = "UTF-8"
  response.writer.write(objectMapper.writeValueAsString(errorResponse))
}
