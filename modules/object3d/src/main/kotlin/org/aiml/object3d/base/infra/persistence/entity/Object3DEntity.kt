package org.aiml.object3d.base.infra.persistence.entity

import jakarta.persistence.*
import org.aiml.libs.common.entity.BaseEntity
import org.aiml.object3d.base.domain.model.Object3D
import org.aiml.object3d.base.domain.model.Object3DType
import java.util.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(
  name = "object3d",
  indexes = [Index(name = "idx_object3d_scene_id", columnList = "scene_id")]
)
abstract class Object3DEntity(
  @Id
  open val id: UUID,

  @Column(name = "scene_id", nullable = false)
  open val sceneId: UUID,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  open val parent: Object3DEntity? = null,

  open val name: String? = null,

  @Embedded
  open val transform: TransformMatrix = TransformMatrix(),

  open val visible: Boolean = true,

  @Enumerated(EnumType.STRING)
  open val type: Object3DType

) : BaseEntity() {

  fun toDomain(): Object3D {
    return Object3D(
      id = id,
      sceneId = sceneId,
      parentId = parent?.id,
      name = name,
      transform = transform.toDomain(),
      visible = visible,
      type = type,
      createdAt = createdAt,
      updatedAt = updatedAt,
    )
  }
}
