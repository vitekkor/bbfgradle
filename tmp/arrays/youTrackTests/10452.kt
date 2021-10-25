// Original bug: KT-5258

fun bar() = "bar"
val baz = "baz"
val boo = true
fun foo(): String =
            bar() +
            "$baz " +
            (if (boo) "a" else "b") +
            baz +
            bar() +
            if (boo) "b" else ""
