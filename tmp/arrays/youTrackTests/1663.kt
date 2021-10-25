// Original bug: KT-14259

fun testOverrideWithNothing() {
    class A {
        override fun toString(): String = throw Exception("Don't call toString() on A")
    }

    class B {
        override fun toString()/*: Nothing*/ = throw Exception("Don't call toString() on B")
    }

    println("1) " + try {"" + A()} catch (e: Throwable) {"Died saying: ${e.message}"})
    println("2) " + try {"" + B()} catch (e: Throwable) {"Died saying: ${e.message}"})
}
