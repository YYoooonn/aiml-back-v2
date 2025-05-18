package org.aiml.api.dto.scene

import org.aiml.object3d.base.application.dto.Object3DDTO
import org.aiml.scene.application.dto.SceneDTO
import java.time.LocalDateTime
import java.util.*

data class SceneResponse(
  val id: UUID,
  val name: String,
  val type: String = "SCENE",
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime,

  val children: List<Object3DDTO>
) {
  companion object {
    fun fromDTO(dto: SceneDTO): SceneResponse {
      return SceneResponse(
        id = dto.id,
        name = dto.name,
        createdAt = dto.createdAt,
        updatedAt = dto.updatedAt,
        children = dto.children
      )
    }
  }
}


