// Original bug: KT-45405

enum class X {
    Y {
        val q = X.Y // here resolve works

        override fun q() {
            val x = 10
            println(x) // not resolved
        }
    }
    ;
    open fun q () {
        val x = 10
        println(x) // here resolve works
    }
}
