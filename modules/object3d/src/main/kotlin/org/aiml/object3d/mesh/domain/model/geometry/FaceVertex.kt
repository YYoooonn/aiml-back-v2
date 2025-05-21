package org.aiml.object3d.mesh.domain.model.geometry

data class FaceVertex(
  val faceIndex: Int,
  val vertexIndexOrder: Int,
  val vertexIndex: Int
) {
  companion object {
    fun build(faceIndex: Int, index: Int, vertexIndex: Int) = FaceVertex(
      faceIndex = faceIndex,
      vertexIndexOrder = index,
      vertexIndex = vertexIndex,
    )
  }
}
