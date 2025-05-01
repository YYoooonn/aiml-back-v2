package org.aiml.object3d.base.domain.model

data class Transform(
  val position: Vector3 = Vector3(),
  val quaternion: Quaternion = Quaternion(),
  val scale: Vector3 = Vector3(1.0f, 1.0f, 1.0f)
)

data class Quaternion(
  val x: Float = 0.0f,
  val y: Float = 0.0f,
  val z: Float = 0.0f,
  val w: Float = 1.0f
)

data class Vector3(
  val x: Float = 0.0f,
  val y: Float = 0.0f,
  val z: Float = 0.0f
)
