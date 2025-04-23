import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  kotlin("plugin.spring") apply false
  kotlin("plugin.jpa") apply false
  id("org.springframework.boot") apply false
  id("io.spring.dependency-management") apply false

  // for querydsl
  kotlin("plugin.allopen") version "2.0.21" apply false
  kotlin("plugin.noarg") version "2.0.21" apply false

  // lint
  id("org.jlleitschuh.gradle.ktlint") version "11.3.1"
}

ktlint {
  verbose.set(true)
  outputToConsole.set(true)
  filter {
    exclude("**/generated/**")
    include("**/kotlin/**")
  }
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(17)
  }
}

allprojects {
  group = "org.aiml"
  version = "0.0.1-SNAPSHOT"

  repositories {
    mavenCentral()
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      freeCompilerArgs = listOf("-Xjsr305=strict")
      jvmTarget = "17"
    }
  }

  tasks.withType<Test> {
    useJUnitPlatform()
  }
}

subprojects {
  apply(plugin = "org.springframework.boot")
  apply(plugin = "io.spring.dependency-management")
  apply(plugin = "org.jetbrains.kotlin.plugin.spring")
  apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
  apply(plugin = "org.jetbrains.kotlin.jvm")
  apply(plugin = "kotlin")
  apply(plugin = "kotlin-kapt")

  dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
  }

  tasks.getByName("bootJar") {
    enabled = false
  }

  tasks.getByName("jar") {
    enabled = true
  }
}
