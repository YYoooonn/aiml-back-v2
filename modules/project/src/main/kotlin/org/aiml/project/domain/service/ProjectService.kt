package org.aiml.project.domain.service

import org.aiml.project.domain.command.CreateProjectCommand
import org.aiml.project.domain.command.UpdateProjectCommand
import org.aiml.project.domain.ProjectNotFoundException
import org.aiml.project.domain.model.Project
import org.aiml.project.domain.port.inbound.ProjectUseCase
import org.aiml.project.domain.port.outbound.ProjectPersistencePort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ProjectService(
  val projectPersistencePort: ProjectPersistencePort
) : ProjectUseCase {

  override fun getProject(projectId: UUID): Project {
    return projectPersistencePort.findById(projectId)
      ?: throw ProjectNotFoundException(projectId.toString())
  }

  override fun createProject(command: CreateProjectCommand): Project {
    return projectPersistencePort.save(Project.from(command))
      ?: throw RuntimeException("Project creation failed.")
  }

  override fun updateProject(command: UpdateProjectCommand): Project {
    val project = getProject(command.id)
    return projectPersistencePort.save(project.update(command))
      ?: throw RuntimeException("Project update failed.")
  }

  override fun deleteProject(projectId: UUID) {
    return projectPersistencePort.delete(projectId)
  }
}
