// Original bug: KT-36460

val path: String =
    if (true) {
        "Hi"
    } else {
        var foo = if (listOf(42) != null) "foo" else "bar"
        "Hi"
    }

fun dummy() {}
