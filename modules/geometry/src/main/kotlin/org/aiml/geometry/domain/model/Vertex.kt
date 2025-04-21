package org.aiml.geometry.domain.model

import java.util.*

data class Vertex(
  val id: UUID = UUID.randomUUID(),
  val x: Float,
  val y: Float,
  val z: Float
)
