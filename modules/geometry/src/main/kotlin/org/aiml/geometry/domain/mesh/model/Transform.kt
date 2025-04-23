package org.aiml.geometry.domain.mesh.model

import org.aiml.geometry.application.dto.Vector3DTO
import org.aiml.geometry.domain.mesh.command.UpdateTransformCommand

data class Transform(
  val position: Vector3 = Vector3(),
  val rotation: Vector3 = Vector3(),
  val scale: Vector3 = Vector3(1.0f, 1.0f, 1.0f)
) {
  companion object {
    fun build(p: Vector3DTO, r: Vector3DTO, s: Vector3DTO) = Transform(
      position = Vector3.build(p),
      rotation = Vector3.build(r),
      scale = Vector3.build(s)
    )
  }

  fun update(command: UpdateTransformCommand?): Transform {
    if (command == null) return this
    return this.copy(
      position = command.position ?: position,
      rotation = command.rotation ?: rotation,
      scale = command.scale ?: scale
    )

  }
}

data class Vector3(
  val x: Float = 0.0f,
  val y: Float = 0.0f,
  val z: Float = 0.0f
) {
  companion object {
    fun build(vDTO: Vector3DTO) = Vector3(
      x = vDTO.x,
      y = vDTO.y,
      z = vDTO.z
    )
  }
}
