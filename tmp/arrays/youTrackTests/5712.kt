// Original bug: KT-32434

interface Cache<K, V> {

    fun asMap(): MutableMap<K, V>
}

interface Mono<E>
interface Signal<E> : Mono<E>

interface AttributeDefinition

private val cache: Cache<String, Signal<out AttributeDefinition>> = TODO()

fun findByName(name: String): Mono<AttributeDefinition> = CacheMono
    .lookup(cache.asMap(), name)


object CacheMono {

    fun <K, V> lookup(map: MutableMap<K, in Signal<out V>>, key: K): V {
        TODO()
    }
}
