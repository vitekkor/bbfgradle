// Original bug: KT-30717

inline fun bar(block: () -> Unit) = block()
fun baz(block: () -> Unit) = block()

fun foo() {
    var used = 1

    bar {
        while (true) {
            if (false) return
            baz {}
            println(used)
        }
    }
}
