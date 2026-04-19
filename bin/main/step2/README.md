# Step 2 — data class & 구조분해

> 우선순위: 🔴 필수 · 소요 예상: ~25분

## 학습 목표

- `data class` 한 줄 선언으로 Java의 boilerplate(equals/hashCode/toString/getter)가 어떻게 사라지는지 설명할 수 있다.
- `copy(...)` 로 불변 객체의 일부 필드만 바꾸어 새 인스턴스를 만들 수 있다.
- 구조분해(`val (a, b, c) = obj`) 로 필드를 한 번에 꺼낼 수 있다.

## Java로는 이렇게 쓰던 걸 Kotlin에선

```java
// Java (Lombok 없이): 30줄짜리 POJO
public final class Waypoint {
    private final double lat, lng;
    private final long timestamp;
    // 생성자, getter, equals, hashCode, toString ...
}
```

```kotlin
// Kotlin: 한 줄
data class Waypoint(val lat: Double, val lng: Double, val timestamp: Long)
```

## 핵심 개념

| 키워드 / 패턴 | 의미 |
|---|---|
| `val` | 불변 필드 (Java `final` 과 동일) |
| `data class` | equals/hashCode/toString/copy/componentN 자동 생성 |
| `obj.copy(field = newValue)` | 일부 필드만 교체한 새 인스턴스 |
| `val (a, b, c) = obj` | 구조분해 — 내부적으로 `componentN()` 호출 |
| `"...$x..." / "${expr}"` | 문자열 템플릿 |

## Suri-Map과의 연결점

- `Waypoint(lat, lng, timestamp)`, `Marker(...)`, `IncidentMeta(...)` 같은 도메인 값 객체 전부 data class.
- 위치 트래킹에서 timestamp만 갱신할 때 `copy(timestamp = now)` 패턴.

## 구현할 것

`DataClass.kt` 의 세 함수.

- `durationBetween(a, b)` — `kotlin.math.abs` 활용한 절댓값
- `withTimestamp(waypoint, newTimestamp)` — `copy()` 한 줄
- `formatWaypoint(waypoint)` — 구조분해 + 문자열 템플릿

## 함정 / 주의사항

- `var` 로 선언하지 말 것 — data class는 불변 값 객체로 쓰는 게 관용. mutability가 필요하면 `copy()` 로 새 인스턴스를 만든다.
- `formatWaypoint` 에서 `waypoint.lat + "," + waypoint.lng` 같은 접근자 체인을 쓰지 말고 구조분해를 연습해보자.

## 검증

```bash
./gradlew test --tests "step2*"
```

## AI 페어프로그래밍 팁

- "data class를 안 쓰고 구현해달라"고 요청해서 차이를 직접 비교해보면 가치가 체감된다.
- 함정: AI가 `Waypoint` 외에 별도 헬퍼 클래스를 만드는 경우가 있다. 본 step의 핵심은 **새 클래스를 만들지 않고** 주어진 data class만으로 해결하는 것.
