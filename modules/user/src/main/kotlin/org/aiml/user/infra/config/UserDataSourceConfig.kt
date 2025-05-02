package org.aiml.user.infra.config

import org.aiml.libs.infra.mysql.BeanNames
import org.aiml.libs.infra.mysql.MysqlSourceConfig
import org.springframework.context.annotation.*
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = ["org.aiml.user.infra"],
  entityManagerFactoryRef = BeanNames.ENTITY_MANAGER_FACTORY,
  transactionManagerRef = BeanNames.TRANSACTION_MANAGER
)
@Import(MysqlSourceConfig::class)
class UserDataSourceConfig
//{
//
//  // @Primary
//  @Bean(name = ["userEntityManagerFactory"])
//  fun userEntityManagerFactory(
//    builder: EntityManagerFactoryBuilder,
//    @Qualifier(BeanNames.DATASOURCE) dataSource: DataSource,
//    @Qualifier(BeanNames.JPA_PROPERTIES) jpaProperties: JpaProperties
//  ): LocalContainerEntityManagerFactoryBean = builder
//    .dataSource(dataSource)
//    .packages("org.aiml.user.infra")
//    .persistenceUnit("userPersistenceUnit")
//    .properties(jpaProperties.properties)
//    .build()
//
//  // @Primary
//  @Bean(name = ["userTransactionManager"])
//  fun userTransactionManager(
//    @Qualifier("userEntityManagerFactory") emf: EntityManagerFactory
//  ): PlatformTransactionManager = JpaTransactionManager(emf)
//
//}
