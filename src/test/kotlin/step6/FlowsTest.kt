package step6

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FlowsTest {

    @DisplayName("countTo - n이 양수인 경우, 1부터 n까지 순서대로 방출한다.")
    @Test
    fun emitsFromOneToN() = runTest {
        // given
        val n = 5

        // when
        val result = countTo(n).toList()

        // then
        assertThat(result).containsExactly(1, 2, 3, 4, 5)
    }

    @DisplayName("countTo - n이 0 이하인 경우, 빈 flow 를 반환한다.")
    @Test
    fun emitsNothingWhenNIsNonPositive() = runTest {
        // given, when
        val zeroResult = countTo(0).toList()
        val negativeResult = countTo(-3).toList()

        // then
        assertThat(zeroResult).isEmpty()
        assertThat(negativeResult).isEmpty()
    }

    @DisplayName("evensDoubled - 짝수만 골라 2배로 변환해 방출한다.")
    @Test
    fun filtersEvensAndDoubles() = runTest {
        // given
        val source = countTo(6)

        // when
        val result = evensDoubled(source).toList()

        // then
        assertThat(result).containsExactly(4, 8, 12)
    }
}
