// Original bug: KT-31656

fun a() {
    foo(1.0, 3.5, 2.5, 4.0, 2.0)
}

fun b() {
    foo(1.0, 3.0, 2.3, 4.0, 2.0)
}

private fun foo(vararg p1: Double, p2: Double? = null) {
    println(p1)
    println(p2)
}
