package org.aiml.object3d.base.domain.model

import java.time.LocalDateTime
import java.util.*

open class Object3D(
  open val id: UUID = UUID.randomUUID(),
  open val sceneId: UUID,
  open val parentId: UUID? = null,
  open val name: String? = null,
  open val transform: List<Float> = DEFAULT_TRANSFORM,
  open val visible: Boolean = true,
  open val type: Object3DType,

  open val createdAt: LocalDateTime = LocalDateTime.now(),
  open val updatedAt: LocalDateTime = LocalDateTime.now()
)

// add more if needed
enum class Object3DType {
  MESH, GROUP, LIGHT, CAMERA, DEFAULT
}

val DEFAULT_TRANSFORM = listOf(
  1f, 0f, 0f, 0f,
  0f, 1f, 0f, 0f,
  0f, 0f, 1f, 0f,
  0f, 0f, 0f, 1f
)
