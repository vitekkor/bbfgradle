// Original bug: KT-36980

fun <T> myRun(action: () -> T): T = action()
fun foo(): String = "foo"

var result = "fail"

fun test1(n: Number, b: Boolean) {
    n.let {
        if (b) return@let

        myRun {
            result = "O"
            foo()
        }
    }
}

fun main() {
    println(test1(42, false))
}
