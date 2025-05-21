package org.aiml.object3d.mesh.infra.persistence.entity

import jakarta.persistence.*
import org.aiml.object3d.mesh.domain.model.Mesh
import org.aiml.object3d.base.domain.model.Object3DType
import org.aiml.object3d.base.infra.persistence.entity.Object3DEntity
import org.aiml.object3d.base.infra.persistence.entity.TransformMatrix

import java.util.*


@Entity
@DiscriminatorValue("mesh")
class MeshEntity(

  id: UUID = UUID.randomUUID(),

  sceneId: UUID,

  parent: Object3DEntity? = null,

  name: String,

  transform: TransformMatrix = TransformMatrix(),

  visible: Boolean = true,

  type: Object3DType = Object3DType.MESH,

  /* ----------------- mesh only  ----------------------- */

  @Column(nullable = false)
  val geometryId: UUID,

  @Column(nullable = false)
  val materialId: UUID,

  ) : Object3DEntity(id, sceneId, parent, name, transform, visible, type) {
  override fun toDomain(): Mesh {
    return Mesh(
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
  }

  companion object {
    fun from(mesh: Mesh, parent: Object3DEntity? = null): MeshEntity {
      return MeshEntity(
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
}
