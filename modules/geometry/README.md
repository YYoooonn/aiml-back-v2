### Geometry Domain 구조

```bash
Geometry
├── vertices: List<Vertex> ← 실제 좌표 데이터를 가진 정점
└── faces: List<Face>
     └── vertices: List<FaceVertex> ← vertex의 index만을 참조
```

### Geometry Entity 구조

```bash
Geometry (geometry)
├── id : UUID (PK)
└── name : String?

Vertex (vertex)
├── geometry_id : UUID (PK, FK → Geometry.id)
├── index : Int (PK)
├── x : Float
├── y : Float
└── z : Float

Face (face)
├── id : Long (PK)
├── geometry_id : UUID (FK → Geometry.id)
└── index : Int (optional, for ordering)

FaceVertex (face_vertex)
├── face_id : Long (PK, FK → Face.id)
├── vertex_ref_index : Int (index of vertex in Vertex table)
└── vertex_index_order : Int (순서 지정용, 예: 삼각형이라면 0~2)
```

### ERD

```
+-------------------+
|   Geometry        |
+-------------------+
|PK| id : UUID      |
|   name : String?  |
+-------------------+

           1
           |
           | has many
           v

+----------------------------+
|   Vertex                  |
+----------------------------+
|PK| geometry_id : UUID      |
|PK| index : Int             |
|   x : Float                |
|   y : Float                |
|   z : Float                |
+----------------------------+
|FK geometry_id → Geometry.id|

           1
           |
           | has many
           v

+----------------------------+
|   Face                    |
+----------------------------+
|PK| id : Long              |
|   geometry_id : UUID      |
|   index : Int?            |
+----------------------------+
|FK geometry_id → Geometry.id|

           1
           |
           | has many
           v

+------------------------------------+
|   FaceVertex                      |
+------------------------------------+
|PK| face_id : Long                 |
|PK| vertex_index_order : Int       |
|   vertex_ref_index : Int          |
+------------------------------------+
|FK face_id → Face.id               |
|FK vertex_ref_index + geometry_id → Vertex.index + geometry_id |
```

### Geometry 폴더 구조

```bash
geometry
├── application ← 실제 외부에서 바로 사용 application level
│   └── GeometryFacade.kt
├── domain ← 절대 외부 주입 받지 않음. 순수한 비지니스 로직만
│   ├── command ← 도메인 생성, 업데이트 등 관련 요청 양식. 외부 request -> command로 매핑해서 domain 접근해야함
│   ├── model ← 도메인 모델
│   ├── port ← domain 외부에서 domain을 사용하기 위한 interface, 직접 model이나 서비스에 접근 하지 못함.
│   │   ├── inbound ← 외부 모듈에서 사용할 usecase, 직접 application 차원에서 사용할 땐 GeometryFacade 이용
│   │   └── outbound ← db 연결 시, 데이터 가져오는 용도
│   └── service ← 실제 도메인 비지니스 로직, port inbound의 실제 구현체
├── infra ← db, port outbound 의 연결 인프라
│   └── persistence ← db, 영속성 계층
│       ├── adapter ← outbound port 의 실제 구현체, entity 가지고 작업하고, 넘길 땐 domain으로 mapping 해서
│       ├── config ← db 설정값, querydsl config & data source config
│       ├── entity ← 실제 db에 저장될 양식
│       └── repository ← jpa, 실제 db와 연결된 orm
```
