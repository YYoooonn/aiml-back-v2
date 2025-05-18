package org.aiml.api.common.response

data class ApiResponse<T>(
  val data: T? = null,
  val code: Int = 200,
  val success: Boolean = true,
  val message: String? = null,
  val revalidate: Boolean? = null
)
