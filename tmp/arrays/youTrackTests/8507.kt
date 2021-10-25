// Original bug: KT-20879

fun main(args: Array<String>) {
    val a = Math.abs(-1)
    val c = a..10  // works

    val b = 5
    when (b) {
        in c -> foo()  // works
        in a..10 -> foo()  // compilation exception
        in Math.abs(-1)..10 -> foo()  // compilation exception
    }
}

fun foo() {}
