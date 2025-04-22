package org.aiml.geometry.domain.geometry.model

import org.aiml.geometry.domain.geometry.command.CreateVertexCommand
import java.util.*

data class Vertex(
  val geometryId: UUID,
  val index: Int,
  val x: Float,
  val y: Float,
  val z: Float
) {
  companion object {
    fun build(command: CreateVertexCommand) = Vertex(
      geometryId = command.geometryId,
      index = command.index,
      x = command.vector.x,
      y = command.vector.y,
      z = command.vector.z
    )
  }
}
