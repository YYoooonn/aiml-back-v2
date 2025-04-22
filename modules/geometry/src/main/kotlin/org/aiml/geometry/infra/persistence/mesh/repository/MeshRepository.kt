package org.aiml.geometry.infra.persistence.mesh.repository

import org.aiml.geometry.infra.persistence.mesh.entity.MeshEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MeshRepository : JpaRepository<MeshEntity, UUID>
