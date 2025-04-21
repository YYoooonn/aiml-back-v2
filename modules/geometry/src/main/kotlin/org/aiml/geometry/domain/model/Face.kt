package org.aiml.geometry.domain.model

import java.util.UUID

data class Face(
  val id: UUID = UUID.randomUUID(),
  val vertexIndices: List<Int>,
)
