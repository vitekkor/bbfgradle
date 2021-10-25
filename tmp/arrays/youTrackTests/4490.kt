// Original bug: KT-36295

fun foo(a: Int?) {
    println("AAAA")
    var b = a ?: error("Fail!") // it fails even when type of a is Int
    println("BBBB")
    run {
        b
    }
    println("CCCC")
}

fun main() {
    foo(1)
    println("OK!")
}
