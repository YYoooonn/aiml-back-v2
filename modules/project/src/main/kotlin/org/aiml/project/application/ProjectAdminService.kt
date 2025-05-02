package org.aiml.project.application

import org.aiml.project.application.dto.ProjectDTO
import org.aiml.project.domain.port.inbound.ProjectCommandService
import org.aiml.project.domain.port.inbound.ProjectQueryService
import org.springframework.stereotype.Service


@Service
class ProjectAdminService(
  private val projectCommandService: ProjectCommandService,
  private val projectQueryService: ProjectQueryService
) {

  fun deleteAllProjects() {
    return projectCommandService.deleteAll()
  }

  fun findAllProjects(): List<ProjectDTO> {
    return projectQueryService.findAll()
  }
}
