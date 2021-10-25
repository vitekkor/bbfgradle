// Original bug: KT-41625

fun main(args: Array<String>) {
    println(coolFunc(false))
}

fun coolFunc(b: Boolean): Unit = anotherCoolFunc { if (b) Unit else null }

fun <R> anotherCoolFunc(coolLambda: () -> R?): R {
    val result = coolLambda()
    if (result == null) {
        throw IllegalArgumentException("Result was null")
    } else {
        return result
    }
}
