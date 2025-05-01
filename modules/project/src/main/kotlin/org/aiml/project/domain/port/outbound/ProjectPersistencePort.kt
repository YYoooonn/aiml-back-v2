package org.aiml.project.domain.port.outbound

import org.aiml.project.domain.model.Project
import java.util.*

interface ProjectPersistencePort {
  fun save(project: Project): Result<Project>
  fun delete(id: UUID): Result<Unit>

  fun existsById(id: UUID): Boolean
  fun findById(id: UUID): Result<Project>
  fun findByIds(ids: List<UUID>): Result<List<Project>>

  fun findAll(): Result<List<Project>>
}
