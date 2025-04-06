package org.aiml.user.core.application.port.incoming

import org.aiml.user.core.domain.model.Project

interface ProjectUseCase {
  fun createProject(project: Project): Project
  fun updateProject(project: Project): Project
  fun getProjectsByUser(id: String): List<Project>
}
