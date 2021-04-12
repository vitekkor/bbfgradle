// Original bug: KT-31923

inline fun inlineFun(block: (String) -> String) {
    try {
        try {
            block("lambda")
        } catch (fail: Throwable) {
            println("catch")
        }
    } finally {
        println("finally")
        throw RuntimeException()
    }

}

fun test() {
    inlineFun {
        println("lambda")
        return
    }
}

fun main() {
    test()
}
