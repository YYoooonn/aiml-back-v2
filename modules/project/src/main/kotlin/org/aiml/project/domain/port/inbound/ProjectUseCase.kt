package org.aiml.project.domain.port.inbound

import org.aiml.project.domain.command.CreateProjectCommand
import org.aiml.project.domain.command.UpdateProjectCommand
import org.aiml.project.domain.model.Project
import java.util.*

interface ProjectUseCase {
    fun getProject(projectId: UUID): Project
    fun createProject(command: CreateProjectCommand): Project
    fun updateProject(command: UpdateProjectCommand): Project
    fun deleteProject(projectId: UUID)
}
