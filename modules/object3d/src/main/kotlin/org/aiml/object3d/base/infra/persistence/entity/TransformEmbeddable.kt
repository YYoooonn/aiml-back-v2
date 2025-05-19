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
data class TransformMatrix(
  val m00: Float = 1f, val m01: Float = 0f, val m02: Float = 0f, val m03: Float = 0f,
  val m10: Float = 0f, val m11: Float = 1f, val m12: Float = 0f, val m13: Float = 0f,
  val m20: Float = 0f, val m21: Float = 0f, val m22: Float = 1f, val m23: Float = 0f,
  val m30: Float = 0f, val m31: Float = 0f, val m32: Float = 0f, val m33: Float = 1f
) {
  fun toDomain(): List<Float> {
    return listOf(
      m00, m01, m02, m03,
      m10, m11, m12, m13,
      m20, m21, m22, m23,
      m30, m31, m32, m33
    )
  }

  companion object {
    fun from(matrix: List<Float>): TransformMatrix {
      require(matrix.size == 16) { "Matrix must have exactly 16 elements." }
      return TransformMatrix(
        matrix[0], matrix[1], matrix[2], matrix[3],
        matrix[4], matrix[5], matrix[6], matrix[7],
        matrix[8], matrix[9], matrix[10], matrix[11],
        matrix[12], matrix[13], matrix[14], matrix[15]
      )
    }
  }
}

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
