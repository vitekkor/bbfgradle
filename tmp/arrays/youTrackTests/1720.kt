// Original bug: KT-34816

inline class Foo(val s: String) {
    suspend fun String.bar() {
        println(this)
    }
}

suspend fun main() {
    with(Foo("FAIL")) {
        "OK".bar()
    }
}
