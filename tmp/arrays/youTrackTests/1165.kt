// Original bug: KT-19752

package debugger.kt19752

class Foo {
    val withField = ""
    val noField: String get() = "tada"
    fun noField() = noField
    fun some() = noField
}

fun main(args: Array<String>) {
    val f = Foo()
    println("")
}
