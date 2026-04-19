package step3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SyncStatusTest {

    @DisplayName("label - Pending 상태인 경우, 대기 중 을 반환한다.")
    @Test
    fun returnsPendingLabel() {
        // given
        val status: SyncStatus = SyncStatus.Pending

        // when
        val result = label(status)

        // then
        assertThat(result).isEqualTo("대기 중")
    }

    @DisplayName("label - Syncing 상태인 경우, 전송 중 을 반환한다.")
    @Test
    fun returnsSyncingLabel() {
        // given
        val status: SyncStatus = SyncStatus.Syncing

        // when
        val result = label(status)

        // then
        assertThat(result).isEqualTo("전송 중")
    }

    @DisplayName("label - Synced 상태인 경우, 동기화 시각이 포함된 문자열을 반환한다.")
    @Test
    fun returnsSyncedLabelWithTimestamp() {
        // given
        val status: SyncStatus = SyncStatus.Synced(syncedAt = 1234L)

        // when
        val result = label(status)

        // then
        assertThat(result).isEqualTo("완료 (1234)")
    }

    @DisplayName("label - Failed 상태인 경우, 이유가 포함된 문자열을 반환한다.")
    @Test
    fun returnsFailedLabelWithReason() {
        // given
        val status: SyncStatus = SyncStatus.Failed(reason = "timeout")

        // when
        val result = label(status)

        // then
        assertThat(result).isEqualTo("실패: timeout")
    }

    @DisplayName("shouldRetry - Pending, Failed 상태인 경우, true 를 반환한다.")
    @Test
    fun returnsTrueForRetriableStatuses() {
        // given, when, then
        assertThat(shouldRetry(SyncStatus.Pending)).isTrue()
        assertThat(shouldRetry(SyncStatus.Failed("x"))).isTrue()
    }

    @DisplayName("shouldRetry - Syncing, Synced 상태인 경우, false 를 반환한다.")
    @Test
    fun returnsFalseForNonRetriableStatuses() {
        // given, when, then
        assertThat(shouldRetry(SyncStatus.Syncing)).isFalse()
        assertThat(shouldRetry(SyncStatus.Synced(0L))).isFalse()
    }

    @DisplayName("failureReasons - Failed 상태만 섞여있는 경우, reason 만 추출한다.")
    @Test
    fun extractsOnlyFailedReasons() {
        // given
        val statuses = listOf(
            SyncStatus.Pending,
            SyncStatus.Failed("timeout"),
            SyncStatus.Synced(1L),
            SyncStatus.Failed("offline"),
        )

        // when
        val result = failureReasons(statuses)

        // then
        assertThat(result).containsExactly("timeout", "offline")
    }
}
