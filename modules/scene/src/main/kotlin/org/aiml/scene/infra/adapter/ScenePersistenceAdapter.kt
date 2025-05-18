package org.aiml.scene.infra.adapter

import org.aiml.scene.domain.model.Scene
import org.aiml.scene.domain.port.outbound.ScenePersistencePort
import org.aiml.scene.infra.persistence.entity.SceneEntity
import org.aiml.scene.infra.persistence.repository.SceneRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class ScenePersistenceAdapter(
  private val sceneRepository: SceneRepository
) : ScenePersistencePort {
  override fun findById(id: UUID): Result<Scene> = runCatching {
    sceneRepository.findById(id).get().toDomain()
  }

  override fun findByProjectIds(projectIds: List<UUID>): Result<List<Scene>> = runCatching {
    sceneRepository.findAllByProjectIdIn(projectIds).map { it.toDomain() }
  }

  override fun save(scene: Scene): Result<Scene> = runCatching {
    sceneRepository.save(SceneEntity.from(scene)).toDomain()
  }

  override fun deleteById(id: UUID): Result<Unit> = runCatching {
    sceneRepository.deleteById(id)
  }

  // ------- to query?

  override fun findAll(): Result<List<Scene>> = runCatching {
    sceneRepository.findAll().map { it.toDomain() }
  }

  override fun findByProjectId(projectId: UUID): Result<List<Scene>> = runCatching {
    sceneRepository.findAllByProjectId(projectId).map { it.toDomain() }
  }

  override fun deleteByProjectId(projectId: UUID): Result<Unit> = runCatching {
    sceneRepository.deleteAllByProjectId(projectId)
  }

  override fun deleteAll(): Result<Unit> = runCatching {
    sceneRepository.deleteAll()
  }

}
