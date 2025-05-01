package org.aiml.object3d.group.infra.config

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GroupQuerydslConfig(
  @PersistenceContext(unitName = "groupPersistenceUnit")
  val entityManager: EntityManager
) {

  @Bean(name = ["groupQueryFactory"])
  fun jpaQueryFactory(): JPAQueryFactory {
    return JPAQueryFactory(entityManager)
  }
}
