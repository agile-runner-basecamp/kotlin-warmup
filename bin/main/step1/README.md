# Step 1 — null safety

> 우선순위: 🔴 필수 · 소요 예상: ~30분

## 학습 목표

- `?` / `?.` / `?:` / `let` / `!!` 의 차이를 설명하고, 상황별로 골라 쓸 수 있다.
- nullable 한 줄 처리를 if-else 분기 없이 표현할 수 있다.
- nullable 컬렉션을 안전하게 매핑/필터링할 수 있다 (`filterNotNull` 등).

## Java로는 이렇게 쓰던 걸 Kotlin에선

```java
// Java
String name = (user != null && user.getName() != null) ? user.getName() : "anonymous";
if (user != null && user.getName() != null) { ... }
```

```kotlin
// Kotlin
val name = user?.name ?: "anonymous"
user?.name?.let { ... }
```

## 핵심 개념

| 연산자 | 의미 |
|---|---|
| `String?` | nullable 타입 선언 — 컴파일러가 null 가능성을 추적한다 |
| `?.` | safe call — 좌변이 null이면 전체 식이 null |
| `?:` | elvis — 좌변이 null이면 우변 반환 |
| `let { it -> ... }` | 값이 null이 아닐 때만 블록 실행 |
| `!!` | non-null assertion — **이 step의 개념 학습 외엔 사용 금지** |

## Suri-Map과의 연결점

- 서버에서 내려온 `Marker?` (없을 수도 있음)
- 위치 권한이 거부된 상태의 `Location?`
- DB에서 조회한 `User?` 의 옵션 필드 (`nickname`, `email`)

## 구현할 것

`NullSafety.kt` 의 세 함수 — 함수별 KDoc에 요구사항 + hint 있음.

- `displayName(user: User?)` — `?.` 와 `?:` 한 줄 조합
- `profileLabel(user: User?)` — nickname 우선, fallback 체인
- `upperNonNull(values: List<String?>)` — `filterNotNull` + `map`

## 함정 / 주의사항

- `if (x != null) x.foo()` 처럼 직접 분기하지 말고 `?.` / `?:` 관용구를 써야 다음 step들에서 쌓이는 패턴이 자연스럽다.
- `!!` 를 쓰면 테스트는 통과해도 리뷰에서 막힌다. 이 step에서는 의도적으로 한 번만 써본 뒤 나머지는 안전 연산자로 바꿔보자.

## 검증

```bash
./gradlew test --tests "step1*"
```

모든 케이스가 초록불이면 완료.

## AI 페어프로그래밍 팁

- 첫 함수는 직접 작성해본 뒤, AI에게 "더 관용적인 Kotlin 표현으로 바꿔달라"고 요청하면 학습 효과가 크다.
- AI에게 코드만 받지 말고 **"왜 그 연산자를 골랐는지"** 한 줄 설명을 함께 요청하자.
- 함정: AI가 `!!` 를 써서 짧게 만들 수 있다. 받은 코드에 `!!` 가 있으면 거부하고 안전 연산자 버전을 다시 요청.
