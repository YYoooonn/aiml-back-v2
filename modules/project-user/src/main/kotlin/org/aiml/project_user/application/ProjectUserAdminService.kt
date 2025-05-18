package org.aiml.project_user.application

import org.aiml.project_user.application.dto.ProjectUserDTO
import org.aiml.project_user.domain.port.inbound.ProjectUserCommandService
import org.aiml.project_user.domain.port.inbound.ProjectUserQueryService
import org.springframework.stereotype.Service

@Service
class ProjectUserAdminService(
  private val projectUserQueryService: ProjectUserQueryService,
  private val projectUserCommandService: ProjectUserCommandService
) {
  fun findAllProjectUsers(): List<ProjectUserDTO> {
    return projectUserQueryService.findAll()
  }

  fun deleteAllProjectUsers() {
    return projectUserCommandService.deleteAll()
  }


}
