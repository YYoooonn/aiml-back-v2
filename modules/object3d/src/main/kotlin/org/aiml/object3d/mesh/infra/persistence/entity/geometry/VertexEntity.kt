package org.aiml.object3d.mesh.infra.persistence.entity.geometry

import jakarta.persistence.*
import org.aiml.object3d.mesh.domain.model.geometry.Vertex
import java.io.Serializable
import java.util.*

@Entity
@IdClass(VertexId::class)
@Table(name = "vertex")
data class VertexEntity(
    @Id
    @Column(name = "geometry_id", nullable = false)
    val geometryId: UUID,

    @Column(name = "index", nullable = false)
    val index: Int,

    @Column(nullable = false)
    val x: Float,

    @Column(nullable = false)
    val y: Float,

    @Column(nullable = false)
    val z: Float
) {
    companion object {
        fun from(domain: Vertex, geoId: UUID) = VertexEntity(
            geometryId = geoId,
            index = domain.index,
            x = domain.x,
            y = domain.y,
            z = domain.z
        )
    }

    fun toDomain() = Vertex(
        geometryId = geometryId,
        index = index,
        x = x,
        y = y,
        z = z
    )

}

data class VertexId(
    val geometryId: UUID = UUID(0, 0),
    val index: Int = 0
) : Serializable
