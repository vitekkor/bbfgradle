// Original bug: KT-4543

package a

class A() {
}

fun a(body: A.() -> Unit) {
    val r = A()
    r.body()
}

object C {
    private fun A.f() {
    }

    val g = a {
        f()
    }
}

fun main(args: Array<String>) {
    C.g
}
