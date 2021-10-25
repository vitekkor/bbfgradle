// Original bug: KT-15770

fun foo() {
    fun bar(x: String) {
        if (x.isEmpty()) return
        println("foo: ${x[0]}")
        bar(x.substring(1))
    }
    bar("OK")
}

fun baz() {
    fun bar(x: String) {
        if (x.isEmpty()) return
        println("baz: ${x[0]}")
        bar(x.substring(1))
    }
    bar("OK")
}

fun main(args: Array<String>) {
    foo()
    baz()
}
