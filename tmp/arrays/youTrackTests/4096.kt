// Original bug: KT-32850

fun <T> materialize(): T = TODO()
fun <K> select(x: K, y: K): K = x

fun test(): String {

    return select(
        materialize(),
        try {
            materialize<String>() // with or without type argument
        } catch (e: Exception) {
            materialize() // OK in NI, error in OI 
        }
    )
}
