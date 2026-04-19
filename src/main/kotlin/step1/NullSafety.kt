package step1

/**
 * Step 1 — null safety
 *
 * 학습 가이드 / Java 비교 / Suri-Map 연결점은 step1/README.md 참조.
 * 핵심 연산자: `?`, `?.`, `?:`, `let`. (`!!` 는 학습용 외 사용 금지)
 */
data class User(val name: String?, val nickname: String?)

/**
 * user가 null이거나 name이 null이면 "anonymous"를 반환한다.
 * 그 외에는 name을 반환한다.
 */
fun displayName(user: User?): String {
    // TODO: `?.` 와 `?:` 를 조합해 한 줄로 구현하세요.
    throw NotImplementedError()
}

/**
 * nickname이 있으면 "@{nickname}" 형식, 없으면 name, name마저 없으면 "anonymous"를 반환한다.
 */
fun profileLabel(user: User?): String {
    // TODO: safe call, elvis 를 활용해 구현하세요.
    //   - 문자열 템플릿: "@$it"
    throw NotImplementedError()
}

/**
 * 문자열 리스트 중 null이 아닌 값만 모아 대문자로 반환한다.
 *
 * 입력: ["a", null, "b", null, "c"]  →  ["A", "B", "C"]
 */
fun upperNonNull(values: List<String?>): List<String> {
    // TODO: `filterNotNull()` + `map { it.uppercase() }` 로 구현하세요.
    throw NotImplementedError()
}
