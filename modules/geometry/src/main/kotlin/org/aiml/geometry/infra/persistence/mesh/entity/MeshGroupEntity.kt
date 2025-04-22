package org.aiml.geometry.infra.persistence.mesh.entity

import jakarta.persistence.*
import org.aiml.geometry.domain.mesh.model.Mesh
import org.aiml.geometry.domain.mesh.model.MeshGroup
import java.util.*

@Entity
@Table(name = "mesh_groups")
data class MeshGroupEntity(
  @Id val id: UUID = UUID.randomUUID(),

  val name: String? = null,
) {
  @OneToMany(mappedBy = "meshGroup")
  val meshes: List<MeshEntity> = mutableListOf()

  fun toDomain(meshes: List<Mesh>): MeshGroup = MeshGroup(
    id = id,
    name = name,
    meshes = meshes
  )

  companion object {
    fun from(meshGroup: MeshGroup): MeshGroupEntity = MeshGroupEntity(
      id = meshGroup.id,
      name = meshGroup.name,
    )
  }

}
