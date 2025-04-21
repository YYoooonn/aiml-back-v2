dependencies {
  implementation(project(":libs:common"))
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter")
  runtimeOnly("org.postgresql:postgresql")
}
