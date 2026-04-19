package step5

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutinesTest {

    @DisplayName("doubleAfterDelay - 주어진 값의 2배를 반환한다.")
    @Test
    fun returnsDoubledValue() = runTest {
        // given
        val value = 21

        // when
        val result = doubleAfterDelay(value)

        // then
        assertThat(result).isEqualTo(42)
    }

    @DisplayName("parallelDoubleSum - 두 값을 각각 2배 한 뒤 합을 반환한다.")
    @Test
    fun returnsSumOfDoubled() = runTest {
        // given, when
        val result = parallelDoubleSum(5, 10)

        // then
        assertThat(result).isEqualTo(30)
    }

    @DisplayName("parallelDoubleSum - 병렬로 실행되는 경우, 가상 시간이 10ms 미만이다.")
    @Test
    fun executesInParallel() = runTest {
        // given
        val start = currentTime

        // when
        parallelDoubleSum(1, 2)
        val elapsed = currentTime - start

        // then
        assertThat(elapsed)
            .`as`("순차 실행이라면 20ms, 병렬이라면 10ms 근처여야 한다.")
            .isLessThan(20L)
    }

    @DisplayName("parallelDoubleAll - 입력 순서를 유지하며 각 값을 2배로 반환한다.")
    @Test
    fun returnsDoubledListInOrder() = runTest {
        // given
        val values = listOf(1, 2, 3, 4)

        // when
        val result = parallelDoubleAll(values)

        // then
        assertThat(result).containsExactly(2, 4, 6, 8)
    }

    @DisplayName("parallelDoubleAll - 여러 값을 병렬 처리하는 경우, 가상 시간이 30ms 미만이다.")
    @Test
    fun executesAllInParallel() = runTest {
        // given
        val values = listOf(1, 2, 3, 4, 5)
        val start = currentTime

        // when
        parallelDoubleAll(values)
        val elapsed = currentTime - start

        // then
        assertThat(elapsed)
            .`as`("5개 순차 실행이라면 50ms, 병렬이라면 10ms 근처여야 한다.")
            .isLessThan(30L)
    }
}
