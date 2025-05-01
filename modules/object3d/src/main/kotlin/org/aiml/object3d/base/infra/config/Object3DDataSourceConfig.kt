package org.aiml.object3d.base.infra.config


import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.*
import org.springframework.orm.jpa.*
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import javax.sql.DataSource

import org.aiml.libs.common.config.postgres.BeanNames

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = ["org.aiml.object3d.base.infra"],
  entityManagerFactoryRef = "object3DEntityManagerFactory",
  transactionManagerRef = "object3DTransactionManager"
)
class Object3DDataSourceConfig {

  @Bean(name = ["object3DEntityManagerFactory"])
  fun object3DEntityManagerFactory(
    builder: EntityManagerFactoryBuilder,
    @Qualifier(BeanNames.DATASOURCE) dataSource: DataSource,
    @Qualifier(BeanNames.JPA_PROPERTIES) jpaProperties: JpaProperties
  ): LocalContainerEntityManagerFactoryBean =
    builder
      .dataSource(dataSource)
      .packages("org.aiml.object3d.base.infra")
      .persistenceUnit("object3DPersistenceUnit")
      .properties(jpaProperties.properties)
      .build()

  @Bean(name = ["object3DTransactionManager"])
  fun object3DTransactionManager(
    @Qualifier("object3DEntityManagerFactory") emf: EntityManagerFactory
  ): PlatformTransactionManager = JpaTransactionManager(emf)

}
