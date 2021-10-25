// Original bug: KT-29493

fun main() {
}

class Test {

    data class C(val a: String)

    class A {
        fun a(
            f: (C) -> Int
        ) {

        }
    }

    class B {
        fun b(c: C): Int {
            return 10
        }
    }

    fun f(f: () -> Unit) {

    }

    fun `Method with parentheses ()`() {

        val b = B()

        val a = A()

        f { a.a({ b.b(it) }) }

    }

}
