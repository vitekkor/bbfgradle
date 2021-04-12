// Original bug: KT-21184

fun s() {
    val numbers = listOf<Number>()

    listOf<String>()
            .firstOrNull()
            ?.length
                .apply {
                    numbers.map { it }
                            .map { it }.map { it }
                }
            ?: numbers.map { it }
                .map { it }.map { it }
}
