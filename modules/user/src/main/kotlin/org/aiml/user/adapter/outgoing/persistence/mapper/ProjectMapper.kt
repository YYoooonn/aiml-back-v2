package org.aiml.user.adapter.outgoing.persistence.mapper

import org.aiml.user.adapter.outgoing.persistence.entity.ProjectEntity
import org.aiml.user.core.domain.model.Project
import org.springframework.stereotype.Component

@Component
class ProjectMapper {
  fun toDomain(entity: ProjectEntity): Project {
    return Project(
      id = entity.id,
      title = entity.title,
      subtitle = entity.subtitle,
      description = entity.description,
      updatedAt = entity.updatedAt,
      createdAt = entity.createdAt
    )
  }

  fun toEntity(project: Project): ProjectEntity {
    return ProjectEntity(
      id = project.id,
      title = project.title,
      subtitle = project.subtitle,
      description = project.description,
    )
  }
}
