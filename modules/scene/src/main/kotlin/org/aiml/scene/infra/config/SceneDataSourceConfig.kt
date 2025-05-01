package org.aiml.scene.infra.config


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
  basePackages = ["org.aiml.scene.infra"],
  entityManagerFactoryRef = "sceneEntityManagerFactory",
  transactionManagerRef = "sceneTransactionManager"
)
class SceneDataSourceConfig {

  @Bean(name = ["sceneEntityManagerFactory"])
  fun sceneEntityManagerFactory(
    builder: EntityManagerFactoryBuilder,
    @Qualifier(BeanNames.DATASOURCE) dataSource: DataSource,
    @Qualifier(BeanNames.JPA_PROPERTIES) jpaProperties: JpaProperties
  ): LocalContainerEntityManagerFactoryBean =
    builder
      .dataSource(dataSource)
      .packages("org.aiml.scene.infra")
      .persistenceUnit("scenePersistenceUnit")
      .properties(jpaProperties.properties)
      .build()

  @Bean(name = ["sceneTransactionManager"])
  fun sceneTransactionManager(
    @Qualifier("sceneEntityManagerFactory") emf: EntityManagerFactory
  ): PlatformTransactionManager = JpaTransactionManager(emf)

}
