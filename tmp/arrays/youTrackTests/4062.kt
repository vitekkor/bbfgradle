// Original bug: KT-14351

fun foo(x: Any, y: Any) {
    val x: Comparable<*> = when (x.hashCode()) {
        1 -> y as? Int
        2-> y as? Double
        else -> null
    } ?: return
}

