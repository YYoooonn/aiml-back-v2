package org.aiml.geometry.domain.geometry.model

data class FaceVertex(
  val faceId: Long,
  val vertexIndexOrder: Int,
  val vertexIndex: Int
) {
  companion object {
    fun build(faceId: Long, index: Int, vertexIndex: Int) = FaceVertex(
      faceId = faceId,
      vertexIndexOrder = index,
      vertexIndex = vertexIndex,
    )
  }
}
