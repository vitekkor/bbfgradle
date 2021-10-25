// Original bug: KT-24680

inline fun runMe(block: () -> Unit) {
    block.invoke() // fails
//    block() // works
}

fun box(): String {
    runMe {
        return "OK"
    }
    return "fail"
}

fun main(args: Array<String>) {
  println(box())
}
