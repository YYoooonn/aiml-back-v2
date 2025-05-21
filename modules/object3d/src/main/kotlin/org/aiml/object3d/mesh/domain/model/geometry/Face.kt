package org.aiml.object3d.mesh.domain.model.geometry

import java.util.UUID

data class Face(
  val geometryId: UUID,
  val index: Int,
  val vertices: List<FaceVertex>
)
