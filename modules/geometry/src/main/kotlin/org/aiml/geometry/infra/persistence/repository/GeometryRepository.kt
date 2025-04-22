package org.aiml.geometry.infra.persistence.repository

import org.aiml.geometry.infra.persistence.entity.GeometryEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface GeometryRepository : JpaRepository<GeometryEntity, UUID>
