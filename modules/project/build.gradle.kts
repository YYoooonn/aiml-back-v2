dependencies {
  implementation(project(":libs:common"))
  implementation(project(":libs:infra"))

  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter")
  runtimeOnly("com.mysql:mysql-connector-j")
//  implementation("org.mapstruct:mapstruct:1.5.5.Final")
//  kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")
}
