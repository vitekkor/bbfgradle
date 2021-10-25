// Original bug: KT-11664

    fun <T: Any> T?.process(f: (T) -> Unit): T?
        = this ?. let { f(this) ; this }

    fun test1() {
        var x: Int? = null
        fun test() { x.process { println(x) } }
    }

    fun <K> test2() {
        var x: K? = null
        fun test() { x.process { println(x) } }
    }
