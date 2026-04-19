package step4

/**
 * Step 4 — 확장 함수 & 스코프 함수
 *
 * 학습 가이드 / 스코프 함수 비교표 / Suri-Map 연결점은 step4/README.md 참조.
 * 핵심: `let` / `run` / `apply` / `also` / `with` 의 수신자/반환값 차이, 확장 함수.
 */

data class Point(var x: Int = 0, var y: Int = 0, var label: String = "")

/**
 * apply 로 Point를 만들어 설정한 뒤 반환한다.
 * 결과: Point(x = 10, y = 20, label = "origin")
 *
 * Hint:
 *   return Point().apply {
 *       x = 10
 *       y = 20
 *       label = "origin"
 *   }
 */
fun buildPoint(): Point {
    // TODO
    throw NotImplementedError()
}

/**
 * 문자열을 받아 trim 후 대문자로 변환해 반환한다.
 * 단, 입력이 null이거나 trim 결과가 빈 문자열이면 null을 반환한다.
 *
 * Hint: `?.takeIf { it.isNotEmpty() }?.let { ... }` 체인을 고려.
 */
fun normalize(raw: String?): String? {
    // TODO
    throw NotImplementedError()
}

/**
 * [확장 함수] String을 받아 "!" 를 끝에 붙여 반환한다.
 *   - 이미 "!" 로 끝나면 그대로 반환한다.
 */
fun String.emphasize(): String {
    // TODO
    throw NotImplementedError()
}
