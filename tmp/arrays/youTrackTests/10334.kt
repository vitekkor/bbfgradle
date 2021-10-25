// Original bug: KT-6242

inline fun<T> foo(block: () -> T):T = block()

fun bar() {
    val x: String = foo { // error: T infered as String? and result of call is String?
        val x: String? = null
        if (x == null) {
            "default"
        } else {
            x
        }
    }
}
