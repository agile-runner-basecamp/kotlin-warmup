# Step 5 — 코루틴 기초

> 우선순위: 🔴 필수 · 소요 예상: ~50분

## 학습 목표

- `suspend` 함수가 일반 함수와 무엇이 다른지 한 문장으로 설명할 수 있다 ("스레드를 점유하지 않고 일시 중단된다").
- `coroutineScope { }` 안에서 `async { }.await()` 로 병렬 작업을 합칠 수 있다.
- 순차 실행과 병렬 실행의 차이를 `runTest` 의 가상 시간으로 검증할 수 있다.

## Java로는 이렇게 쓰던 걸 Kotlin에선

```java
// Java: Thread + ExecutorService + CompletableFuture
CompletableFuture<Integer> a = CompletableFuture.supplyAsync(() -> doubleValue(x));
CompletableFuture<Integer> b = CompletableFuture.supplyAsync(() -> doubleValue(y));
return a.get() + b.get();
```

```kotlin
// Kotlin: 동기 코드처럼 읽히지만 실제로는 비동기
suspend fun parallelDoubleSum(x: Int, y: Int): Int = coroutineScope {
    val a = async { doubleAfterDelay(x) }
    val b = async { doubleAfterDelay(y) }
    a.await() + b.await()
}
```

## 핵심 개념

| API | 의미 |
|---|---|
| `suspend fun` | 일시 중단 가능한 함수 — 다른 suspend 함수 또는 코루틴 빌더 안에서만 호출 가능 |
| `delay(ms)` | 코루틴 일시 중단 (스레드 sleep 아님) |
| `coroutineScope { }` | 자식 코루틴이 모두 끝날 때까지 기다리는 구조화된 스코프 |
| `async { }` | 결과를 가진 병렬 작업 — `Deferred<T>` 반환 |
| `.await()` | Deferred 결과 대기 |
| `awaitAll(...)` | 여러 Deferred를 한번에 대기 |
| `runTest { }` | 가상 시간으로 코루틴 테스트 (kotlinx-coroutines-test) |

### 가장 큰 멘탈 모델 차이

- 코루틴은 **스코프 안에서** 실행된다 (구조화된 동시성). 부모 스코프가 취소되면 자식도 모두 취소.
- 호출 지점은 동기처럼 읽히지만 (콜백 지옥 없음), 실제로는 일시 중단/재개됨.

## Suri-Map과의 연결점

- 위치 요청 (`fusedLocationProviderClient.lastLocation.await()`)
- Room DAO 쿼리 (`@Query suspend fun ...`)
- 서버 호출 (Retrofit + suspend)
- → 거의 모든 I/O가 suspend.

## 구현할 것

`Coroutines.kt` 의 세 함수.

- `doubleAfterDelay(value)` — `delay(10)` 후 `value * 2`
- `parallelDoubleSum(x, y)` — `async` 두 개 + `await` 합산
- `parallelDoubleAll(values)` — `map { async { ... } }` 후 `awaitAll` 또는 각 `await`

## 함정 / 주의사항

- `doubleAfterDelay(x) + doubleAfterDelay(y)` 라고 쓰면 **순차 실행**된다. 반드시 `async` 로 감싸야 병렬.
- 테스트에서 `@OptIn(ExperimentalCoroutinesApi::class)` 가 붙어있는 이유: `runTest` 의 `currentTime` 가상 시간 API가 아직 실험적이기 때문. 이 step에서는 그대로 두면 됨.
- `runTest` 안에서는 실제 시간이 흐르지 않는다 — `delay(10)` 이 즉시 진행되며 `currentTime` 만 증가. 병렬/순차 차이는 `currentTime` 으로 검증한다.

## 검증

```bash
./gradlew test --tests "step5*"
```

병렬성 테스트(`executesInParallel`, `executesAllInParallel`) 가 통과하지 않으면 `async` 가 빠진 것이다.

## AI 페어프로그래밍 팁

- AI에게 "병렬 버전과 순차 버전 둘 다 보여달라"고 요청해서 가상 시간 차이를 비교해보자.
- 함정: AI가 `runBlocking` 을 쓰자고 제안할 수 있다. 본 step의 함수는 **suspend 함수 자체** 이므로 호출 지점에 `runBlocking` 이 필요 없다 (`runTest` 가 이미 있다).
