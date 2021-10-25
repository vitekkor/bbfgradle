// Original bug: KT-35566

open class Case1<K : Number> {
    open inner class Case1_1<L> : Case1<Int>() where L : CharSequence {
        var x: L? = null

        fun bar() {
            x?.let {
                println(it.length)
            }
        }

        inner class Case1_2<M>(m: M) : Case1<K>.Case1_1<M>() where M : Map<K, L> {
            init {
                x = m
            }
        }

        fun foo() = Case1_2(mapOf())
    }
}

fun main() {
    Case1<Number>().Case1_1<String>().foo().bar()
}
