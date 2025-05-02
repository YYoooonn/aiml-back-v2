package org.aiml.object3d.group.infra.persistence.entity

import jakarta.persistence.*
import org.aiml.object3d.group.domain.model.Group
import org.aiml.object3d.base.domain.model.Object3DType
import org.aiml.object3d.base.infra.persistence.entity.Object3DEntity
import org.aiml.object3d.base.infra.persistence.entity.TransformEmbeddable
import java.util.*

@Entity
@Table(name = "object3d_groups")
data class GroupEntity(
  @Id
  override val id: UUID = UUID.randomUUID(),

  @Column(name = "scene_id", nullable = false)
  override val sceneId: UUID,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  override val parent: Object3DEntity? = null,

  override val name: String,

  @Embedded
  override val transform: TransformEmbeddable = TransformEmbeddable(),

  override val visible: Boolean = true,
) : Object3DEntity(id, sceneId, parent, name, transform, visible, Object3DType.GROUP) {
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
    fun from(domain: Group, parent: Object3DEntity?): GroupEntity = GroupEntity(
      id = domain.id,
      sceneId = domain.sceneId,
      parent = parent,
      name = domain.name,
      transform = TransformEmbeddable.from(domain.transform),
      visible = domain.visible,
    )
  }
}
