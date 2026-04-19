# Step 6 — Flow 기초

> 우선순위: 🟡 권장 · 소요 예상: ~30분

## 학습 목표

- Flow가 **cold stream of suspend values** 라는 정의를 풀어 설명할 수 있다.
- `flow { emit(...) }` 빌더와 `.map`, `.filter`, `.toList` 같은 기본 연산자를 쓸 수 있다.
- Sequence/List와 Flow의 차이를 안다 (suspend 가능 여부, hot/cold).

## Java로는 이렇게 쓰던 걸 Kotlin에선

```java
// Java RxJava
Observable<Integer> source = Observable.range(1, 10);
source.filter(i -> i % 2 == 0).map(i -> i * 2).subscribe(...);
```

```kotlin
// Kotlin Flow — RxJava와 비슷하지만 cold이며 코루틴 위에서 동작
val source: Flow<Int> = (1..10).asFlow()
source.filter { it % 2 == 0 }.map { it * 2 }.collect { println(it) }
```

## 핵심 개념

| 빌더 / 연산자 | 의미 |
|---|---|
| `flow { emit(x) }` | 빌더 — 람다 안에서 값을 수동으로 방출 |
| `listOf(...).asFlow()` | 컬렉션을 Flow로 |
| `.map { }` / `.filter { }` | 변환 / 필터 (lazy, terminal에서 실행) |
| `.take(n)` | 처음 n개만 |
| `.toList()` | terminal — 모든 값을 List로 수집 (suspend) |
| `.collect { }` | terminal — 각 값에 람다 실행 (suspend) |

### Cold vs Hot

- **Cold**: `collect` 하는 시점부터 흐른다. 매 collect마다 처음부터. — Flow가 여기 해당.
- **Hot**: 구독자 수와 무관하게 흐른다. — `SharedFlow`, `StateFlow` (이번 step 범위 외).

## Suri-Map과의 연결점

- Room DAO → `Flow<List<Waypoint>>` → Compose `collectAsState()` 로 UI 자동 갱신
- 위치 업데이트 스트림 (`callbackFlow` 로 콜백 → Flow 변환)
- 서버 SSE/WebSocket 이벤트 구독

## 구현할 것

`Flows.kt` 의 두 함수.

- `countTo(n)` — `flow { for (i in 1..n) emit(i) }`. n ≤ 0 이면 빈 flow.
- `evensDoubled(source)` — `source.filter { it % 2 == 0 }.map { it * 2 }`

## 함정 / 주의사항

- Flow는 **terminal operator** (`.collect`, `.toList`, `.first` 등)를 호출해야 실제로 흐른다. `.map` 만 쓰고 끝내면 아무 일도 일어나지 않는다.
- Flow 함수를 `suspend` 로 만들 필요는 없다 — Flow 빌더 자체가 cold라 호출 시점엔 아무것도 안 함. terminal에서만 suspend 필요.
- 테스트에서는 `runTest { flow.toList() }` 패턴으로 검증.

## 검증

```bash
./gradlew test --tests "step6*"
```

## AI 페어프로그래밍 팁

- AI에게 "이 Flow를 List로 바꿨을 때 동작이 어떻게 다른지" 물어보면 cold/lazy 개념이 명확해진다.
- 함정: AI가 `Flow.collect { mutableList.add(it) }` 같은 mutable state 패턴을 제안할 수 있다. **`.toList()` 한 줄이면 충분**한 자리에 쓰지 말 것.
