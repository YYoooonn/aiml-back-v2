# aiml-back

> _git subtree for [aiml-mono](https://github.com/YYoooonn/aiml-mono)_ \
> \*back repository **`WIP`\***

[**A Collaborative Workspace for 3D** - edit and archive 3D projects](http://ec2-3-39-43-240.ap-northeast-2.compute.amazonaws.com/) \
온라인으로 동료들과 함께 3D 작업하고, 작업물들을 아카이빙 해보세요!

## _about_

<img src="https://img.shields.io/badge/kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white"> <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/postgresql-4169E1?style=for-the-badge&logo=postgresql&logoColor=white"> <img src="https://img.shields.io/badge/redis-FF4438?style=for-the-badge&logo=redis&logoColor=white">

<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white"> <img src="https://img.shields.io/badge/githubactions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white">


### _architecture_

#### backend system architecture

<center>
<img width="1200" alt="Screenshot 2025-06-08 at 4 21 02 PM" src="https://github.com/user-attachments/assets/2230fb21-53ef-4124-b6f6-ab198abeb0db" />
</center>


## _how?_

```bash
# at root

# set .env file and
docker compose up --build
```

_and you will be able to access through `localhost:8080`_


## _deploy_

`aws ec2`에 free-tier instance를 사용하여 테스트 서버로 활용

free-tier의 성능 한계로 인해 빌드 과정에서 너무 오래 걸리고 빌드 도중에 멈추는 일이 빈번하게 발생하여\
직접 image를 빌드하고, 서버에서 `docker-hub`를 통하여 pull 받는 방식으로 변경

```bash
# CI : github actions - ./.github/workflows/docker-build-push.yml

# manual deployment
# on ec2 instance

# 1. copy necessary files
# docker-compose.prod.yml as docker-compose.yml
# .env

# 2. pull images
docker compose pull

# 3. run docker images with detached
docker compose up -d
```

## _features_

### _hexagonal architecture_


각 모듈들은 독립성을 가지기에, 타 모듈의 서비스를 직접 불러서 사용하는 일은 없어야함.

타 모듈을 사용하기 위해선 port/inbound의 Service를 사용하고,

CQRS를 위한 CommandService, QueryService로 구분.

실제 구현체는 ServiceImp 형태로 통일

혹은, orchestration을 위한 facade를 가져다가 사용

facade는 모듈을 여러개 묶어서 사용하는 orchestration 용도로 사용

```bash
# module
adapter : controller (api)
    ↓
port inbound (Service interface)
    ↓
[ DOMAIN ]
    ↓
port outbound (Persistence interface)
    ↓
adapter (DB, external API 등)

# orchestration
adapter : controller
    ↓
facade (Orchestration Service) : @Transactional
    ↓ ↓ ↓
port inbound (service 1)
port inbound (service 2)
port inbound (service 3)

```

### _multi-database_

`mysql` 기존 db : user, porject

`postgresql` 3차원 객체 정보는 추후 postgis 사용을 위한 postgresql 사용 : scene, object3d (mesh, group)

`redis` 인증 정보 저장 : JWT token, logout blacklist


libs/infra 에 Bean 정의, 각 모듈마다 필요한 Bean 주입하여 사용

```bash
# Bean, DataSourceConfig
...
├── libs
│   ├── ...
│   └── infra
│       └── src/main/org/aiml/libs/infra
│           ├── mysql : MysqlDatasourceConfig.kt
│           ├── postgres : PostgresqlDatasourceConfig.kt
│           ├── redis : RedisDatasourceConfig.kt
│           └── ...
...
```

### _database design_

<center>
<img width="1200" alt="Screenshot 2025-06-08 at 4 23 03 PM" src="https://github.com/user-attachments/assets/c15da2a2-44e9-4d5f-8591-6c36901fa00b" />
</center>

각 객체들의 의존성 최소화를 위한 간접 참조, 모듈간의 독립성 유지

```bash
project-user -> project
             -> user

scene        -> object3d
             -> auth) project-user

object3d     -> auth) project-user
```

### _folder structure_

```bash
# module structrue
module
├── src/main/kotlin/org/aiml/module 
│   ├── application
│   │   ├── facade
│   │   ├── dto
│   │   ├── command
│   │   └── query
│   ├── domain
│   │   ├── port
│   │   │   ├── inbound
│   │   │   └── outbound
│   │   └── model
│   └── infra
│       ├── adapter
│       ├── persistence
│       └── config
└── build.gradle.kts
```


```bash
# project structure
aiml-back
├── apps
│   ├── api
│   │   ├── src/main/kotlin/org/aiml/api
│   │   │   ├── common
│   │   │   ├── controller # REST controller
│   │   │   ├── dto
│   │   │   ├── exception
│   │   │   ├── features/auth
│   │   │   ├── security # JWT filter
│   │   │   └── ApiApplication.kt # main application
│   │   └── build.gradle.kts
│   └── batch  
│       └── ...
├── modules
│   ├── user
│   │   └── ...
│   ├── project
│   │   └── ...
│   ├── project-user
│   │   └── ...
│   ├── scene
│   │   └── ...
│   └── object3d
│       └── src/main/kotlin/org/aiml/object3d
│           ├── base
│           │   └── ...
│           ├── group
│           │   └── ...
│           ├── mesh
│           │   └── ...
│           └── ...
├── libs
│   ├── common
│   │   └── src/main/org/aiml/libs/common
│   │       ├── entity
│   │       ├── file
│   │       ├── logger
│   │       └── security
│   └── infra
│       └── src/main/org/aiml/libs/infra
│           ├── mysql
│           ├── postgres
│           ├── redis
│           └── s3
├── config
├── docker-compose.yml
├── Dockerfile
├── build.gradle.kts
├── gradlew
├── gradle.properties
└── settings.gradle.kts
```
