// Original bug: KT-22838

class WInvoke {
    operator fun invoke(param: String = "", body: () -> Unit) { }
}

class Second {
    val testInvoke = WInvoke()
}

fun boo(s: Second, body: () -> Unit) { }

fun foo(s: Second) {
    boo(s) {
        s.testInvoke {
            "Hello"
        }
    }
}
