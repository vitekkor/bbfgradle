// Original bug: KT-28573

class A {
    fun fooLongSyntheticAccessor(capt: Int): String {
        val o = run {
            object {
                fun run(captured: String): String {
                    return {
                        callPrivate(capt, captured)
                    }()
                }

                private fun callPrivate(x: Int, y: String?): String = "O" + x + y
            }
        }
        return o.run("K")
    }
}

fun main() {
    println(A().fooLongSyntheticAccessor(5))
}
