package org.aiml.geometry.domain.model

import java.util.*

data class Edge(
  val id: UUID = UUID.randomUUID(),
  val start: org.aiml.geometry.domain.model.Vertex,
  val end: org.aiml.geometry.domain.model.Vertex
)
