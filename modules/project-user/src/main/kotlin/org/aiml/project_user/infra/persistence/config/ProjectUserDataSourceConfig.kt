package org.aiml.project_user.infra.persistence.config

import org.aiml.libs.common.config.mysql.BeanNames
import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.*
import org.springframework.orm.jpa.*
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = ["org.aiml.project_user.infra"],
  entityManagerFactoryRef = "projectUserEntityManagerFactory",
  transactionManagerRef = "projectUserTransactionManager"
)
class ProjectUserDataSourceConfig {

  @Bean(name = ["projectUserEntityManagerFactory"])
  fun userEntityManagerFactory(
    builder: EntityManagerFactoryBuilder,
    @Qualifier(BeanNames.DATASOURCE) dataSource: DataSource,
    @Qualifier(BeanNames.JPA_PROPERTIES) jpaProperties: JpaProperties
  ): LocalContainerEntityManagerFactoryBean =
    builder
      .dataSource(dataSource)
      .packages("org.aiml.project_user.infra")
      .persistenceUnit("projectUserPersistenceUnit")
      .properties(jpaProperties.properties)
      .build()

  @Bean(name = ["projectUserTransactionManager"])
  fun userTransactionManager(
    @Qualifier("projectUserEntityManagerFactory") emf: EntityManagerFactory
  ): PlatformTransactionManager = JpaTransactionManager(emf)

}
