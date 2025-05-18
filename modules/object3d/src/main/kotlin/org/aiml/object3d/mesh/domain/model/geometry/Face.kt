package org.aiml.object3d.mesh.domain.model.geometry

import java.util.UUID

data class Face(
  val id: Long = 0L,
  val geometryId: UUID,
  val index: Int,
  val vertices: List<FaceVertex>
)
