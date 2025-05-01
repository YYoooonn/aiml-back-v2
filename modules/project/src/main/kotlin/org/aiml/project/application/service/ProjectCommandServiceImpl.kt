package org.aiml.project.application.service

import org.aiml.project.application.dto.ProjectDTO
import org.aiml.project.domain.ProjectNotFoundException
import org.aiml.project.domain.port.inbound.ProjectCommandService
import org.aiml.project.domain.port.outbound.ProjectPersistencePort
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProjectCommandServiceImpl(
  val projectPersistencePort: ProjectPersistencePort
) : ProjectCommandService {

  override fun create(dto: ProjectDTO): ProjectDTO {
    val project = projectPersistencePort.save(dto.toDomain()).getOrThrow()
    return ProjectDTO.from(project)
  }

  override fun update(dto: ProjectDTO): ProjectDTO {
    if (dto.id == null || !projectPersistencePort.existsById(dto.id)) throw ProjectNotFoundException(dto.id.toString())
    val updated = projectPersistencePort.save(dto.toDomain()).getOrThrow()
    return ProjectDTO.from(updated)
  }

  override fun delete(projectId: UUID) {
    projectPersistencePort.delete(projectId).getOrThrow()
  }
}
