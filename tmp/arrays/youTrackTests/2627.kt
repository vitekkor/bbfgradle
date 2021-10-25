// Original bug: KT-24388

fun main() {
    run {
        var flag = 0
        while (true) {
            println(flag)
            flag = -1 // UNUSED_VALUE
            if (false) return
        }
    }
}

inline fun run(block: () -> Unit) {
    block()
}
