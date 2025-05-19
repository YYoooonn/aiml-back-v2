package org.aiml.project.infra.persistence

import jakarta.persistence.*
import org.aiml.libs.common.entity.BaseEntity
import org.aiml.project.domain.model.Project
import org.aiml.project.domain.model.ProjectStatus
import java.util.*

@Entity
@Table(name = "project")
data class ProjectEntity(
  @Id
  val id: UUID,

  @Column(nullable = false)
  val title: String,

  val subtitle: String? = null,

  val description: String? = null,

  @Enumerated(EnumType.STRING)
  val status: ProjectStatus

) : BaseEntity() {
  companion object {
    fun from(domain: Project): ProjectEntity {
      return ProjectEntity(
        id = domain.id,
        title = domain.title,
        subtitle = domain.subtitle,
        description = domain.description,
        status = domain.status
      )
    }
  }

  fun toDomain(): Project {
    return Project(
      id = id,
      title = title,
      subtitle = subtitle,
      description = description,
      status = status,
      createdAt = createdAt,
      updatedAt = updatedAt
    )
  }
}
