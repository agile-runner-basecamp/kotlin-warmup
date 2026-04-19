# 🟪 Mission 0: Kotlin 워밍업

> **대상:** Java + JUnit 5 + Spring Boot 경험은 있으나 Kotlin은 처음인 팀원 (Suri-Map Android 온보딩)
>
> **목표:** Android 미션(Compose + Room + Flow)에서 필요한 **Kotlin 고유 문법**을 학습합니다.
>
> Java와 동일한 개념(기본 타입, 함수, 클래스, 컬렉션)은 별도 Step 없이 각 Step 예제에 녹였습니다.

## 📌 환경 세팅

- JDK 17 이상
- IntelliJ IDEA Community 또는 Android Studio (Kotlin 플러그인 기본 포함)

```bash
./gradlew test                    # 전체 테스트 실행
./gradlew test --tests "step1*"   # 특정 step만 실행
./gradlew test -t                 # 파일 변경 시 자동 재실행
./gradlew test --info             # 실패 원인 자세히 보기
./gradlew build                   # 컴파일 + 테스트 + 모든 검사
```

> Android Studio는 여기선 **불필요**합니다. 순수 JVM + JUnit 5 환경입니다.

## 📖 Step 구성

각 Step 폴더에 **자체 README**가 있습니다. 학습 목표, Java 비교, 함정, 검증 방법까지 한 페이지에 정리돼 있으니 작업 전에 먼저 읽으세요.

| Step | 주제 | 핵심 | 우선순위 | 가이드 |
|------|------|------|:---:|---|
| Step 1 | null safety | `?`, `?.`, `?:`, `let` | 🔴 필수 | [step1/README](src/main/kotlin/step1/README.md) |
| Step 2 | data class & 구조분해 | `equals/hashCode/copy` 자동 | 🔴 필수 | [step2/README](src/main/kotlin/step2/README.md) |
| Step 3 | sealed class & when | exhaustive 검사, ADT | 🔴 필수 | [step3/README](src/main/kotlin/step3/README.md) |
| Step 4 | 확장 함수 & 스코프 함수 | `apply/let/run/also/with` | 🔴 필수 | [step4/README](src/main/kotlin/step4/README.md) |
| Step 5 | 코루틴 기초 | `suspend`, `async/await` | 🔴 필수 | [step5/README](src/main/kotlin/step5/README.md) |
| Step 6 | Flow 기초 | cold stream, `emit`/`collect` | 🟡 권장 | [step6/README](src/main/kotlin/step6/README.md) |

> Suri-Map에서 각 문법을 만나는 지점은 **각 Step README의 "Suri-Map과의 연결점"** 섹션에서 확인하세요.

## 🧪 테스트 작성 규칙

Spring Boot에서 써오던 스타일을 그대로 유지합니다.

- **`@DisplayName("~ 경우, ~ 한다.")`** — 한국어 문장, 주어·조건·결과 포함
- **메서드명은 영어 camelCase 또는 snake_case**
- **`// given / // when / // then`** 주석으로 BDD 3단 구성
- **AssertJ `assertThat()`** 사용 — `assertEquals` 대신

### 예시

```kotlin
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DisplayNameTest {

    @DisplayName("user가 null인 경우, anonymous 를 반환한다.")
    @Test
    fun returnsAnonymousWhenUserIsNull() {
        // given
        val user: User? = null

        // when
        val result = displayName(user)

        // then
        assertThat(result).isEqualTo("anonymous")
    }
}
```

### 왜 `kotlin.test` 대신 AssertJ?

Spring Boot에서 쓰던 Fluent assertion 을 그대로 가져가기 위함입니다. `assertThat(list).containsExactly(...)`, `assertThat(throwable).hasMessage(...)` 같은 표현이 Kotlin에서도 그대로 동작합니다.

## 🤖 AI 페어프로그래밍 사용 가이드

이 워밍업은 **AI Agent와 페어로 진행**하는 것을 전제로 설계됐습니다. 각 Step README는 AI에게 컨텍스트로 던지기 좋은 형태로 정리돼 있습니다.

**권장 흐름:**

1. 작업 전 해당 Step README를 직접 한 번 읽고 학습 목표를 머릿속에 담는다.
2. AI에게 폴더 단위로 컨텍스트 전달 (`@src/main/kotlin/step3/`).
3. **테스트를 먼저 같이 읽어달라**고 요청 → 의도 정렬.
4. **구현 전에 어떤 Kotlin 관용구를 쓸지 한 줄로 설명**해달라고 요청 → 학습 효과.
5. 받은 코드는 그대로 붙여넣지 말고, 각 Step README의 "함정 / 주의사항" 항목과 대조하며 검토.
6. 빨간불 → 초록불을 직접 확인 (`./gradlew test --tests "stepN*"`).

**AI에게 요청할 때 함께 명시할 것:**

- "이 워밍업의 규칙을 따라달라" — `!!`, `Any` 사용 금지, AssertJ + DisplayName + BDD
- "왜 그 표현을 골랐는지" 한 줄 설명
- "다른 관용구 1~2개 대안"도 함께 제시

**자주 빠지는 함정 (Step별 README에도 정리됨):**

- `!!` 로 짧게 만들기 → 거부, 안전 연산자 버전 재요청
- sealed class에 `else ->` 자동 추가 → 거부 (exhaustive 학습 의도 훼손)
- 코루틴에서 `runBlocking` 남발 → 본 step 함수는 이미 `suspend` 이므로 불필요

## 📝 제출 방법

1. `feature/[티켓번호]-kotlin-warmup-이름` 브랜치 생성
2. 각 Step 폴더에서 작업

```
src/
├── main/kotlin/
│   ├── step1/  ← README.md + NullSafety.kt
│   ├── step2/  ← README.md + DataClass.kt
│   └── ... (step3~6)
└── test/kotlin/
    ├── step1/NullSafetyTest.kt
    └── ... (step2~6)
```

3. 모든 Step의 테스트가 초록불이 되면 **PR 올리기**
   - PR 제목 예시: `이승환 - Kotlin 워밍업 미션 제출합니다. (티켓번호)`

## 🚫 규칙

- `!!` (non-null assertion) 사용 금지 — Step 1 예제 외에는 쓸 일이 없습니다.
- `Any` 타입 사용 금지.
- 각 Step의 `// TODO` 주석 위 요구사항을 먼저 읽고 시작하세요.
- 테스트는 위 **테스트 작성 규칙** 을 따릅니다. (`@DisplayName` 필수, AssertJ 사용)
- AI가 생성한 코드라도 **본인이 한 줄씩 이해한 뒤** 커밋합니다.

## 💡 Java 개발자를 위한 팁

각 Step의 README 상단에 **"Java로는 이렇게 쓰던 걸 Kotlin에선 이렇게"** 비교 블록이 있습니다. Kotlin은 Java와 같은 개념을 더 짧게 쓰는 경우가 많으니, **Java 습관을 먼저 의심**하며 읽으세요.

## ✅ PR 리뷰 체크리스트

리뷰어/AI가 동일 기준으로 점검할 수 있도록:

- [ ] 모든 Step 테스트 초록불
- [ ] `!!`, `Any` 사용 없음
- [ ] 테스트가 `@DisplayName` + given/when/then + AssertJ 컨벤션 준수
- [ ] sealed class에 불필요한 `else ->` 없음 (Step 3)
- [ ] 코루틴 step에서 병렬 케이스가 실제로 가상 시간 단축 (Step 5)
- [ ] 각 함수의 KDoc 요구사항을 그대로 만족 (스펙 변경 없음)
