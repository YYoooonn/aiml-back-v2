package org.aiml.geometry.domain.geometry.model

import org.aiml.geometry.domain.geometry.command.CreateGeometryCommand
import org.aiml.geometry.domain.geometry.command.UpdateGeometryCommand
import java.time.LocalDateTime
import java.util.UUID

data class Geometry(
  val id: UUID,
  val vertices: List<Vertex>,
  val faces: List<Face>,
  val name: String? = null,
  val createdAt: LocalDateTime = LocalDateTime.now(),
  val updatedAt: LocalDateTime = LocalDateTime.now()
) {
  companion object {
    fun build(command: CreateGeometryCommand) = Geometry(
      id = command.id,
      vertices = command.vertices,
      faces = command.faces,
      name = command.name,
    )
  }

  fun update(command: UpdateGeometryCommand) = this.copy(
    vertices = command.vertices ?: vertices,
    faces = command.faces ?: faces,
    name = command.name ?: name
  )
}
