package org.aiml.geometry.domain.geometry.model

import org.aiml.geometry.domain.geometry.command.CreateFaceCommand
import java.util.UUID

data class Face(
  val id: Long,
  val geometryId: UUID,
  val index: Int,
  val vertices: List<FaceVertex>
) {
  companion object {
    fun build(command: CreateFaceCommand) = Face(
      id = command.id,
      geometryId = command.geometryId,
      index = command.index,
      vertices = command.indices.mapIndexed { i, vIndex ->
        FaceVertex.build(command.id, i, vIndex)
      }
    )
  }
}
