package org.aiml.object3d.mesh.domain.model.geometry

import java.time.LocalDateTime
import java.util.UUID

data class Geometry(
  val id: UUID,
  val meshId: UUID,
  val vertices: List<Vertex>,
  val faces: List<Face>,
  val name: String = "untitled",
  val createdAt: LocalDateTime = LocalDateTime.now(),
  val updatedAt: LocalDateTime = LocalDateTime.now()
)
