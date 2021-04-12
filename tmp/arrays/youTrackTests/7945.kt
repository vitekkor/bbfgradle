// Original bug: KT-24301

class A {
    operator fun invoke(f: () -> Int): Int = f()
}

fun foooo() {
    val i: Int = (if (true) A() else A()) { // Parsed as lambda argument
        12
    }
}
