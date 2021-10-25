// Original bug: KT-5467

fun String.foo() {
    fun Int.bar() {
        fun Long.baz() {
            val x = this@foo
            val y = this@bar
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
