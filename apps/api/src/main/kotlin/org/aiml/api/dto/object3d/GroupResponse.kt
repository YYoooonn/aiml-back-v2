package org.aiml.api.dto.object3d

import org.aiml.object3d.base.application.dto.GroupDTO
import java.time.LocalDateTime
import java.util.*

data class GroupResponse(
  override val id: UUID,
  override val name: String,
  override val type: String,
  override val transform: List<Float>,
  override val visible: Boolean,
  override val parentId: UUID? = null,

  override val createdAt: LocalDateTime,
  override val updatedAt: LocalDateTime,

  val children: List<Object3DResponse> = emptyList()

) : Object3DResponse {
  companion object {
    fun fromDTO(dto: GroupDTO): GroupResponse {
      return GroupResponse(
        id = dto.id ?: throw IllegalArgumentException("GroupDTO -> Response id is null"),
        name = dto.name,
        type = dto.type,
        transform = dto.transform,
        visible = dto.visible,
        parentId = dto.parentId,
        createdAt = dto.createdAt,
        updatedAt = dto.updatedAt,

        children = dto.children.map(Object3DResponse.Companion::fromDTO)
      )
    }
  }
}
