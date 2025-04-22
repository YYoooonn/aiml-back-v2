package org.aiml.geometry.domain.geometry.model

import java.util.*

// FIXME 필요 한가?

data class Edge(
  val id: UUID = UUID.randomUUID(),
  val start: Vertex,
  val end: Vertex
)
