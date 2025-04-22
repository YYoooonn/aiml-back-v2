package org.aiml.geometry.domain.geometry.model

import java.util.UUID

data class Face(
  val id: Long,
  val geometryId: UUID,
  val index: Int,
  val vertices: List<FaceVertex>
)
