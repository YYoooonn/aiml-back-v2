package org.aiml.geometry.infra.persistence.mesh.repository

import org.aiml.geometry.infra.persistence.mesh.entity.MaterialEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MaterialRepository : JpaRepository<MaterialEntity, UUID>
