package org.aiml.geometry.application.mapper

import org.aiml.geometry.application.dto.MaterialDTO
import org.aiml.geometry.domain.mesh.command.*
import org.springframework.stereotype.Component
import java.util.*

@Component
class MaterialMapper {
  fun toCreateMatCommand(matDTO: MaterialDTO) = CreateMaterialCommand(
    color = matDTO.color,
    opacity = matDTO.opacity,
    transparent = matDTO.transparent,
    textureMapUrl = matDTO.map
  )

  fun toUpdateMatCommand(matDTO: MaterialDTO, id: UUID) = UpdateMaterialCommand(
    id = id,
    color = matDTO.color,
    opacity = matDTO.opacity,
    transparent = matDTO.transparent,
    textureMapUrl = matDTO.map
  )
}
