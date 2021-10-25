// Original bug: KT-28064

private inline fun foo(crossinline f: () -> Int) = object {
    fun bar() {
        println(f())
    }
}

fun test(b: Boolean) {
    var x = foo { 111 }
    if (b) {
        x = foo { 222 }
    }
    x.bar()
}

fun main() {
    test(true)
    test(false)
}
