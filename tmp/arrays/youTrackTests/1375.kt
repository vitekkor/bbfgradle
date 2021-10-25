// Original bug: KT-10455

fun main() {
    object {
        val foo: String

        init {
            println(fail())
            foo = "foo"
        }

        fun fail(): String = foo
    }
}
