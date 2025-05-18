package org.aiml.object3d.base.application.facade

import org.aiml.object3d.base.application.dto.GroupDTO
import org.aiml.object3d.base.application.dto.MeshDTO
import org.aiml.object3d.base.application.dto.Object3DDTO

object Object3DTreeBuilder {

  fun build(objects: List<Object3DDTO>): List<Object3DDTO> {
    val childrenMap = objects.groupBy { it.parentId }
    // 루트 객체 (parentId == null) 들만 시작
    val roots = objects.filter { it.parentId == null }
    return roots.map { root ->
      when (root) {
        is MeshDTO -> root
        is GroupDTO -> root.addChildren(childrenMap[root.id] ?: emptyList())
        else -> throw IllegalArgumentException("Unsupported Object3D type: ${root::class.simpleName}")
      }
    }
  }
}

object Object3DFlattener {
  fun flatten(objects: List<Object3DDTO>): List<Object3DDTO> {
    val result = mutableListOf<Object3DDTO>()

    fun dfs(dto: Object3DDTO) {
      result.add(dto)

      when (dto) {
        is GroupDTO -> dfs(dto)
        is MeshDTO -> Unit
        else -> throw IllegalArgumentException("Unsupported Object3D type: ${dto::class.simpleName}")
      }
    }

    objects.forEach { dfs(it) }
    return result
  }
}
