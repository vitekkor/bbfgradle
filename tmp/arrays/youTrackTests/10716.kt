// Original bug: KT-2134

fun main(args: Array<String>) {
    Foo().bar()
}

class Foo () {
    fun bar() {
        var x: String = "10"
        fun a1() {
            fun a2() {
                x += "20"
            }
        }
        println(x)
    }
}
