// Original bug: KT-6242

inline fun<T> foo(block: () -> T):T = block()
fun bar() {
    val x: String = foo {
        val task: String? = null
        if (task == null) {
            return
        } else task
    }
}
