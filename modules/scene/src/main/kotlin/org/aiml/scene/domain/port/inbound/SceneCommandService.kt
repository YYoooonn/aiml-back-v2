package org.aiml.scene.domain.port.inbound

import org.aiml.scene.application.dto.SceneDTO
import java.util.*

interface SceneCommandService {
  fun initiate(projectId: UUID): Result<SceneDTO>
  fun create(sceneDTO: SceneDTO): Result<SceneDTO>
  fun update(dto: SceneDTO): Result<SceneDTO>
  fun delete(id: UUID): Result<Unit>
  fun deleteByProjectId(projectId: UUID): Result<Unit>
}
