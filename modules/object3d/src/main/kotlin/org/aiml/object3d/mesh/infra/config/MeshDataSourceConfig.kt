package org.aiml.object3d.mesh.infra.config


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
  basePackages = ["org.aiml.object3d.mesh.infra"],
  entityManagerFactoryRef = "meshEntityManagerFactory",
  transactionManagerRef = "meshTransactionManager"
)
class MeshDataSourceConfig {

  @Bean(name = ["meshEntityManagerFactory"])
  fun meshEntityManagerFactory(
    builder: EntityManagerFactoryBuilder,
    @Qualifier(BeanNames.DATASOURCE) dataSource: DataSource,
    @Qualifier(BeanNames.JPA_PROPERTIES) jpaProperties: JpaProperties
  ): LocalContainerEntityManagerFactoryBean =
    builder
      .dataSource(dataSource)
      .packages("org.aiml.object3d.mesh.infra")
      .persistenceUnit("meshPersistenceUnit")
      .properties(jpaProperties.properties)
      .build()

  @Bean(name = ["meshTransactionManager"])
  fun meshTransactionManager(
    @Qualifier("meshEntityManagerFactory") emf: EntityManagerFactory
  ): PlatformTransactionManager = JpaTransactionManager(emf)

}
