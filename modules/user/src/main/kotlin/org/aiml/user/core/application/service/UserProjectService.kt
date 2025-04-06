package org.aiml.user.core.application.service

import org.aiml.user.core.application.port.incoming.UserProjectUseCase
import org.aiml.user.core.application.port.outgoing.UserProjectPersistencePort
import org.aiml.user.core.domain.model.UserProject
import org.aiml.user.core.domain.model.UserProjectPermission
import org.aiml.user.core.domain.model.UserProjectRole
import org.springframework.stereotype.Service

@Service
class UserProjectService(
  private val port: UserProjectPersistencePort
) : UserProjectUseCase {
  override fun addUserToProject(
    userId: Long,
    projectId: Long,
    role: UserProjectRole,
    permissions: UserProjectPermission
  ) {
    TODO("Not yet implemented")
  }

  override fun findUserFromProject(projectId: Long): List<UserProject> {
    TODO("Not yet implemented")
  }

  override fun removeUserFromProject(userId: Long, projectId: Long) {
    TODO("Not yet implemented")
  }
}
