각 모듈들은 독립성을 가지기에, 타 모듈의 서비스를 직접 불러서 사용하는 일은 없어야함.

타 모듈을 사용하기 위해선 port/inbound 의 usecase를 사용하고,

외부 사용하는 네이밍은 usecase로 통일

혹은, orchestration을 위한 facade를 가져다가 사용

facade는 usecase들을 묶어서 사용하는 orchestration 용도로 사용하자.

추후에 복잡해진다면 usecase를 직접 사용하는게 아닌, application 서비스 차원으로 만들어주자.

```bash
# core module
controller
    ↓
application layer (facade)
    ↓
port inbound (UseCase interface)
    ↓
[ DOMAIN ]
    ↓
port outbound (Persistence interface)
    ↓
adapter (DB, external API 등)

# orchestration
controller
    ↓
facade (Orchestration Service) : @Transactional
    ↓ ↓ ↓
port inbound (usecase 1)
port inbound (usecase 2)
port inbound (usecase 3)

```

```bash
# format
./gradlew ktlintFormat

```
