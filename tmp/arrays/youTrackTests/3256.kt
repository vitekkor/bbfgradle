// Original bug: KT-36387

fun foo() {
    listOf(42).map { it }.map { it }.map { it }
    bar(A().b.c)
    "hello".length.javaClass
}

fun bar(x: Int) {
}

class A {
    val b: B = B()
}

class B {
    val c: Int = 42
}
