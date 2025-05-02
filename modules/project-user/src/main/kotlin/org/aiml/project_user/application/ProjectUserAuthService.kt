package org.aiml.project_user.application

import org.aiml.project_user.domain.exception.NotAuthorizedException
import org.aiml.project_user.domain.model.ProjectUserRole
import org.aiml.project_user.domain.port.outbound.ProjectUserQueryPort
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProjectUserAuthService(
  private val projectUserQueryPort: ProjectUserQueryPort
) {
  fun authenticate(userId: UUID, projectId: UUID, role: ProjectUserRole) {
    val pUser = projectUserQueryPort.findByProjectIdAndUserId(projectId, userId).getOrElse {
      throw NotAuthorizedException("Not a participant")
    }
    if (!pUser.role.hasPermission(role)) {
      throw NotAuthorizedException("Not Authorized, current: ${pUser.role.name} required: ${role.name}")
    }
  }

  fun authenticateOwner(userId: UUID, projectId: UUID) = authenticate(userId, projectId, ProjectUserRole.OWNER)
  fun authenticateEditor(userId: UUID, projectId: UUID) = authenticate(userId, projectId, ProjectUserRole.EDITOR)
  fun authenticateViewer(userId: UUID, projectId: UUID) = authenticate(userId, projectId, ProjectUserRole.VIEWER)
}
