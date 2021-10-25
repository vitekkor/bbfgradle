// Original bug: KT-27117

inline fun on(body: () -> Unit) {
    body()
}

class A {

    fun baz() {
        foo()
    }

    private companion object {
        private fun foo() {
            on {
                println(this::class.java)
            }
        }
    }
}

fun main(args: Array<String>) {
    A().baz()
}
