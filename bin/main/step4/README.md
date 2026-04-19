# Step 4 — 확장 함수 & 스코프 함수

> 우선순위: 🔴 필수 · 소요 예상: ~40분

## 학습 목표

- `let` / `run` / `apply` / `also` / `with` 의 차이를 한 문장으로 설명할 수 있다 (수신자, 반환값 기준).
- 확장 함수를 정의하고, 멤버 함수와의 차이를 안다.
- nullable 체인에 스코프 함수를 결합해 if-else 없이 변환 파이프라인을 짤 수 있다.

## Java로는 이렇게 쓰던 걸 Kotlin에선

```java
// Java: 객체 설정 시 변수 선언 + 여러 줄
Point p = new Point();
p.setX(10); p.setY(20); p.setLabel("origin");
return p;
```

```kotlin
// Kotlin: apply 로 한 표현식
return Point().apply {
    x = 10; y = 20; label = "origin"
}
```

## 핵심 개념

### 스코프 함수 비교표

| 함수 | 수신자 참조 | 반환값 | 주 용도 |
|---|---|---|---|
| `let` | `it` | 블록 결과 | nullable 체이닝, 타입 변환 |
| `run` | `this` | 블록 결과 | this 컨텍스트에서 변환 |
| `apply` | `this` | 객체 자신 | 객체 **설정** (빌더 대체) |
| `also` | `it` | 객체 자신 | **부수 효과** (로깅, 디버깅) |
| `with(obj) { }` | `this` | 블록 결과 | 확장이 아닌 일반 함수 |

### 확장 함수

```kotlin
fun String.shout(): String = this.uppercase() + "!"
"hello".shout()  // "HELLO!"
```

> 컴파일 시 정적 메서드로 변환됨. 실제로 String 클래스를 수정하지 않는다.

## Suri-Map과의 연결점

- Compose UI: `Modifier.padding(8.dp).background(...)` 체인 — 확장 함수 + 빌더 패턴
- 위치 객체 변환: `location?.let { service.send(it) }`
- 디버그 로깅: `result.also { Log.d("API", it.toString()) }`
- ViewModel 초기 설정: `MutableStateFlow(InitState).apply { ... }`

## 구현할 것

`Scopes.kt` 의 세 함수.

- `buildPoint()` — `Point().apply { ... }` 로 설정하고 반환
- `normalize(raw: String?)` — `?.trim()?.takeIf { ... }?.let { ... }` 체인
- `String.emphasize()` — 확장 함수, 끝에 `!` 추가 (이미 있으면 그대로)

## 함정 / 주의사항

- `apply` 와 `also` 는 **객체 자신을 반환** — 마지막 줄 결과를 반환하지 않는다. 헷갈릴 때는 "내가 객체 자체를 원하는가, 변환 결과를 원하는가?" 로 판단.
- `it` 과 `this` 가 섞이면 가독성이 급락한다. 중첩 스코프 함수 쓸 때는 명시적 람다 파라미터 (`obj.let { point -> ... }`) 권장.
- 확장 함수는 **import** 가 필요할 수 있다 — IDE 자동완성 활용.

## 검증

```bash
./gradlew test --tests "step4*"
```

## AI 페어프로그래밍 팁

- "이 코드를 `let` / `run` / `apply` / `also` 4가지 버전으로 다 보여달라"고 요청하면 차이가 한눈에 들어온다.
- 함정: AI가 모든 곳에 스코프 함수를 남발하기 쉽다. **단순 대입(`p.x = 10`)이 더 명확하면 그게 맞다** — 스코프 함수는 체이닝/변환 파이프라인일 때 가치가 있다.
