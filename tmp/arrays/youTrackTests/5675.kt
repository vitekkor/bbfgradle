// Original bug: KT-33021

fun main() {
    (100.0/100).printNames("Andrey", "Ilya", "Roman", "Alexey", "Vsevolod", "Syatoslav", "Nikolay")
}

fun Double.printNames(
    vararg names: String,
    ln: Boolean = true
) {
    names.asList().shuffled().let {
        if (ln) println(it) else print(it)
    }
    println("Rate: ${this * 100}/100")
}

@Suppress("unused")
@Deprecated(message = "Names are required", level = DeprecationLevel.ERROR)
fun Double.printNames(ln: Boolean): Nothing {
    throw UnsupportedOperationException()
}
