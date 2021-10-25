// Original bug: KT-28239

class A {
    override fun equals(other: Any?) = true
    fun m1() = true
}

fun f1() {
    val x: A? = null
    val y = A()
    if (y == x) {
        x.m1() // x smart casted to A, NPE
    }
}

fun f2() {
    val x: A? = null
    val y: A? = A()
    if (y != null) {
        if (y == x) { // y smart casted to not-null (A)
            x.m1() // x smart casted to A, NPE
        }
    }
}
