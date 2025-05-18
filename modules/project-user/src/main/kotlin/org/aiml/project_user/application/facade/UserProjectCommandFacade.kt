package org.aiml.project_user.application.facade

import org.aiml.project.domain.port.inbound.ProjectCommandService
import org.aiml.project_user.domain.model.ProjectUserRole
import org.aiml.project_user.domain.port.inbound.ProjectUserCommandService
import org.aiml.project_user.domain.port.inbound.ProjectUserQueryService
import org.aiml.user.application.UserServiceFacade
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserProjectCommandFacade(
  private val userServiceFacade: UserServiceFacade,
  private val projectUserQueryService: ProjectUserQueryService,
  private val projectUserCommandService: ProjectUserCommandService,
  private val projectCommandService: ProjectCommandService,
) {

  fun deleteByUserId(id: UUID) {
    val pIds = projectUserQueryService.findProjectsByUserId(id)
      .filter { it.role.hasPermission(ProjectUserRole.OWNER) }.map { it.projectId }


    projectUserCommandService.deleteAllByUserId(id)
    projectCommandService.deleteByIds(pIds)
    userServiceFacade.delete(id)
  }


}
