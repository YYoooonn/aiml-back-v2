package org.aiml.geometry.infra.persistence.geometry.repository

import org.aiml.geometry.infra.persistence.geometry.entity.GeometryEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface GeometryRepository : JpaRepository<GeometryEntity, UUID>
