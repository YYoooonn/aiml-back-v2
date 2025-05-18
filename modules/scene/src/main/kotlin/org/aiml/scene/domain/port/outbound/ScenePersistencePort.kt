package org.aiml.scene.domain.port.outbound

import org.aiml.scene.domain.model.Scene
import java.util.UUID

interface ScenePersistencePort {
  fun save(scene: Scene): Result<Scene>
  fun findById(id: UUID): Result<Scene>
  fun deleteById(id: UUID): Result<Unit>

  fun findAll(): Result<List<Scene>>
  fun findByProjectId(projectId: UUID): Result<List<Scene>>
  fun findByProjectIds(projectIds: List<UUID>): Result<List<Scene>>
  fun deleteByProjectId(projectId: UUID): Result<Unit>


  fun deleteAll(): Result<Unit>
}
