package org.aiml.project_user.domain.facade

import org.aiml.project.domain.command.*
import org.aiml.project_user.domain.command.*
import org.aiml.project_user.domain.facade.dto.ProjectDTO
import org.aiml.project_user.domain.facade.dto.ProjectUserDTO
import java.util.UUID

interface ProjectUserFacade {
    fun getProjectInfo(userId: UUID, projectId: UUID): Result<ProjectDTO>
    fun getProjectInfos(userId: UUID): Result<List<ProjectDTO>>
    fun getParticipants(userId: UUID, projectId: UUID): Result<List<ProjectUserDTO>>
    fun addParticipant(userId: UUID, command: ParticipantCommand): Result<ProjectUserDTO>
    fun deleteParticipant(userId: UUID, command: DeleteParticipantCommand): Result<Unit>
    fun createProject(userId: UUID, command: CreateProjectCommand): Result<ProjectDTO>
    fun updateProject(userId: UUID, command: UpdateProjectCommand): Result<ProjectDTO>
    fun deleteProject(userId: UUID, projectId: UUID): Result<Unit>
}
