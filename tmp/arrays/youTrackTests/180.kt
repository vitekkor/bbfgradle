// Original bug: KT-28672

fun String?.foo() {
    if (!isNullOrBlank()) {
        bar(this) // bar is resolved to (2), after the fix it'll be resolved to (1)
    }
}

fun bar(s: String) {} // (1)

@JvmName("barNullable")
fun bar(s: String?) {} // (2)
