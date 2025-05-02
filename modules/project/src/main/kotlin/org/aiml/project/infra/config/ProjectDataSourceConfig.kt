package org.aiml.project.infra.config

import org.aiml.libs.infra.mysql.BeanNames
import org.aiml.libs.infra.mysql.MysqlSourceConfig
import org.springframework.context.annotation.*
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = ["org.aiml.project.infra"],
  entityManagerFactoryRef = BeanNames.ENTITY_MANAGER_FACTORY,
  transactionManagerRef = BeanNames.TRANSACTION_MANAGER
)
@Import(MysqlSourceConfig::class)
class ProjectDataSourceConfig

