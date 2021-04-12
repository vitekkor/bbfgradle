// Original bug: KT-41458

fun main() = try {
    foo()
} catch (e: Throwable) {
    e.printStackTrace()
    //console.error(e) // for js
}

fun foo() = bar()

fun bar(): Unit = throw RuntimeException("Im on line 10")
