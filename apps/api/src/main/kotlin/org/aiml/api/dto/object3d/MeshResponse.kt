package org.aiml.api.dto.object3d

import org.aiml.object3d.base.application.dto.GeometryDTO
import org.aiml.object3d.base.application.dto.MaterialDTO
import org.aiml.object3d.base.application.dto.MeshDTO
import org.aiml.object3d.base.application.dto.TransformDTO
import java.time.LocalDateTime
import java.util.*

data class MeshResponse(
  override val id: UUID,
  override val name: String,
  override val type: String,
  override val transform: TransformDTO,
  override val visible: Boolean,
  override val parentId: UUID? = null,

  override val createdAt: LocalDateTime,
  override val updatedAt: LocalDateTime,

  val geometry: GeometryDTO,
  val material: MaterialDTO
) : Object3DResponse {
  companion object {
    fun fromDTO(dto: MeshDTO): MeshResponse {
      return MeshResponse(
        id = dto.id ?: throw IllegalArgumentException("MeshDTO -> Response id is null"),
        name = dto.name,
        type = dto.type,
        transform = dto.transform,
        visible = dto.visible,
        parentId = dto.parentId,
        createdAt = dto.createdAt,
        updatedAt = dto.updatedAt,
        geometry = dto.geometry,
        material = dto.material,
      )
    }
  }
}
