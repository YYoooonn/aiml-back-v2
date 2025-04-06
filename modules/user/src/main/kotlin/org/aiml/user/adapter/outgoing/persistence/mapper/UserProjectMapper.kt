package org.aiml.user.adapter.outgoing.persistence.mapper

import org.aiml.user.adapter.outgoing.persistence.entity.ProjectEntity
import org.aiml.user.adapter.outgoing.persistence.entity.UserEntity
import org.aiml.user.adapter.outgoing.persistence.entity.UserProjectEntity
import org.aiml.user.adapter.outgoing.persistence.entity.UserProjectId
import org.aiml.user.core.domain.model.UserProject
import org.springframework.stereotype.Component


@Component
class UserProjectMapper(
  private val projectMapper: ProjectMapper
) {

  fun toEntity(domain: UserProject, user: UserEntity, project: ProjectEntity): UserProjectEntity {
    return UserProjectEntity(
      id = UserProjectId(user.id!!, project.id!!),
      user = user,
      project = project,
      role = domain.role,
      permission = domain.permission
    )
  }

  fun toDomain(entity: UserProjectEntity): UserProject {
    return UserProject(
      project = projectMapper.toDomain(entity.project),
      role = entity.role,
      permission = entity.permission,
    )
  }
}
