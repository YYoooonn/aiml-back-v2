package org.aiml.geometry.application.mapper

import org.aiml.geometry.application.dto.GeometryDTO
import org.aiml.geometry.domain.geometry.command.*
import org.aiml.geometry.domain.geometry.model.*
import org.springframework.stereotype.Component
import java.util.*

@Component
class GeometryMapper {
  fun toCreateCommand(geoDTO: GeometryDTO): CreateGeometryCommand {
    val id = UUID.randomUUID()
    return CreateGeometryCommand(
      id = id,
      vertices = toVertices(id, geoDTO),
      faces = toFaces(id, geoDTO)
    )
  }

  fun toUpdateCommand(geoDTO: GeometryDTO, id: UUID): UpdateGeometryCommand {
    return UpdateGeometryCommand(
      id = id,
      vertices = toVertices(id, geoDTO),
      faces = toFaces(id, geoDTO)
    )
  }

  private fun toVertices(geoId: UUID, geoDTO: GeometryDTO) =
    geoDTO.vertices.mapIndexed { index, v ->
      CreateVertexCommand(
        geometryId = geoId,
        index = index,
        vector = v
      )
    }.map { Vertex.build(it) }


  private fun toFaces(geoId: UUID, geoDTO: GeometryDTO) =
    geoDTO.faces.mapIndexed { index, indices ->
      CreateFaceCommand(
        geometryId = geoId,
        index = index,
        indices = indices
      )
    }.map { Face.build(it) }
}
