package org.aiml.geometry.domain.geometry.service

import jakarta.transaction.Transactional
import org.aiml.geometry.domain.geometry.command.*
import org.aiml.geometry.domain.geometry.model.Geometry
import org.aiml.geometry.domain.geometry.port.inbound.GeometryUseCase
import org.aiml.geometry.domain.geometry.port.outbound.GeometryPersistencePort
import org.aiml.geometry.exception.*
import org.springframework.stereotype.Service
import java.util.*

@Service
class GeometryService(
  private val geometryPersistencePort: GeometryPersistencePort
) : GeometryUseCase {
  override fun create(command: CreateGeometryCommand): Geometry {
    return geometryPersistencePort
      .save(Geometry.build(command))
      .getOrElse { throw GeometryUnknownException("error while creating geometry") }
  }

  @Transactional
  override fun getById(id: UUID): Geometry = geometryPersistencePort
    .findById(id)
    .getOrElse { throw GeometryUnknownException("error while getting geometry") }

  override fun update(command: UpdateGeometryCommand): Geometry {

    return geometryPersistencePort
      .save(getById(command.id).update(command))
      .getOrElse { throw GeometryUnknownException("error while updating geometry") }
  }

  override fun deleteById(id: UUID) = geometryPersistencePort
    .deleteById(id)
    .getOrElse { throw GeometryUnknownException("error while deleting geometry") }
}
