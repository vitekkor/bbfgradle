// Original bug: KT-37621

class Inv<T>

inline fun <reified T : Inv<T>> foo(): T {
    return try {
        bar() // OK in OI, NI: Cannot use 'Nothing' as reified type parameter
    } catch (ex: Exception) {
        throw Exception()
    }
}

inline fun <reified T : Inv<T>> bar() = null as T
