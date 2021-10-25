// Original bug: KT-41197

fun foo(cond: Nothing?) {
    cond?.let { // no warning
        println("never") // no warning
    }
    cond!!.let { // The result of the expression is always null
        println("never") // Unreachable code
    }
}
