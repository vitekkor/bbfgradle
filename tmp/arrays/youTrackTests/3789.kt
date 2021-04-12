// Original bug: KT-37628

fun foo(x: Int) {
    val x = if (true) {
        mapOf("" to { x }) // `Map<String, () â Int>` is in type info in IDE
    } else {
        null
    } // type of entire `if` is `Map<String, *>?` instead of `Map<String, () â Int>?`
}
