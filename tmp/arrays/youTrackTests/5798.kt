// Original bug: KT-32160

class Foo

inline fun Foo.body(id: String) {
    myAddChild(id) // breakpoint 1
}

inline fun Foo.myAddChild(id: String) {
    println("hello") // breakpoint 2
}

fun main() {
    Foo().apply {
        body("snth")
    }
}
