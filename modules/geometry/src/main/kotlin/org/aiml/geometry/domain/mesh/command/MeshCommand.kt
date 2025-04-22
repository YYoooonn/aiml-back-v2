package org.aiml.geometry.domain.mesh.command

import org.aiml.geometry.domain.mesh.model.Transform
import java.util.*

data class CreateMeshCommand(
  val geometryId: UUID,
  val materialId: UUID,
  val name: String? = null,
  val transform: Transform = Transform(), // scale, rotation, translation
)

data class UpdateMeshCommand(
  val id: UUID,
  val geometryId: UUID,
  val materialId: UUID,
  val name: String? = null,
  val transform: Transform? = null, // scale, rotation, translation
)
