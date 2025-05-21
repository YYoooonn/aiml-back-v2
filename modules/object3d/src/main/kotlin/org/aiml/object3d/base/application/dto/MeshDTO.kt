package org.aiml.object3d.base.application.dto

import org.aiml.object3d.mesh.domain.model.Mesh
import java.time.LocalDateTime
import java.util.*

data class MeshDTO(
  override val id: UUID?,
  override val sceneId: UUID,
  override val name: String = "untitled mesh",
  override val type: String = "MESH",
  override val transform: List<Float>,
  override val visible: Boolean = true,
  override val parentId: UUID? = null,

  override val createdAt: LocalDateTime = LocalDateTime.now(),
  override val updatedAt: LocalDateTime = LocalDateTime.now(),
  val geometry: GeometryDTO,
  val material: MaterialDTO,
) : Object3DDTO {
  companion object {
    fun from(mesh: Mesh, geo: GeometryDTO, mat: MaterialDTO): MeshDTO = MeshDTO(
      id = mesh.id,
      sceneId = mesh.sceneId,
      name = mesh.name,
      transform = mesh.transform,
      visible = mesh.visible,
      parentId = mesh.parentId,
      createdAt = mesh.createdAt,
      updatedAt = mesh.updatedAt,

      geometry = geo,
      material = mat,
    )
  }

  fun toDomain(parentId: UUID?): Mesh = Mesh(
    id = id ?: UUID.randomUUID(),
    sceneId = sceneId,
    parentId = parentId,
    name = name,
    transform = transform,
    visible = visible,

    geometryId = geometry.id,
    materialId = material.id
  )

}
