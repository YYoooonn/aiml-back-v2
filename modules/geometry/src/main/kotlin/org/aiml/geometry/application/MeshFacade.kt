package org.aiml.geometry.application

import org.aiml.geometry.application.dto.MeshDTO
import java.util.*

interface MeshFacade {
  fun save(meshDTO: MeshDTO): Result<MeshDTO>
  fun getById(id: UUID): Result<MeshDTO>
  fun delete(id: UUID): Result<Unit>
}
