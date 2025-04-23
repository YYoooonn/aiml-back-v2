package org.aiml.geometry.infra.persistence.config

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GeometryQuerydslConfig(
  @PersistenceContext(unitName = "geometryPersistenceUnit")
  val entityManager: EntityManager
) {

  @Bean(name = ["geometryQueryFactory"])
  fun jpaQueryFactory(): JPAQueryFactory {
    return JPAQueryFactory(entityManager)
  }
}
