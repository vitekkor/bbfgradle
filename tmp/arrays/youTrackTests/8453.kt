// Original bug: KT-21309

fun foo() {
    bar {
        try {
            baz()
        }
        catch (e: RuntimeException) {
            e.toString()
        }
    }

    bar {
        when (boo()) {
            "boo" -> baz()
            else -> "111"
        }
    }
}

inline fun bar(x: () -> String): String {
    return x()
}

fun baz() = "baz"

fun boo() = "boo"

fun main(args: Array<String>) {
    foo()
}
