package org.aiml.user.core.application.service

import org.aiml.user.core.application.port.incoming.ProjectUseCase
import org.aiml.user.core.application.port.outgoing.ProjectPersistencePort
import org.aiml.user.core.domain.model.Project
import org.springframework.stereotype.Service

@Service
class ProjectService(
  private val port: ProjectPersistencePort
) : ProjectUseCase {
  override fun createProject(project: Project): Project {
    TODO("Not yet implemented")
  }

  override fun updateProject(project: Project): Project {
    TODO("Not yet implemented")
  }

  override fun getProjectsByUser(id: String): List<Project> {
    TODO("Not yet implemented")
  }
}
