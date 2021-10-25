// Original bug: KT-26500

inline fun <R> myLet(f: () -> R) = f()

fun box() {
    val baz = myLet {
        object {
            fun bar() = println("bar")
        }
    }
    baz.bar()
}

fun main(args: Array<String>) {
    box()
}
