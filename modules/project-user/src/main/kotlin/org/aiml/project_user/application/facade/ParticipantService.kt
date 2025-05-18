package org.aiml.project_user.application.facade

import org.aiml.project_user.application.ProjectUserAuthService
import org.aiml.project_user.application.dto.ProjectUserDTO
import org.aiml.project_user.application.dto.ProjectUserNameDTO
import org.aiml.project_user.domain.port.inbound.ProjectUserCommandService
import org.aiml.project_user.domain.port.inbound.ProjectUserQueryService
import org.aiml.user.exception.UserNotFoundException
import org.aiml.user.domain.port.inbound.UserCoreQueryService
import org.springframework.stereotype.Service
import java.util.*

@Service
class ParticipantService(
  private val userCoreQueryService: UserCoreQueryService,
  private val projectUserCommandService: ProjectUserCommandService,
  private val projectUserQueryService: ProjectUserQueryService,
  private val authService: ProjectUserAuthService
) {

  fun addParticipant(userId: UUID, dto: ProjectUserNameDTO): ProjectUserNameDTO {
    authService.authenticateOwner(userId, dto.projectId)
    val user = findUser(dto.username)
    val pUser = projectUserCommandService.create(ProjectUserDTO.build(dto.projectId, user.id, dto.role))
    return ProjectUserNameDTO.from(pUser, user.username)
  }

  fun updateParticipant(userId: UUID, dto: ProjectUserNameDTO): ProjectUserNameDTO {
    authService.authenticateOwner(userId, dto.projectId)
    val user = findUser(dto.username)
    val pUser = projectUserCommandService.update(ProjectUserDTO.build(dto.projectId, user.id, dto.role))
    return ProjectUserNameDTO.from(pUser, user.username)
  }

  fun removeParticipant(userId: UUID, projectId: UUID, username: String) {
    authService.authenticateOwner(userId, projectId)
    val user = findUser(username)
    projectUserCommandService.delete(projectId, user.id)
  }

  fun findParticipants(userId: UUID, projectId: UUID): List<ProjectUserNameDTO> {
    authService.authenticateOwner(userId, projectId)

    val projectUsers = projectUserQueryService.findUsersByProjectId(userId, projectId)

    val userIds = projectUsers.map { it.userId }
    val users = userCoreQueryService.findByIds(userIds)

    val roleMap = projectUsers.associateBy { it.userId }
    val userMap = users.associateBy { it.id }

    return userIds.map { id ->
      val role = roleMap[id] ?: throw RuntimeException("user role not found")
      val user = userMap[id] ?: throw RuntimeException("user not found")
      ProjectUserNameDTO.from(role, user.username)
    }
  }

  private fun findUser(username: String) = userCoreQueryService.findByUsername(username)
    ?: throw UserNotFoundException("User $username not found")
}
