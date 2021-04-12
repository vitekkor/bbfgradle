// Original bug: KT-34051

inline fun <reified T> topMethod() {
    innerMethod {
        accessClass<T>()
    }
}

fun innerMethod(block: () -> Unit) {
    block()
}

inline fun <reified Z> accessClass() { Z::class.java }

fun main() {
    topMethod<String>()
}
