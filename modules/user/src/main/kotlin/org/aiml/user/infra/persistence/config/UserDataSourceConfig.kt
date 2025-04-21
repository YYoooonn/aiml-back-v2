package org.aiml.user.infra.persistence.config

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
  basePackages = ["org.aiml.user.infra"],
  entityManagerFactoryRef = "userEntityManagerFactory",
  transactionManagerRef = "userTransactionManager"
)
class UserDataSourceConfig {

  @Primary
  @Bean(name = ["userEntityManagerFactory"])
  fun userEntityManagerFactory(
    builder: EntityManagerFactoryBuilder,
    @Qualifier(BeanNames.DATASOURCE) dataSource: DataSource,
    @Qualifier(BeanNames.JPA_PROPERTIES) jpaProperties: JpaProperties
  ): LocalContainerEntityManagerFactoryBean = builder
    .dataSource(dataSource)
    .packages("org.aiml.user.infra")
    .persistenceUnit("userPersistenceUnit")
    .properties(jpaProperties.properties)
    .build()

  @Primary
  @Bean(name = ["userTransactionManager"])
  fun userTransactionManager(
    @Qualifier("userEntityManagerFactory") emf: EntityManagerFactory
  ): PlatformTransactionManager = JpaTransactionManager(emf)

}
