package step5

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

/**
 * Step 5 — 코루틴 기초
 *
 * 학습 가이드 / Java 비교 / Suri-Map 연결점은 step5/README.md 참조.
 * 핵심 API: `suspend`, `delay`, `coroutineScope`, `async/await`, `runTest`.
 */

/**
 * 주어진 값을 10ms 뒤에 2배로 반환한다.
 */
suspend fun doubleAfterDelay(value: Int): Int {
    // TODO: delay(10) 후 value * 2 반환
    throw NotImplementedError()
}

/**
 * 두 값을 각각 doubleAfterDelay 로 **병렬** 처리한 뒤, 합을 반환한다.
 *
 * Hint: coroutineScope { val a = async { ... }; val b = async { ... }; a.await() + b.await() }
 *
 * 주의: 그냥 `doubleAfterDelay(x) + doubleAfterDelay(y)` 로 쓰면 **순차 실행**됩니다.
 *       반드시 async 로 병렬화해야 runTest 기준 가상 시간이 절반으로 줄어듭니다.
 */
suspend fun parallelDoubleSum(x: Int, y: Int): Int = coroutineScope {
    // TODO
    throw NotImplementedError()
}

/**
 * 리스트의 모든 값을 doubleAfterDelay 로 병렬 처리한 뒤, 결과 리스트를 반환한다.
 *
 * Hint: values.map { async { doubleAfterDelay(it) } }.map { it.await() }
 *       또는 awaitAll(*deferreds.toTypedArray()) 활용.
 */
suspend fun parallelDoubleAll(values: List<Int>): List<Int> = coroutineScope {
    // TODO
    throw NotImplementedError()
}
