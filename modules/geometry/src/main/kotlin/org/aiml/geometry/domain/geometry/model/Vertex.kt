package org.aiml.geometry.domain.geometry.model

import java.util.*

data class Vertex(
  val geometryId: UUID,
  val index: Int,
  val x: Float,
  val y: Float,
  val z: Float
)
