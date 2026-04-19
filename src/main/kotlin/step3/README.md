# Step 3 — sealed class & when (exhaustive)

> 우선순위: 🔴 필수 · 소요 예상: ~30분

## 학습 목표

- `enum` 과 `sealed class` 의 차이를 설명할 수 있다 (특히 "각 상태가 다른 데이터를 가질 수 있는가").
- `when` 식이 **exhaustive** 하다는 의미를 이해하고, sealed 계층에서 `else` 가 왜 불필요한지 안다.
- `filterIsInstance<T>()` 로 타입별 필터링을 할 수 있다.

## Java로는 이렇게 쓰던 걸 Kotlin에선

```java
// Java enum + switch — 각 상태가 동일한 형태여야 함
enum SyncStatus { PENDING, SYNCING, SYNCED, FAILED }
switch (status) { case SYNCED: ...; default: ...; }
```

```kotlin
// Kotlin sealed class — 각 subtype이 서로 다른 데이터를 가질 수 있음
sealed class SyncStatus {
    object Pending : SyncStatus()
    data class Synced(val syncedAt: Long) : SyncStatus()
    data class Failed(val reason: String) : SyncStatus()
}
when (status) {
    is SyncStatus.Synced -> "완료 (${status.syncedAt})"
    is SyncStatus.Failed -> "실패: ${status.reason}"
    // ...컴파일러가 빠진 브랜치를 잡아준다
}
```

## 핵심 개념

| 키워드 / 패턴 | 의미 |
|---|---|
| `sealed class` | 같은 파일 안에 정의된 subtype만 허용하는 닫힌 계층 |
| `object Foo : Sealed()` | 데이터 없는 singleton subtype |
| `data class Foo(...) : Sealed()` | 데이터 있는 subtype |
| `when (x) { is Foo -> ... }` | 타입 분기 + smart cast (`x` 가 자동으로 Foo로 좁혀짐) |
| `filterIsInstance<Foo>()` | List에서 특정 타입만 골라내기 |

## Suri-Map과의 연결점

- `SyncStatus.Pending / Syncing / Synced / Failed` — 각 상태가 다른 메타데이터(`syncedAt`, `reason`)를 가짐
- `MarkerType.Clue / Obstacle / Support` — UI에서 타입별로 다른 아이콘/색상
- `NetworkResult.Success<T>(data) / Error(throwable)` — 응답 처리 ADT

## 구현할 것

`SyncStatus.kt` 의 세 함수.

- `label(status)` — 상태별 한국어 라벨 (`Synced` 는 `syncedAt` 포함, `Failed` 는 `reason` 포함)
- `shouldRetry(status)` — Pending/Failed → true, 그 외 false
- `failureReasons(statuses)` — `filterIsInstance<SyncStatus.Failed>()` 활용

## 함정 / 주의사항

- `when` 을 statement(`when { ... }`)가 아닌 **expression** (`val x = when { ... }`) 으로 쓰면 exhaustive 검사가 강제된다. 가능하면 expression으로 쓸 것.
- `is SyncStatus.Synced -> status.syncedAt` 처럼 smart cast가 자동으로 적용된다 — 명시적 캐스팅 불필요.
- subtype에 `else ->` 를 넣지 말 것. 새 상태가 추가됐을 때 컴파일러 도움을 잃는다.

## 검증

```bash
./gradlew test --tests "step3*"
```

## AI 페어프로그래밍 팁

- AI에게 "새 상태 `Cancelled` 를 추가하고 어떤 함수가 깨지는지 보자"고 요청해서 exhaustive 검사의 가치를 체감해보자.
- 함정: AI가 `else -> "unknown"` 같은 안전망을 자동으로 넣을 수 있다. sealed class 학습 의도와 반대이므로 거부.
