// Original bug: KT-31290

interface Container<out T> // Removing `out` removes the error.

fun fun91(p: Container<String>) {}
fun fun91(p: Int) {}

fun <T, C : Container<T>> Container<T>.localMap(destination: C): C = TODO()
fun <T> provideContainer(): Container<T> = TODO()

fun reduce(c1: Container<String>, c2: Container<String>) {
    fun91(c1.localMap(provideContainer())) // OVERLOAD_RESOLUTION_AMBIGUITY
    fun91(c1.localMap(c2)) // no error
}
