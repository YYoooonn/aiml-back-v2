package org.aiml.geometry.domain.mesh.model

import org.aiml.geometry.domain.mesh.command.*
import java.util.UUID
import java.time.LocalDateTime

data class Mesh(
  val id: UUID = UUID.randomUUID(),
  val geometryId: UUID,
  val materialId: UUID,
  val name: String? = null,
  val transform: Transform = Transform(), // scale, rotation, translation
  val createdAt: LocalDateTime = LocalDateTime.now(),
  val updatedAt: LocalDateTime = LocalDateTime.now(),
) {
  companion object {
    fun build(command: CreateMeshCommand): Mesh = Mesh(
      geometryId = command.geometryId,
      materialId = command.materialId,
      name = command.name,
      transform = command.transform,
    )
  }

  fun update(command: UpdateMeshCommand) = this.copy(
    name = command.name ?: name,
    transform = command.transform ?: transform
  )
}
