// Original bug: KT-20397

package test2

class C {
    fun foo() {
        bar()
        qux()
    }

    private fun bar() {
        println("1")
    }

    companion object {
        private fun qux() {
            println("2")
        }
    }
}

fun main(args: Array<String>) {
    C().foo()
}
