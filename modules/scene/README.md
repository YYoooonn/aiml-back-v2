### 논리 구조, 실제 값 보다는 사용자 편의를 위한 tree hierarchy

```bash
# 실제 렌더 시 필요한 정보들
modules:geometry - Mesh(transform), Material, Geometry(vertex, face)

# 사용자 편의를 위한 논리 계층
modules:scene - Scene, Group, Light, Camera, ...

SCENE
├── CAMERA
├── LIGHTS
├── GROUP
│   ├── mesh
│   ├── mesh
│   └── mesh
...
```
