// Original bug: KT-32112

package foo.bar

class Foo {
    fun test() {
        foo.bar.myRun {
            42
        }
    }
}

inline fun <R> myRun(block: () -> R): R {
    println("1")
    return block()
}

inline fun <T, R> T.myRun(block: T.() -> R): R {
    println("2")
    return block()
}
