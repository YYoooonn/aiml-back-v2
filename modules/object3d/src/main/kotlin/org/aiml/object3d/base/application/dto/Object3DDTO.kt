package org.aiml.object3d.base.application.dto

import org.aiml.object3d.base.domain.model.Quaternion
import org.aiml.object3d.base.domain.model.Transform
import org.aiml.object3d.base.domain.model.Vector3
import java.time.LocalDateTime
import java.util.*

sealed interface Object3DDTO {
  val sceneId: UUID
  val id: UUID?
  val name: String
  val type: String
  val transform: List<Float>
  val visible: Boolean
  val parentId: UUID?

  val createdAt: LocalDateTime
  val updatedAt: LocalDateTime
}


data class TransformDTO(
  val position: Vector3DTO, // [x,y,z]
  val quaternion: QuaternionDTO, // [x,y,z, w] (radians)
  val scale: Vector3DTO // [x,y,z]
) {
  companion object {
    fun from(t: Transform): TransformDTO = TransformDTO(
      position = Vector3DTO.from(t.position),
      quaternion = QuaternionDTO.from(t.quaternion),
      scale = Vector3DTO.from(t.scale)
    )
  }

  fun toDomain(): Transform = Transform(
    position = position.toDomain(),
    quaternion = quaternion.toDomain(),
    scale = scale.toDomain()
  )
}

data class Vector3DTO(val x: Float, val y: Float, val z: Float) {
  companion object {
    fun from(vec: Vector3): Vector3DTO = Vector3DTO(
      x = vec.x,
      y = vec.y,
      z = vec.z
    )
  }

  fun toDomain(): Vector3 = Vector3(x, y, z)
}

data class QuaternionDTO(val x: Float, val y: Float, val z: Float, val w: Float) {
  companion object {
    fun from(q: Quaternion): QuaternionDTO = QuaternionDTO(
      x = q.x,
      y = q.y,
      z = q.z,
      w = q.w
    )
  }

  fun toDomain(): Quaternion = Quaternion(x, y, z, w)
}
