// Original bug: KT-26759

fun foo(): Int {
    return if (true) {
        return 42
    } else {
        throw Exception("")
    }
}

fun main(args: Array<String>) {
    println(foo())
}
