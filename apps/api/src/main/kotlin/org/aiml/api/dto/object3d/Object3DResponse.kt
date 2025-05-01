package org.aiml.api.dto.object3d

import org.aiml.object3d.base.application.dto.*
import java.time.LocalDateTime
import java.util.UUID

sealed interface Object3DResponse {
  val id: UUID
  val name: String
  val type: String
  val transform: TransformDTO
  val visible: Boolean
  val parentId: UUID?

  val createdAt: LocalDateTime
  val updatedAt: LocalDateTime

  companion object {
    fun fromDTO(dto: Object3DDTO): Object3DResponse {
      return when (dto) {
        is GroupDTO -> GroupResponse.fromDTO(dto)
        is MeshDTO -> MeshResponse.fromDTO(dto)
        else -> throw IllegalArgumentException("dto not defined")
      }
    }
  }
}


