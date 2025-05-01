package org.aiml.project_user.domain.port.inbound

import org.aiml.project_user.application.dto.ProjectUserDTO
import java.util.*

interface ProjectUserQueryService {
  fun findProjectUser(userId: UUID, projectId: UUID): Result<ProjectUserDTO>
  fun findUsersByProjectId(userId: UUID, projectId: UUID): Result<List<ProjectUserDTO>>
  fun findProjectsByUserId(userId: UUID): Result<List<ProjectUserDTO>>
}
