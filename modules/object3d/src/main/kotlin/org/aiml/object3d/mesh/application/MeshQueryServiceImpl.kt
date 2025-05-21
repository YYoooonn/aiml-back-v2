package org.aiml.object3d.mesh.application

import org.aiml.object3d.base.application.dto.GeometryDTO
import org.aiml.object3d.base.application.dto.MaterialDTO
import org.aiml.object3d.base.application.dto.MeshDTO
import org.aiml.object3d.mesh.domain.port.inbound.MeshQueryService
import org.aiml.object3d.mesh.domain.port.outbound.query.GeometryQueryPort
import org.aiml.object3d.mesh.domain.port.outbound.query.MaterialQueryPort
import org.aiml.object3d.mesh.domain.port.outbound.query.MeshQueryPort
import org.springframework.stereotype.Service
import java.util.*

@Service
class MeshQueryServiceImpl(
  private val meshQueryPort: MeshQueryPort,
  private val geometryQueryPort: GeometryQueryPort,
  private val materialQueryPort: MaterialQueryPort,
) : MeshQueryService {
  override fun findById(id: UUID): MeshDTO {
    val mesh = meshQueryPort.findById(id).getOrThrow()
    val geo = geometryQueryPort.findById(mesh.geometryId).getOrThrow()
    val mat = materialQueryPort.findById(mesh.materialId).getOrThrow()
    return MeshDTO.from(mesh, GeometryDTO.from(geo), MaterialDTO.from(mat))
  }

  override fun findBySceneId(sceneId: UUID): List<MeshDTO> {
    val meshes = meshQueryPort.findBySceneId(sceneId).getOrThrow()

    val geometryIds = meshes.map { it.geometryId }.distinct()
    val materialIds = meshes.map { it.materialId }.distinct()

    // 3. Geometry, Material 조회
    val geometries = geometryQueryPort.findByIds(geometryIds).getOrThrow()
    val materials = materialQueryPort.findByIds(materialIds).getOrThrow()

    // 4. Map 으로 만들어 빠르게 access
    val geometryMap = geometries.associateBy { it.id }
    val materialMap = materials.associateBy { it.id }
    return meshes.map { mesh ->
      val geometry = geometryMap[mesh.geometryId]
        ?: throw IllegalStateException("Geometry ${mesh.geometryId} not found")
      val material = materialMap[mesh.materialId]
        ?: throw IllegalStateException("Material ${mesh.materialId} not found")
      MeshDTO.from(mesh, GeometryDTO.from(geometry), MaterialDTO.from(material))
    }
  }

  override fun getGeometry(id: UUID): GeometryDTO {
    val geo = geometryQueryPort.findById(id).getOrThrow()
    return GeometryDTO.from(geo)
  }

  override fun getMaterial(id: UUID): MaterialDTO {
    val mat = materialQueryPort.findById(id).getOrThrow()
    return MaterialDTO.from(mat)
  }


}



