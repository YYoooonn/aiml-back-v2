package org.aiml.geometry.config

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QuerydslConfig(
  @PersistenceContext val entityManager: EntityManager
) {
//  @Bean
//  fun jpaQueryFactory(): JpaQueryF {
//    return JpaQueryMethodFactory(entityManager)
//  }
}
