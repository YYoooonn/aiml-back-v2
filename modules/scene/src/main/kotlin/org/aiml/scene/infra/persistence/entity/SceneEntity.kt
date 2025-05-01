package org.aiml.scene.infra.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.aiml.libs.common.entity.BaseEntity
import org.aiml.scene.domain.model.Scene
import java.util.UUID

@Entity
@Table(name = "scenes")
data class SceneEntity(
  @Id val id: UUID = UUID.randomUUID(),

  @Column(name = "project_id", nullable = false)
  val projectId: UUID,

  val name: String
) : BaseEntity() {
  companion object {
    fun from(scene: Scene): SceneEntity = SceneEntity(
      id = scene.id,
      name = scene.name,
      projectId = scene.projectId
    )
  }

  fun toDomain(): Scene = Scene(
    id = id,
    name = name,
    projectId = projectId,
    createdAt = createdAt,
    updatedAt = updatedAt
  )
}
