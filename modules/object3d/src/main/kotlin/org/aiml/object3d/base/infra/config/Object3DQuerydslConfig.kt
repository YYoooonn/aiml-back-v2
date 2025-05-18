package org.aiml.object3d.base.infra.config

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import org.aiml.libs.infra.postgres.BeanNames

@Configuration
class Object3DQuerydslConfig(
  @PersistenceContext(unitName = BeanNames.PERSISTENCE_UNIT)
  val entityManager: EntityManager
) {

  @Bean(name = ["object3DQueryFactory"])
  fun jpaQueryFactory(): JPAQueryFactory {
    return JPAQueryFactory(entityManager)
  }
}
