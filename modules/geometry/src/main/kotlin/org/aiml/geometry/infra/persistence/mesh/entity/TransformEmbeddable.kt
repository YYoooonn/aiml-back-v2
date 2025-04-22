package org.aiml.geometry.infra.persistence.mesh.entity

import jakarta.persistence.AttributeOverride
import jakarta.persistence.AttributeOverrides
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded
import org.aiml.geometry.domain.mesh.model.Transform
import org.aiml.geometry.domain.mesh.model.Vector3

@Embeddable
data class TransformEmbeddable(
  @Embedded
  @AttributeOverrides(
    AttributeOverride(name = "x", column = Column(name = "position_x")),
    AttributeOverride(name = "y", column = Column(name = "position_y")),
    AttributeOverride(name = "z", column = Column(name = "position_z"))
  )
  val position: Vector3Embeddable = Vector3Embeddable(),
  @Embedded
  @AttributeOverrides(
    AttributeOverride(name = "x", column = Column(name = "rotation_x")),
    AttributeOverride(name = "y", column = Column(name = "rotation_y")),
    AttributeOverride(name = "z", column = Column(name = "rotation_z"))
  )
  val rotation: Vector3Embeddable = Vector3Embeddable(),
  @Embedded
  @AttributeOverrides(
    AttributeOverride(name = "x", column = Column(name = "scale_x")),
    AttributeOverride(name = "y", column = Column(name = "scale_y")),
    AttributeOverride(name = "z", column = Column(name = "scale_z"))
  )
  val scale: Vector3Embeddable = Vector3Embeddable()
) {
  fun toDomain(): Transform = Transform(
    position = position.toDomain(),
    rotation = rotation.toDomain(),
    scale = scale.toDomain()
  )

  companion object {
    fun from(transform: Transform): TransformEmbeddable = TransformEmbeddable(
      position = Vector3Embeddable.from(transform.position),
      rotation = Vector3Embeddable.from(transform.rotation),
      scale = Vector3Embeddable.from(transform.scale)
    )
  }
}

@Embeddable
data class Vector3Embeddable(
  val x: Float = 0.0f,
  val y: Float = 0.0f,
  val z: Float = 0.0f
) {
  fun toDomain(): Vector3 = Vector3(x, y, z)

  companion object {
    fun from(v: Vector3): Vector3Embeddable = Vector3Embeddable(v.x, v.y, v.z)
  }
}
