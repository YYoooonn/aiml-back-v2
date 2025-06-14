dependencies {
  implementation(project(":libs:common"))

  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-security")

  // for redis
  implementation("org.springframework.boot:spring-boot-starter-data-redis")
  implementation("org.springframework.boot:spring-boot-starter-validation")

  implementation("software.amazon.awssdk:s3:2.31.53")
}
