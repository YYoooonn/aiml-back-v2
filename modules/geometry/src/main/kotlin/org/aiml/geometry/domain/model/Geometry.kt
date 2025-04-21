package org.aiml.geometry.domain.model

import java.util.UUID

data class Geometry(
  val id: UUID = UUID.randomUUID(),
  val vertices: List<org.aiml.geometry.domain.model.Vertex>,
  val faces: List<org.aiml.geometry.domain.model.Face>,
)
