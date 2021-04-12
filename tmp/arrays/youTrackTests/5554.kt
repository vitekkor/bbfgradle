// Original bug: KT-22480

fun foo(x: Any) {
    if (x !is String) return
    print((x as? String).isNullOrBlank()) // resolved to (1)
    print((x as String).isNullOrBlank())  // resolved to (2)
}

fun String?.isNullOrBlank() {} // (1)

@JvmName("other")
fun String.isNullOrBlank() {} // (2)
