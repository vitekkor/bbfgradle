// Original bug: KT-8089

class C {
    companion object {
        private val s: String

        init {
            s = ""
        }

        fun foo() {
            println(s)
        }
    }
}

fun main(args: Array<String>) {
    C.foo()
}
