package org.aiml.project_user.domain.facade

import jakarta.transaction.Transactional
import org.aiml.project.domain.command.*
import org.aiml.project_user.domain.command.ProjectUserCommand
import org.aiml.project_user.domain.exception.NotAuthorizedException
import org.aiml.project_user.domain.facade.dto.*
import org.aiml.project_user.domain.model.ProjectUserRole
import org.aiml.project_user.domain.port.ProjectUserUseCase
import org.aiml.user.domain.port.inbound.UserCoreUseCase
import org.aiml.project.domain.port.inbound.ProjectUseCase
import org.aiml.project_user.domain.command.ParticipantCommand
import org.aiml.project_user.domain.command.DeleteParticipantCommand
import org.springframework.stereotype.Service
import java.util.*

@Transactional
@Service
class ProjectUserFacadeImp(
  private val userCoreUseCase: UserCoreUseCase,
  private val projectUseCase: ProjectUseCase,
  private val projectUserUseCase: ProjectUserUseCase
) : ProjectUserFacade {

  override fun createProject(userId: UUID, command: CreateProjectCommand): Result<ProjectDTO> {
    return runCatching {
      val newProject = projectUseCase.createProject(command)
      val newProjectUser = projectUserUseCase.create(ProjectUserCommand.fromCreation(userId, newProject.id))
      ProjectDTO.from(newProject, newProjectUser)
    }
  }

  override fun getParticipants(userId: UUID, projectId: UUID): Result<List<ProjectUserDTO>> {
    return runCatching {
      val projectUser = projectUserUseCase.getProjectUser(projectId, userId)
      if (projectUser.role != ProjectUserRole.OWNER) {
        throw NotAuthorizedException("Only project owner can add participants")
      }
      projectUserUseCase.getUsersByProject(projectId).map {
        val user = userCoreUseCase.getUserById(it.userId)
        ProjectUserDTO.from(user, it)
      }
    }
  }

  override fun addParticipant(userId: UUID, command: ParticipantCommand): Result<ProjectUserDTO> {
    return runCatching {
      val projectUser = projectUserUseCase.getProjectUser(command.projectId, userId)
      if (projectUser.role != ProjectUserRole.OWNER) {
        throw NotAuthorizedException("Only project owner can add participants")
      }
      val user = userCoreUseCase.getUserByUsername(command.username)
      ProjectUserDTO.from(
        user,
        projectUserUseCase.assignRole(ProjectUserCommand.from(user.id, command))
      )
    }
  }

  override fun deleteParticipant(userId: UUID, command: DeleteParticipantCommand): Result<Unit> {
    return runCatching {
      val projectUser = projectUserUseCase.getProjectUser(command.projectId, userId)
      if (projectUser.role != ProjectUserRole.OWNER) {
        throw NotAuthorizedException("Only project owner can add participants")
      }
      val user = userCoreUseCase.getUserByUsername(command.username)
      projectUserUseCase.delete(command.projectId, user.id)
    }
  }

  override fun getProjectInfo(userId: UUID, projectId: UUID): Result<ProjectDTO> {
    return runCatching {
      val pu = projectUserUseCase.getProjectUser(projectId, userId)
      ProjectDTO.from(projectUseCase.getProject(pu.projectId), pu)
    }
  }

  override fun getProjectInfos(userId: UUID): Result<List<ProjectDTO>> {
    return runCatching {
      projectUserUseCase.getProjectsByUser(userId).map {
        ProjectDTO.from(projectUseCase.getProject(it.projectId), it)
      }
    }
  }

  override fun updateProject(userId: UUID, command: UpdateProjectCommand): Result<ProjectDTO> {
    return runCatching {
      val projectUser = projectUserUseCase.getProjectUser(command.id, userId)
      if (projectUser.role == ProjectUserRole.VIEWER) {
        throw NotAuthorizedException("Updates not authorized for project viewer")
      }
      val project = projectUseCase.updateProject(command)
      ProjectDTO.from(project, projectUser)
    }
  }

  override fun deleteProject(userId: UUID, projectId: UUID): Result<Unit> {
    return runCatching {
      val projectUser = projectUserUseCase.getProjectUser(projectId, userId)
      if (projectUser.role == ProjectUserRole.OWNER) {
        projectUserUseCase.deleteAll(projectId)
        projectUseCase.deleteProject(projectId)
      } else {
        throw NotAuthorizedException("Only project owner can delete project")
      }
    }
  }

}
