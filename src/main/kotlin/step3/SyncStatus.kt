package step3

/**
 * Step 3 — sealed class & when (exhaustive)
 *
 * 학습 가이드 / Java 비교 / Suri-Map 연결점은 step3/README.md 참조.
 * 핵심: `sealed class`, `when` 식 exhaustive 검사, `is` smart cast, `filterIsInstance<T>()`.
 */
sealed class SyncStatus {
    object Pending : SyncStatus()
    object Syncing : SyncStatus()
    data class Synced(val syncedAt: Long) : SyncStatus()
    data class Failed(val reason: String) : SyncStatus()
}

/**
 * 상태를 사람이 읽을 수 있는 라벨로 변환한다.
 *
 *   Pending  -> "대기 중"
 *   Syncing  -> "전송 중"
 *   Synced   -> "완료 ({syncedAt})"
 *   Failed   -> "실패: {reason}"
 *
 * Hint: when 식은 expression으로 쓸 수 있고, sealed class는 else가 필요 없다.
 */
fun label(status: SyncStatus): String {
    // TODO: when(status) { is ... -> ... } 로 구현
    throw NotImplementedError()
}

/**
 * 재시도가 필요한 상태만 true 반환.
 *   Pending, Failed  -> true
 *   Syncing, Synced  -> false
 */
fun shouldRetry(status: SyncStatus): Boolean {
    // TODO: when 으로 구현
    throw NotImplementedError()
}

/**
 * 리스트에서 Failed 상태의 reason만 모아 반환한다.
 *
 * Hint: filterIsInstance<SyncStatus.Failed>() 를 활용.
 */
fun failureReasons(statuses: List<SyncStatus>): List<String> {
    // TODO
    throw NotImplementedError()
}
