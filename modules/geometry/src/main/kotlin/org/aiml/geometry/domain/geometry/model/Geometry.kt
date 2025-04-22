package org.aiml.geometry.domain.geometry.model

import java.time.LocalDateTime
import java.util.UUID

data class Geometry(
  val id: UUID = UUID.randomUUID(),
  val vertices: List<Vertex>,
  val faces: List<Face>,
  val name: String? = null,
  val createdAt: LocalDateTime = LocalDateTime.now(),
  val updatedAt: LocalDateTime = LocalDateTime.now()
)
