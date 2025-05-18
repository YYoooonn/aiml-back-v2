package org.aiml.object3d.base.infra.persistence.entity

import jakarta.persistence.AttributeOverride
import jakarta.persistence.AttributeOverrides
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded
import org.aiml.object3d.base.domain.model.Quaternion
import org.aiml.object3d.base.domain.model.Transform
import org.aiml.object3d.base.domain.model.Vector3

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
    AttributeOverride(name = "x", column = Column(name = "quaternion_x")),
    AttributeOverride(name = "y", column = Column(name = "quaternion_y")),
    AttributeOverride(name = "z", column = Column(name = "quaternion_z")),
    AttributeOverride(name = "w", column = Column(name = "quaternion_w"))
  )
  val quaternion: QuaternionEmbeddable = QuaternionEmbeddable(),
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
    quaternion = quaternion.toDomain(),
    scale = scale.toDomain()
  )

  companion object {
    fun from(transform: Transform): TransformEmbeddable = TransformEmbeddable(
      position = Vector3Embeddable.from(transform.position),
      quaternion = QuaternionEmbeddable.from(transform.quaternion),
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

@Embeddable
data class QuaternionEmbeddable(
  val x: Float = 0.0f,
  val y: Float = 0.0f,
  val z: Float = 0.0f,
  val w: Float = 1.0f
) {
  fun toDomain(): Quaternion = Quaternion(x, y, z, w)

  companion object {
    fun from(q: Quaternion): QuaternionEmbeddable = QuaternionEmbeddable(q.x, q.y, q.z, q.w)
  }
}
