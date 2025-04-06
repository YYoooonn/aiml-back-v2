package org.aiml.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["org.aiml"])
class ApiApplication

fun main(args: Array<String>) {
  runApplication<ApiApplication>(*args)
}
