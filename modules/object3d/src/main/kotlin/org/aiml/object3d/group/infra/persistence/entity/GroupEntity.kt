package org.aiml.object3d.group.infra.persistence.entity

import jakarta.persistence.*
import org.aiml.object3d.group.domain.model.Group
import org.aiml.object3d.base.domain.model.Object3DType
import org.aiml.object3d.base.infra.persistence.entity.Object3DEntity
import org.aiml.object3d.base.infra.persistence.entity.TransformMatrix
import java.util.*

@Entity
@DiscriminatorValue("group")
class GroupEntity(

  id: UUID = UUID.randomUUID(),

  sceneId: UUID,

  parent: Object3DEntity? = null,

  name: String,

  transform: TransformMatrix = TransformMatrix(),

  visible: Boolean = true,

  type: Object3DType = Object3DType.GROUP
) : Object3DEntity(id, sceneId, parent, name, transform, visible, type) {
  override fun toDomain(): Group {
    return Group(
      id = id,
      sceneId = sceneId,
      parentId = parent?.id,
      name = name,
      transform = transform.toDomain(),
      visible = visible,

      createdAt = createdAt,
      updatedAt = updatedAt,
    )
  }

  companion object {
    fun from(domain: Group, parent: Object3DEntity? = null): GroupEntity = GroupEntity(
      id = domain.id,
      sceneId = domain.sceneId,
      parent = parent,
      name = domain.name,
      transform = TransformMatrix.from(domain.transform),
      visible = domain.visible,
    )
  }
}
