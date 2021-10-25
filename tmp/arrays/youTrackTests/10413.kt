// Original bug: KT-5849

fun bar1(): Int {
    try {
        return 0
    } finally {
        return 1
    }
}

fun bar2() = try { 0 } finally { 1 }

fun main(args: Array<String>) {
    println(bar1()) // 1
    println(bar2()) // 0
}
