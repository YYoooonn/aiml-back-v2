package org.aiml.user.core.application.port.outgoing

import org.aiml.user.core.domain.model.UserProject

interface UserProjectPersistencePort {
  fun findByProjectId(projectId: Long): List<UserProject>
  fun findByUserId(userId: Long): List<UserProject>
  fun save(userProject: UserProject, userId: Long, projectId: Long): UserProject
  fun delete(userId: Long, projectId: Long)
}
