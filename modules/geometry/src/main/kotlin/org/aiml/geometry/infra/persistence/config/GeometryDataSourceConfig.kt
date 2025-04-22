package org.aiml.geometry.infra.persistence.config

import org.aiml.libs.common.config.postgres.BeanNames
import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.*
import org.springframework.orm.jpa.*
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import javax.sql.DataSource

@Configuration
@EnableConfigurationProperties
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = ["org.aiml.geometry.infra"],
  entityManagerFactoryRef = "geometryEntityManagerFactory",
  transactionManagerRef = "geometryTransactionManager"
)
class GeometryDataSourceConfig {

  @Bean(name = ["geometryEntityManagerFactory"])
  fun geometryEntityManagerFactory(
    builder: EntityManagerFactoryBuilder,
    @Qualifier(BeanNames.DATASOURCE) dataSource: DataSource,
    @Qualifier(BeanNames.JPA_PROPERTIES) jpaProperties: JpaProperties
  ): LocalContainerEntityManagerFactoryBean =
    builder
      .dataSource(dataSource)
      .packages("org.aiml.project.infra")
      .persistenceUnit("geometryPersistenceUnit")
      .properties(jpaProperties.properties)
      .build()

  @Bean(name = ["geometryTransactionManager"])
  fun geometryTransactionManager(
    @Qualifier("geometryEntityManagerFactory") emf: EntityManagerFactory
  ): PlatformTransactionManager = JpaTransactionManager(emf)

}
