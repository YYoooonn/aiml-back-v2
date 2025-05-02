package org.aiml.scene.application

import org.aiml.scene.application.dto.SceneDTO
import org.aiml.scene.domain.port.inbound.SceneCommandService
import org.aiml.scene.domain.port.outbound.ScenePersistencePort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SceneCommandServiceImpl(
  private val scenePersistencePort: ScenePersistencePort,
) : SceneCommandService {

  override fun initiate(projectId: UUID): Result<SceneDTO> = runCatching {
    val scene = scenePersistencePort.save(SceneDTO.initialize(projectId).toDomain()).getOrThrow()
    SceneDTO.from(scene)
  }

  override fun create(sceneDTO: SceneDTO): Result<SceneDTO> = runCatching {
    val scene = scenePersistencePort.save(sceneDTO.toDomain()).getOrThrow()
    SceneDTO.from(scene)
  }

  override fun update(dto: SceneDTO): Result<SceneDTO> = runCatching {
    val updated = scenePersistencePort.save(dto.toDomain()).getOrThrow()
    SceneDTO.from(updated)
  }

  override fun delete(id: UUID): Result<Unit> = runCatching {
    scenePersistencePort.deleteById(id).getOrThrow()
  }

  override fun deleteByProjectId(projectId: UUID): Result<Unit> = runCatching {
    scenePersistencePort.deleteByProjectId(projectId).getOrThrow()
  }

  override fun deleteAll() {
    scenePersistencePort.deleteAll().getOrThrow()
  }

}
