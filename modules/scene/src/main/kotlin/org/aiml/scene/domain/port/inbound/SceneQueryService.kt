package org.aiml.scene.domain.port.inbound

import org.aiml.scene.application.dto.SceneDTO
import java.util.*

interface SceneQueryService {
  fun findByProjectId(projectId: UUID): List<SceneDTO>
  fun findByProjectIds(projectIds: List<UUID>): List<SceneDTO>
  fun findById(id: UUID): SceneDTO
  fun findProjectId(id: UUID): UUID
}
