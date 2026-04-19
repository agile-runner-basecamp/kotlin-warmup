package step1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class NullSafetyTest {

    @DisplayName("displayName - user가 null인 경우, anonymous 를 반환한다.")
    @Test
    fun returnsAnonymousWhenUserIsNull() {
        // given
        val user: User? = null

        // when
        val result = displayName(user)

        // then
        assertThat(result).isEqualTo("anonymous")
    }

    @DisplayName("displayName - name이 null인 경우, anonymous 를 반환한다.")
    @Test
    fun returnsAnonymousWhenNameIsNull() {
        // given
        val user = User(name = null, nickname = "bob")

        // when
        val result = displayName(user)

        // then
        assertThat(result).isEqualTo("anonymous")
    }

    @DisplayName("displayName - name이 존재하는 경우, name 을 반환한다.")
    @Test
    fun returnsNameWhenNameExists() {
        // given
        val user = User(name = "Alice", nickname = null)

        // when
        val result = displayName(user)

        // then
        assertThat(result).isEqualTo("Alice")
    }

    @DisplayName("profileLabel - nickname이 있는 경우, @nickname 을 반환한다.")
    @Test
    fun returnsNicknameLabelWhenNicknameExists() {
        // given
        val user = User(name = "Bob", nickname = "bob")

        // when
        val result = profileLabel(user)

        // then
        assertThat(result).isEqualTo("@bob")
    }

    @DisplayName("profileLabel - nickname이 없고 name만 있는 경우, name 을 반환한다.")
    @Test
    fun returnsNameWhenOnlyNameExists() {
        // given
        val user = User(name = "Bob", nickname = null)

        // when
        val result = profileLabel(user)

        // then
        assertThat(result).isEqualTo("Bob")
    }

    @DisplayName("profileLabel - user가 null이거나 둘 다 null인 경우, anonymous 를 반환한다.")
    @Test
    fun returnsAnonymousWhenBothAreMissing() {
        // given
        val nullUser: User? = null
        val emptyUser = User(name = null, nickname = null)

        // when
        val fromNull = profileLabel(nullUser)
        val fromEmpty = profileLabel(emptyUser)

        // then
        assertThat(fromNull).isEqualTo("anonymous")
        assertThat(fromEmpty).isEqualTo("anonymous")
    }

    @DisplayName("upperNonNull - null이 섞여있는 경우, null을 제거하고 대문자 리스트를 반환한다.")
    @Test
    fun filtersNullAndUppercases() {
        // given
        val values = listOf("a", null, "b", null, "c")

        // when
        val result = upperNonNull(values)

        // then
        assertThat(result).containsExactly("A", "B", "C")
    }

    @DisplayName("upperNonNull - 전부 null인 경우, 빈 리스트를 반환한다.")
    @Test
    fun returnsEmptyWhenAllNull() {
        // given
        val values = listOf<String?>(null, null)

        // when
        val result = upperNonNull(values)

        // then
        assertThat(result).isEmpty()
    }
}
