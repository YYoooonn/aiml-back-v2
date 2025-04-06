package org.aiml.user.core.application.port.outgoing

import org.aiml.user.core.domain.model.Project

interface ProjectPersistencePort {
  fun save(project: Project): Project
  fun findByProjectId(projectId: String): Project
  fun findByOwnerId(ownerId: String): List<Project>
}
