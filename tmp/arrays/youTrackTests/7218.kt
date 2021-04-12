// Original bug: KT-28243

class A {
    override fun equals(other: Any?) = true
}

fun f1(x: A) {
    val y = null
    if (x == y) { // y smart cast to Nothing? (always null)
        // x is smart cast to Nothing, because any not-null type + Nothing? = Nothing & ...
        x.inv() // is a correct code (and unreachable), ClassCastException in a runtime (cannot be cast to java.math.BigInteger)
    }
}

fun f2(x: A) {
    val y = null
    if (x == y) { // y smart cast to Nothing? (always null)
        // x is smart cast to Nothing, because any not-null type + Nothing? = Nothing & ...
        x.let {  } // is a correct code (and unreachable), ClassCastException in a runtime (cannot be cast to java.lang.Void)
    }
}

fun main() {
    f1(A()) // ClassCastException
    // f2(A()) // ClassCastException
}
