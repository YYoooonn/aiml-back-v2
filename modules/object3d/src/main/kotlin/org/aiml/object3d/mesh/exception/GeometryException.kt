package org.aiml.object3d.mesh.exception

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
    GEO_NOT_FOUND(HttpStatus.NOT_FOUND, "GEO_NOT_FOUND", "Geometry not found"),
    GEO_UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "GEO_UNKNOWN_ERROR", "Unknown error from geometry")
}

class GeometryNotFoundException(message: String? = null) :
    GeometryException(ErrorCode.GEO_NOT_FOUND, message ?: ErrorCode.GEO_NOT_FOUND.message)

class GeometryUnknownException(message: String? = null) :
    GeometryException(ErrorCode.GEO_UNKNOWN_ERROR, message ?: ErrorCode.GEO_UNKNOWN_ERROR.message)
