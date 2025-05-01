package org.aiml.project_user.application.facade

import org.aiml.project_user.application.ProjectUserAuthService
import org.aiml.project_user.application.dto.ProjectUserDTO
import org.aiml.project_user.application.dto.ProjectUserNameDTO
import org.aiml.project_user.domain.model.ProjectUserRole
import org.aiml.project_user.domain.port.inbound.ProjectUserCommandService
import org.aiml.project_user.domain.port.inbound.ProjectUserQueryService
import org.aiml.user.exception.UserNotFoundException
import org.aiml.user.domain.port.inbound.UserCoreQueryService
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProjectUserFacade(
  private val userCoreQueryService: UserCoreQueryService,
  private val projectUserCommandService: ProjectUserCommandService,
  private val projectUserQueryService: ProjectUserQueryService,
  private val authService: ProjectUserAuthService
) {

  fun addParticipant(userId: UUID, projectId: UUID, username: String, role: String): ProjectUserNameDTO {
    authService.authenticateOwner(userId, projectId)
    val user = findUser(username)
    val pUser = projectUserCommandService.create(ProjectUserDTO.build(projectId, user.id, role))
    return ProjectUserNameDTO.from(pUser, user.username)
  }

  fun updateParticipant(userId: UUID, projectId: UUID, username: String, role: String): ProjectUserNameDTO {
    authService.authenticateOwner(userId, projectId)
    val user = findUser(username)
    val pUser = projectUserCommandService.update(ProjectUserDTO.build(projectId, user.id, role))
    return ProjectUserNameDTO.from(pUser, user.username)
  }

  fun removeParticipant(userId: UUID, projectId: UUID, username: String) {
    authService.authenticateOwner(userId, projectId)
    val user = findUser(username)
    projectUserCommandService.delete(projectId, user.id)
  }

  fun findUsers(userId: UUID, projectId: UUID): List<ProjectUserNameDTO> {
    authService.authenticateOwner(userId, projectId)

    val projectUsers = projectUserQueryService.findUsersByProjectId(userId, projectId)
      .getOrThrow()

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
