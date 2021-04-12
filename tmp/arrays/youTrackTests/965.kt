// Original bug: KT-9304

inline fun foo(f: () -> Unit) {
    f()
}

fun test3(): String = (bar@ l@ fun(): String {
    foo { return@bar "OK" }
    return "fail"
}) ()

fun main(args: Array<String>) {
    test3()
}
