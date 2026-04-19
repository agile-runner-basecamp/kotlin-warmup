package step6

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Step 6 — Flow 기초
 *
 * 학습 가이드 / RxJava 비교 / Suri-Map 연결점은 step6/README.md 참조.
 * 핵심: cold stream, `flow { emit(x) }`, `.map`, `.filter`, `.toList`, `.collect`.
 */

/**
 * 1부터 n까지의 정수를 순서대로 방출하는 Flow를 반환한다.
 * n <= 0 이면 아무 값도 방출하지 않는다.
 *
 * Hint: flow { for (i in 1..n) emit(i) }
 */
fun countTo(n: Int): Flow<Int> {
    // TODO
    throw NotImplementedError()
}

/**
 * 주어진 Flow<Int> 에서 짝수만 골라 2배로 변환해 방출하는 Flow를 반환한다.
 *
 * Hint: source.filter { it % 2 == 0 }.map { it * 2 }
 */
fun evensDoubled(source: Flow<Int>): Flow<Int> {
    // TODO
    throw NotImplementedError()
}
