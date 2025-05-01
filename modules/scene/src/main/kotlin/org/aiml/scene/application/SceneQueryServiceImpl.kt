package org.aiml.scene.application

import org.aiml.scene.application.dto.SceneDTO
import org.aiml.scene.domain.port.inbound.SceneQueryService
import org.aiml.scene.domain.port.outbound.ScenePersistencePort
import org.springframework.stereotype.Service
import java.util.*

@Service
class SceneQueryServiceImpl(
  private val scenePersistencePort: ScenePersistencePort,
) : SceneQueryService {
  override fun findById(id: UUID): SceneDTO {
    return scenePersistencePort.findById(id).getOrThrow()
      .let { SceneDTO.from(it) }
  }

  override fun findByProjectId(projectId: UUID): List<SceneDTO> {
    return scenePersistencePort.findByProjectId(projectId)
      .getOrThrow().map { SceneDTO.from(it) }
  }

  override fun findProjectId(id: UUID): UUID {
    return scenePersistencePort.findById(id).getOrThrow().projectId
  }
}
