package org.aiml.scene.application.dto

import org.aiml.object3d.base.application.dto.Object3DDTO
import org.aiml.scene.domain.model.Scene
import java.time.LocalDateTime
import java.util.*

data class SceneDTO(
  val id: UUID = UUID.randomUUID(),
  val projectId: UUID,
  val name: String = "untitled",
  val type: String = "SCENE",

  val createdAt: LocalDateTime = LocalDateTime.now(),
  val updatedAt: LocalDateTime = LocalDateTime.now(),

  var children: List<Object3DDTO> = emptyList()
) {
  companion object {
    fun from(scene: Scene): SceneDTO = SceneDTO(
      id = scene.id,
      name = scene.name,
      projectId = scene.projectId,
      createdAt = scene.createdAt,
      updatedAt = scene.updatedAt,
    )

    fun initialize(projectId: UUID): SceneDTO =
      SceneDTO(id = UUID.randomUUID(), name = "default", projectId = projectId)
  }

  fun toDomain(): Scene = Scene(
    id = id,
    name = name,
    projectId = projectId,
  )

  fun addChildren(children: List<Object3DDTO>) = apply { this.children = children }
}
