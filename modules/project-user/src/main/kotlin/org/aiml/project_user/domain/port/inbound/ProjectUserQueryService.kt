package org.aiml.project_user.domain.port.inbound

import org.aiml.project_user.application.dto.ProjectUserDTO
import java.util.*

interface ProjectUserQueryService {
  fun findProjectUser(userId: UUID, projectId: UUID): ProjectUserDTO
  fun findUsersByProjectId(userId: UUID, projectId: UUID): List<ProjectUserDTO>
  fun findProjectsByUserId(userId: UUID): List<ProjectUserDTO>
  fun findUserOwnedProjects(userId: UUID): List<ProjectUserDTO>

  fun findAll(): List<ProjectUserDTO>
}
