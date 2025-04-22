package org.aiml.geometry.domain.geometry.command

import org.aiml.geometry.application.dto.Vector3DTO
import org.aiml.geometry.domain.geometry.model.Face
import org.aiml.geometry.domain.geometry.model.Vertex
import java.util.*

data class CreateGeometryCommand(
  val id: UUID,
  val vertices: List<Vertex>,
  val faces: List<Face>,
  val name: String? = null,
)

data class UpdateGeometryCommand(
  val id: UUID,
  val vertices: List<Vertex>? = null,
  val faces: List<Face>? = null,
  val name: String? = null,
)

data class CreateVertexCommand(
  val geometryId: UUID,
  val index: Int,
  val vector: Vector3DTO,
)

data class CreateFaceCommand(
  val id: Long = 0L,
  val geometryId: UUID,
  val index: Int,
  val indices: List<Int>,
)
