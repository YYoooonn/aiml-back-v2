package org.aiml.object3d.base.infra.adapter.mapper

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.aiml.object3d.base.domain.model.Object3D
import org.aiml.object3d.base.infra.persistence.entity.Object3DEntity
import org.springframework.stereotype.Component

import org.aiml.libs.infra.postgres.BeanNames

@Component
class Object3DEntityMapper(
  @PersistenceContext(unitName = BeanNames.PERSISTENCE_UNIT)
  private val entityManager: EntityManager
) {
  /**
   * 도메인 모델 → JPA 엔티티 'reference' (프록시) 로 변환
   * - DB에 row가 존재한다고 가정하고 SELECT 쿼리를 날리지 않는다.
   * - 엔티티 그래프 연결(FK)만 필요할 때 유용
   */
  fun toEntityRef(domain: Object3D): Object3DEntity =
    domain.let { entityManager.getReference(Object3DEntity::class.java, it.id) }
}
