package org.aiml.object3d.mesh.infra.persistence.entity

import jakarta.persistence.*
import org.aiml.object3d.mesh.domain.model.geometry.Geometry
import org.aiml.object3d.mesh.domain.model.material.Material
import org.aiml.object3d.mesh.domain.model.Mesh
import org.aiml.object3d.base.domain.model.Object3DType
import org.aiml.object3d.base.infra.persistence.entity.Object3DEntity
import org.aiml.object3d.base.infra.persistence.entity.TransformMatrix

import java.util.*


@Entity
@Table(name = "mesh")
class MeshEntity(

  @Id
  override val id: UUID = UUID.randomUUID(),

  @Column(name = "scene_id", nullable = false)
  override val sceneId: UUID,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  override val parent: Object3DEntity? = null,

  override val name: String,

  @Embedded
  override val transform: TransformMatrix = TransformMatrix(),

  override val visible: Boolean = true,

  /* ----------------- mesh only  ----------------------- */

  @Column(nullable = false)
  val geometryId: UUID,

  @Column(nullable = false)
  val materialId: UUID,

  @Enumerated(EnumType.STRING)
  override val type: Object3DType = Object3DType.MESH

) : Object3DEntity(id, sceneId, parent, name, transform, visible, type) {
  override fun toDomain(): Mesh = Mesh(
    id = id,
    sceneId = sceneId,
    name = name,
    transform = transform.toDomain(),
    parentId = parent?.id,
    visible = visible,
    createdAt = createdAt,
    updatedAt = updatedAt,

    geometryId = geometryId,
    materialId = materialId,
  )

  companion object {
    fun from(mesh: Mesh, parent: Object3DEntity?): MeshEntity = MeshEntity(
      id = mesh.id,
      sceneId = mesh.sceneId,
      parent = parent,
      name = mesh.name,
      transform = TransformMatrix.from(mesh.transform),
      visible = mesh.visible,

      geometryId = mesh.geometryId,
      materialId = mesh.materialId,
    )
  }
}
