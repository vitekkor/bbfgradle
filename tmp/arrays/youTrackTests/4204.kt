// Original bug: KT-11323

class A<E>

fun <T> bar(function: () -> List<T>): A<T> = null!!
fun <K> emptyList(): List<K> = null!!
fun foo(): A<String> =
        bar {
            emptyList()
        }
