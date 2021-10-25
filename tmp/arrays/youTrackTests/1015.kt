// Original bug: KT-34051

inline fun <reified T> topMethod() {
    innerMethod {
        run {
        	accessClass<T>()
        }
    }
}

fun innerMethod(block: () -> Unit) {
    block()
}

inline fun <reified Z> accessClass() { println(Any() is Z); println("" is Z); println(Z::class.java) }

fun main() {
    topMethod<String>()
}
