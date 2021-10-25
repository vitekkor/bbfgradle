// Original bug: KT-36854

interface A {
    fun foo(a: String, b: String = "b"): String
}

class B : A {
    override fun foo(a: String, b: String) = a + b
}

fun box(): String {
    val f = A::foo

    assert("ab" == f.callBy(mapOf(
            f.parameters.first() to B(),
            f.parameters.single { it.name == "a" } to "a"
    )))

    return "OK"
}

fun main() {
    println(box())
}
