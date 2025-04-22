package org.aiml.geometry.domain.mesh.model

import java.util.UUID

data class MeshGroup(
  val id: UUID = UUID.randomUUID(),
  val name: String?,
  val meshes: List<Mesh> = emptyList()
)
