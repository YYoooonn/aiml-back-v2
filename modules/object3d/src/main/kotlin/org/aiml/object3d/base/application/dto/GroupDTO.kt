package org.aiml.object3d.base.application.dto

import org.aiml.object3d.group.domain.model.Group
import java.time.LocalDateTime
import java.util.UUID

data class GroupDTO(
  override val id: UUID? = null,
  override val sceneId: UUID,
  override val name: String = "untitled group",
  override val type: String = "GROUP",
  override val transform: List<Float>,
  override val visible: Boolean = true,
  override val parentId: UUID? = null,
  override val createdAt: LocalDateTime = LocalDateTime.now(),
  override val updatedAt: LocalDateTime = LocalDateTime.now(),

  var children: List<Object3DDTO> = emptyList()
) : Object3DDTO {
  companion object {
    fun from(group: Group, children: List<Object3DDTO>? = null): GroupDTO = GroupDTO(
      id = group.id,
      sceneId = group.sceneId,
      name = group.name,
      transform = group.transform,
      visible = group.visible,
      parentId = group.parentId,

      createdAt = group.createdAt,
      updatedAt = group.updatedAt,
      children = children ?: emptyList()
    )
  }

  fun toDomain(): Group = Group(
    id = id ?: UUID.randomUUID(),
    name = name,
    sceneId = sceneId,
    parentId = parentId,
    transform = transform,
    visible = visible,
  )

  fun addChildren(children: List<Object3DDTO>): GroupDTO = this.apply { this.children = children }
}
