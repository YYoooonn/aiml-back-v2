package org.aiml.project_user.infra.config

import org.aiml.libs.infra.mysql.BeanNames
import org.aiml.libs.infra.mysql.MysqlSourceConfig
import org.springframework.context.annotation.*
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = ["org.aiml.project_user.infra"],
  entityManagerFactoryRef = BeanNames.ENTITY_MANAGER_FACTORY,
  transactionManagerRef = BeanNames.TRANSACTION_MANAGER
)
@Import(MysqlSourceConfig::class)
class ProjectUserDataSourceConfig
//{
//
//  @Bean(name = ["projectUserEntityManagerFactory"])
//  fun userEntityManagerFactory(
//    builder: EntityManagerFactoryBuilder,
//    @Qualifier(BeanNames.DATASOURCE) dataSource: DataSource,
//    @Qualifier(BeanNames.JPA_PROPERTIES) jpaProperties: JpaProperties
//  ): LocalContainerEntityManagerFactoryBean =
//    builder
//      .dataSource(dataSource)
//      .packages("org.aiml.project_user.infra")
//      .persistenceUnit("projectUserPersistenceUnit")
//      .properties(jpaProperties.properties)
//      .build()
//
//  @Bean(name = ["projectUserTransactionManager"])
//  fun userTransactionManager(
//    @Qualifier("projectUserEntityManagerFactory") emf: EntityManagerFactory
//  ): PlatformTransactionManager = JpaTransactionManager(emf)
//
//}
