package org.aiml.object3d.base.infra.config

import org.springframework.context.annotation.*
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

import org.aiml.libs.infra.postgres.BeanNames
import org.aiml.libs.infra.postgres.PostgresqlDataSourceConfig

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = ["org.aiml.object3d"],
  entityManagerFactoryRef = BeanNames.ENTITY_MANAGER_FACTORY,
  transactionManagerRef = BeanNames.TRANSACTION_MANAGER
)
@Import(PostgresqlDataSourceConfig::class)
class Object3DDataSourceConfig
