package org.aiml.scene.infra.config

import org.springframework.context.annotation.*
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

import org.aiml.libs.infra.postgres.BeanNames
import org.aiml.libs.infra.postgres.PostgresqlDataSourceConfig

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = ["org.aiml.scene.infra"],
  entityManagerFactoryRef = BeanNames.ENTITY_MANAGER_FACTORY,
  transactionManagerRef = BeanNames.TRANSACTION_MANAGER
)
@Import(PostgresqlDataSourceConfig::class)
class SceneDataSourceConfig
//{
//
//  @Bean(name = ["sceneEntityManagerFactory"])
//  fun sceneEntityManagerFactory(
//    builder: EntityManagerFactoryBuilder,
//    @Qualifier(BeanNames.DATASOURCE) dataSource: DataSource,
//    @Qualifier(BeanNames.JPA_PROPERTIES) jpaProperties: JpaProperties
//  ): LocalContainerEntityManagerFactoryBean =
//    builder
//      .dataSource(dataSource)
//      .packages("org.aiml.scene.infra")
//      .persistenceUnit("scenePersistenceUnit")
//      .properties(jpaProperties.properties)
//      .build()
//
//  @Bean(name = ["sceneTransactionManager"])
//  fun sceneTransactionManager(
//    @Qualifier("sceneEntityManagerFactory") emf: EntityManagerFactory
//  ): PlatformTransactionManager = JpaTransactionManager(emf)
//
//}
