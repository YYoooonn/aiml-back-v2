package org.aiml.geometry.domain.mesh.command

import org.aiml.geometry.domain.mesh.model.Transform
import org.aiml.geometry.domain.mesh.model.Vector3
import java.util.*

data class CreateMeshCommand(
  val geometryId: UUID,
  val materialId: UUID,
  val name: String? = null,
  val transform: Transform = Transform(), // scale, rotation, translation
)

data class UpdateMeshCommand(
  val id: UUID,
  val name: String? = null,
  val transform: UpdateTransformCommand? = null, // scale, rotation, translation
)

data class UpdateTransformCommand(
  val position: Vector3? = null,
  val rotation: Vector3? = null,
  val scale: Vector3? = null,
)
