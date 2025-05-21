package org.aiml.api.dto.scene

import org.aiml.api.dto.object3d.Object3DRequest
import org.aiml.api.dto.object3d.toDTO
import org.aiml.scene.application.dto.SceneDTO
import java.util.*

data class SceneRequest(
  val id: UUID? = null,
  val projectId: UUID,
  val name: String,
  val type: String = "SCENE",

  val children: List<Object3DRequest> = emptyList()
)

fun SceneRequest.toDTO(id: UUID? = null): SceneDTO {
  val sceneId = id ?: UUID.randomUUID()
  return SceneDTO(
    id = sceneId,
    projectId = projectId,
    name = name,

    children = children.map { it.toDTO(sceneId) }
  )
}
