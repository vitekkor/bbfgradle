// Original bug: KT-17144

package debugger

fun main(args: Array<String>) {
    val a = true
    1 + when (a) {
        true -> {
            println("foo")
            1
        }
        else -> {
            println("bar")
            2
        }
    }
}
