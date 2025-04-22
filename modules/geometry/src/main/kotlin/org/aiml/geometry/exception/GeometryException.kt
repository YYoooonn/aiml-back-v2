package org.aiml.geometry.exception

import org.springframework.http.HttpStatus

open class GeometryException(
  val errorCode: ErrorCode,
  override val message: String = errorCode.message // <- 이 부분 추가
) : RuntimeException(message)

enum class ErrorCode(
  val httpStatus: HttpStatus,
  val code: String,
  val message: String
) {
  GEO_NOT_FOUND(HttpStatus.NOT_FOUND, "GEO_NOT_FOUND", "geometry를 찾을 수 없습니다."),
  GEO_UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "GEO_UNKNOWN_ERROR", "알 수 없는 geometry 에러")
}

class GeometryNotFoundException(message: String? = null) :
  GeometryException(ErrorCode.GEO_NOT_FOUND, message ?: ErrorCode.GEO_NOT_FOUND.message)

class GeometryUnknownException(message: String? = null) :
  GeometryException(ErrorCode.GEO_UNKNOWN_ERROR, message ?: ErrorCode.GEO_UNKNOWN_ERROR.message)
