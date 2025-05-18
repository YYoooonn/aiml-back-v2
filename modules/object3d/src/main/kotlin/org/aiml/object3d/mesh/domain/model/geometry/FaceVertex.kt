package org.aiml.object3d.mesh.domain.model.geometry

data class FaceVertex(
  val faceId: Long = 0L,
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
