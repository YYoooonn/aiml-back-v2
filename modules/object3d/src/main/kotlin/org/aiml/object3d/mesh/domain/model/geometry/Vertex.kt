package org.aiml.object3d.mesh.domain.model.geometry

import java.util.*

data class Vertex(
  val geometryId: UUID,
  val index: Int,
  val x: Float,
  val y: Float,
  val z: Float
)
