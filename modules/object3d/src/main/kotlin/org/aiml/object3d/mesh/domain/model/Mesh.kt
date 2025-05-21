package org.aiml.object3d.mesh.domain.model

import org.aiml.object3d.base.domain.model.*
import java.time.LocalDateTime

import java.util.UUID

data class Mesh(
  override val id: UUID = UUID.randomUUID(),
  override val sceneId: UUID,
  override val parentId: UUID? = null,
  override val name: String,
  override val transform: List<Float> = DEFAULT_TRANSFORM, // scale, rotation, translation
  override val visible: Boolean = true,

  override val createdAt: LocalDateTime = LocalDateTime.now(),
  override val updatedAt: LocalDateTime = LocalDateTime.now(),

  val geometryId: UUID,
  val materialId: UUID,

  override val type: Object3DType = Object3DType.MESH

) : Object3D()
