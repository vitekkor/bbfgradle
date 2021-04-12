// Original bug: KT-24301

class A {
    operator fun invoke(f: () -> Int): Int = f()
}

fun foooo() {
    val arr = arrayOf(A())
    val a1: Int = arr[0] {
        12 // OK
    }

    val a2: A = arr[0]
    {
        12 // Unused lambda
    }
}
