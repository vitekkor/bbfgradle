// Original bug: KT-21900

class Key<T>(val value: T)

class Project {
    fun <T> get(k: Key<T>): T = k.value
}

fun main() {
    val id = Key<Long>(1L)
    Project().get(id).equals(1)
}
