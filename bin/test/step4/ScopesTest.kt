package step4

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ScopesTest {

    @DisplayName("buildPoint - apply 로 설정된 Point 를 반환한다.")
    @Test
    fun returnsConfiguredPoint() {
        // given, when
        val result = buildPoint()

        // then
        assertThat(result).isEqualTo(Point(x = 10, y = 20, label = "origin"))
    }

    @DisplayName("normalize - 앞뒤 공백이 있는 문자열인 경우, trim 후 대문자로 반환한다.")
    @Test
    fun trimsAndUppercases() {
        // given
        val raw = "  hello "

        // when
        val result = normalize(raw)

        // then
        assertThat(result).isEqualTo("HELLO")
    }

    @DisplayName("normalize - 입력이 null 인 경우, null 을 반환한다.")
    @Test
    fun returnsNullWhenInputIsNull() {
        // given
        val raw: String? = null

        // when
        val result = normalize(raw)

        // then
        assertThat(result).isNull()
    }

    @DisplayName("normalize - 공백만 있는 문자열인 경우, null 을 반환한다.")
    @Test
    fun returnsNullWhenBlank() {
        // given
        val raw = "   "

        // when
        val result = normalize(raw)

        // then
        assertThat(result).isNull()
    }

    @DisplayName("emphasize - 느낌표가 없는 문자열인 경우, 끝에 느낌표를 붙여 반환한다.")
    @Test
    fun appendsExclamationMark() {
        // given, when
        val result = "wow".emphasize()

        // then
        assertThat(result).isEqualTo("wow!")
    }

    @DisplayName("emphasize - 이미 느낌표로 끝나는 문자열인 경우, 그대로 반환한다.")
    @Test
    fun keepsOriginalWhenAlreadyEndsWithExclamation() {
        // given, when
        val result = "wow!".emphasize()

        // then
        assertThat(result).isEqualTo("wow!")
    }
}
