package org.aiml.object3d.base.domain.model

import java.time.LocalDateTime
import java.util.*

abstract class Object3D {
  abstract val id: UUID
  abstract val sceneId: UUID
  abstract val parentId: UUID?
  abstract val name: String
  abstract val transform: List<Float>
  abstract val visible: Boolean
  abstract val type: Object3DType

  abstract val createdAt: LocalDateTime
  abstract val updatedAt: LocalDateTime
}
//(
//  open val id: UUID = UUID.randomUUID(),
//  open val sceneId: UUID,
//  open val parentId: UUID? = null,
//  open val name: String? = null,
//  open val transform: List<Float> = DEFAULT_TRANSFORM,
//  open val visible: Boolean = true,
//  open val type: Object3DType,
//
//  open val createdAt: LocalDateTime = LocalDateTime.now(),
//  open val updatedAt: LocalDateTime = LocalDateTime.now()
//)

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
