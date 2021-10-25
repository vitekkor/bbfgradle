// Original bug: KT-24220

/**
 * Returns a new map with entries of the keys of this Map, or throws an exception if not all keys are in the map.
 * If the map was created by withDefault, resorts to its defaultValue provider function instead of throwing an exception.
 *
 * The returned map preserves the iteration order of the keys.
 */
fun <K, V> Map<K, V>.getAll(keys: Iterable<K>) : Map<K,V> = TODO()

/**
 * Returns the value corresponding to the given key, or defaultValue if any of the keys are not present in the map.
 *
 * The returned map preserves the iteration order of the keys.
 */
fun <K, V> Map<K, V>.getAllOrDefault(keys: Iterable<K>, defaultValue: V) : Map<K,V> = TODO()

/**
 * Returns a new map with entries of the keys of this Map, or the result of the defaultValue function if there was
 * no entry for the given key.
 *
 * The returned map preserves the iteration order of the keys.
 */
fun <K, V> Map<K, V>.getAllOrElse(keys: Iterable<K>, defaultValue: (K) -> V) : Map<K,V> = TODO()

/**
 * Returns a new map with entries of the keys of this Map if they are present.
 *
 * The returned map preserves the iteration order of the keys.
 */
fun <K, V> Map<K, V>.getAllPresent(keys: Iterable<K>) : Map<K,V> = TODO()
