// Original bug: KT-43120

suspend fun impl(p1: Long, p2: Long) {}

fun partialApply(
        impl: suspend (Long, Long) -> Unit
) {
}

suspend fun main() {
    println(partialApply(::impl))
}
