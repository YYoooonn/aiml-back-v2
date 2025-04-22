package org.aiml.geometry.infra.persistence.mesh.repository

import org.aiml.geometry.infra.persistence.mesh.entity.MeshGroupEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MeshGroupRepository : JpaRepository<MeshGroupEntity, UUID>
