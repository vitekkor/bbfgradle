// Original bug: KT-28603

fun main(args: Array<String>) {
    f1 { test() } // Smart step into to lambda
}

inline fun f1(f1Param: () -> Unit) {
    f1Param()
}

fun test() {
    println("Hello")
}
