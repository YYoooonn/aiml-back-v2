package org.aiml.geometry.application.mapper

import org.aiml.geometry.application.dto.GeometryDTO
import org.aiml.geometry.application.dto.MaterialDTO
import org.aiml.geometry.application.dto.MeshDTO
import org.aiml.geometry.application.dto.Vector3DTO
import org.aiml.geometry.domain.geometry.command.*
import org.aiml.geometry.domain.geometry.model.*
import org.aiml.geometry.domain.mesh.command.*
import org.aiml.geometry.domain.mesh.model.*
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class MeshMapper {
  fun toCreateGeoCommand(geoDTO: GeometryDTO): CreateGeometryCommand {
    val geoId = UUID.randomUUID()
    return CreateGeometryCommand(
      id = geoId,
      vertices = toVertices(geoId, geoDTO),
      faces = toFaces(geoId, geoDTO)
    )
  }

  fun toCreateMatCommand(matDTO: MaterialDTO) = CreateMaterialCommand(
    color = matDTO.color,
    opacity = matDTO.opacity,
    transparent = matDTO.transparent,
    textureMapUrl = matDTO.map
  )

  fun toCreateMeshCommand(meshDTO: MeshDTO, geoId: UUID, matId: UUID) = CreateMeshCommand(
    geometryId = geoId,
    materialId = matId,
    name = meshDTO.name,
    transform = Transform.build(meshDTO.position, meshDTO.rotation, meshDTO.scale)
  )

  fun toMeshDTO(mesh: Mesh, geo: Geometry, mat: Material) = MeshDTO(
    id = mesh.id,
    name = mesh.name,
    geometry = toGeoDTO(geo),
    material = toMatDTO(mat),
    position = Vector3DTO.from(mesh.transform.position),
    rotation = Vector3DTO.from(mesh.transform.rotation),
    scale = Vector3DTO.from(mesh.transform.scale)
  )

  // ------------------- PRIVATE HELPERS -------------------

  private fun toMatDTO(mat: Material) = MaterialDTO(
    color = mat.color,
    opacity = mat.opacity,
    transparent = mat.transparent,
    map = mat.textureMapUrl,
  )

  private fun toGeoDTO(geo: Geometry) = GeometryDTO(
    vertices = geo.vertices.map { Vector3DTO(it.x, it.y, it.z) },
    faces = geo.faces.map { face ->
      face.vertices.map { fv -> fv.vertexIndex }
    }
  )


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
