package org.aiml.geometry.application

import org.aiml.geometry.application.dto.MeshDTO
import org.aiml.geometry.application.dto.MeshUpdateDTO
import java.util.*

interface MeshFacade {
  fun save(meshDTO: MeshDTO): Result<MeshDTO>
  fun update(updateDTO: MeshUpdateDTO, id: UUID): Result<MeshDTO>
  fun getById(id: UUID): Result<MeshDTO>
  fun delete(id: UUID): Result<Unit>

  // for test purpose only
  fun getAll(): Result<List<MeshDTO>>
}
