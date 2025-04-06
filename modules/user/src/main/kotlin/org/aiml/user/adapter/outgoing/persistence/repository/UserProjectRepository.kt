package org.aiml.user.adapter.outgoing.persistence.repository

import org.aiml.user.adapter.outgoing.persistence.entity.UserProjectEntity
import org.aiml.user.adapter.outgoing.persistence.entity.UserProjectId
import org.springframework.data.jpa.repository.JpaRepository

interface UserProjectRepository : JpaRepository<UserProjectEntity, UserProjectId> {
  fun findAllByUserId(userId: Long): List<UserProjectEntity>
  fun findAllByProjectId(projectId: Long): List<UserProjectEntity>
}
