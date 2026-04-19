package step2

/**
 * Step 2 — data class & 구조분해
 *
 * 학습 가이드 / Java 비교 / Suri-Map 연결점은 step2/README.md 참조.
 * 핵심: `val`, `data class`, `copy(...)`, 구조분해 `val (a, b, c) = obj`.
 */
data class Waypoint(val lat: Double, val lng: Double, val timestamp: Long)

/**
 * 두 waypoint의 타임스탬프 차이(ms)를 반환한다.
 * 반드시 절댓값으로 반환하라.
 */
fun durationBetween(a: Waypoint, b: Waypoint): Long {
    // TODO: kotlin.math.abs 를 사용해 구현하세요.
    throw NotImplementedError()
}

/**
 * 기존 waypoint에서 타임스탬프만 바꾼 새 waypoint를 반환한다.
 *
 * Hint: data class의 `copy()` 를 사용하세요.
 */
fun withTimestamp(waypoint: Waypoint, newTimestamp: Long): Waypoint {
    // TODO: waypoint.copy(timestamp = newTimestamp)
    throw NotImplementedError()
}

/**
 * waypoint를 "lat,lng@timestamp" 형식 문자열로 변환한다.
 *
 * Hint: 구조분해를 사용하세요.
 *   val (lat, lng, ts) = waypoint
 */
fun formatWaypoint(waypoint: Waypoint): String {
    // TODO: 구조분해 + 문자열 템플릿으로 구현
    throw NotImplementedError()
}
