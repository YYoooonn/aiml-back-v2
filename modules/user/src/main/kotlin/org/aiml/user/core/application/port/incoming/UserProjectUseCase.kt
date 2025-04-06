package org.aiml.user.core.application.port.incoming

import org.aiml.user.core.domain.model.UserProject
import org.aiml.user.core.domain.model.UserProjectPermission
import org.aiml.user.core.domain.model.UserProjectRole

interface UserProjectUseCase {
  fun addUserToProject(
    userId: Long,
    projectId: Long,
    role: UserProjectRole,
    permissions: UserProjectPermission
  )

  fun findUserFromProject(projectId: Long): List<UserProject>
  fun removeUserFromProject(userId: Long, projectId: Long)
}
