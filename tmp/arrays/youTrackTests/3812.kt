// Original bug: KT-5467

fun String.foo() {
    val x = this@foo
    fun Int.bar() {
        val y = this@bar
        fun Long.baz() {
            val z = this@baz
            println("$x $y $z")
        }
        0L.baz()
    }
    42.bar()
}

fun main(args: Array<String>) {
    "".foo()
}
