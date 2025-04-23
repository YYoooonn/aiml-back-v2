package org.aiml.geometry.application.mapper

import org.aiml.geometry.application.dto.*
import org.aiml.geometry.domain.geometry.model.*
import org.aiml.geometry.domain.mesh.command.*
import org.aiml.geometry.domain.mesh.model.*
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class MeshMapper(
  private val geoMapper: GeometryMapper,
  private val matMapper: MaterialMapper
) {
  fun toGeoCreateCommand(geoDTO: GeometryDTO) = geoMapper.toCreateCommand(geoDTO)

  fun toGeoUpdateCommand(geoDTO: GeometryDTO, geoId: UUID) = geoMapper.toUpdateCommand(geoDTO, geoId)

  fun toMatCreateCommand(matDTO: MaterialDTO) = matMapper.toCreateMatCommand(matDTO)

  fun toMatUpdateCommand(matDTO: MaterialDTO, matId: UUID) = matMapper.toUpdateMatCommand(matDTO, matId)

  fun toCreateMeshCommand(meshDTO: MeshDTO, geoId: UUID, matId: UUID) = CreateMeshCommand(
    geometryId = geoId,
    materialId = matId,
    name = meshDTO.name,
    transform = Transform.build(meshDTO.position, meshDTO.rotation, meshDTO.scale)
  )

  fun toUpdateMeshCommand(meshUpdateDTO: MeshUpdateDTO, id: UUID): UpdateMeshCommand {
    return UpdateMeshCommand(
      id = id,
      name = meshUpdateDTO.name,
      transform = toUpdateTransformCommand(meshUpdateDTO)
    )
  }

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

  private fun toUpdateTransformCommand(dto: MeshUpdateDTO): UpdateTransformCommand {
    return UpdateTransformCommand(
      position = dto.position?.let(::toVector3),
      rotation = dto.rotation?.let(::toVector3),
      scale = dto.scale?.let(::toVector3)
    )
  }

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

  private fun toVector3(input: Vector3DTO) = Vector3(input.x, input.y, input.z)
}
