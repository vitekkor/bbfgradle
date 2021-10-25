// Original bug: KT-35801

private val variable: Any = Object()

fun foo1() {
    @Suppress("MoveLambdaOutsideParentheses")
    foo2({ variable.hashCode() })
}

fun foo2(function: () -> Int) {}
