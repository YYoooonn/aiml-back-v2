package org.aiml.geometry.application

import jakarta.transaction.Transactional
import org.aiml.geometry.application.dto.MeshDTO
import org.aiml.geometry.application.mapper.MeshMapper
import org.aiml.geometry.domain.geometry.port.inbound.GeometryUseCase
import org.aiml.geometry.domain.mesh.port.inbound.MaterialUseCase
import org.aiml.geometry.domain.mesh.port.inbound.MeshUseCase
import org.springframework.stereotype.Service
import java.util.UUID

@Transactional
@Service
class MeshFacadeImpl(
  private val meshUseCase: MeshUseCase,
  private val geometryUseCase: GeometryUseCase,
  private val materialUseCase: MaterialUseCase,
  private val mapper: MeshMapper
) : MeshFacade {

  override fun save(meshDTO: MeshDTO): Result<MeshDTO> = runCatching {
    val geo = geometryUseCase.create(mapper.toCreateGeoCommand(meshDTO.geometry))
    val mat = materialUseCase.create(mapper.toCreateMatCommand(meshDTO.material))
    val mesh = meshUseCase.create(mapper.toCreateMeshCommand(meshDTO, geo.id, mat.id))
    mapper.toMeshDTO(mesh, geo, mat)
  }

  override fun getById(id: UUID): Result<MeshDTO> = runCatching {
    val mesh = meshUseCase.getById(id)
    val geo = geometryUseCase.getById(mesh.geometryId)
    val mat = materialUseCase.getById(mesh.materialId)
    mapper.toMeshDTO(mesh, geo, mat)
  }

  override fun delete(id: UUID): Result<Unit> = runCatching {
    val mesh = meshUseCase.getById(id)
    geometryUseCase.deleteById(mesh.geometryId)
    materialUseCase.deleteById(mesh.materialId)
    meshUseCase.deleteById(id)
  }

}
