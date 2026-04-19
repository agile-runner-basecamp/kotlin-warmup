package step2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DataClassTest {

    private val a = Waypoint(37.5, 127.0, 1000L)
    private val b = Waypoint(37.6, 127.1, 3500L)

    @DisplayName("durationBetween - 두 waypoint 타임스탬프 차이의 절댓값을 반환한다.")
    @Test
    fun returnsAbsoluteDuration() {
        // given, when
        val forward = durationBetween(a, b)
        val backward = durationBetween(b, a)

        // then
        assertThat(forward).isEqualTo(2500L)
        assertThat(backward).isEqualTo(2500L)
    }

    @DisplayName("withTimestamp - 타임스탬프만 교체된 새 인스턴스를 반환한다.")
    @Test
    fun returnsNewInstanceWithReplacedTimestamp() {
        // given
        val newTimestamp = 9999L

        // when
        val updated = withTimestamp(a, newTimestamp)

        // then
        assertThat(updated).isEqualTo(Waypoint(37.5, 127.0, 9999L))
        assertThat(updated).isNotSameAs(a)
    }

    @DisplayName("formatWaypoint - lat,lng@timestamp 형식의 문자열을 반환한다.")
    @Test
    fun formatsAsLatLngAtTimestamp() {
        // given, when
        val result = formatWaypoint(a)

        // then
        assertThat(result).isEqualTo("37.5,127.0@1000")
    }

    @DisplayName("data class - 값이 같은 경우, equals 가 true 를 반환한다.")
    @Test
    fun isEqualWhenValuesMatch() {
        // given
        val other = Waypoint(37.5, 127.0, 1000L)

        // when, then
        assertThat(other).isEqualTo(a)
    }
}
