package org.aiml.object3d.base.infra.persistence.entity

import jakarta.persistence.*
import org.aiml.libs.common.entity.BaseEntity
import org.aiml.object3d.base.domain.model.Object3D
import org.aiml.object3d.base.domain.model.Object3DType
import java.util.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING) // JPA 전용
@Table(
  name = "object3d",
  indexes = [Index(name = "idx_object3d_scene_id", columnList = "scene_id")]
)
abstract class Object3DEntity(
  @Id
  val id: UUID,

  @Column(name = "scene_id", nullable = false)
  val sceneId: UUID,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  val parent: Object3DEntity? = null,

  val name: String,

  @Embedded
  val transform: TransformMatrix = TransformMatrix(),

  val visible: Boolean = true,

  @Enumerated(EnumType.STRING)
  val type: Object3DType

) : BaseEntity() {

  abstract fun toDomain(): Object3D
//  {
//    return Object3D(
//      id = id,
//      sceneId = sceneId,
//      parentId = parent?.id,
//      name = name,
//      transform = transform.toDomain(),
//      visible = visible,
//      type = type,
//      createdAt = createdAt,
//      updatedAt = updatedAt,
//    )
//  }
}
