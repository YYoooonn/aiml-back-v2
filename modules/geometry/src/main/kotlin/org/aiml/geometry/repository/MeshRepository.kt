package org.aiml.geometry.repository

import org.aiml.geometry.entity.Mesh
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MeshRepository : JpaRepository<Mesh, Long>
