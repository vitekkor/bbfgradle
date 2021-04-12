// Original bug: KT-3297

fun <R> Function0<R>.or(alt: () -> R): R {
    try {
        println("Trying first function")
        return this()
    } catch (e: Exception) {
        println("Trying second function")
        return alt.invoke()
    }
}
