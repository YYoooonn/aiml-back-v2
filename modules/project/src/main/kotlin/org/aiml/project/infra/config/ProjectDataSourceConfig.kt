package org.aiml.project.infra.config

import org.aiml.libs.common.config.mysql.BeanNames
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
  basePackages = ["org.aiml.project.infra"],
  entityManagerFactoryRef = "projectEntityManagerFactory",
  transactionManagerRef = "projectTransactionManager"
)
class ProjectDataSourceConfig {

  @Bean(name = ["projectEntityManagerFactory"])
  fun projectEntityManagerFactory(
    builder: EntityManagerFactoryBuilder,
    @Qualifier(BeanNames.DATASOURCE) dataSource: DataSource,
    @Qualifier(BeanNames.JPA_PROPERTIES) jpaProperties: JpaProperties
  ): LocalContainerEntityManagerFactoryBean =
    builder
      .dataSource(dataSource)
      .packages("org.aiml.project.infra")
      .persistenceUnit("projectPersistenceUnit")
      .properties(jpaProperties.properties)
      .build()

  @Bean(name = ["projectTransactionManager"])
  fun projectTransactionManager(
    @Qualifier("projectEntityManagerFactory") emf: EntityManagerFactory
  ): PlatformTransactionManager = JpaTransactionManager(emf)

}
