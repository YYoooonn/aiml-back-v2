package org.aiml.project.domain.port.outbound

import org.aiml.project.domain.model.Project
import java.util.*

interface ProjectPersistencePort {
  fun save(project: Project): Project?
  fun findById(id: UUID): Project?
  fun existsById(id: UUID): Boolean
  fun delete(id: UUID)
}
