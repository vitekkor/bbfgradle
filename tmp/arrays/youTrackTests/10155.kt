// Original bug: KT-9118

fun main(args: Array<String>) {
    if (1 > 2) {  // 1
        println() // 2 ???
    }
    var isCompiledDataFromCache = true //3
    foo {
        isCompiledDataFromCache = false
    }
}

fun foo(f: () -> Unit) {
    f()
}
