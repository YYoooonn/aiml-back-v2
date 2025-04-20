package org.aiml.project.infra.persistence

import org.aiml.project.domain.model.Project
import org.aiml.project.domain.port.outbound.ProjectPersistencePort
import org.aiml.project.infra.persistence.repository.ProjectRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProjectPersistenceAdapter(
  private val projectRepository: ProjectRepository
) : ProjectPersistencePort {
  override fun existsById(id: UUID): Boolean {
    return projectRepository.existsById(id)
  }

  override fun findById(id: UUID): Project? {
    val projectEntity = projectRepository.findById(id).orElse(null)
    return projectEntity?.toDomain()
  }

  override fun save(project: Project): Project {
    val entity = ProjectEntity.from(project)
    val saved = projectRepository.save(entity)
    return saved.toDomain()
  }

  override fun delete(id: UUID) {
    return projectRepository.deleteById(id)
  }
}
