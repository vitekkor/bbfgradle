// Original bug: KT-18179

class C {
    private abstract class PA {
        fun foo() { bar() }
        protected abstract fun bar() // (*)
    }

    private val test = object : PA() {
        override fun bar() { // (**)
            println("test")
        }
    }
}
