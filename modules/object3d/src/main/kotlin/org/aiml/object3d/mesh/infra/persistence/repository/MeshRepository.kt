package org.aiml.object3d.mesh.infra.persistence.repository

import org.aiml.object3d.mesh.infra.persistence.entity.MeshEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MeshRepository : JpaRepository<MeshEntity, UUID>
